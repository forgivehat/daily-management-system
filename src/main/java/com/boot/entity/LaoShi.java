package com.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LaoShi {
    private Integer ls_id;         //老师账号id
    private String  ls_realName;   //真实姓名
    private String  ls_tel;  //联系电话
    private String  department; //所属学院
    private Integer acc_id;  //对应账户表id
    private String  acc_username; //对应账户表用户名

}
