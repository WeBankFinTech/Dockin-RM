package cn.webank.dockin.rm.database.dao;

import cn.webank.dockin.rm.database.dto.PodSet;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public interface PodSetDAO {

    int insert(PodSet podSet) throws Exception;

    PodSet getFirstPodSet(@Param("subsystemId") String subsystemId, @Param("dcn") String dcn, @Param("status") String status) throws SQLException;

    PodSet getById(String setId) throws SQLException;

    PodSet getLastPodSet(@Param("subsystemId") String subsystemId, @Param("dcn") String dcn) throws SQLException;

    List<PodSet> getBoundStatusButNonPod(@Param("subsystemId") String subsystemId) throws SQLException;

    List<PodSet> getFreeStatusButBoundPod(@Param("subsystemId") String subsystemId) throws SQLException;

    int changeStatus(@Param("setId") String setId, @Param("from") String from, @Param("to") String to) throws SQLException;

    int updateStatus(@Param("podSetList") List<PodSet> podSetList, @Param("status") String status) throws SQLException;

    int deletePodSet(@Param("setId") String setId, @Param("status") String status) throws SQLException;
}
