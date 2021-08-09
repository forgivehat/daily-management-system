package com.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Integer id ;      //账号id
    private String username; //用户名
    private String password; //密码
     private String role;//账号类型(0学生,1教师,2辅导员,3学院管理人员,4系统管理员)
}
