package com.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author weish
 * @create 2021/5/7 20:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuanLi {
    private Integer gl_id;         //老师账号id
    private String  gl_realName;   //真实姓名
    private String  gl_tel;  //联系电话
    private String  department; //所属学院
    private Integer acc_id;  //对应账户表id
    private String  acc_username; //对应账户表用户名

}
