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

package cn.webank.dockin.rm.web.service;

import cn.webank.dockin.rm.bean.biz.ResultDto;
import cn.webank.dockin.rm.bean.pod.PodInfo;
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

    public ResultDto<List<PodInfo>> getPodInfoByPodSetId(String podSetId) {
        ResultDto<List<PodInfo>> result = new ResultDto();
        if (StringUtils.isEmpty(podSetId)) {
            result.setCode(Constants.FAIL);
            result.setMessage("illegal parameter, pod set id is empty");
            return result;
        }

        try {
            List<PodInfo> podInfoList = podInfoService.getPodInfoByPodSetId(podSetId);
            if (podInfoList != null && podInfoList.size() > 0) {
                result.setData(podInfoList);
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

    private List<PodInfo> matchResult(List<PodInfo> podInfoList, GetPodDTO getPodDTO) {
        if (CollectionUtils.isEmpty(podInfoList)) return new ArrayList<>();

        List<PodInfo> matchedList = new ArrayList<>();

        for (PodInfo podInfo : podInfoList) {
            if ((getPodDTO.getPodName() == null || podInfo.getPodName().equals(getPodDTO.getPodName()))
                    && (getPodDTO.getPodSetId() == null || podInfo.getPodSetId().equals(getPodDTO.getPodSetId()))
                    && (getPodDTO.getHostIp() == null || podInfo.getHostIp().equals(getPodDTO.getHostIp()))
                    && (getPodDTO.getSubsystem() == null || podInfo.getSubSystem().equals(getPodDTO.getSubsystem()))
                    && (getPodDTO.getDcn() == null || podInfo.getDcn().equals(getPodDTO.getDcn()))
            ) {
                matchedList.add(podInfo);
            }
        }

        return matchedList;
    }

    
    public void validate(GetPodDTO getPodDTO) {
    }
}


