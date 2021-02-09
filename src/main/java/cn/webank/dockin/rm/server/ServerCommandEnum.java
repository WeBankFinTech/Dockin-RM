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
package cn.webank.dockin.rm.server;
public enum ServerCommandEnum {
    START("start"), STOP("stop"), HELP("help");
    private String value;
    ServerCommandEnum(String value) {
        this.value = value;
    }
    public static ServerCommandEnum getServerCommandEnum(String value) {
        for (ServerCommandEnum e : values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException(value + " is not a valid value");
    }
    public String getValue() {
        return this.value;
    }
}
