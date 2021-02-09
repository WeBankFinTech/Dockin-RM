



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

package cn.webank.dockin.rm.database.dao.impl;
import cn.webank.dockin.rm.database.dto.Subsystem;
import cn.webank.dockin.rm.bean.PageInfo;
import cn.webank.dockin.rm.database.WbSqlSession;
import cn.webank.dockin.rm.database.dao.AbstractSimpleDAO;
import cn.webank.dockin.rm.database.dao.SubsystemDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;


@Repository
public class SubsystemDAOImpl extends AbstractSimpleDAO implements SubsystemDAO {
    @Override
    public int insert(Subsystem subsystem) throws SQLException {
        try (WbSqlSession session = getSession()) {
            SubsystemDAO mapper = session.getMapper(SubsystemDAO.class);
            return mapper.insert(subsystem);
        }
    }

    @Override
    public int batchInsert(@Param("subsystems") List<Subsystem> subsystems) throws SQLException {
        try (WbSqlSession session = getSession()) {
            SubsystemDAO mapper = session.getMapper(SubsystemDAO.class);
            return mapper.batchInsert(subsystems);
        }
    }

    @Override
    public int update(Subsystem subsystem) throws SQLException {
        try (WbSqlSession session = getSession()) {
            SubsystemDAO mapper = session.getMapper(SubsystemDAO.class);
            return mapper.update(subsystem);
        }
    }

    @Override
    public Subsystem getBySubsystemName(String subsystemName) throws SQLException {
        try (WbSqlSession session = getSession()) {
            SubsystemDAO mapper = session.getMapper(SubsystemDAO.class);
            return mapper.getBySubsystemName(subsystemName);
        }
    }

    @Override
    public Subsystem getBySubsystemId(String subsystemId) throws SQLException {
        try (WbSqlSession session = getSession()) {
            SubsystemDAO mapper = session.getMapper(SubsystemDAO.class);
            return mapper.getBySubsystemId(subsystemId);
        }
    }

    @Override
    public List<Subsystem> getByPage(Subsystem subsystem, PageInfo pageInfo) throws SQLException {
        try (WbSqlSession session = getSession()) {
            SubsystemDAO mapper = session.getMapper(SubsystemDAO.class);
            return mapper.getByPage(subsystem, pageInfo);
        }
    }
}
