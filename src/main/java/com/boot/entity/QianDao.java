package com.boot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author weish
 * @create 2021/4/26 15:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QianDao {
    private Integer qd_id; //签到表id

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date qd_created_time;  //学生提交签到的时间

    private String qd_state;   //签到状态
    private String department; //签到学生所属学院
    private String className;  //签到学生所属班级

    private String xs_acc_username; //学生用户名
    private String xs_realName;    //学生真名

    private String ls_acc_username;  //发布签到老师用户名
    private String ls_realName;     //发布签到老师真名

    private Integer qd_rw_id;    //本次签到对应的签到任务的id
}
