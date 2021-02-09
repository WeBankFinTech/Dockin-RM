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
import cn.webank.dockin.rm.bean.pod.PodInfoDTO;
import cn.webank.dockin.rm.database.dto.PodInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.springframework.util.StringUtils.isEmpty;
public class PodUtil {
    private static Logger logger = LoggerFactory.getLogger(PodUtil.class);
    public static Integer getDefaultPort(String port) {
        Integer intport = 0;
        if (isEmpty(port)) {
            return intport;
        }
        try {
            intport = Integer.valueOf(port);
        } catch (Exception e) {
            logger.warn("illegal port format, port ={}", port);
        }
        return intport;
    }
    public static Set<String> getPodNames(List<PodInfo> podInfoDTOS) {
        Set<String> set = new HashSet<>();
        for (PodInfo podInfo : podInfoDTOS) {
            set.add(podInfo.getPodName());
        }
        return set;
    }
    public static Set<String> extractPodNames(List<PodInfo> podInfoDTOS) {
        Set<String> set = new HashSet<>();
        for (PodInfo podInfoDTO : podInfoDTOS) {
            set.add(podInfoDTO.getPodName());
        }
        return set;
    }
    public static Set<String> getPodIps(List<PodInfo> podInfoDTOS) {
        Set<String> set = new HashSet<>();
        for (PodInfo podInfo : podInfoDTOS) {
            set.add(podInfo.getPodIp());
        }
        return set;
    }
    public static Set<String> getSubSystemNames(List<PodInfoDTO> podInfoDTOS) {
        Set<String> set = new HashSet<>();
        for (PodInfoDTO podInfoDTO : podInfoDTOS) {
            set.add(podInfoDTO.getPodName());
        }
        return set;
    }
    public static String parsePodIp(String podName) {
        String[] components = podName.split("-");
        int length = components.length;
        return String.format("%s.%s.%s.%s", components[length - 4], components[length - 3], components[length - 2],
                components[length - 1]);
    }
}
