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

import cn.webank.dockin.rm.bean.biz.ResultDto;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

public class RmUtil {
    public static boolean mockTest = false;

    public static boolean isMockTest() {
        return mockTest;
    }

    public static void appendResultMsg(ResultDto resultDto, String msg) {
        Assert.notNull(resultDto, "resultDto can not be null");
        StringBuilder sb = new StringBuilder(msg);
        if (resultDto.getMessage() != null) {
            sb.append(";");
            sb.append(resultDto.getMessage());
        }

        resultDto.setMessage(sb.toString());
    }

    public static String getIP(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");
        String ip = null;
        if (realIp == null) {
            if (forwarded == null) {
                ip = remoteAddr;
            } else {
                ip = remoteAddr + "/" + forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ip = realIp;
            } else {
                if (forwarded != null) {
                    forwarded = forwarded.split(",")[0];
                }
                ip = realIp + "/" + forwarded;
            }
        }
        return ip;
    }
}
