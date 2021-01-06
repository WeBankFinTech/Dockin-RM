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

package cn.webank.dockin.rm.database.dao.impl;
import cn.webank.dockin.rm.database.dto.PodInfoDTO;
import cn.webank.dockin.rm.bean.PageInfo;
import cn.webank.dockin.rm.database.WbSqlSession;
import cn.webank.dockin.rm.database.dao.AbstractSimpleDAO;
import cn.webank.dockin.rm.database.dao.PodInfoDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Repository
public class PodInfoDAOImpl extends AbstractSimpleDAO implements PodInfoDAO {
    @Override
    public int insert(PodInfoDTO podInfo) throws Exception {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.insert(podInfo);
        }
    }

    @Override
    public PodInfoDTO getPodInfoByPodName(String podName) throws Exception {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getPodInfoByPodName(podName);
        }
    }

    @Override
    public List<PodInfoDTO> getPodInfoBySubsystem(String subSystem) throws Exception {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getPodInfoBySubsystem(subSystem);
        }
    }

    @Override
    public int recyclePodInfo(String podIp, String offlineItsmId) throws Exception {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.recyclePodInfo(podIp, offlineItsmId);
        }
    }

    @Override
    public PodInfoDTO getPodInfoByPodIp(String podIp) throws Exception {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getPodInfoByPodIp(podIp);
        }
    }

    @Override
    public List<PodInfoDTO> getAllPodInfo() throws Exception {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getAllPodInfo();
        }
    }

    @Override
    public List<PodInfoDTO> getPodInfoList(int page, int pageSize) throws Exception {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getPodInfoList(page, pageSize);
        }
    }


    @Override
    public List<String> getPodIpListBySubsystem(String subSystem) throws Exception {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getPodIpListBySubsystem(subSystem);
        }
    }

    @Override
    public List<String> getPodIpListByItsmId(String itsmId) throws Exception {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getPodIpListByItsmId(itsmId);
        }
    }

    @Override
    public List<String> getPodIpListByOfflineItsmId(String offlineItsmId) throws Exception {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getPodIpListByOfflineItsmId(offlineItsmId);
        }
    }

    @Override
    public int updatePodInfo(PodInfoDTO podInfoDTO) throws Exception {
        if (StringUtils.isEmpty(podInfoDTO.getPodName())) {
            throw new Exception("podName can not be empty");
        }
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.updatePodInfo(podInfoDTO);
        }
    }

    @Override
    public List<PodInfoDTO> getPodListByHostIp(String hostIp) throws Exception {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getPodListByHostIp(hostIp);
        }
    }

    @Override
    public String getHostIp(String podIp) throws Exception {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getHostIp(podIp);
        }
    }

    @Override
    public List<String> getPodIpList() throws Exception {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getPodIpList();
        }
    }

    @Override
    public List<PodInfoDTO> getPodInfosByPodNameList(List<String> podNames) throws SQLException {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getPodInfosByPodNameList(podNames);
        }
    }

    @Override
    public List<PodInfoDTO> getPodInfosByPodIpList(List<String> podIps) throws SQLException {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getPodInfosByPodIpList(podIps);
        }
    }

    @Override
    public List<PodInfoDTO> getPodInfosByPodNameListIgnoreStatus(List<String> podNames) throws SQLException {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getPodInfosByPodNameListIgnoreStatus(podNames);
        }
    }

    @Override
    public Set<String> getAllSubSystem() throws SQLException {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getAllSubSystem();
        }
    }

    @Override
    public Set<String> getAllSubSystemId() throws SQLException {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getAllSubSystemId();
        }
    }

    @Override
    public List<PodInfoDTO> getAllocatedInstanceBySubsystemAndDcn(@Param("subsystem") String subsystem, @Param("dcn") String dcn) throws SQLException {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getAllocatedInstanceBySubsystemAndDcn(subsystem, dcn);
        }
    }

    @Override
    public int removePodByName(String podName) throws Exception {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.removePodByName(podName);
        }
    }

    @Override
    public int removePodByIp(String podIp) throws Exception {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.removePodByIp(podIp);
        }
    }

    @Override
    public int removeAllBySubsystem(String subsystem) throws SQLException {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.removeAllBySubsystem(subsystem);
        }
    }

    @Override
    public int updatePodSubsystemNames() throws SQLException {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.updatePodSubsystemNames();
        }
    }

    @Override
    public int updatePodNamespace() throws SQLException {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.updatePodNamespace();
        }
    }

    @Override
    public int getSubsystemIdNullRecordsCount() throws SQLException {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getSubsystemIdNullRecordsCount();
        }
    }

    @Override
    public int getPodsCountByClusterId(String clusterId) throws SQLException {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getPodsCountByClusterId(clusterId);
        }
    }

    @Override
    public List<PodInfoDTO> getPodListByItsmId(String itsmId) throws SQLException {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getPodListByItsmId(itsmId);
        }
    }

    @Override
    public List<PodInfoDTO> getPodListByOfflineItsmId(String itsmId) throws SQLException {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getPodListByOfflineItsmId(itsmId);
        }
    }

    @Override
    public List<String> getPodNameListBySubsystemIdWithoutSetId(String subsystemId) throws SQLException {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getPodNameListBySubsystemIdWithoutSetId(subsystemId);
        }
    }

    @Override
    public int changeStatus(String podName, String from, String to) throws SQLException {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.changeStatus(podName, from, to);
        }
    }

    @Override
    public int batchChangeStatus(Set<String> podNames, String from, String to) throws SQLException {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.batchChangeStatus(podNames, from, to);
        }
    }

    @Override
    public List<PodInfoDTO> getPodInfo(PodInfoDTO podInfoDTO) throws SQLException {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getPodInfo(podInfoDTO);
        }
    }

    @Override
    public List<PodInfoDTO> getByPage(PodInfoDTO podInfoDTO, PageInfo pageInfo) throws SQLException {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getByPage(podInfoDTO, pageInfo);
        }
    }

    @Override
    public List<PodInfoDTO> getByUpdateTime(Date dateFrom, Date dateTo) throws SQLException {
        try (WbSqlSession session = getSession()) {
            PodInfoDAO mapper = session.getMapper(PodInfoDAO.class);
            return mapper.getByUpdateTime(dateFrom, dateTo);
        }
    }
}
