package com.boot.service.impl;

import com.boot.entity.GuanLi;
import com.boot.entity.JiaTiao;
import com.boot.entity.QianDao;
import com.boot.entity.QianDaoRenWu;
import com.boot.mapper.GuanLiMapper;
import com.boot.mapper.JiaTiaoMapper;
import com.boot.mapper.QianDaoMapper;
import com.boot.mapper.QianDaoRenWuMapper;
import com.boot.service.GuanLIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author weish
 * @create 2021/5/7 20:23
 */
@Service("guanLiService")
public class GuanLIServiceImpl implements GuanLIService {
    @Autowired
    JiaTiaoMapper jiaTiaoMapper;

    @Autowired
    GuanLiMapper guanLiMapper;

    @Autowired
    QianDaoRenWuMapper qianDaoRenWuMapper;
    @Autowired
    QianDaoMapper qianDaoMapper;
    @Override
    public GuanLi findGuanLiByUsername(String username) {
        return guanLiMapper.selectGuanLiByUsername(username);
    }

    @Override
    public List<JiaTiao> findJtListByDate(Date startTime, Date endTime, String state,String dep,String cls) {
        return jiaTiaoMapper.selectJtListSelective(startTime,endTime,state,dep,cls);
    }

    @Override
    public List<QianDao> findQdListByQdRwId(Integer id) {
        return qianDaoMapper.selectQdListByQdRwId(id);
    }

    @Override
    public List<QianDaoRenWu> findQdRwListByClassName(String className) {
        return qianDaoRenWuMapper.selectQdRwListByClassName(className);
    }

    @Override
    public QianDaoRenWu findQdRwById(Integer id) {
        return qianDaoRenWuMapper.selectQdRwById(id);
    }

    @Override
    public Integer CountNumberOfQingJiaXs(Date startTime, Date endTime, String state, String dep,String cls) {
        return jiaTiaoMapper.CountQingJiaXs(startTime,endTime,state,dep,cls);
    }

}
