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

package cn.webank.dockin.rm.web.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public enum ResponseStatus {
    /* 一切正常 */
    SUCCESS("0", "请求成功"),
    /* Internal Server Error */
    /* Front Internal Server Error */
    FRONT_INTERNAL_SERVER_ERROR("999998", "系统异常，请您稍后再试"),
    REQUEST_UNSAFE("999997", "系统请求不安全"),
    RMB_REPLICATED_MESSAGE("-999999", "RMB replicated message");
    private static final Map<String, ResponseStatus> CACHE_MAP =
            new ConcurrentHashMap<String, ResponseStatus>();
    private static final Logger LOG = LoggerFactory.getLogger(ResponseStatus.class);
    private final static int MAX_CACHE_NUM = 500;
    private final static AtomicInteger cacheCount = new AtomicInteger(0);
    private String code;
    private String message;


    ResponseStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    
    public static ResponseStatus getByCode(String code) {
        ResponseStatus wopStatus = CACHE_MAP.get(code);
        if (wopStatus != null) {
            return wopStatus;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("cache map isn't cache code:" + code);
        }

        for (ResponseStatus w : values()) {
            if (w.getCode().equals(code)) {
                if (cacheCount.addAndGet(1) <= MAX_CACHE_NUM) {
                    CACHE_MAP.put(code, w);
                }
                return w;
            }
        }

        throw new IllegalArgumentException("code is error:" + code);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
