package cn.webank.dockin.rm.database.dao;
import cn.webank.dockin.rm.bean.PageInfo;
import cn.webank.dockin.rm.database.dto.Subsystem;
import cn.webank.dockin.rm.server.DockinRMServer;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.sql.SQLException;
import java.util.List;
@SpringBootTest(classes = {DockinRMServer.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Transactional("transactionManager")
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@DirtiesContext
public class SubsystemDAOTest {
    @Autowired
    private SubsystemDAO subsystemDAO;
    public void testAll() throws SQLException {
        Subsystem subsystem = new Subsystem();
        subsystem.setSystemName("Dockin");
        subsystem.setSubsystemName("Dockin-QUERY");
        subsystem.setSubsystemId("1111");
        subsystem.setProOperGroup("dockin");
        subsystemDAO.insert(subsystem);
        subsystemDAO.getByPage(subsystem, new PageInfo());
        Subsystem querySubsystem = subsystemDAO.getBySubsystemName(subsystem.getSubsystemName());
        Assert.assertEquals(querySubsystem.getSystemName(), subsystem.getSystemName());
        Assert.assertEquals(querySubsystem.getSubsystemId(), subsystem.getSubsystemId());
        querySubsystem = subsystemDAO.getBySubsystemId(subsystem.getSubsystemId());
        Assert.assertEquals(querySubsystem.getSystemName(), subsystem.getSystemName());
        Assert.assertEquals(querySubsystem.getSubsystemId(), subsystem.getSubsystemId());
        subsystem.setSubsystemId("5002");
        subsystemDAO.update(subsystem);
    }
    public void testBatchInsert() throws SQLException {
        List<Subsystem> sys = Lists.newArrayList();
        Subsystem subsystem = new Subsystem();
        subsystem.setSystemName("Dockin");
        subsystem.setSubsystemName("Dockin-QUERY");
        subsystem.setSubsystemId("1111");
        subsystem.setLogicArea("aaaab");
        sys.add(subsystem);
        subsystem = new Subsystem();
        subsystem.setSystemName("Dockin");
        subsystem.setSubsystemName("Dockin-VERIFY");
        subsystem.setSubsystemId("5037");
        subsystem.setSystemName("UDockin");
        sys.add(subsystem);
        subsystemDAO.batchInsert(sys);
    }
}