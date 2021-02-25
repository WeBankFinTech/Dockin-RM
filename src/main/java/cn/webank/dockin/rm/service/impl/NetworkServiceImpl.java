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
import cn.webank.dockin.rm.database.dao.PodNetworkDAO;
import cn.webank.dockin.rm.database.dto.PodInfo;
import cn.webank.dockin.rm.database.dto.PodNetwork;
import cn.webank.dockin.rm.bean.biz.PodNetworkInfoRsp;
import cn.webank.dockin.rm.bean.biz.ResultDto;
import cn.webank.dockin.rm.bean.network.Network;
import cn.webank.dockin.rm.bean.network.NetworkType;
import cn.webank.dockin.rm.common.Constants;
import cn.webank.dockin.rm.service.NetworkService;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
import static cn.webank.dockin.rm.common.Constants.FAIL;
@Service
public class NetworkServiceImpl implements NetworkService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PodInfoDAO podInfoDAO;
    @Autowired
    private PodNetworkDAO podNetworkDAO;
    @Override
    public ResultDto<List<Network>> getPodNetwork(String podName) {
        ResultDto<List<Network>> result = new ResultDto();
        logger.info("pod name of request from cni is {}", podName);
        if (StringUtils.isEmpty(podName)) {
            result.setMessage("illegal parameter, pod name is empty");
            return result;
        }
        try {
            PodInfo podInfo = podInfoDAO.getPodInfoByPodName(podName);
            if (podInfo == null) {
                result.setMessage(String.format("pod info not found for pod name: %s", podName));
                result.setCode(FAIL);
                return result;
            }
            if (Constants.POD_OFFLINE.equals(podInfo.getState())) {
                result.setMessage(String.format("pod state is offline, pod name: %s", podName));
                result.setCode(FAIL);
                return result;
            }
            Network network = new Network();
            network.setPodIp(podInfo.getPodIp());
            network.setGateway(podInfo.getGateway());
            network.setSubnetMask(podInfo.getSubnetMask());
            network.setMaster(true);
            network.setIfName("eth0");
            network.setType(NetworkType.WEBANK.value);
            List<Network> networks = Lists.newArrayList();
            networks.add(network);
            List<PodNetwork> addNetworks = podNetworkDAO.getByPodName(podName);
            if (addNetworks != null && addNetworks.size() > 0) {
                int ifCount = 0;
                for (PodNetwork podNetwork : addNetworks) {
                    ++ifCount;
                    network = new Network();
                    network.setPodIp(podNetwork.getIp());
                    network.setGateway(podNetwork.getGateway());
                    network.setSubnetMask(podNetwork.getSubnetMask());
                    network.setType(podNetwork.getType());
                    network.setMaster(false);
                    network.setIfName("eth" + ifCount);
                    networks.add(network);
                }
            }
            result.setData(networks);
            result.setCode(Constants.SUCCESS);
        } catch (Exception e) {
            logger.warn("exception occur while get pod info by pod name", e);
            result.setMessage(e.getMessage());
        }
        return result;
    }
    @Override
    public ResultDto getPodNetworkInfoByPodName(String podName) {
        ResultDto resultDto = new ResultDto();
        try {
            PodInfo podInfo = podInfoDAO.getPodInfoByPodName(podName);
            if (podInfo == null) {
                resultDto.setMessage(String.format("pod info not found for pod name: %s", podName));
                resultDto.setCode(FAIL);
                return resultDto;
            }
            if (Constants.POD_OFFLINE.equals(podInfo.getState())) {
                resultDto.setMessage(String.format("pod state is offline, pod name: %s", podName));
                resultDto.setCode(FAIL);
                return resultDto;
            }
            PodNetworkInfoRsp podNetworkInfoRsp = new PodNetworkInfoRsp();
            podNetworkInfoRsp.setPodIp(podInfo.getPodIp());
            podNetworkInfoRsp.setGateway(podInfo.getGateway());
            podNetworkInfoRsp.setSubnetMask(podInfo.getSubnetMask());
            resultDto.setData(podNetworkInfoRsp);
            resultDto.setCode(Constants.SUCCESS);
        } catch (Exception e) {
            logger.warn("exception occur while get pod info by pod name", e);
            resultDto.setMessage(e.getMessage());
        }
        return resultDto;
    }

    @Override
    public void recycleAdditionalNetwork(String podName) {
        // TODO
    }
}
