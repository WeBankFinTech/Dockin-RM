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

package cn.webank.dockin.rm.utils;

import cn.webank.dockin.rm.web.bean.MonitorKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


public class StringFormatterTest {
    Logger logger = LoggerFactory.getLogger(StringFormatterTest.class);


    @Test
    public void test() {
        double d = StringFormatter.parseNumberFromPercentage("121%");
        assert d == 1.21d;

        d = StringFormatter.parseNumberFromPercentage("2.1%");
        assert d == 0.021d;

        assert d != 1.21d;
    }

    @Test
    public void testFormat() {
        String formatMonitorMsg = String.format(MonitorKeys.REQUEST_SUCCESS_TEMPLATE,
                "callerClassName", "callerMethodName", "null",
                "" + "responseTime", "resultFlag", null, null, null, null);

        System.out.println(formatMonitorMsg);
    }

}
