



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

package cn.webank.dockin.rm.web.service;

import cn.webank.dockin.rm.bean.biz.ResultDto;
import cn.webank.dockin.rm.bean.pod.PodInfoDTO;
import cn.webank.dockin.rm.common.Constants;
import cn.webank.dockin.rm.service.*;
import cn.webank.dockin.rm.web.bean.GetPodDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class QueryRmService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PodInfoService podInfoService;

    public ResultDto<List<PodInfoDTO>> getPodInfoByPodSetId(String podSetId) {
        ResultDto<List<PodInfoDTO>> result = new ResultDto();
        if (StringUtils.isEmpty(podSetId)) {
            result.setCode(Constants.FAIL);
            result.setMessage("illegal parameter, pod set id is empty");
            return result;
        }

        try {
            List<PodInfoDTO> podInfoDTOList = podInfoService.getPodInfoByPodSetId(podSetId);
            if (podInfoDTOList != null && podInfoDTOList.size() > 0) {
                result.setData(podInfoDTOList);
                result.setCode(Constants.SUCCESS);
            } else {
                result.setCode(Constants.FAIL);
                result.setMessage("pod not found");
            }
        } catch (Exception e) {
            result.setCode(Constants.FAIL);
            result.setMessage("query failed:" + e.getMessage());
        }

        logger.info("getPodInfoByPodSetId podSetId {}, result {}", podSetId, result);
        return result;
    }

    private List<PodInfoDTO> matchResult(List<PodInfoDTO> podInfoDTOList, GetPodDTO getPodDTO) {
        if (CollectionUtils.isEmpty(podInfoDTOList)) return new ArrayList<>();

        List<PodInfoDTO> matchedList = new ArrayList<>();

        for (PodInfoDTO podInfoDTO : podInfoDTOList) {
            if ((getPodDTO.getPodName() == null || podInfoDTO.getPodName().equals(getPodDTO.getPodName()))
                    && (getPodDTO.getPodSetId() == null || podInfoDTO.getPodSetId().equals(getPodDTO.getPodSetId()))
                    && (getPodDTO.getHostIp() == null || podInfoDTO.getHostIp().equals(getPodDTO.getHostIp()))
                    && (getPodDTO.getSubsystem() == null || podInfoDTO.getSubSystem().equals(getPodDTO.getSubsystem()))
                    && (getPodDTO.getDcn() == null || podInfoDTO.getDcn().equals(getPodDTO.getDcn()))
            ) {
                matchedList.add(podInfoDTO);
            }
        }

        return matchedList;
    }

    
    public void validate(GetPodDTO getPodDTO) {
    }
}


