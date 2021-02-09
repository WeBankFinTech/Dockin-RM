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
import cn.webank.dockin.rm.database.dto.PodInfo;
import cn.webank.dockin.rm.bean.PageInfo;
import org.apache.ibatis.annotations.Param;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;
public interface PodInfoDAO {
    int insert(PodInfo podInfo) throws Exception;
    PodInfo getPodInfoByPodName(@Param("podName") String podName) throws Exception;
    PodInfo getPodInfoByPodIp(@Param("podIp") String podIp) throws Exception;
    List<PodInfo> getPodInfoBySubsystem(@Param("subSystem") String subSystem) throws Exception;
    List<PodInfo> getAllPodInfo() throws Exception;
    List<PodInfo> getPodInfoList(@Param("page") int page, @Param("pageSize") int pageSize) throws Exception;
    int recyclePodInfo(@Param("podIp") String podIp, @Param("offlineItsmId") String offlineItsmId) throws Exception;
    List<String> getPodIpListBySubsystem(@Param("subSystem") String subSystem) throws Exception;
    List<String> getPodIpListByItsmId(@Param("itsmId") String itsmId) throws Exception;
    List<String> getPodIpListByOfflineItsmId(@Param("offlineItsmId") String offlineItsmId) throws Exception;
    int updatePodInfo(PodInfo podInfo) throws Exception;
    List<PodInfo> getPodListByHostIp(@Param("hostIp") String hostIp) throws Exception;
    String getHostIp(@Param("podIp") String podIp) throws Exception;
    List<String> getPodIpList() throws Exception;
    List<PodInfo> getPodInfosByPodNameList(@Param("podNames") List<String> podNames) throws SQLException;
    List<PodInfo> getPodInfosByPodIpList(@Param("podIps") List<String> podIps) throws SQLException;
    List<PodInfo> getPodInfosByPodNameListIgnoreStatus(@Param("podNames") List<String> podNames) throws SQLException;
    Set<String> getAllSubSystem() throws SQLException;
    Set<String> getAllSubSystemId() throws SQLException;
    List<PodInfo> getAllocatedInstanceBySubsystemAndDcn(@Param("subsystem") String subsystem, @Param("dcn") String dcn) throws SQLException;
    int removePodByName(String podName) throws Exception;
    int removePodByIp(@Param("podIp") String podIp) throws Exception;
    int removeAllBySubsystem(String subsystem) throws SQLException;
    int updatePodSubsystemNames() throws SQLException;
    int updatePodNamespace() throws SQLException;
    int getSubsystemIdNullRecordsCount() throws SQLException;
    int getPodsCountByClusterId(String clusterId) throws SQLException;
    List<PodInfo> getPodListByItsmId(String itsmId) throws SQLException;
    List<PodInfo> getPodListByOfflineItsmId(String itsmId) throws SQLException;
    List<String> getPodNameListBySubsystemIdWithoutSetId(String subsystemId) throws SQLException;
    int changeStatus(@Param("podName") String podName, @Param("from") String from, @Param("to") String to) throws SQLException;
    int batchChangeStatus(@Param("podNames") Set<String> podNames, @Param("from") String from, @Param("to") String to) throws SQLException;
    List<PodInfo> getPodInfo(@Param("info") PodInfo podInfo) throws SQLException;
    List<PodInfo> getByPage(@Param("info") PodInfo podInfo, @Param("page") PageInfo pageInfo) throws SQLException;
    List<PodInfo> getByUpdateTime(@Param("from") Date dateFrom, @Param("to") Date dateTo) throws SQLException;
}
