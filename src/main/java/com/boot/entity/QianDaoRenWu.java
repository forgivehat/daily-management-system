package com.boot.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/*
 * 老师发布的签到任务
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QianDaoRenWu {
    private Integer qd_rw_id;       //签到任务id

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date qd_rw_start_time;  //签到起始时间（签到任务创建时间）

    @JsonFormat(pattern =                                                                                                                                                                   "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date qd_rw_end_time;  //签到截止时间

    private String department;    //需签到的学生所属学院
    private String className;   //需签到的学生所属班级

    private String ls_realName;  //发布该签到的老师的真名
    private String ls_acc_username; //发布该签到的老师的用户名
}
