package com.boot.service.impl;

import com.boot.entity.Account;
import com.boot.mapper.AccountMapper;
import com.boot.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;


    @Override
    public Boolean updateAccount(Account account) {
        return accountMapper.updateAccount(account)>=1;
    }

    @Override
    public Account findByUsername(String username) {
    return accountMapper.selectByUsername(username);
    }

    //插入数据成功返回1
    @Override
    public Boolean addUser(Account account) {
        return  accountMapper.insertUser(account)>=1;
    }
}
