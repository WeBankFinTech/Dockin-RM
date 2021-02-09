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
import cn.webank.dockin.rm.web.bean.BaseDTO;
public class PodNetwork extends BaseDTO {
    private String podName;
    private String ip;
    private String type;
    private String subnetMask;
    private String gateway;
    public String getPodName() {
        return podName;
    }
    public void setPodName(String podName) {
        this.podName = podName;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getSubnetMask() {
        return subnetMask;
    }
    public void setSubnetMask(String subnetMask) {
        this.subnetMask = subnetMask;
    }
    public String getGateway() {
        return gateway;
    }
    public void setGateway(String gateway) {
        this.gateway = gateway;
    }
}
