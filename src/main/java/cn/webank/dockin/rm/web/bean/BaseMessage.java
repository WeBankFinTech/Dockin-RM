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
public class BaseMessage extends BaseDTO {
    private String code = ResponseStatus.SUCCESS.getCode();
    private String msg = "请求成功";
        private String bizSeqNo;
        private String debugMsg;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public void setResponseStatus(ResponseStatus status) {
        this.code = status.getCode();
        this.msg = status.getMessage();
    }
    public String getBizSeqNo() {
        return bizSeqNo;
    }
    public void setBizSeqNo(String bizSeqNo) {
        this.bizSeqNo = bizSeqNo;
    }
    public String getDebugMsg() {
        return debugMsg;
    }
    public void setDebugMsg(String debugMsg) {
        this.debugMsg = debugMsg;
    }
}
