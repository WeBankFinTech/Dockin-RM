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
package cn.webank.dockin.rm.bean.biz;
import cn.webank.dockin.rm.bean.biz.BaseDTO;
import java.util.List;
public class RemoveInstanceDTO extends BaseDTO {
    private String itsmId;
    private String people;
    private String subsystem;
    private List<String> ipList;
    private List<String> podNameList;
    public List<String> getPodNameList() {
        return podNameList;
    }
    public void setPodNameList(List<String> podNameList) {
        this.podNameList = podNameList;
    }
    public String getItsmId() {
        return itsmId;
    }
    public void setItsmId(String itsmId) {
        this.itsmId = itsmId;
    }
    public String getPeople() {
        return people;
    }
    public void setPeople(String people) {
        this.people = people;
    }
    public String getSubsystem() {
        return subsystem;
    }
    public void setSubsystem(String subsystem) {
        this.subsystem = subsystem;
    }
    public List<String> getIpList() {
        return ipList;
    }
    public void setIpList(List<String> ipList) {
        this.ipList = ipList;
    }
}