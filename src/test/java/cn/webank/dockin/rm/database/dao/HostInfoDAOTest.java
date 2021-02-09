package cn.webank.dockin.rm.database.dao;
import cn.webank.dockin.rm.bean.PageInfo;
import cn.webank.dockin.rm.server.DockinRMServer;
import cn.webank.dockin.rm.database.dto.HostInfo;
import cn.webank.dockin.rm.database.dto.PodInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.collections.Lists;
import java.util.Collections;
import java.util.Date;
import java.util.List;
@SpringBootTest(classes = {DockinRMServer.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Transactional("transactionManager")
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@DirtiesContext
public class HostInfoDAOTest {
    @Autowired
    private HostInfoDAO hostInfoDAO;
    @Autowired
    private PodInfoDAO podInfoDAO;
    public void testAll() throws Exception {
        HostInfo hostInfoDTO = new HostInfo("192.168.2.1", "sjkda", "FT", "TEDCN", 1, "1", "1", 10d, 10d, 10, 10d, 10d, 10, "available");
        hostInfoDAO.batchInsert(Collections.singletonList(hostInfoDTO));
        hostInfoDAO.batchInsert(Collections.singletonList(hostInfoDTO));
        Assert.assertEquals(hostInfoDAO.getByPage(hostInfoDTO, new PageInfo(1, 1)).size(), 1);
        Assert.assertEquals(hostInfoDAO.getByPage(hostInfoDTO, new PageInfo(10, 10)).size(), 0);
        HostInfo cHostInfo = hostInfoDTO.clone();
        cHostInfo.setTor(null);
        cHostInfo.setDcn(null);
        cHostInfo.setIdc(null);
        Assert.assertEquals(hostInfoDAO.getHosts(cHostInfo).size(), 1);
        HostInfo hostInfo = new HostInfo();
        hostInfo.setHostIp("192.168.2.1");
        hostInfo.setTor("tor1");
        hostInfo.setIdc("FT");
        hostInfo.setDcn("101");
        hostInfo.setEnvId(1);
        hostInfo.setAllCpu(100.1);
        hostInfo.setAllMem(100d);
        hostInfo.setAllDisk(100);
        hostInfo.setAvailableCpu(20d);
        hostInfo.setAvailableMem(20d);
        hostInfo.setAvailableDisk(20);
        hostInfo.setState("available");
        hostInfo.setClusterId("FT01");
        hostInfo.setClusterVersion("1");
        System.out.println(hostInfoDAO.insertHostInfo(hostInfoDTO));
        System.out.println(hostInfoDAO.insertHostInfo(hostInfoDTO));
        System.out.println(hostInfoDAO.getHostByHostIp("192.168.2.1"));
        Assert.assertEquals(hostInfoDAO.getHostByHostIp("192.168.2.1"), hostInfoDTO);
        System.out.println(hostInfoDAO.getTorByHostIp("192.168.2.1"));
        Assert.assertEquals(hostInfoDAO.getTorByHostIp("192.168.2.1"), "sjkda");
        Assert.assertEquals(hostInfoDAO.getClusterIdByHostIp("192.168.2.1"), "1");
        Assert.assertEquals(hostInfoDAO.getAvailableHostsByClusterId("1").get(0).getHostIp(), "192.168.2.1");
        System.out.println(hostInfoDAO.getTorByAvailableHostDcn("TEDCN"));
        Assert.assertEquals(hostInfoDAO.getTorByAvailableHostDcn("TEDCN"), Lists.newArrayList("sjkda"));
        System.out.println(hostInfoDAO.getAvailableHostByTorAndDcn("sjkda", "TEDCN"));
        Assert.assertEquals(hostInfoDAO.getAvailableHostByTorAndDcn("sjkda", "TEDCN"), Lists.newArrayList(hostInfoDTO));
        Assert.assertNotEquals(hostInfoDAO.getByUpdateTime(new Date(System.currentTimeMillis() - 10 * 1000), new Date()).size(), 0);
        PodInfo podInfoDTO = new PodInfo("dockin-1", "1001-0", "umg-core", "1001", "TEDCN", "192.168.34.46", "192.168.34.1", "255.255.255.0", "192.168.2.1", 2, 4, 2, 4, 100, "10000001", "alanwwu", "JAVA", 36000, 8080, "A-TAG", "tctp", 1, "aaa", "ALLOCATED");
        podInfoDAO.insert(podInfoDTO);
        PodInfo dbPod = podInfoDAO.getPodInfoByPodName("dockin-1");
        System.out.println(dbPod);
        assert dbPod.getTag().equals("A-TAG");
        assert hostInfoDAO.updateHostResource("192.168.2.1") == 1;
        System.out.println(hostInfoDAO.getHostByHostIp("192.168.2.1"));
        hostInfoDTO.setAvailableCpu(hostInfoDTO.getAvailableCpu() - podInfoDTO.getCpu());
        hostInfoDTO.setAvailableMem(hostInfoDTO.getAvailableMem() - podInfoDTO.getMem());
        hostInfoDTO.setAvailableDisk(hostInfoDTO.getAvailableDisk() - podInfoDTO.getDisk());
        Assert.assertEquals(hostInfoDAO.getHostByHostIp("192.168.2.1"), hostInfoDTO);
        HostInfo newHostInfoDTO = new HostInfo("192.168.2.2", "sjdhkaa", "FT", "TEDCN", 1, "1", "1", 10d, 10d, 10, 10d, 10d, 10, "available");
        assert hostInfoDAO.addHost(newHostInfoDTO) == 1;
        List<HostInfo> hosts = hostInfoDAO.getAllAvailableHostsByEnvIdAndIdcAndDcnInSameNetworkArea(1, "FT", "TEDCN");
        assert hosts.size() >= 1;
        Assert.assertEquals(hostInfoDAO.getHostByHostIp("192.168.2.2"), newHostInfoDTO);
        String newTor = "daskjdk";
        newHostInfoDTO.setTor(newTor);
        assert hostInfoDAO.updateHost(newHostInfoDTO) == 1;
        Assert.assertEquals(hostInfoDAO.getTorByHostIp("192.168.2.2"), newTor);
        assert hostInfoDAO.offlineHost("192.168.2.2") == 1;
    }
    public void testUpdateEnv() throws Exception {
        hostInfoDAO.updateEnvIdBaseOrganization();
        hostInfoDAO.updateEnvIdBaseDeployAreaByHostIp("1111");
        hostInfoDAO.updateEnvIdBaseOrganizationByHostIp("11111");
    }
}