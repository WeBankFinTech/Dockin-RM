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

package cn.webank.dockin.rm.database.dto;

import cn.webank.dockin.rm.web.bean.BaseDTO;


public class HostInfo extends BaseDTO implements Cloneable {
    
    public static final String STATE_AVAILABLE = "available";

    
    public static final String STATE_UNAVAILABLE = "unavailable";

    
    public static final String STATE_OFFLINE = "offline";

    private String hostIp;
    private String tor;
    private String waTor;
    private String idc;
    private String dcn;
    private Integer envId;
    private String clusterId;
    private String clusterVersion;
    private Double allCpu;
    private Double allMem;
    private Integer allDisk;
    private Double availableCpu;
    private Double availableMem;
    private Integer availableDisk;
    private String state;

    public HostInfo() {
    }

    public HostInfo(String hostIp, String tor, String idc, String dcn, Integer envId, String clusterName, String clusterVersion,
                    Double allCpu, Double allMem, Integer allDisk, Double availableCpu, Double availableMem,
                    Integer availableDisk, String state) {
        this.hostIp = hostIp;
        this.tor = tor;
        this.idc = idc;
        this.dcn = dcn;
        this.envId = envId;
        this.clusterId = clusterName;
        this.clusterVersion = clusterVersion;
        this.allCpu = allCpu;
        this.allMem = allMem;
        this.allDisk = allDisk;
        this.availableCpu = availableCpu;
        this.availableMem = availableMem;
        this.availableDisk = availableDisk;
        this.state = state;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getTor() {
        return tor;
    }

    public void setTor(String tor) {
        this.tor = tor;
    }

    public String getIdc() {
        return idc == null ? null : idc.toUpperCase();
    }

    public void setIdc(String idc) {
        this.idc = idc;
    }

    public String getDcn() {
        return dcn == null ? null : dcn.toUpperCase();
    }

    public void setDcn(String dcn) {
        this.dcn = dcn;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getClusterVersion() {
        return clusterVersion;
    }

    public void setClusterVersion(String clusterVersion) {
        this.clusterVersion = clusterVersion;
    }

    public Integer getEnvId() {
        return envId;
    }

    public void setEnvId(Integer envId) {
        this.envId = envId;
    }

    public Double getAllCpu() {
        return allCpu;
    }

    public void setAllCpu(Double allCpu) {
        this.allCpu = allCpu;
    }

    public Double getAllMem() {
        return allMem;
    }

    public void setAllMem(Double allMem) {
        this.allMem = allMem;
    }

    public Double getAvailableCpu() {
        return availableCpu;
    }

    public void setAvailableCpu(Double availableCpu) {
        this.availableCpu = availableCpu;
    }

    public Double getAvailableMem() {
        return availableMem;
    }

    public void setAvailableMem(Double availableMem) {
        this.availableMem = availableMem;
    }

    public Integer getAllDisk() {
        return allDisk;
    }

    public void setAllDisk(Integer allDisk) {
        this.allDisk = allDisk;
    }


    public Integer getAvailableDisk() {
        return availableDisk;
    }

    public void setAvailableDisk(Integer availableDisk) {
        this.availableDisk = availableDisk;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWaTor() {
        return waTor;
    }

    public void setWaTor(String waTor) {
        this.waTor = waTor;
    }

    @Override
    public HostInfo clone() throws CloneNotSupportedException {
        return (HostInfo) super.clone();
    }
}
