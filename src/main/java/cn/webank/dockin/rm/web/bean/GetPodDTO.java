



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

package cn.webank.dockin.rm.web.bean;

public class GetPodDTO extends RequestDTO {
    private String podName;
    private String podSetId;
    private String podIp;
    private String hostIp;
    private String subsystem;
    private String dcn;

    public String getPodName() {
        return podName;
    }

    public void setPodName(String podName) {
        this.podName = podName;
    }

    public String getPodSetId() {
        return podSetId;
    }

    public void setPodSetId(String podSetId) {
        this.podSetId = podSetId;
    }

    public String getPodIp() {
        return podIp;
    }

    public void setPodIp(String podIp) {
        this.podIp = podIp;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getSubsystem() {
        return subsystem;
    }

    public void setSubsystem(String subsystem) {
        this.subsystem = subsystem;
    }

    public String getDcn() {
        return dcn;
    }

    public void setDcn(String dcn) {
        this.dcn = dcn;
    }

    @Override
    public String toString() {
        return "GetPodDTO{" +
                "podName='" + podName + '\'' +
                ", podSetId='" + podSetId + '\'' +
                ", podIp='" + podIp + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", subsystem='" + subsystem + '\'' +
                ", dcn='" + dcn + '\'' +
                '}';
    }
}
