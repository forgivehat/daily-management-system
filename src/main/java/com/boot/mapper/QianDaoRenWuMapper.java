package com.boot.mapper;

import com.boot.entity.QianDaoRenWu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author weish
 * @create 2021/5/4 0:07
 */
@Mapper
@Component("qianDaoRenWuMapper")
public interface QianDaoRenWuMapper {

    //老师添加签到任务
    int insertQdRw(QianDaoRenWu qianDaoRenWu);

    //根据老师的用户名查询签到任务
    List<QianDaoRenWu> selectQdRwListByLaoShiUsername(String ls_acc_username);

    List<QianDaoRenWu> selectQdRwListByClassName(String className);

    QianDaoRenWu selectQdRwById(Integer qd_rw_id);
}
