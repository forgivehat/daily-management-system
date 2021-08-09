package com.boot.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer xs_id; //学生id
    private String  xs_realName;  //学生真名
    private String  xs_gender;      //学生性别
    private String  department;  //学生所属学院
    private String  className;   //学生所属班级
    private Integer acc_id;     //学生对应的账户的id
    private String  acc_username; //学生对应的账号名

}
