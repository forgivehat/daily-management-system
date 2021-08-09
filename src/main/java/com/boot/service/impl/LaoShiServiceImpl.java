package com.boot.service.impl;

import com.boot.entity.*;
import com.boot.mapper.*;
import com.boot.service.LaoShiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author weish
 * @create 2021/5/3 23:47
 */
@Service("laoShiService")
public class LaoShiServiceImpl implements LaoShiService {

    @Autowired
    LaoShiMapper laoShiMapper;

    @Autowired
    JiaTiaoMapper jiaTiaoMapper;

    @Autowired
    QianDaoMapper qianDaoMapper;
    @Autowired
    QianDaoRenWuMapper qianDaoRenWuMapper;


    @Autowired
    ZuoYeRenWuMapper zuoYeRenWuMapper;
    @Autowired
    ZuoYeMapper zuoYeMapper;
    @Override
    public LaoShi findLaoShiByUsername(String username) {
        return laoShiMapper.selectLaoShiByUsername(username);
    }
    @Override
    public Boolean addQdRw(QianDaoRenWu qianDaoRenWu) {
        return qianDaoRenWuMapper.insertQdRw(qianDaoRenWu)>=1;
    }

    @Override
    public List<QianDaoRenWu> findQdRwListByUsername(String username) {
        return qianDaoRenWuMapper.selectQdRwListByLaoShiUsername(username);
    }

    @Override
    public QianDaoRenWu findQdRwById(Integer qd_rw_id) {
        return qianDaoRenWuMapper.selectQdRwById(qd_rw_id);
    }

    @Override
    public List<QianDao> findQdListByQdRwId(Integer id) {
        return qianDaoMapper.selectQdListByQdRwId(id);
    }

    @Override
    public List<JiaTiao> findJtBySelectiveParam(Date start, Date end,String state,String dep, String className) {
        return jiaTiaoMapper.selectJtListSelective(start,end,state,dep,className);
    }


    @Override
    public Boolean addZyRw(ZuoYeRenWu zuoYeRenWu) {
        return zuoYeRenWuMapper.insertZyRw(zuoYeRenWu)>=1;
    }

    @Override
    public List<ZuoYeRenWu> findZyRwListByUsername(String username) {
        return zuoYeRenWuMapper.selectZyRwListByLaoShiUsername(username);
    }

    @Override
    public List<ZuoYe> findZyListByZyRwId(Integer id) {
        return zuoYeMapper.selectZyListByZyRwId(id);
    }

    @Override
    public Boolean editZuoYe(ZuoYe zuoYe) {
        return zuoYeMapper.updateZy(zuoYe)>=1;
    }
}
