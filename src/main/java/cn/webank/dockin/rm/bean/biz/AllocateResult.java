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

package cn.webank.dockin.rm.bean.biz;


import cn.webank.dockin.rm.database.dto.PodInfo;

import java.util.List;


public class AllocateResult {
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_RESOURCE_NOT_ENOUGH = 1;
    public static final int RESULT_EXCEPTION = -1;


    private int result;


    private String message;


    private List<PodInfo> instances;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PodInfo> getInstances() {
        return instances;
    }

    public void setInstances(List<PodInfo> instances) {
        this.instances = instances;
    }

    @Override
    public String toString() {
        return "AllocateResult{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", instances=" + instances +
                '}';
    }
}
