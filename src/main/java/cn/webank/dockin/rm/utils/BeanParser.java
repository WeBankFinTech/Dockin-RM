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

package cn.webank.dockin.rm.utils;
import cn.webank.dockin.rm.bean.pod.PodInfo;
import cn.webank.dockin.rm.database.dto.PodInfoDTO;


public class BeanParser {

        public static PodInfo parsePodInfo(PodInfoDTO podInfoDTO, String clusterId) throws Exception {
        PodInfo podInfo = new PodInfo();
        podInfo.setPodName(podInfoDTO.getPodName());
        podInfo.setSubSystem(podInfoDTO.getSubSystem());
        podInfo.setSubSystemId(podInfoDTO.getSubSystemId());
        podInfo.setDcn(podInfoDTO.getDcn());
        podInfo.setPodIp(podInfoDTO.getPodIp());
        podInfo.setGateway(podInfoDTO.getGateway());
        podInfo.setSubnetMask(podInfoDTO.getSubnetMask());
        podInfo.setHostIp(podInfoDTO.getHostIp());
        podInfo.setCpu(String.valueOf(podInfoDTO.getCpu()));
        podInfo.setMem(String.valueOf(podInfoDTO.getMem()));
        podInfo.setDisk(String.valueOf(podInfoDTO.getDisk()));
        podInfo.setItsmId(podInfoDTO.getItsmId());
        podInfo.setPeople(podInfoDTO.getPeople());
        podInfo.setType(podInfoDTO.getType());
        podInfo.setPort(podInfoDTO.getPort());
        podInfo.setJmxPort(podInfoDTO.getJmxPort());
        podInfo.setPodSetId(podInfoDTO.getPodSetId());
        podInfo.setNamespace(podInfoDTO.getNamespace());

        podInfo.setStatus(podInfoDTO.getState());
        podInfo.setTag(podInfoDTO.getTag());
        podInfo.setUpdateItsmId(podInfoDTO.getUpdateItsmId());
        if (clusterId == null) {
            throw new Exception("cluster Id not found");
        }
        podInfo.setClusterId(clusterId);
        return podInfo;
    }
}
