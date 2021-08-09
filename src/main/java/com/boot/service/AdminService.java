package com.boot.service;

import com.boot.entity.Account;
import com.boot.entity.Student;

import java.util.List;

/**
 * @author weish
 * @create 2021/5/15 12:37
 */
public interface AdminService {


    Account findAccById(Integer id);

    Boolean updateAccount(Account account);

    Boolean deleteAccountById(Integer id);

    List<Account> selectAccBySelectiveRole(String role);
}
