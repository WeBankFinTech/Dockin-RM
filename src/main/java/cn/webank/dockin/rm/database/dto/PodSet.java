package cn.webank.dockin.rm.database.dto;

public class PodSet {
    private String id;
    private String subsystemId;
    private String dcn;
    private int index;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubsystemId() {
        return subsystemId;
    }

    public void setSubsystemId(String subsystemId) {
        this.subsystemId = subsystemId;
    }

    public String getDcn() {
        return dcn;
    }

    public void setDcn(String dcn) {
        this.dcn = dcn;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
