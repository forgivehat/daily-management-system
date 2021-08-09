package com.boot.controller;

import com.boot.entity.Account;
import com.boot.service.AccountService;
import com.boot.service.AdminService;
import com.boot.util.Result;
import com.boot.util.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author weish
 * @create 2021/5/15 12:37
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AccountService accountService;

    @Autowired
    AdminService adminService;
    /**
     * 查看账户信息列表（role字段是可选的)
     */
    @RequestMapping("/findAcc")
    public Result findAcc(@RequestBody  Account account){
       List<Account> list = adminService.selectAccBySelectiveRole(account.getRole());
        if (list == null){
            return new Result("20001","未查询到数据");
        }
        return new Result("10001","查询成功",list);
    }


    /**
     * 更新账户密码
     * @param account
     * @return
     */
    @RequestMapping("/updateAcc")
    public Result updateAcc(@RequestBody Account account){
        if (!adminService.updateAccount(account))                         {
            return new Result("20001","更新失败");
        }
        Account acc = adminService.findAccById(account.getId());
        return new Result("10001","更新成功",acc);
    }

    /**
     * 删除账户
     * @param id
     * @return
     */
    @RequestMapping("/deleteAccById")
    public  Result delete(@RequestParam(value = "id") Integer id){
        if (!adminService.deleteAccountById(id)){
            return new Result("20001","删除失败");
        }
        return new Result("10001","删除成功！");
    }

    /**
     * 根据账户查询账号信息
     */
    @GetMapping("/findAccById")
    public Result findAccById(@RequestParam(value = "id") Integer id){
        Account account = adminService.findAccById(id);
        if (account == null){
            return new Result("20001","未查询到数据");
        }
        return new Result("10001","查询成功",account);

    }
}
