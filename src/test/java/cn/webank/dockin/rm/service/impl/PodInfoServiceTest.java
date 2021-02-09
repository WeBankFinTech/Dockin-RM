package cn.webank.dockin.rm.service.impl;
import cn.webank.dockin.rm.database.dao.HostInfoDAO;
import cn.webank.dockin.rm.database.dao.PodInfoDAO;
import cn.webank.dockin.rm.database.dto.HostInfo;
import cn.webank.dockin.rm.database.dto.PodInfo;
import cn.webank.dockin.rm.server.DockinRMServer;
import cn.webank.dockin.rm.service.PodInfoService;
import cn.webank.dockin.rm.service.PodSetService;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
@SpringBootTest(classes = {DockinRMServer.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Transactional("transactionManager")
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@DirtiesContext
public class PodInfoServiceTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    PodInfoService podInfoService;
    @Autowired
    PodInfoDAO podInfoDAO;
    @Autowired
    HostInfoDAO hostInfoDAO;
    @Autowired
    PodSetService podSetService;
    public void getPodInfoByPodSetId() throws Exception {
        HostInfo hostInfo = new HostInfo();
        hostInfo.setHostIp("192.168.96.21");
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
        hostInfo.setClusterId("xl01");
        hostInfo.setClusterVersion("1");
        hostInfoDAO.insertHostInfo(hostInfo);
        PodInfo podInfoDTO = new PodInfo("Dockin-query-192.168-34-46", "1111-0",
                "Dockin-query", "1111", "101", "192.168.34.46", "192.168.34.1",
                "255.255.255.0", "192.168.96.21", 2, 4, 2, 4,
                100, "10000001", "alanwwu", "JAVA", 36000, 8080,
                "A-TAG", "tctp", 1, "aaa", "ALLOCATED");
        Assert.assertEquals(podInfoDAO.insert(podInfoDTO), 1);
        Assert.assertEquals(podInfoService.getPodInfoByPodSetId("1111-0").size(), 1);
        Assert.assertEquals(podInfoService.getPodInfoByPodSetId("Dockin-query-0").size(), 1);
        podSetService.bindPod("1111-1", podInfoDTO.getPodName());
    }
}
