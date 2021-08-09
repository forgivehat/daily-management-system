package com.boot.service.impl;

import com.boot.entity.Account;
import com.boot.entity.Student;
import com.boot.mapper.AccountMapper;
import com.boot.mapper.StudentMapper;
import com.boot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author weish
 * @create 2021/5/15 12:37
 */
@Service
@Component("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    AccountMapper accountMapper;

    @Override
    public Account findAccById(Integer id) {
        return accountMapper.selectUserById(id);
    }

    @Override
    public Boolean updateAccount(Account account) {
        return accountMapper.updateAccount(account)>=1;
    }

    @Override
    public Boolean deleteAccountById(Integer id) {
        return accountMapper.deleteAccount(id)>=1;
    }

    @Override
    public List<Account> selectAccBySelectiveRole(String role) {
        return accountMapper.selectAccBySelectiveRole(role);
    }

}
