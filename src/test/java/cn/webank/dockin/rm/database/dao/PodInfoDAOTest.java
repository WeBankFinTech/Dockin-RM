/*
 * Copyright (C) @2020 Webank Group Holding Limited
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package cn.webank.dockin.rm.database.dao;

import cn.webank.dockin.rm.bean.PageInfo;
import cn.webank.dockin.rm.bean.pod.PodStatus;
import cn.webank.dockin.rm.server.DockinRMServer;
import cn.webank.dockin.rm.database.dto.PodInfo;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static cn.webank.dockin.rm.common.Constants.POD_OFFLINE;

@SpringBootTest(classes = {DockinRMServer.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Transactional("transactionManager")
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@DirtiesContext
public class PodInfoDAOTest {
    @Autowired
    private PodInfoDAO podInfoDAO;

//    @Test
    public void testGetPodList() throws Exception {
        List<PodInfo> list = podInfoDAO.getPodInfoList(1, 10);
        System.out.println(list.size());
    }

//    @Test
    public void testAll() {
        try {
            PodInfo podInfoDTO = new PodInfo("dockin-1", "1001-0", "umg-core", "1001", "101", "192.168.34.46", "192.168.34.1", "255.255.255.0", "192.168.96.21", 2, 4, 2, 4, 100, "100000001", "alanwwu", "JAVA", 36000, 8080, "A-TAG", "tctp", 1, "aaa", "ALLOCATED");
            podInfoDAO.insert(podInfoDTO);
            Assert.assertEquals(podInfoDAO.getPodInfoByPodName("dockin-1"), podInfoDTO);
            assert podInfoDAO.getPodsCountByClusterId("aaa") > 0;

            Assert.assertEquals(1, podInfoDAO.getByPage(podInfoDTO, new PageInfo()).size());

            List<String> podNames = Collections.singletonList(podInfoDTO.getPodName());
            List<PodInfo> podinfos = podInfoDAO.getPodInfosByPodNameList(podNames);

            Assert.assertNotEquals(podInfoDAO.getByUpdateTime(new Date(System.currentTimeMillis() - 10 * 1000), new Date()), 0);

            assert podinfos.size() != 0;

            Set<String> sys = podInfoDAO.getAllSubSystem();
            assert sys.size() > 0;
            sys = podInfoDAO.getAllSubSystemId();

            Assert.assertTrue(sys.size() > 0);
            podinfos = podInfoDAO.getAllocatedInstanceBySubsystemAndDcn("umg-core", "101");
            assert podinfos.size() == 1;

            assert podInfoDAO.getPodInfoByPodIp("192.168.34.46").equals(podInfoDTO);
            assert podInfoDAO.getPodInfoBySubsystem("umg-core").get(0).equals(podInfoDTO);
            assert podInfoDAO.getPodInfoBySubsystem("1001").get(0).equals(podInfoDTO);
            assert podInfoDAO.getPodIpListBySubsystem("umg-core").get(0).equals("192.168.34.46");
            Assert.assertEquals(0, podInfoDAO.getPodNameListBySubsystemIdWithoutSetId("1001").size());
            assert podInfoDAO.getPodIpList().contains("192.168.34.46");

            assert podInfoDAO.getPodIpListByItsmId("100000001").get(0).equals("192.168.34.46");

            assert podInfoDAO.getPodListByItsmId("100000001").get(0).equals(podInfoDTO);
            Assert.assertEquals(podInfoDAO.getPodListByHostIp("192.168.96.21").get(0).getPodIp(), "192.168.34.46");
            assert podInfoDAO.getHostIp("192.168.34.46").equals("192.168.96.21");
            assert podInfoDAO.recyclePodInfo("192.168.34.46", "10000002") == 1;
            assert podInfoDAO.getPodIpListByOfflineItsmId("10000002").get(0).equals("192.168.34.46");
//            assert podInfoDAO.updatePodInfo("dockin-1", "umg-core", "101", "192.168.34.46", "192.168.34.1", "255.255.255.0", "192.168.96.21", 2, 4, 100, "100000001", "alanwwu", "JAVA", 36000, 8080,"B", "tctp", "ALLOCATED") == 1;
            assert podInfoDAO.updatePodInfo(podInfoDTO) == 1;

            podInfoDTO.setState(POD_OFFLINE);
            podInfoDTO.setOfflineItsmId("offlineitsmid");
            Assert.assertEquals(podInfoDAO.updatePodInfo(podInfoDTO), 1);
            Assert.assertEquals(podInfoDAO.getPodListByOfflineItsmId(podInfoDTO.getOfflineItsmId()).get(0).getPodIp(), "192.168.34.46");

            assert podInfoDAO.getPodInfosByPodNameList(podNames).size() == 0;
            assert podInfoDAO.getPodInfosByPodNameListIgnoreStatus(podNames).size() == 1;

            assert podInfoDAO.changeStatus(podInfoDTO.getPodName(), PodStatus.OFFLINE.getName(), PodStatus.ALLOCATED.getName()) == 1;
            assert podInfoDAO.batchChangeStatus(Collections.singleton(podInfoDTO.getPodName()), PodStatus.ALLOCATED.getName(), PodStatus.OFFLINE.getName()) == 1;
            assert podInfoDAO.changeStatus(podInfoDTO.getPodName(), PodStatus.OFFLINE.getName(), PodStatus.ALLOCATED.getName()) == 1;

            assert podInfoDAO.removePodByIp("192.168.34.46") == 1;
            assert podInfoDAO.getPodListByHostIp("192.168.96.21").size() == 0;

            podinfos = podInfoDAO.getAllocatedInstanceBySubsystemAndDcn("umg-core", "101");
            assert podinfos.size() == 0;

            podInfoDAO.updatePodSubsystemNames();
            podInfoDAO.updatePodNamespace();

            podInfoDAO.removeAllBySubsystem("umg-core");
        } catch (Exception e) {
            assert false;
        }
    }


}