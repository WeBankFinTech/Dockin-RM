package cn.webank.dockin.rm.service.impl;
import cn.webank.dockin.rm.autoconfigure.ReloadRMConfiguration;
import cn.webank.dockin.rm.server.DockinRMServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;
@SpringBootTest(classes = {DockinRMServer.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Transactional("transactionManager")
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@DirtiesContext
public class PersistenceServiceImplTest {
    @Autowired
    ReloadRMConfiguration reloadRMConfiguration;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    public void testGetCpu() throws Exception {
        assert reloadRMConfiguration.getContent().getCpuExceededPercentage() == 1.2d;
    }
}
