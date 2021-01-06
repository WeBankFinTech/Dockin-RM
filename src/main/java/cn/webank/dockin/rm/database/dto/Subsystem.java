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

import java.io.Serializable;


public class Subsystem implements Serializable {

    private static final long serialVersionUID = 1564480690777L;


    
    private String subsystemName;

    
    private String importantLevel;

    
    private String subsystemId;

    
    private String systemName;

    
    private String logicArea;

    
    private String devLanguage;

    
    private String busiResDept;

    
    private String proOperGroup;

    public String getSubsystemName() {
        return subsystemName == null ? null : subsystemName.toLowerCase();
    }

    public void setSubsystemName(String subsystemName) {
        this.subsystemName = subsystemName;
    }

    public String getImportantLevel() {
        return importantLevel;
    }

    public void setImportantLevel(String importantLevel) {
        this.importantLevel = importantLevel;
    }

    public String getSubsystemId() {
        return subsystemId;
    }

    public void setSubsystemId(String subsystemId) {
        this.subsystemId = subsystemId;
    }

    public String getSystemName() {
        return systemName == null ? null : systemName.toLowerCase();
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getLogicArea() {
        return logicArea;
    }

    public void setLogicArea(String logicArea) {
        this.logicArea = logicArea;
    }

    public String getDevLanguage() {
        return devLanguage;
    }

    public void setDevLanguage(String devLanguage) {
        this.devLanguage = devLanguage;
    }

    public String getBusiResDept() {
        return busiResDept;
    }

    public void setBusiResDept(String busiResDept) {
        this.busiResDept = busiResDept;
    }

    public String getProOperGroup() {
        return proOperGroup;
    }

    public void setProOperGroup(String proOperGroup) {
        this.proOperGroup = proOperGroup;
    }

    @Override
    public String toString() {
        return "Subsystem{" +
                "subsystemName='" + subsystemName + '\'' +
                ", importantLevel='" + importantLevel + '\'' +
                ", subsystemId='" + subsystemId + '\'' +
                ", systemName='" + systemName + '\'' +
                ", logicArea='" + logicArea + '\'' +
                ", devLanguage='" + devLanguage + '\'' +
                ", busiResDept='" + busiResDept + '\'' +
                ", proOperGroup='" + proOperGroup + '\'' +
                '}';
    }
}
