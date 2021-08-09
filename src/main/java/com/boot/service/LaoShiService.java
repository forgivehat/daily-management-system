package com.boot.service;


import com.boot.entity.*;

import java.util.Date;
import java.util.List;

/**
 * @author weish
 * @create 2021/5/3 23:47
 */
public interface LaoShiService {
     LaoShi findLaoShiByUsername(String username);


     Boolean addQdRw(QianDaoRenWu qianDaoRenWu);
     List<QianDaoRenWu> findQdRwListByUsername(String ls_username);
     QianDaoRenWu findQdRwById(Integer qd_rw_id);
     List<QianDao> findQdListByQdRwId(Integer id);          //根据签到任务id查询签到学生的签到信息列表

     List<JiaTiao> findJtBySelectiveParam(Date start,Date end,String state,String dep,String className);

     Boolean addZyRw(ZuoYeRenWu zuoYeRenWu);

     List<ZuoYeRenWu> findZyRwListByUsername(String username);

     List<ZuoYe> findZyListByZyRwId(Integer id);

     Boolean editZuoYe(ZuoYe zuoYe);
}
