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
package cn.webank.dockin.rm.utils;
import cn.webank.dockin.rm.bean.biz.AllocateResult;
import cn.webank.dockin.rm.bean.biz.ResultDto;
import cn.webank.dockin.rm.bean.pod.PodInfoDTO;
import cn.webank.dockin.rm.database.dto.PodInfo;
public class BeanParser {
        public static PodInfoDTO parsePodInfo(PodInfo podInfo, String clusterId) throws Exception {
        PodInfoDTO podInfoDTO = new PodInfoDTO();
        podInfoDTO.setPodName(podInfo.getPodName());
        podInfoDTO.setSubSystem(podInfo.getSubSystem());
        podInfoDTO.setSubSystemId(podInfo.getSubSystemId());
        podInfoDTO.setDcn(podInfo.getDcn());
        podInfoDTO.setPodIp(podInfo.getPodIp());
        podInfoDTO.setGateway(podInfo.getGateway());
        podInfoDTO.setSubnetMask(podInfo.getSubnetMask());
        podInfoDTO.setHostIp(podInfo.getHostIp());
        podInfoDTO.setCpu(String.valueOf(podInfo.getCpu()));
        podInfoDTO.setMem(String.valueOf(podInfo.getMem()));
        podInfoDTO.setDisk(String.valueOf(podInfo.getDisk()));
        podInfoDTO.setItsmId(podInfo.getItsmId());
        podInfoDTO.setPeople(podInfo.getPeople());
        podInfoDTO.setType(podInfo.getType());
        podInfoDTO.setPort(podInfo.getPort());
        podInfoDTO.setJmxPort(podInfo.getJmxPort());
        podInfoDTO.setPodSetId(podInfo.getPodSetId());
        podInfoDTO.setNamespace(podInfo.getNamespace());
        podInfoDTO.setStatus(podInfo.getState());
        podInfoDTO.setTag(podInfo.getTag());
        podInfoDTO.setUpdateItsmId(podInfo.getUpdateItsmId());
        if (clusterId == null) {
            throw new Exception("cluster Id not found");
        }
        podInfoDTO.setClusterId(clusterId);
        return podInfoDTO;
    }
        public static ResultDto parseResultDto(AllocateResult result) {
                ResultDto resultDto = new ResultDto();
                resultDto.setCode(result.getResult());
                resultDto.setData(result.getInstances());
                resultDto.setMessage(result.getMessage());
                return resultDto;
        }
}
