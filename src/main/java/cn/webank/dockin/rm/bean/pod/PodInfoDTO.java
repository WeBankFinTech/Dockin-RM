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
package cn.webank.dockin.rm.bean.pod;
import cn.webank.dockin.rm.web.bean.BaseDTO;
public class PodInfoDTO extends BaseDTO {
    private String podName;
    private String podSetId;
    private String subSystem;
    private String subSystemId;
    private String dcn;
    private String podIp;
    private String gateway;
    private String subnetMask;
    private String hostIp;
    private String cpu;
    private String mem;
    private String disk;
    private String itsmId;
    private String offlineItsmId;
    private String updateItsmId;
    private String people;
    private String type;
    private Integer port;
    private Integer jmxPort;
    private String tag;
    private String namespace;
    private String status;
    private String clusterId;
    public PodInfoDTO() {
    }
    public PodInfoDTO(String podName, String podSetId, String subSystem, String subSystemId, String dcn, String podIp, String gateway, String
            subnetMask, String hostIp, String cpu, String mem, String disk, String itsmId, String people, String
                           type, Integer port, Integer jmxPort, String namespace, String status, String
                           clusterId) {
        this.podName = podName;
        this.podSetId = podSetId;
        this.subSystem = subSystem;
        this.subSystemId = subSystemId;
        this.dcn = dcn;
        this.podIp = podIp;
        this.gateway = gateway;
        this.subnetMask = subnetMask;
        this.hostIp = hostIp;
        this.cpu = cpu;
        this.mem = mem;
        this.disk = disk;
        this.itsmId = itsmId;
        this.people = people;
        this.type = type;
        this.port = port;
        this.jmxPort = jmxPort;
        this.namespace = namespace;
        this.status = status;
        this.clusterId = clusterId;
    }
    public String getSubSystemId() {
        return subSystemId;
    }
    public void setSubSystemId(String subSystemId) {
        this.subSystemId = subSystemId;
    }
    public String getPodName() {
        return podName;
    }
    public void setPodName(String podName) {
        this.podName = podName;
    }
    public String getSubSystem() {
        return subSystem == null ? null : subSystem.toLowerCase().trim();
    }
    public void setSubSystem(String subSystem) {
        this.subSystem = subSystem;
    }
    public String getDcn() {
        return dcn == null ? null : dcn.toUpperCase();
    }
    public void setDcn(String dcn) {
        this.dcn = dcn;
    }
    public String getPodIp() {
        return podIp;
    }
    public void setPodIp(String podIp) {
        this.podIp = podIp;
    }
    public String getGateway() {
        return gateway;
    }
    public void setGateway(String gateway) {
        this.gateway = gateway;
    }
    public String getSubnetMask() {
        return subnetMask;
    }
    public void setSubnetMask(String subnetMask) {
        this.subnetMask = subnetMask;
    }
    public String getHostIp() {
        return hostIp;
    }
    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }
    public String getCpu() {
        return cpu;
    }
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }
    public String getMem() {
        return mem;
    }
    public void setMem(String mem) {
        this.mem = mem;
    }
    public String getDisk() {
        return disk;
    }
    public void setDisk(String disk) {
        this.disk = disk;
    }
    public String getItsmId() {
        return itsmId;
    }
    public void setItsmId(String itsmId) {
        this.itsmId = itsmId;
    }
    public String getOfflineItsmId() {
        return offlineItsmId;
    }
    public void setOfflineItsmId(String offlineItsmId) {
        this.offlineItsmId = offlineItsmId;
    }
    public String getPeople() {
        return people;
    }
    public void setPeople(String people) {
        this.people = people;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Integer getPort() {
        return port;
    }
    public void setPort(Integer port) {
        this.port = port;
    }
    public Integer getJmxPort() {
        return jmxPort;
    }
    public void setJmxPort(Integer jmxPort) {
        this.jmxPort = jmxPort;
    }
    public String getNamespace() {
        return namespace == null ? null : namespace.toUpperCase();
    }
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getClusterId() {
        return clusterId;
    }
    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }
    public String getUpdateItsmId() {
        return updateItsmId;
    }
    public void setUpdateItsmId(String updateItsmId) {
        this.updateItsmId = updateItsmId;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getPodSetId() {
        return podSetId;
    }
    public void setPodSetId(String podSetId) {
        this.podSetId = podSetId;
    }
    @Override
    public String toString() {
        return "PodInfo{" +
                "podName='" + podName + '\'' +
                ", podSetId='" + podSetId + '\'' +
                ", subSystem='" + subSystem + '\'' +
                ", subSystemId='" + subSystemId + '\'' +
                ", dcn='" + dcn + '\'' +
                ", podIp='" + podIp + '\'' +
                ", gateway='" + gateway + '\'' +
                ", subnetMask='" + subnetMask + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", cpu='" + cpu + '\'' +
                ", mem='" + mem + '\'' +
                ", disk='" + disk + '\'' +
                ", itsmId='" + itsmId + '\'' +
                ", offlineItsmId='" + offlineItsmId + '\'' +
                ", updateItsmId='" + updateItsmId + '\'' +
                ", people='" + people + '\'' +
                ", type='" + type + '\'' +
                ", port=" + port +
                ", jmxPort=" + jmxPort +
                ", tag='" + tag + '\'' +
                ", namespace='" + namespace + '\'' +
                ", state='" + status + '\'' +
                ", clusterId='" + clusterId + '\'' +
                '}';
    }
}
