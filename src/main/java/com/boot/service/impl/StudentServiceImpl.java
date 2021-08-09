package com.boot.service.impl;

import com.boot.entity.*;
import com.boot.mapper.*;
import com.boot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author weish
 * @create 2021/4/29 14:58
 */
@Service("studentService")

public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    JiaTiaoMapper jiaTiaoMapper;
    @Autowired
    QianDaoRenWuMapper qianDaoRenWuMapper;
    @Autowired
    QianDaoMapper qianDaoMapper;
    @Autowired
    ZuoYeRenWuMapper zuoYeRenWuMapper;
    @Autowired
    ZuoYeMapper zuoYeMapper;
    /**
     * 填写学生信息
     * @param student
     * @return
     */
    @Override
    public Boolean addStudent(Student student) {
        return studentMapper.insertStudent(student)>=1;
    }

    /**
     * 根据唯一用户名查找学生
     * @param username
     * @return
     */
    @Override
    public Student findStudentByUsername(String username) {
        return studentMapper.selectStudentByUsername(username);
    }

    /**
     * 学生填写请假条
     * @param jiaTiao
     * @return
     */
    public Boolean addJiaTiao(JiaTiao jiaTiao){
        return jiaTiaoMapper.insertJt(jiaTiao)>=1;
    }

    /**
     * 学生查看自己的请假记录
     * @param xs_acc_username
     * @return
     */
    @Override
    public List<JiaTiao> findJtListByUsername(String xs_acc_username) {
        return jiaTiaoMapper.selectJtListByUsername(xs_acc_username);
    }
    /**
     * 学生根据假条id查询请假记录
     * @param
     * @return
     */
    @Override
    public JiaTiao findJtById(Integer id) {
        return jiaTiaoMapper.selectJtByJtId(id);
    }
    /**
     * 学生提交签到
     * @param qianDao
     * @return
     */
    @Override
    public Boolean addQianDao(QianDao qianDao) {
        return qianDaoMapper.insertQd(qianDao)>=1;
    }

    /**
     * 根据学生账户的用户名与签到任务id查询唯一的签到记录
     * @param xs_acc_username
     * @param qd_rw_id
     * @return
     */
    @Override
    public QianDao findQianDaoByUsernameAndQdRwId(String xs_acc_username, Integer qd_rw_id) {
        return qianDaoMapper.selectQdByQdRwIdAndUsername(xs_acc_username,qd_rw_id);
    }


    @Override
    public List<QianDao> findQdListByUsername(String acc_username) {
        return qianDaoMapper.selectQdByUsername(acc_username);
    }

    @Override
    public QianDao findQianDaoByQdId(Integer id) {
        return qianDaoMapper.selectQdById(id);
    }

    /**
     * 学生根据自己的班级查询签到任务列表
     *
     * @param className
     * @return
     */

    @Override
    public List<QianDaoRenWu> findQdRwListByClassName(String className) {
        return qianDaoRenWuMapper.selectQdRwListByClassName(className);
    }

    /**
     * 前端获得List<QianDaoRenWu>并展示后，用户根据对应的id获取其中的数据填入签到表
     * @param qd_rw_id  签到任务id
     * @return
     */
    public QianDaoRenWu findQdRwById(Integer qd_rw_id){
        return qianDaoRenWuMapper.selectQdRwById(qd_rw_id);
    }





    /**
     * 学生根据自己的班级查询作业任务列表
     * @param className
     * @return
     */
    @Override
    public List<ZuoYeRenWu> findZyRwByClassName(String className) {
        return zuoYeRenWuMapper.selectZyRwListByClassName(className);
    }

    /**
     * 学生提交作业
     * @param zuoYe
     * @return
     */
    @Override
    public Boolean addZuoYe(ZuoYe zuoYe) {
        return zuoYeMapper.insertZy(zuoYe)>=1;
    }

    /**
     * 根据学生账户的用户名与作业任务id查询唯一的作业提交记录
     * @param xs_acc_username
     * @param zy_rw_id
     * @return
     */
    @Override
    public ZuoYe findZyByUsernameAndZyRwId(String xs_acc_username, Integer zy_rw_id) {
        return zuoYeMapper.selectZyByZyRwIdAndUsername(xs_acc_username,zy_rw_id);
    }

    @Override
    public ZuoYeRenWu findZyRwById(Integer zy_rw_id) {
        return zuoYeRenWuMapper.selectZyRwById(zy_rw_id);
    }



    @Override
    public List<ZuoYe> findZyListByUsername(String xs_acc_username) {
        return zuoYeMapper.selectZyListByXsUsername(xs_acc_username);
    }
}
