package com.boot.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/*
* 老师发布作业任务
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZuoYeRenWu {
    private Integer zy_rw_id;       //作业任务id

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date zy_rw_start_time;  //作业起始时间（签到任务创建时间）

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date zy_rw_end_time;  //作业截止时间

    private String zy_rw_theme;

    private String department;    //提交作业的学生所属学院
    private String className;   //提交作业的学生所属班级

    private String ls_realName;  //发布该作业任务的老师的真名
    private String ls_acc_username; //发布作业任务的老师的用户名
}
