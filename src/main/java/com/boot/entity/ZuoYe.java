package com.boot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZuoYe {

    private Integer zy_id; //签到表id

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date zy_created_time;  //学生提交作业的时间

    private String zy_path_url;   //作业文件对应的访问路径
    private Integer zy_score;     //老师对作业的评分

    private String department; //学生所属学院
    private String className;  //学生所属班级

    private String xs_acc_username; //提交作业的学生用户名
    private String xs_realName;    //提交作业的学生真名

    private String ls_acc_username;  //发布作业任务老师用户名
    private String ls_realName;     //发布作业任务老师真名

    private Integer zy_rw_id;    //本次提交的作业对应的作业任务的id
    private String zy_rw_theme;  //作业任务的主题
}
