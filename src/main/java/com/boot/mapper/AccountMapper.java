package com.boot.mapper;

import com.boot.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "accountMapper")
@Mapper
public interface AccountMapper {

    Account selectUserById(Integer id);

    Account selectByUsername(String username);


    //只能添加学生类型的账号！！
    int insertUser(Account account);

    int updateAccount(Account account);

    List<Account> selectAccBySelectiveRole(String role);

    int deleteAccount(Integer id);
}
