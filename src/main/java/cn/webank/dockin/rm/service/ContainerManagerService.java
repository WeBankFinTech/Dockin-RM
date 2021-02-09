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
