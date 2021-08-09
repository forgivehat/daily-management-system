package com.boot.controller;

import com.boot.entity.*;
import com.boot.service.AccountService;
import com.boot.service.FuDaoService;
import com.boot.service.LaoShiService;
import com.boot.service.StudentService;
import com.boot.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author weish
 * @create 2021/4/29 15:00
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private FuDaoService fuDaoService;

    @Autowired
    private LaoShiService laoShiService;

    /**
     *      注册接口
     *     入参为JSON格式，包括账号、密码 用户类型（需要注册的用户只能为学生，role值为“0”）
     * @param userInfo
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody Param<Student> userInfo){
        Account account = userInfo.account;
        Student student =  userInfo.t;
        Account uFromDB = accountService.findByUsername(account.getUsername());
        if(uFromDB == null){
            if (accountService.addUser(account)){
                Account u2 = accountService.findByUsername(account.getUsername());
                student.setAcc_id(u2.getId());
                student.setAcc_username(u2.getUsername());
                if (studentService.addStudent(student)){
                    return new Result("10001","注册成功！");
                }
                return new Result("20002","信息录入失败！");
            }
            return new Result("20001","注册失败！");
        }else {
            return new Result("20002","该账户已被注册！");
        }
    }

    /**
     * 登录接口
     * 账号(username)、密码(password)、用户类型（role）
     * @param account
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account account){
        Account u = accountService.findByUsername(account.getUsername());
        if (u == null){
            return new Result("20003","用户不存在");
        }
        if (!u.getPassword().equals(account.getPassword())){
            return new Result("20004","密码错误");
        }
        String role = u.getRole();
        String token = TokenUtil.getToken(u.getUsername(),u.getPassword(),role);
        return new Result("10002",role,token);
    }

    /**
     * 更新账户密码
     * @param account
     * @return
     */
    @VerifyToken
    @RequestMapping("/updateAcc")
    public Result updateAcc(@RequestBody Account account,HttpServletRequest request){
       String username = TokenUtil.getUsernameFromToken(request);
       Account account1 = new Account();
       account1.setUsername(username);
        account1.setPassword(account.getPassword());
        if (!accountService.updateAccount(account1)){
            return new Result("20001","更新账户信息失败");
        }
        Account acc = accountService.findByUsername(account1.getUsername());
        return new Result("10001","更新账户信息成功",acc);
    }

    /**
     * 展示账户信息
     * @param request
     * @return
     */
    @VerifyToken
    @RequestMapping("/findAccInfo")
    public Result findAccInfo(HttpServletRequest request){
       String username = TokenUtil.getUsernameFromToken(request);
       String role  = TokenUtil.getRoleFromToken(request);
        Account account = accountService.findByUsername(username);
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount(account);
          if(role.equals("0")){
           Student  student = studentService.findStudentByUsername(username);
           userInfo.setAccount(account);
           userInfo.setRealName(student.getXs_realName());
        userInfo.setClassName(student.getClassName());
        userInfo.setDepartment(student.getDepartment());
        userInfo.setGender(student.getXs_gender());
            return new Result("10001","success",userInfo);
          }
          else if (role.equals("1")){
              FuDao fuDao = fuDaoService.findFuDaoByUsername(username);
                userInfo.setAccount(account);
              userInfo.setDepartment(fuDao.getDepartment());
              userInfo.setRealName(fuDao.getFd_realName());
              userInfo.setTel(fuDao.getFd_tel());
              return new Result("10001","success",userInfo);
          }
          else if (role.equals("2")) {
              LaoShi laoShi = laoShiService.findLaoShiByUsername(username);
              userInfo.setAccount(account);
              userInfo.setDepartment(laoShi.getDepartment());
              userInfo.setRealName(laoShi.getLs_realName());
              userInfo.setTel(laoShi.getLs_tel());
              return new Result("10001","success",userInfo);
          }
          return   new Result("20001","fail");
    }
















}
