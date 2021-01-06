package cn.webank.dockin.rm.bean.pod;

public enum PodStatus {

    OFFLINE("OFFLINE"),

    GOING_OFFLINE("GOING_OFFLINE"),

    ALLOCATED("ALLOCATED");

    private final String name;

    PodStatus(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "PodStatus{" +
                "name='" + name + '\'' +
                '}';
    }
}
