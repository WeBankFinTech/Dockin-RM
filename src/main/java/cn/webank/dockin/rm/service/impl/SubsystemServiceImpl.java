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
import cn.webank.dockin.rm.database.dao.SubsystemDAO;
import cn.webank.dockin.rm.database.dto.Subsystem;
import cn.webank.dockin.rm.exception.SysException;
import cn.webank.dockin.rm.service.SubsystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
@Service
public class SubsystemServiceImpl implements SubsystemService {
    private Logger logger = LoggerFactory.getLogger(SubsystemServiceImpl.class);
    @Autowired
    SubsystemDAO subsystemDAO;
    @Override
    public Subsystem getBySubsystemName(String subSystemName) {
        return getBySubsystem(subSystemName, TYPE_NAME);
    }
    public Subsystem getBySubsystem(String subSystem, String type) {
        Subsystem subsystem = null;
        try {
            if (type.equals(TYPE_NAME)) {
                subsystem = subsystemDAO.getBySubsystemName(subSystem);
            } else if (type.equals(TYPE_ID)) {
                subsystem = subsystemDAO.getBySubsystemId(subSystem);
            }
        } catch (Exception e) {
            throw new SysException("get subsystem from db or cmdb exception", e);
        }
        if (subsystem == null || StringUtils.isEmpty(subsystem.getSubsystemId())
                || StringUtils.isEmpty(subsystem.getSubsystemName())) {
            throw new SysException("system not found in db and cmdb, name or id=" + subSystem);
        }
        return subsystem;
    }
}
