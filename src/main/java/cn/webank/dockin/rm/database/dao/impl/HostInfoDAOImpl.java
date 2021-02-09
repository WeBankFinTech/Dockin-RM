



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
import cn.webank.dockin.rm.database.dto.HostInfo;
import cn.webank.dockin.rm.bean.PageInfo;
import cn.webank.dockin.rm.database.WbSqlSession;
import cn.webank.dockin.rm.database.dao.AbstractSimpleDAO;
import cn.webank.dockin.rm.database.dao.HostInfoDAO;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


@Repository
public class HostInfoDAOImpl extends AbstractSimpleDAO implements HostInfoDAO {
    @Override
    public int insertHostInfo(HostInfo hostInfo) throws Exception {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.insertHostInfo(hostInfo);
        }
    }

    @Override
    public String getTorByHostIp(String hostIp) throws Exception {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.getTorByHostIp(hostIp);
        }
    }

    @Override
    public List<String> getTorByAvailableHostDcn(String dcn) throws SQLException {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.getTorByAvailableHostDcn(dcn);
        }
    }

    @Override
    public List<HostInfo> getAvailableHostByTorAndDcn(String tor, String dcn) throws SQLException {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.getAvailableHostByTorAndDcn(tor, dcn);
        }
    }

    @Override
    public int updateHostResource(String hostIp) throws Exception {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.updateHostResource(hostIp);
        }
    }

    @Override
    public List<HostInfo> getAllHosts() throws Exception {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.getAllHosts();
        }
    }

    @Override
    public List<HostInfo> getAllAvailableHosts() throws Exception {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.getAllAvailableHosts();
        }
    }

    @Override
    public HostInfo getHostByHostIp(String hostIp) throws Exception {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.getHostByHostIp(hostIp);
        }
    }

    @Override
    public int addHost(HostInfo item) throws Exception {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.addHost(item);
        }
    }

    @Override
    public int updateHost(HostInfo item) throws Exception {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.updateHost(item);
        }
    }

    @Override
    public int offlineHost(String ip) throws Exception {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.offlineHost(ip);
        }
    }

    @Override
    public String getClusterIdByHostIp(String hostIp) throws Exception {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.getClusterIdByHostIp(hostIp);
        }
    }

    @Override
    public List<HostInfo> getAllAvailableHostsByEnvIdAndIdcAndDcnInSameNetworkArea(Integer envId, String idc, String dcn) throws Exception {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.getAllAvailableHostsByEnvIdAndIdcAndDcnInSameNetworkArea(envId, idc, dcn);
        }
    }

    @Override
    public int updateEnvIdBaseOrganization() throws Exception {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.updateEnvIdBaseOrganization();
        }
    }

    @Override
    public int updateEnvIdBaseOrganizationByHostIp(String hostIp) throws Exception {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.updateEnvIdBaseOrganizationByHostIp(hostIp);
        }
    }

    @Override
    public int updateEnvIdBaseDeployAreaByHostIp(String hostIp) throws Exception {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.updateEnvIdBaseDeployAreaByHostIp(hostIp);
        }
    }

    @Override
    public int updateEnvIdBaseDeployArea() throws Exception {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.updateEnvIdBaseDeployArea();
        }
    }

    @Override
    public List<HostInfo> getAvailableHostsByClusterId(String clusterId) throws SQLException {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.getAvailableHostsByClusterId(clusterId);
        }
    }

    @Override
    public int resetAllEnvId() throws SQLException {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.resetAllEnvId();
        }
    }

    @Override
    public int batchInsert(List<HostInfo> hostInfoList) throws SQLException {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.batchInsert(hostInfoList);
        }
    }

    @Override
    public List<HostInfo> getHosts(HostInfo filter) throws SQLException {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.getHosts(filter);
        }
    }

    @Override
    public List<HostInfo> getByPage(HostInfo hostInfo, PageInfo pageInfo) throws SQLException {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.getByPage(hostInfo, pageInfo);
        }
    }

    @Override
    public List<HostInfo> getByUpdateTime(Date dateFrom, Date dateTo) throws SQLException {
        try (WbSqlSession session = getSession()) {
            HostInfoDAO mapper = session.getMapper(HostInfoDAO.class);
            return mapper.getByUpdateTime(dateFrom, dateTo);
        }
    }
}
