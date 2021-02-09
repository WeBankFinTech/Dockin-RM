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
import cn.webank.dockin.rm.bean.biz.AllocateResult;
import cn.webank.dockin.rm.bean.resource.ContainerResource;
import cn.webank.dockin.rm.database.dto.HostInfo;
import cn.webank.dockin.rm.database.dto.PodInfo;
import java.util.List;
import java.util.Set;
public interface AllocateContainerService {
    AllocateResult allocateCrossDcn(String idc, String dcn, String subsystem, ContainerResource containerResource, PodInfo podInfo, int requestNum, String clusterId, Set<String> podSetIds);
    void allocate(String subsystem, String dcn, ContainerResource containerResource, PodInfo podInfo, int requestNum);
    void rollbackAllocate(PodInfo podInfo) throws Exception;
    AllocateResult allocateCrossDcn(List<AddInstanceDTO> addInstances);
    List<HostInfo> selectHostsCrossDcn(String idc, String dcn, ContainerResource containerResource) throws Exception;
}
