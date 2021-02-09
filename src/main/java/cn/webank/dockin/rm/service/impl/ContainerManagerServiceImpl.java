/*
 * Copyright (C) @2021 Webank Group Holding Limited
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package cn.webank.dockin.rm.service.impl;
import cn.webank.dockin.rm.exception.SysException;
import cn.webank.dockin.rm.bean.biz.*;
import cn.webank.dockin.rm.bean.pod.PodSetStatus;
import cn.webank.dockin.rm.common.Constants;
import cn.webank.dockin.rm.common.DataType;
import cn.webank.dockin.rm.database.dao.OperateRecordDAO;
import cn.webank.dockin.rm.database.dao.PodInfoDAO;
import cn.webank.dockin.rm.database.dao.PodIpPoolDAO;
import cn.webank.dockin.rm.database.dto.OperateRecord;
import cn.webank.dockin.rm.database.dto.PodInfo;
import cn.webank.dockin.rm.exception.RmException;
import cn.webank.dockin.rm.service.*;
import cn.webank.dockin.rm.utils.PodUtil;
import cn.webank.dockin.rm.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.*;
import static cn.webank.dockin.rm.common.Constants.*;
import static cn.webank.dockin.rm.utils.BeanParser.parseResultDto;
@Service
public class ContainerManagerServiceImpl implements ContainerManagerService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${rm.env:prd}")
    private String env;
    @Autowired
    AllocateContainerService allocateContainerService;
    @Autowired
    PersistenceService persistenceService;
    @Autowired
    SubsystemService subsystemService;
    @Autowired
    PodSetService podSetService;
    @Autowired
    NetworkService networkService;
    @Autowired
    PodIpPoolDAO podIpPoolDAO;
    @Autowired
    OperateRecordDAO operateRecordDAO;
    @Autowired
    PodInfoDAO podInfoDAO;
    @Override
    public ResultDto addContainerCrossDcn(List<AddInstanceDTO> addInstanceDTOs) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(SUCCESS);
        try {
            AllocateResult allocateResult = allocateContainerService.allocateCrossDcn(addInstanceDTOs);
            if (allocateResult.getResult() == AllocateResult.RESULT_SUCCESS) {
                List<PodInfo> podInfoDTOList = allocateResult.getInstances();
                if (resultDto.getCode() != SUCCESS) {
                    return resultDto;
                }
            }
            resultDto = parseResultDto(allocateResult);
        } catch (Exception e) {
            logger.warn("addContainerCrossDcn exception", e);
            resultDto.setCode(FAIL);
            resultDto.setMessage(e.getMessage());
        }
        return resultDto;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean rollbackAddInstance(String itsmId) {
        try {
            List<PodInfo> podInfoList = podInfoDAO.getPodListByItsmId(itsmId);
            if (podInfoList == null || podInfoList.size() == 0) {
                return true;
            }
            for (PodInfo podInfo : podInfoList) {
                allocateContainerService.rollbackAllocate(podInfo);
            }
        } catch (Exception e) {
            logger.warn("exception occur while rollback operation", e);
            return false;
        }
        return true;
    }
    @Override
    public boolean rollbackAddInstance(AddInstanceDTO addInstance) {
        logger.info("rollback add instance, request={}", addInstance);
        String itsmId = addInstance.getItsmId();
        return rollbackAddInstance(itsmId);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean rollbackRemoveInstance(String itsmId) throws Exception {
        List<PodInfo> podList = podInfoDAO.getPodListByOfflineItsmId(itsmId);
        if (podList == null || podList.size() == 0) {
            return true;
        }
        for (PodInfo pod : podList) {
            if (podIpPoolDAO.allocatePodIp(pod.getPodIp()) == 0) {
                throw new RmException("re-allocate pod ip from db failed");
            }
            pod.setState(POD_ALLOCATED);
            if (podInfoDAO.updatePodInfo(pod) == 0) {
                throw new RmException("reset pod info from db failed");
            }
            PodInfo podInfo = persistenceService.getPodInfo(pod.getPodIp());
            if (podInfo == null) {
                throw new RmException("no pod info found from db");
            }
            if (persistenceService.updateHostInfo(podInfo.getHostIp()) == 0) {
                throw new RmException("update host info from db failed");
            }
            podSetService.updateStatus(podInfo.getPodSetId(), PodSetStatus.BOUND);
        }
        return true;
    }
    @Override
    public boolean removeInstances(RemoveInstanceDTO removeInstanceDTO) throws Exception {
        Set<String> podNameSet = null;
        Set<String> podIpSet = null;
        List<PodInfo> podInfoList = null;
        if (CollectionUtils.isEmpty(removeInstanceDTO.getPodNameList())) {
            podInfoList = podInfoDAO.getPodInfosByPodIpList(removeInstanceDTO.getIpList());
        } else {
            podInfoList = podInfoDAO.getPodInfosByPodNameList(removeInstanceDTO.getPodNameList());
        }
        if (podInfoList.size() == 0) {
            throw new IllegalArgumentException("pod not found, remove request " + JSONObject.toJSONString(removeInstanceDTO));
        }
        podNameSet = PodUtil.getPodNames(podInfoList);
        podIpSet = PodUtil.getPodIps(podInfoList);
        boolean result = removeInstance(podNameSet, podIpSet, removeInstanceDTO.getItsmId());
        return result;
    }
    @Override
    public boolean postOperate(ResultDto result) {
        logger.info("postOperate result={}", result);
        try {
            OperateRecord operateRecord = new OperateRecord();
            operateRecord.setItsmId(result.getReqId());
            if (result.getCode() == SUCCESS) {
                operateRecord.setState(ITSM_STATE_SUCCEED);
            } else {
                operateRecord.setState(ITSM_STATE_FAILED);
            }
            operateRecord.setResult(StringUtil.substring(result.getMessage(), 3000));
            operateRecordDAO.updateInfo(operateRecord);
        } catch (Exception e) {
            logger.warn("update operate record exception", e);
            return false;
        }
        return true;
    }
    @Override
    public String preOperate(String reqId, String content, RequestType requestType) throws Exception {
        String jobState = operateRecordDAO.getState(reqId);
        if (StringUtils.isEmpty(jobState)) {
            if (operateRecordDAO.insert(reqId, content, ITSM_STATE_RECEIVED) == 0) {
                throw new SysException("insert operate record failed");
            }
        } else {
            if (jobState.equals(ITSM_STATE_SUCCEED)) {
                return ITSM_STATE_SUCCEED;
            } else {
                if (requestType == RequestType.addPodInstance) {
                    rollbackAddInstance(reqId);
                } else if (requestType == RequestType.removeInstance) {
                    rollbackRemoveInstance(reqId);
                }
            }
        }
        return ITSM_STATE_RECEIVED;
    }
    private boolean removeInstance(Set<String> podNameList, Set<String> ipList, String offlineItsmId) throws Exception {
        List<PodInfo> podsFromDB = podInfoDAO.getPodInfosByPodNameListIgnoreStatus(new ArrayList<>(podNameList));
        if (podsFromDB.size() < podNameList.size()) {
            logger.warn("some pod in {} not found ,please check ", podNameList);
            return false;
        }
        for (PodInfo podInfo : podsFromDB) {
            if (podInfo.getState().equals(POD_OFFLINE)) {
                logger.warn("pod {} already offline, please delete it ", podInfo.getPodName());
                return false;
            }
        }
        ResultDto resultDto = persistenceService.removeInfoFromDB(ipList, offlineItsmId);
        if (resultDto.getCode() != Constants.SUCCESS) {
            throw new RmException("remove pod from db failed, cause=" + resultDto.getMessage());
        }
        for (String ip : ipList) {
            int updateRows = podIpPoolDAO.releasePodIp(ip);
            if (updateRows == 0) {
                throw new RmException("release pod ip exception, update pod ip pool affected row is 0");
            }
        }
        for (PodInfo podInfo : podsFromDB) {
            networkService.recycleAdditionalNetwork(podInfo.getPodName());
        }
        for (PodInfo pod : podsFromDB) {
            if (!podSetService.recyclePodSetId(pod.getPodSetId())) {
                throw new RmException("recycle podSetId failed");
            }
        }
        return true;
    }
}
