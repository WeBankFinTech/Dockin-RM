package cn.webank.dockin.rm.utils;

import cn.webank.wcs.rm.bean.itsm.AddInstanceTableContent;
import cn.webank.wcs.rm.bean.itsm.AddInstanceTableContentList;
import cn.webank.wcs.rm.bean.pod.PodInfo;
import cn.webank.dockin.rm.database.dto.PodInfoDTO;
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


    public static Set<String> getPodNames(List<PodInfoDTO> podInfoDTOS) {
        Set<String> set = new HashSet<>();
        for (PodInfoDTO podInfo : podInfoDTOS) {
            set.add(podInfo.getPodName());
        }
        return set;
    }


    public static Set<String> extractPodNames(List<PodInfo> podInfos) {
        Set<String> set = new HashSet<>();
        for (PodInfo podInfo : podInfos) {
            set.add(podInfo.getPodName());
        }
        return set;
    }


    public static Set<String> getPodIps(List<PodInfoDTO> podInfoDTOS) {
        Set<String> set = new HashSet<>();
        for (PodInfoDTO podInfo : podInfoDTOS) {
            set.add(podInfo.getPodIp());
        }
        return set;
    }


    public static Set<String> getPodIps(AddInstanceTableContentList addInstanceTableContentList) {
        Set<String> set = new HashSet<>();
        for (AddInstanceTableContent addInstanceTableContent : addInstanceTableContentList.getDataList()) {
            set.add(addInstanceTableContent.getPodIp());
        }
        return set;
    }


    public static Set<String> getSubSystemNames(List<PodInfo> podInfos) {
        Set<String> set = new HashSet<>();
        for (PodInfo podInfo : podInfos) {
            set.add(podInfo.getPodName());
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
