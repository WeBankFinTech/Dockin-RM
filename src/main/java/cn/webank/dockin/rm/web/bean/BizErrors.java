



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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BizErrors extends BaseDTO {

    private final List<BizError> errors = new LinkedList<BizError>();

    public void reject(String errorCode, Object[] errorArgs, String defaultMessage) {
        addError(new BizError(errorCode, errorArgs, defaultMessage));
    }

    public void addError(BizError error) {
        this.errors.add(error);
    }

    public void addAllErrors(BizErrors errors) {
        this.errors.addAll(errors.getAllErrors());
    }

    public boolean hasErrors() {
        return !this.errors.isEmpty();
    }

    public int getErrorCount() {
        return this.errors.size();
    }

    public List<BizError> getAllErrors() {
        return Collections.unmodifiableList(this.errors);
    }

}
