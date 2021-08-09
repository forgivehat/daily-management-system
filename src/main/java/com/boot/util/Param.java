package com.boot.util;

import com.boot.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * @author weish
 * @create 2021/5/29 21:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Param<T> {
    public Account account;
    public T t;
}
