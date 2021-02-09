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
import cn.webank.dockin.rm.database.dto.HostInfo;
import cn.webank.dockin.rm.bean.PageInfo;
import org.apache.ibatis.annotations.Param;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
public interface HostInfoDAO {
    int insertHostInfo(HostInfo hostInfo) throws Exception;
    String getTorByHostIp(@Param("hostIp") String hostIp) throws Exception;
    List<String> getTorByAvailableHostDcn(@Param("dcn") String dcn) throws SQLException;
    List<HostInfo> getAvailableHostByTorAndDcn(@Param("tor") String tor, @Param("dcn") String dcn) throws SQLException;
    int updateHostResource(@Param("hostIp") String hostIp) throws Exception;
    List<HostInfo> getAllHosts() throws Exception;
    List<HostInfo> getAllAvailableHosts() throws Exception;
    HostInfo getHostByHostIp(@Param("hostIp") String hostIp) throws Exception;
    int addHost(HostInfo item) throws Exception;
    int updateHost(HostInfo item) throws Exception;
    int offlineHost(String ip) throws Exception;
    String getClusterIdByHostIp(@Param("hostIp") String hostIp) throws Exception;
    List<HostInfo> getAllAvailableHostsByEnvIdAndIdcAndDcnInSameNetworkArea(@Param("envId") Integer envId, @Param("idc") String idc,
                                                                            @Param("dcn") String dcn) throws Exception;
    int updateEnvIdBaseOrganization() throws Exception;
    int updateEnvIdBaseOrganizationByHostIp(String hostIp) throws Exception;
    int updateEnvIdBaseDeployAreaByHostIp(String hostIp) throws Exception;
    int updateEnvIdBaseDeployArea() throws Exception;
    List<HostInfo> getAvailableHostsByClusterId(String clusterId) throws SQLException;
    int resetAllEnvId() throws SQLException;
    int batchInsert(@Param("hostInfoList") List<HostInfo> hostInfoList) throws SQLException;
    List<HostInfo> getHosts(@Param("info") HostInfo filter) throws SQLException;
    List<HostInfo> getByPage(@Param("info") HostInfo hostInfo, @Param("page") PageInfo pageInfo) throws SQLException;
    List<HostInfo> getByUpdateTime(@Param("from") Date dateFrom, @Param("to") Date dateTo) throws SQLException;
}
