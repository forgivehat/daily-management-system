package com.boot.service.impl;

import com.boot.entity.FuDao;
import com.boot.entity.JiaTiao;
import com.boot.entity.QianDao;
import com.boot.entity.QianDaoRenWu;
import com.boot.mapper.FuDaoMapper;
import com.boot.mapper.JiaTiaoMapper;
import com.boot.mapper.QianDaoMapper;
import com.boot.mapper.QianDaoRenWuMapper;
import com.boot.service.FuDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author weish
 * @create 2021/5/1 0:05
 */
@Service("fuDaoService")
public class FuDaoServiceImpl implements FuDaoService {

    @Autowired
    JiaTiaoMapper jiaTiaoMapper;

    @Autowired
    FuDaoMapper fuDaoMapper;
    @Autowired
    QianDaoRenWuMapper qianDaoRenWuMapper;
    @Autowired
    QianDaoMapper qianDaoMapper;


    @Override
    public FuDao findFuDaoByUsername(String username) {
        return fuDaoMapper.selectFuDaoByUsername(username);
    }

    @Override
    public List<JiaTiao> findJiaTiaoByDepartment(String department) {
        return jiaTiaoMapper.selectJtListByDepartment(department);
    }



    @Override
    public List<JiaTiao> findJiaTiaoBySelectiveParam(Date start,Date end,String state, String dep,String cls) {
        return jiaTiaoMapper.selectJtListSelective(start,end,state,dep,cls);
    }

    @Override
    public Boolean editJiaTiao(JiaTiao jiaTiao) {
        return jiaTiaoMapper.updateJt(jiaTiao)>=1;
    }


    @Override
    public List<QianDaoRenWu> findQdRwListByClassName(String className) {
        return qianDaoRenWuMapper.selectQdRwListByClassName(className);
    }

    @Override
    public List<QianDao> findQdListByQdRwId(Integer id) {
        return qianDaoMapper.selectQdListByQdRwId(id);
    }

    @Override
    public JiaTiao findJtById(Integer id) {
        return jiaTiaoMapper.selectJtByJtId(id);
    }
}
