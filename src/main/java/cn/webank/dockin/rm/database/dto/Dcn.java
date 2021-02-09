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
public class Dcn {
    private String dcnId;
    private String orgId;
    private String idc;
    private String logicArea;
    private String description;
    public String getIdc() {
        return idc == null ? null : idc.toUpperCase();
    }
    public void setIdc(String idc) {
        this.idc = idc;
    }
    public String getLogicArea() {
        return logicArea;
    }
    public void setLogicArea(String logicArea) {
        this.logicArea = logicArea;
    }
    public String getDcnId() {
        return dcnId == null ? null : dcnId.toUpperCase();
    }
    public void setDcnId(String dcnId) {
        this.dcnId = dcnId;
    }
    public String getOrgId() {
        return orgId;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
