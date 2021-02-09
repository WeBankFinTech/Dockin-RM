package cn.webank.dockin.rm.database.dao;

import cn.webank.dockin.rm.database.dto.OperateRecord;
import org.apache.ibatis.annotations.Param;


public interface OperateRecordDAO {


    int insert(@Param("itsmId") String itsmId, @Param("detail") String detail, @Param("state") String state) throws
            Exception;


    int update(@Param("itsmId") String itsmId, @Param("state") String state) throws Exception;


    String getState(@Param("itsmId") String itsmId) throws Exception;


    OperateRecord get(@Param("itsmId") String itsmId) throws Exception;


    int updateInfo(OperateRecord operateRecord) throws Exception;
}
