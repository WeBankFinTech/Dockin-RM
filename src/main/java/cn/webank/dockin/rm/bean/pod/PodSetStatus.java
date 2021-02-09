package cn.webank.dockin.rm.bean.pod;

public enum PodSetStatus {

    BINDING("BINDING"),


    FREE("FREE"),


    BOUND("BOUND");

    private final String name;

    PodSetStatus(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "HaType{" +
                "name='" + name + '\'' +
                '}';
    }
}
