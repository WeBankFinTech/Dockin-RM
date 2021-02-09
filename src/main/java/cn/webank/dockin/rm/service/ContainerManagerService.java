

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

package cn.webank.dockin.rm.service;


import cn.webank.dockin.rm.bean.biz.AddInstanceDTO;
import cn.webank.dockin.rm.bean.biz.RemoveInstanceDTO;
import cn.webank.dockin.rm.bean.biz.RequestType;
import cn.webank.dockin.rm.bean.biz.ResultDto;

import java.util.List;


public interface ContainerManagerService {

    
    ResultDto addContainerCrossDcn(List<AddInstanceDTO> addInstanceDTOs) throws Exception;

    
    boolean rollbackAddInstance(AddInstanceDTO addInstance);

    
    boolean rollbackAddInstance(String itsmId);

    
    boolean rollbackRemoveInstance(String itsmId) throws Exception;

    
    boolean removeInstances(RemoveInstanceDTO removeInstanceDTO) throws Exception;

    
    String preOperate(String reqId, String content, RequestType requestType) throws Exception;

    
    boolean postOperate(ResultDto result);
}
