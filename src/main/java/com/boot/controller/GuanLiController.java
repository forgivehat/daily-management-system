package com.boot.controller;

import com.boot.entity.GuanLi;
import com.boot.entity.JiaTiao;
import com.boot.entity.QianDao;
import com.boot.entity.QianDaoRenWu;
import com.boot.service.GuanLIService;
import com.boot.util.Result;
import com.boot.util.TokenUtil;
import com.boot.util.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author weish
 * @create 2021/5/7 20:29
 */
@RestController
@RequestMapping("/guanli")
public class GuanLiController {
    public static String state = "审核通过";
    @Autowired
    GuanLIService guanLIService;

    /**
     * 统计指定时间段请假成功的学生信息
     * 传入起止时间或班级信息
     * @param request
     * @param jiaTiao
     * @return
     */
    @VerifyToken
    @RequestMapping("/showJtBySelectiveParam")
    public Result showJiatiaoByDate(HttpServletRequest request,@RequestBody  JiaTiao jiaTiao){
        String acc_username = TokenUtil.getUsernameFromToken(request);
        GuanLi g =  guanLIService.findGuanLiByUsername(acc_username);
        List<JiaTiao>  jiaTiaos =  guanLIService.findJtListByDate(
                jiaTiao.getJt_start_time()
                ,jiaTiao.getJt_end_time()
                ,state
                ,g.getDepartment()
                ,jiaTiao.getClassName());
        if (jiaTiaos == null){
            return new Result("20001","未查到相关数据！");
        }
        return new Result("10001","查询成功！",jiaTiaos);
    }

    @VerifyToken
    @RequestMapping("/countNumberOfStuJt")
    public Result countNumberOfStu(HttpServletRequest request,@RequestBody JiaTiao jiaTiao){
        String username = TokenUtil.getUsernameFromToken(request);
        GuanLi g =  guanLIService.findGuanLiByUsername(username);
        Integer count = guanLIService.CountNumberOfQingJiaXs(
                jiaTiao.getJt_start_time()
                ,jiaTiao.getJt_end_time()
                ,jiaTiao.getJt_state()
                ,g.getDepartment()
                ,jiaTiao.getClassName());
        if (count==null){
            return new Result("20001","未查到相关数据！");
        }
        return new Result("10001","查询成功！",count);
    }


    @VerifyToken
    @RequestMapping("/showQdRwByClassName")
    public Result countNumberOfQingJiaXs(@RequestParam(value = "classname") String className){
        List<QianDaoRenWu> list = guanLIService.findQdRwListByClassName(className);
        if (list==null){
            return new Result("20001","未查到相关数据！");
        }
        return new Result("10001","查询成功！",list);
    }

    @VerifyToken
    @RequestMapping("/showQdRwById")
    public Result showQdReById(@RequestParam(value = "id") Integer id){
            QianDaoRenWu qianDaoRenWu = guanLIService.findQdRwById(id);
    if (qianDaoRenWu==null){
            return new Result("20001","未查到相关数据！");
        }
        return new Result("10001","查询成功！",qianDaoRenWu);
    }

    @VerifyToken
    @RequestMapping("/showQdListByQdRwId")
    public Result countNumberOfQingJiaXs(@RequestParam(value = "id") Integer id){
        List<QianDao> list = guanLIService.findQdListByQdRwId(id);
        if (list==null){
            return new Result("20001","未查到相关数据！");
        }
        return new Result("10001","查询成功！",list);
    }
}
