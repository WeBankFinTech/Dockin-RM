



/*
 * Copyright (C) @2021 Webank Group Holding Limited
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

package cn.webank.dockin.rm.web.bean;

public class MonitorKeys {



    public static final String APP_MONITOR_LOGGER = "appMonitor";

    public static final String REQUEST_SUCCESS_TEMPLATE =
            "{"
                    + "\"s\":\"%s.%s\""
                    + ",\"b\":\"%s\""
                    + ",\"t\":\"%s\""
                    + ",\"r\":\"%s\""
                    + ",\"bizRetCode\":\"%s\""
                    + ",\"retErrMsg\":\"%s\""
                    + ",\"appId\":\"%s\""
                    + ",\"channel\":\"%s\""
                    + "}";

    public static final String SUCCESS_FLAG = "0";

    public static final String SYS_FAIL_FLAG = "1";

    public static final String BIZ_FAIL_FLAG = "2";

}
