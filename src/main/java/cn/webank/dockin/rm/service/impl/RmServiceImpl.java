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
import cn.webank.dockin.rm.bean.biz.AddInstanceDTO;
import cn.webank.dockin.rm.bean.biz.ResultDto;
import cn.webank.dockin.rm.bean.cluster.ClusterInfo;
import cn.webank.dockin.rm.bean.pod.PodInfoDTO;
import cn.webank.dockin.rm.common.Constants;
import cn.webank.dockin.rm.database.dao.HostInfoDAO;
import cn.webank.dockin.rm.database.dao.PodInfoDAO;
import cn.webank.dockin.rm.database.dto.PodInfo;
import cn.webank.dockin.rm.service.*;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import static cn.webank.dockin.rm.common.Constants.FAIL;
@Service
public class RmServiceImpl implements RmService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    PodInfoService podInfoService;
    @Autowired
    NetworkService networkService;
    @Autowired
    ContainerManagerService containerManagerService;
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1, new ThreadFactory() {
        AtomicInteger i = new AtomicInteger();
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "RmService-Thread-" + i.incrementAndGet());
            t.setDaemon(true);
            return t;
        }
    });
    @Value("${rm.ha.check.enabled}")
    private boolean haCheckEnabled = false;
    @Value("${rm.env}")
    private String env = "prd";
    @Autowired
    private PodInfoDAO podInfoDAO;
    @Autowired
    private PersistenceService persistenceService;
    @Autowired
    HostInfoDAO hostInfoDAO;
    @Override
    public ResultDto getPodInfoByHostIp(String hostIp) {
        ResultDto result = new ResultDto();
        logger.info("get pod info request is: hostIp={}", hostIp);
        if (StringUtils.isEmpty(hostIp)) {
            result.setMessage("illegal parameter, host ip is empty");
            return result;
        }
        try {
            List<PodInfo> podInfoDTOList = podInfoDAO.getPodListByHostIp(hostIp);
            List<PodInfoDTO> podInfoDTOForOperators = Lists.newArrayList();
            if (podInfoDTOList != null && podInfoDTOList.size() != 0) {
                for (PodInfo podInfo : podInfoDTOList) {
                    podInfoDTOForOperators.add(persistenceService.podDTO2DOConvertor(podInfo));
                }
            }
            result.setData(podInfoDTOForOperators);
            result.setCode(Constants.SUCCESS);
        } catch (Exception e) {
            logger.warn("exception occur while getting pod info", e);
            result.setMessage(e.getMessage());
            return result;
        }
        return result;
    }
    @Override
    public ResultDto addAppInstance(AddInstanceDTO addInstanceDTO) {
        ResultDto resultDto = null;
        try {
            resultDto = containerManagerService.addContainerCrossDcn(Collections.singletonList(addInstanceDTO));
        }catch (Exception e){
            resultDto = ResultDto.newDefaultFailedResult(e.getMessage());
        }
        return resultDto;
    }
    @Override
    public ResultDto getPodInfoByPodName(String podName) {
        ResultDto result = new ResultDto();
        logger.info("getPodInfoByPodName request is: podName={}", podName);
        if (StringUtils.isEmpty(podName)) {
            result.setMessage("illegal parameter, pod name is empty");
            return result;
        }
        result = persistenceService.getPodInfoByPodName(podName);
        logger.info("getPodInfo {} info is {}", podName, result);
        return result;
    }
    @Override
    public ResultDto getClusterId(String hostIp, String podIp, String timestamp) {
        ResultDto resultDto = new ResultDto();
        logger.info("get cluster id request is: hostIp={}, podIp={}, timestamp={}", hostIp, podIp, timestamp);
        if (hostIp == null && podIp == null) {
            resultDto.setMessage("illegal parameter, please input hostIp or podIp");
            return resultDto;
        }
        try {
            return getClusterId(hostIp, podIp, resultDto);
        } catch (Exception e) {
            logger.warn("exception occur while getting cluster info, timestamnp={}", timestamp, e);
            resultDto.setMessage(e.getMessage());
            return resultDto;
        }
    }
    private ResultDto getClusterId(String hostIp, String podIp, ResultDto resultDto) throws Exception {
        ClusterInfo clusterInfo = new ClusterInfo();
        if (hostIp == null) {
            hostIp = podInfoDAO.getHostIp(podIp);
        }
        if (hostIp == null) {
            throw new Exception("host not found");
        }
        String clusterId = hostInfoDAO.getClusterIdByHostIp(hostIp);
        if (clusterId == null) {
            throw new Exception("cluster Id not found or host offline");
        }
        clusterInfo.setClusterId(clusterId);
        resultDto.setCode(Constants.SUCCESS);
        resultDto.setData(clusterInfo);
        return resultDto;
    }
    @Override
    public ResultDto getPodInfoByPodIp(String podIp) {
        ResultDto result = new ResultDto();
        logger.info("get pod info request is: podIp={}", podIp);
        if (StringUtils.isEmpty(podIp)) {
            result.setMessage("illegal parameter, pod name is empty");
            return result;
        }
        result = persistenceService.getPodInfoByPodIp(podIp);
        logger.info("getPodInfo {} info is {}", podIp, result);
        return result;
    }
    @Override
    public ResultDto getPodInfo(String subsystem, String dcn) {
        ResultDto resultDto = new ResultDto();
        logger.info("get pod info request is: subsystem={}, dcn={}", subsystem, dcn);
        try {
            resultDto.setData(getPodInfoBySubsystemAndDcn(subsystem, dcn));
            resultDto.setCode(Constants.SUCCESS);
            return resultDto;
        } catch (Exception e) {
            logger.warn("exception occur while getting pod info", e);
            resultDto.setMessage(e.getMessage());
            return resultDto;
        }
    }
    public String printPodInfo(String subsystem, String dcn) {
        String newLine = System.getProperty("line.separator");
        String result = newLine;
        try {
            List<PodInfoDTO> podInfoDTOList = getPodInfoBySubsystemAndDcn(subsystem, dcn);
            if (podInfoDTOList == null) {
                return result;
            }
            for (PodInfoDTO podInfoDTO : podInfoDTOList) {
                result += podInfoDTO + newLine;
            }
        } catch (Exception e) {
            logger.warn("exception occur while printing pod info", e);
            result = e.toString();
        }
        return result;
    }
    private List<PodInfoDTO> getPodInfoBySubsystemAndDcn(String subsystem, String dcn) throws Exception {
        if (StringUtils.isEmpty(subsystem)) {
            throw new Exception("subsystem can not be empty");
        }
        List<PodInfoDTO> podInfoDTOForOperators = Lists.newArrayList();
        List<PodInfo> podInfoList = Lists.newArrayList();
        List<PodInfo> result = podInfoDAO.getPodInfoBySubsystem(subsystem);
        for (PodInfo podInfo : result) {
            if (dcn != null && !podInfo.getDcn().equals(dcn)) {
                continue;
            }
            podInfoList.add(podInfo);
        }
        for (PodInfo podInfo : podInfoList) {
            podInfoDTOForOperators.add(persistenceService.podDTO2DOConvertor(podInfo));
        }
        return podInfoDTOForOperators;
    }
    @Override
    public ResultDto getPodNetworkInfo(String podName) {
        ResultDto result = new ResultDto();
        logger.info("pod name of request from cni is {}", podName);
        if (StringUtils.isEmpty(podName)) {
            result.setMessage("illegal parameter, pod name is empty");
            return result;
        }
        result = networkService.getPodNetworkInfoByPodName(podName);
        logger.info("getPodInfo {} network info is {}", podName, result);
        return result;
    }
    @Override
    public ResultDto getPodInfosByPodNameList(List<String> podNames, Boolean ignoreStatus) {
        ResultDto result = new ResultDto();
        logger.info("get pod info request is: podName list={}, ignoreStatus={} (default false)", podNames, ignoreStatus);
        if (ignoreStatus == null) {
            ignoreStatus = false;
        }
        if (podNames == null || podNames.size() == 0) {
            result.setCode(FAIL);
            result.setMessage("illegal parameter, pod name is empty");
            return result;
        }
        result = podInfoService.getPodInfosByPodNameList(podNames, ignoreStatus);
        logger.info("getPodInfo info list is {}", result);
        return result;
    }
    @Override
    public ResultDto getPodMultiNetwork(String podName) {
        return networkService.getPodNetwork(podName);
    }
}