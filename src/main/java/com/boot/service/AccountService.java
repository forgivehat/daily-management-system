package com.boot.service;

import com.boot.entity.Account;


public interface AccountService {


    Boolean updateAccount(Account account);


    Account findByUsername(String username);

    Boolean addUser(Account account);

}
