package com.boot.mapper;

import com.boot.entity.JiaTiao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author weish
 * @create 2021/4/30 23:54
 */
@Mapper
@Component(value = "jiaTiaoMapper")
public interface JiaTiaoMapper {

    //学生添加假条信息
    int insertJt(JiaTiao jiaTiao);

    //辅导员更新假条信息
    int updateJt(JiaTiao jiaTiao);

    //学生查看自己的请假记录
    List<JiaTiao> selectJtListByUsername(String xs_acc_username);

    JiaTiao selectJtByJtId(Integer id);

     //根据账户所属学院查询本学院学生的假条
    List<JiaTiao> selectJtListByDepartment(String department);

    //根据可选字段查询对应假条数据
    List<JiaTiao> selectJtListSelective(
            @Param("jt_start_time") Date startTime
            ,@Param("jt_end_time")Date endTime
            ,@Param("jt_state") String state
            ,@Param("department") String department
            ,@Param("className") String className);

    //管理人员查某时段请假人数
   Integer CountQingJiaXs(@Param("jt_start_time") Date startTime
            ,@Param("jt_end_time")Date endTime
            ,@Param("jt_state") String state
            ,@Param("department") String department
           ,@Param("className") String className);
}
