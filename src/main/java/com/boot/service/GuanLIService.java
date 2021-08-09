package com.boot.service;

import com.boot.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author weish
 * @create 2021/5/7 20:20
 */
public interface GuanLIService {

    GuanLi findGuanLiByUsername(String username);

    List<JiaTiao> findJtListByDate( Date startTime, Date endTime,  String state,String dep,String cls);

    Integer CountNumberOfQingJiaXs(Date startTime, Date endTime,  String state,String dep,String cls);

    List<QianDaoRenWu> findQdRwListByClassName(String className);
    QianDaoRenWu findQdRwById(Integer id);
    List<QianDao> findQdListByQdRwId(Integer id);
}
