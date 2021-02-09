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