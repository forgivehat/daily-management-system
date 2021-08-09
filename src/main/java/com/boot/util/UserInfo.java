package com.boot.util;

import com.boot.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author weish
 * @create 2021/5/20 0:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    public Account account;
    public  String realName;
    public String department;


    public String gender;
    public String className;

    public String tel;
}
