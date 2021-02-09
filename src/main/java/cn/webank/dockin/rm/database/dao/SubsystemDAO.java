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
package cn.webank.dockin.rm.database.dao;
import cn.webank.dockin.rm.database.dto.Subsystem;
import cn.webank.dockin.rm.bean.PageInfo;
import org.apache.ibatis.annotations.Param;
import java.sql.SQLException;
import java.util.List;
public interface SubsystemDAO {
        int insert(Subsystem subsystem) throws SQLException;
        int batchInsert(@Param("subsystems") List<Subsystem> subsystems) throws SQLException;
        int update(Subsystem subsystem) throws SQLException;
        Subsystem getBySubsystemName(String subsystemName) throws SQLException;
        Subsystem getBySubsystemId(String subsystemId) throws SQLException;
        List<Subsystem> getByPage(@Param("info") Subsystem subsystem, @Param("page") PageInfo pageInfo) throws SQLException;
}
