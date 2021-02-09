



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


import cn.webank.dockin.rm.common.Constants;
import cn.webank.dockin.rm.web.bean.BaseDTO;

import java.beans.Transient;

public class ResultDto<T> extends BaseDTO {
    private int code = Constants.FAIL;
    private String message;
    private T data;
    private String reqId;

    public ResultDto() {
    }

    public static ResultDto newDefaultFailedResult(String message) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(Constants.FAIL);
        resultDto.setMessage(message);

        return resultDto;
    }

    public static ResultDto newDefaultFailedResult() {
        return newDefaultFailedResult("unknow error");
    }

    public static ResultDto newDefaultSuccessResult(String message) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(Constants.SUCCESS);
        resultDto.setMessage(message);

        return resultDto;
    }

    public static ResultDto newSuccessResult(String message, Object data) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(Constants.SUCCESS);
        resultDto.setData(data);
        resultDto.setMessage(message);

        return resultDto;
    }

    public static ResultDto newDefaultSuccessResult() {
        return newDefaultSuccessResult("success");
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    @Override
    public String toString() {
        return "ResultDto{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", reqId='" + reqId + '\'' +
                '}';
    }

    @Transient
    public boolean isFailed() {
        return this.code == Constants.FAIL;
    }
}
