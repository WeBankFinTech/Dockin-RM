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
import cn.webank.dockin.rm.database.dao.PodInfoDAO;
import cn.webank.dockin.rm.database.dto.PodInfo;
import cn.webank.dockin.rm.database.dto.Subsystem;
import cn.webank.dockin.rm.exception.RmException;
import cn.webank.dockin.rm.exception.RmRuntimeException;
import cn.webank.dockin.rm.service.PodSetService;
import cn.webank.dockin.rm.service.SubsystemService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
@Service
public class PodSetServiceImpl implements PodSetService {
    private static Logger logger = LoggerFactory.getLogger(PodSetServiceImpl.class);
    @Autowired
    SubsystemService subsystemService;
    @Autowired
    PodInfoDAO podInfoDAO;
    @Override
    public String parsePodSetId(String podSetId) {
        Assert.notNull(podSetId, "podSetId can not be null");
        String[] podSetArray = podSetId.split("-");
        if (podSetArray.length < 2) {
            throw new IllegalArgumentException("podSetId format is error");
        }
        if (StringUtils.isNumeric(podSetArray[0])) {
            return podSetId;
        } else {
            String subsystemName = podSetArray[0] + "-" + podSetArray[1];
            Subsystem subsystem = subsystemService.getBySubsystemName(subsystemName);
            return subsystem == null ? podSetId : subsystem.getSubsystemId() + "-"
                    + podSetArray[podSetArray.length - 1];
        }
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bindPod(String setId, String podName) {
        try {
            PodInfo pod = new PodInfo();
            pod.setPodName(podName);
            pod.setPodSetId(setId);
            int row = podInfoDAO.updatePodInfo(pod);
            if (row == 0) {
                throw new RmException("affected row equals 0");
            }
        } catch (Exception e) {
            throw new RmRuntimeException("bind pod failed", e);
        }
    }
}
