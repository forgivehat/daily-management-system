package com.boot.service;

import com.boot.entity.*;

import java.util.List;

/**
 * @author weish
 * @create 2021/4/29 14:57
 */
public interface StudentService {
    Boolean addStudent(Student student);
    Student findStudentByUsername(String username);


    Boolean addJiaTiao(JiaTiao jiaTiao);    //添加假条
    JiaTiao findJtById(Integer id);         //根据假条的id查询假条
    List<JiaTiao> findJtListByUsername(String xs_acc_username); //根据学生用户名查询假条列表

    List<QianDaoRenWu> findQdRwListByClassName(String className);  //根据学生所属班级查询签到任务列表
    QianDaoRenWu findQdRwById(Integer qd_rw_id);        //根据签到任务的id查询签到任务

    Boolean addQianDao(QianDao qianDao);        //添加签到
    QianDao findQianDaoByUsernameAndQdRwId(String xs_acc_username,Integer qd_rw_id);  //根据签到任务id和学生的用户名查询签到信息
    List<QianDao> findQdListByUsername(String acc_username);
    QianDao findQianDaoByQdId(Integer id);

    List<ZuoYeRenWu> findZyRwByClassName(String className);   //根据学生所属班级查询作业任务列表
    ZuoYeRenWu findZyRwById(Integer zy_rw_id);  //根据作业任务id查询作业任务

    Boolean  addZuoYe(ZuoYe zuoYe);         //添加作业
    List<ZuoYe> findZyListByUsername(String xs_acc_username);  //根据学生用户名查询作业列表
    ZuoYe findZyByUsernameAndZyRwId(String xs_acc_username,Integer zy_rw_id);  //根据学生用户名和作业任务id查询作业信息
}
