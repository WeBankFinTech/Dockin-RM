/*
 * Copyright (C) @2020 Webank Group Holding Limited
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
import cn.webank.dockin.rm.database.dao.PodInfoDAO;
import cn.webank.dockin.rm.database.dto.PodInfoDTO;
import cn.webank.dockin.rm.bean.biz.ResultDto;
import cn.webank.dockin.rm.bean.pod.PodInfo;
import cn.webank.dockin.rm.common.Constants;
import cn.webank.dockin.rm.service.HostService;
import cn.webank.dockin.rm.service.PodInfoService;
import cn.webank.dockin.rm.service.PodSetService;
import cn.webank.dockin.rm.utils.BeanParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PodInfoServiceImpl implements PodInfoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${rm.env}")
    private String env = "prd";

    @Autowired
    PodInfoDAO podInfoDAO;

    @Autowired
    PodSetService podSetService;

    @Autowired
    HostService hostService;

    @Override
    public List<PodInfo> getPodInfoByPodSetId(String podSetId) throws Exception {
        podSetId = podSetService.parsePodSetId(podSetId);
        PodInfoDTO podInfoDTO = new PodInfoDTO();
        podInfoDTO.setPodSetId(podSetId);
        List<PodInfoDTO> queryPodList = podInfoDAO.getPodInfo(podInfoDTO);

        List<PodInfo> result = new ArrayList<>();
        for (PodInfoDTO p : queryPodList) {
            result.add(BeanParser.parsePodInfo(p, hostService.getClusterId(p.getHostIp())));

        }

        return result;
    }


    @Override
    public ResultDto getPodInfosByPodNameList(List<String> podNames, boolean ignoreStatus) {
        ResultDto resultDto = new ResultDto();
        try {
            List<PodInfoDTO> podInfoDTOList = null;
            if (ignoreStatus) {
                podInfoDTOList = podInfoDAO.getPodInfosByPodNameListIgnoreStatus(podNames);
            } else {
                podInfoDTOList = podInfoDAO.getPodInfosByPodNameList(podNames);
            }
            if (podInfoDTOList == null || podInfoDTOList.size() == 0) {
                resultDto.setMessage("pod info not found for pod name");
                return resultDto;
            }

            List<PodInfo> podInfoForOperators = new ArrayList<>();

            for (PodInfoDTO podInfoDTO : podInfoDTOList) {
                podInfoForOperators.add(BeanParser.parsePodInfo(podInfoDTO, hostService.getClusterId(podInfoDTO.getHostIp())));
            }

            resultDto.setData(podInfoForOperators);
            resultDto.setCode(Constants.SUCCESS);
        } catch (Exception e) {
            logger.warn("exception occur while get pod info by pod name", e);
            resultDto.setMessage(e.getMessage());
        }
        return resultDto;
    }


}
