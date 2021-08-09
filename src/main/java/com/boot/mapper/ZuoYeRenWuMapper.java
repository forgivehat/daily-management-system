package com.boot.mapper;

import com.boot.entity.QianDaoRenWu;
import com.boot.entity.ZuoYeRenWu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author weish
 * @create 2021/5/8 22:58
 */
@Mapper
@Component("zuoYeRenWuMapper")
public interface ZuoYeRenWuMapper {
    //老师添加作业任务
    int insertZyRw(ZuoYeRenWu zuoYeRenWu);

    //老师根据用户名查询作业任务历史记录
    List<ZuoYeRenWu> selectZyRwListByLaoShiUsername(String ls_acc_username);

    List<ZuoYeRenWu> selectZyRwListByClassName(String className);

    ZuoYeRenWu selectZyRwById(Integer zy_rw_id);

}


