

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

package cn.webank.dockin.rm.bean.cluster;

public class ClusterInfo {
    private String clusterId;
    private Integer node;
    private Integer notReadyNode;
    private Integer container;
    private Double cpu;
    private Double availableCpu;
    private Double memory;
    private Double availableMemory;


    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public Integer getNode() {
        return node;
    }

    public void setNode(Integer node) {
        this.node = node;
    }

    public Integer getNotReadyNode() {
        return notReadyNode;
    }

    public void setNotReadyNode(Integer notReadyNode) {
        this.notReadyNode = notReadyNode;
    }

    public Integer getContainer() {
        return container;
    }

    public void setContainer(Integer container) {
        this.container = container;
    }

    public Double getCpu() {
        return cpu;
    }

    public void setCpu(Double cpu) {
        this.cpu = cpu;
    }

    public Double getAvailableCpu() {
        return availableCpu;
    }

    public void setAvailableCpu(Double availableCpu) {
        this.availableCpu = availableCpu;
    }

    public Double getMemory() {
        return memory;
    }

    public void setMemory(Double memory) {
        this.memory = memory;
    }

    public Double getAvailableMemory() {
        return availableMemory;
    }

    public void setAvailableMemory(Double availableMemory) {
        this.availableMemory = availableMemory;
    }

    @Override
    public String toString() {
        return "ClusterInfo{" +
                "clusterId='" + clusterId + '\'' +
                ", node=" + node +
                ", notReadyNode=" + notReadyNode +
                ", container=" + container +
                ", cpu=" + cpu +
                ", availableCpu=" + availableCpu +
                ", memory=" + memory +
                ", availableMemory=" + availableMemory +
                '}';
    }
}
