package cn.webank.dockin.rm.database.dto;


public class OperateRecord {
    private String itsmId;
    private String state;
    private String result;

    public String getItsmId() {
        return itsmId;
    }

    public void setItsmId(String itsmId) {
        this.itsmId = itsmId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "OperateRecord{" +
                "itsmId='" + itsmId + '\'' +
                ", state='" + state + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
