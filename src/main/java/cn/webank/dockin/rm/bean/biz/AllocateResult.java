package cn.webank.dockin.rm.bean.biz;


import cn.webank.dockin.rm.database.dto.PodInfo;

import java.util.List;


public class AllocateResult {
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_RESOURCE_NOT_ENOUGH = 1;
    public static final int RESULT_EXCEPTION = -1;


    private int result;


    private String message;


    private List<PodInfo> instances;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PodInfo> getInstances() {
        return instances;
    }

    public void setInstances(List<PodInfo> instances) {
        this.instances = instances;
    }

    @Override
    public String toString() {
        return "AllocateResult{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", instances=" + instances +
                '}';
    }
}
