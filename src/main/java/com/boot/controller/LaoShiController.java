package com.boot.controller;

import com.boot.entity.*;
import com.boot.service.LaoShiService;
import com.boot.util.Result;
import com.boot.util.TokenUtil;
import com.boot.util.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author weish
 * @create 2021/5/5 0:17
 */
@RestController
@RequestMapping("/laoshi")
public class LaoShiController {
    @Autowired
    LaoShiService laoShiService;

    /**
     * 老师查询指定时间内指定班级请假成功的学生的请假信息
     * @param jiaTiao
     * @return
     */
    @VerifyToken
    @RequestMapping("/showJtBySelectiveParam")
    public Result showJtBySelectiveParam(HttpServletRequest request,@RequestBody JiaTiao jiaTiao){
          String state = null;
        String username =TokenUtil.getUsernameFromToken(request);
        LaoShi l = laoShiService.findLaoShiByUsername(username);
        List<JiaTiao> list = laoShiService.findJtBySelectiveParam(
                jiaTiao.getJt_start_time()
                ,jiaTiao.getJt_end_time()
                ,state
                ,l.getDepartment()
                ,jiaTiao.getClassName());
        if (list==null){
            return new Result("20004","未查询到结果！");
        }
        return new Result("10004","查询成功！",list);
    }


    /**
     * 发布签到任务
     * @param request
     * @param qianDaoRenWu
     * @return
     */
    @VerifyToken
    @RequestMapping("/addQianDaoRenWu")
    public Result addQianDaoRenWu(HttpServletRequest request,@RequestBody  QianDaoRenWu qianDaoRenWu){
        String acc_username = TokenUtil.getUsernameFromToken(request);
        LaoShi laoShi = laoShiService.findLaoShiByUsername(acc_username);
        qianDaoRenWu.setLs_acc_username(acc_username);
        qianDaoRenWu.setLs_realName(laoShi.getLs_realName());
        if (!laoShiService.addQdRw(qianDaoRenWu)){
            return new Result("20004","发布失败！");
        }
        return new Result("10004","发布成功！");
    }
    /**
     * 根据老师账号名查询发布的签到任务历史记录
     * @param request
     * @return
     */
    @VerifyToken
    @RequestMapping("/showQdRwList")
    public Result showQdRwList(HttpServletRequest request){
        String acc_username = TokenUtil.getUsernameFromToken(request);
        List<QianDaoRenWu> list = laoShiService.findQdRwListByUsername(acc_username);
        if (list==null){
            return new Result("20004","未查询到结果！");
        }
        return new Result("10004","查询成功！",list);
    }
    /**
     * 根据签到任务id查询签到任务
     * @param request
     * @return
     */
    @VerifyToken
    @RequestMapping("/findQdRwById")
    public Result findQdRwById(HttpServletRequest request, @RequestParam(value = "id") Integer id){

        QianDaoRenWu qianDaoRenWu = laoShiService.findQdRwById(id);
        if (qianDaoRenWu==null){
            return new Result("20004","未查询到结果！");
        }
        return new Result("10004","查询成功！",qianDaoRenWu);
    }
    /**
     * 老师查询每次签到任务，成功签到的学生信息
     *
     * @param id
     * @return
     */
    @VerifyToken
    @RequestMapping("/showQdListByQdRwId")
    public Result showQdRwList(@RequestParam(value = "id") Integer id){

        List<QianDao> list = laoShiService.findQdListByQdRwId(id);
        if (list==null){
            return new Result("20004","未查询到结果！");
        }
        return new Result("10004","查询成功！",list);
    }


    /**
     * 发布作业任务
     * @param request
     * @param zuoYeRenWu
     * @return
     */
    @VerifyToken
    @RequestMapping("/addZuoYeRenWu")
    public Result addZyRw(HttpServletRequest request,@RequestBody ZuoYeRenWu zuoYeRenWu){
        String acc_username = TokenUtil.getUsernameFromToken(request);
        LaoShi laoShi = laoShiService.findLaoShiByUsername(acc_username);
        zuoYeRenWu.setLs_acc_username(acc_username);
        zuoYeRenWu.setLs_realName(laoShi.getLs_realName());
        if (!laoShiService.addZyRw(zuoYeRenWu)){
            return new Result("20004","发布失败！");
        }
        return new Result("10004","发布成功！");
    }
    /**
     * 根据老师账号名查询发布的作业任务历史记录
     * @param request
     * @return
     */
    @VerifyToken
    @RequestMapping("/showZyRwList")
    public Result showZyRwList(HttpServletRequest request){
        String acc_username = TokenUtil.getUsernameFromToken(request);
        List<ZuoYeRenWu> list = laoShiService.findZyRwListByUsername(acc_username);
        if (list==null){
            return new Result("20004","未查询到结果！");
        }
        return new Result("10004","查询成功！",list);
    }

    /**
     * 根据作业任务id查询该任务下提交的作业列表
     * @param request
     * @return
     */
    @VerifyToken
    @RequestMapping("/showZyListByZyRwId")
    public Result showZyListByZyRwId(HttpServletRequest request,@RequestParam(value = "id") Integer id){

        List<ZuoYe> list = laoShiService.findZyListByZyRwId(id);
        if (list==null){
            return new Result("20004","未查询到结果！");
        }
        return new Result("10004","查询成功！",list);
    }
    /**
     * 给作业打分
     * @param zuoYe
     * @return
     */
    @VerifyToken
    @RequestMapping("/checkZy")
    public Result checkZy(@RequestBody ZuoYe zuoYe){
        if(laoShiService.editZuoYe(zuoYe)) {
            return new Result("10001", "成功！");
        }
        return new Result("20001", "失败！");
    }
}
