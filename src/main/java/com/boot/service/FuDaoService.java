package com.boot.service;

import com.boot.entity.FuDao;
import com.boot.entity.JiaTiao;
import com.boot.entity.QianDao;
import com.boot.entity.QianDaoRenWu;

import java.util.Date;
import java.util.List;

/**
 * @author weish
 * @create 2021/5/1 0:05
 */
public interface FuDaoService {
    FuDao findFuDaoByUsername(String username);

    List<JiaTiao> findJiaTiaoByDepartment(String department);

    JiaTiao findJtById(Integer id);

    Boolean editJiaTiao(JiaTiao jiaTiao); //修改假条

    List<JiaTiao> findJiaTiaoBySelectiveParam(Date start, Date end, String state, String dep, String cls);

    List<QianDaoRenWu> findQdRwListByClassName(String className);

    List<QianDao> findQdListByQdRwId(Integer id);
}
