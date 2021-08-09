package com.boot.mapper;

import com.boot.entity.QianDao;
import com.boot.entity.ZuoYe;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author weish
 * @create 2021/5/10 15:58
 */
@Mapper
@Component("zuoYeMapper")
public interface ZuoYeMapper {

    //学生提交作业
    int insertZy(ZuoYe zuoYe);

    //用于老师给作业打分
    int updateZy(ZuoYe zuoYe);

    //老师根据作业任务id查询学生提交的作业
    List<ZuoYe> selectZyListByZyRwId(Integer id);

    //只用于防止学生重复提交的逻辑判断
    ZuoYe selectZyByZyRwIdAndUsername(@Param("username") String xs_username
            ,@Param("id") Integer zy_rw_id);

    //学生查询自己的作业历史
    List<ZuoYe> selectZyListByXsUsername(@Param("username")String xs_acc_username);

    //根据作业id查看提交作业详情
    ZuoYe selectZyById(Integer id);
}

