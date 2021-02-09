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
import cn.webank.dockin.rm.bean.pod.PodInfoDTO;
import cn.webank.dockin.rm.database.dao.HostInfoDAO;
import cn.webank.dockin.rm.database.dao.PodInfoDAO;
import cn.webank.dockin.rm.database.dto.PodInfo;
import cn.webank.dockin.rm.bean.biz.ResultDto;
import cn.webank.dockin.rm.common.Constants;
import cn.webank.dockin.rm.service.PersistenceService;
import cn.webank.dockin.rm.utils.BeanParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static cn.webank.dockin.rm.common.Constants.FAIL;
@Service
public class PersistenceServiceImpl implements PersistenceService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PodInfoDAO podInfoDAO;
    @Autowired
    private HostInfoDAO hostInfoDAO;
    @Override
    public ResultDto getPodInfoByPodName(String podName) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(FAIL);
        try {
            PodInfo podInfo = podInfoDAO.getPodInfoByPodName(podName);
            if (podInfo == null || Constants.POD_OFFLINE.equals(podInfo.getState())) {
                resultDto.setMessage(String.format("pod info not found for pod name: %s", podName));
                return resultDto;
            }
            resultDto.setData(podDTO2DOConvertor(podInfo));
            resultDto.setCode(Constants.SUCCESS);
        } catch (Exception e) {
            logger.warn("exception occur while get pod info by pod name", e);
            resultDto.setMessage(e.getMessage());
        }
        return resultDto;
    }
    @Override
    public ResultDto getPodInfoByPodIp(String podIp) {
        ResultDto resultDto = new ResultDto();
        try {
            PodInfo podInfo = podInfoDAO.getPodInfoByPodIp(podIp);
            if (podInfo == null) {
                resultDto.setMessage(String.format("pod info not found for pod ip: %s", podIp));
                return resultDto;
            }
            if (Constants.POD_OFFLINE.equals(podInfo.getState())) {
                resultDto.setMessage(String.format("pod state is offline, pod ip: %s", podIp));
                resultDto.setCode(FAIL);
                return resultDto;
            }
            resultDto.setData(podDTO2DOConvertor(podInfo));
            resultDto.setCode(Constants.SUCCESS);
        } catch (Exception e) {
            logger.warn("exception occur while get pod info by pod ip", e);
            resultDto.setMessage(e.getMessage());
        }
        return resultDto;
    }
    @Override
    public PodInfoDTO podDTO2DOConvertor(PodInfo podInfo) throws Exception {
        return BeanParser.parsePodInfo(podInfo, getClusterId(podInfo.getHostIp()));
    }
    public String getClusterId(String hostIp) throws Exception {
        return hostInfoDAO.getClusterIdByHostIp(hostIp);
    }
}
