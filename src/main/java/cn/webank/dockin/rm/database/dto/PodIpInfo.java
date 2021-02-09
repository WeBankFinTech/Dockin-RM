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

package cn.webank.dockin.rm.database.dto;

/**
 * Table pod_ip_pool
 *
 * Created by alanwwu on 2019/5/21.
 */
public class PodIpInfo {
    private String ip;
    private String tor;
    private String gatewayIp;
    private String subnetMask;
    private String state;

    public PodIpInfo() {
    }

    public PodIpInfo(String ip, String tor, String gatewayIp, String subnetMask, String state) {
        this.ip = ip;
        this.tor = tor;
        this.gatewayIp = gatewayIp;
        this.subnetMask = subnetMask;
        this.state = state;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTor() {
        return tor;
    }

    public void setTor(String tor) {
        this.tor = tor;
    }

    public String getGatewayIp() {
        return gatewayIp;
    }

    public void setGatewayIp(String gatewayIp) {
        this.gatewayIp = gatewayIp;
    }

    public String getSubnetMask() {
        return subnetMask;
    }

    public void setSubnetMask(String subnetMask) {
        this.subnetMask = subnetMask;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "PodIpInfo{" +
                "ip='" + ip + '\'' +
                ", tor='" + tor + '\'' +
                ", gatewayIp='" + gatewayIp + '\'' +
                ", subnetMask='" + subnetMask + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
