package com.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author weish
 * @create 2021/5/1 0:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuDao {
    private Integer fd_id;  //辅导员Id
    private String  fd_realName;  //辅导员真实姓名
    private String  fd_tel;  //辅导员联系电话
    private String  department;  //所属学院
    private Integer acc_id;  //辅导员对应账户表id
    private String   acc_username; //辅导员对应账户表用户名
}
