package cn.webank.dockin.rm.bean.biz;

import cn.webank.dockin.rm.bean.pod.PodExpiryType;

import java.util.Set;


public class AddInstanceDTO {
    private String itsmId;
    private String people;
    private String subsystem;
    private String subsystemId;
    private String dcn;
    private String idc;


    private Integer num;
    private Double cpu;
    private Double memory;
    private Integer disk;
    private String type;
    private Integer port;
    private Integer jmxPort;
    private String tag;
    private String namespace;


    private String hostIp;


    private String clusterId;


    private Set<String> podSetIds;

    private PodExpiryType expiryType = PodExpiryType.LONG_TERM;


    public Set<String> getPodSetIds() {
        return podSetIds;
    }

    public void setPodSetIds(Set<String> podSetIds) {
        this.podSetIds = podSetIds;
    }

    public String getSubsystemId() {
        return subsystemId;
    }

    public void setSubsystemId(String subsystemId) {
        this.subsystemId = subsystemId;
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
        return subsystem == null ? null : subsystem.trim().toLowerCase();
    }

    public void setSubsystem(String subsystem) {
        this.subsystem = subsystem;
    }

    public String getDcn() {
        return dcn == null ? null : dcn.toUpperCase();
    }

    public void setDcn(String dcn) {
        this.dcn = dcn;
    }

    public String getIdc() {
        return idc;
    }

    public void setIdc(String idc) {
        this.idc = idc;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getCpu() {
        return cpu;
    }

    public void setCpu(Double cpu) {
        this.cpu = cpu;
    }

    public Double getMemory() {
        return memory;
    }

    public void setMemory(Double memory) {
        this.memory = memory;
    }

    public Integer getDisk() {
        return disk;
    }

    public void setDisk(Integer disk) {
        this.disk = disk;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getJmxPort() {
        return jmxPort;
    }

    public void setJmxPort(Integer jmxPort) {
        this.jmxPort = jmxPort;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public PodExpiryType getExpiryType() {
        return expiryType;
    }

    public void setExpiryType(PodExpiryType expiryType) {
        this.expiryType = expiryType;
    }

    @Override
    public String toString() {
        return "AddInstanceDTO{" +
                "itsmId='" + itsmId + '\'' +
                ", people='" + people + '\'' +
                ", subsystem='" + subsystem + '\'' +
                ", subsystemId='" + subsystemId + '\'' +
                ", dcn='" + dcn + '\'' +
                ", idc='" + idc + '\'' +
                ", num=" + num +
                ", cpu=" + cpu +
                ", memory=" + memory +
                ", disk=" + disk +
                ", type='" + type + '\'' +
                ", port=" + port +
                ", jmxPort=" + jmxPort +
                ", tag='" + tag + '\'' +
                ", namespace='" + namespace + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", clusterId='" + clusterId + '\'' +
                ", podSetIds=" + podSetIds +
                ", expiryType='" + expiryType + '\'' +
                '}';
    }
}