package com.boot.controller;

import com.boot.entity.FuDao;
import com.boot.entity.JiaTiao;
import com.boot.entity.QianDao;
import com.boot.entity.QianDaoRenWu;
import com.boot.service.FuDaoService;
import com.boot.util.Result;
import com.boot.util.TokenUtil;
import com.boot.util.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author weish
 * @create 2021/5/2 18:20
 */
@RestController
@RequestMapping("/fudao")
public class FuDaoController {
    @Autowired
    FuDaoService fuDaoService;

    /**
     *  辅导员查询本学院学生的所有请假信息
     * @param request
     * @param
     * @return
     */
    @VerifyToken
    @RequestMapping("/findAllJt")
    public Result showAllJiaTiaoByDepartment(HttpServletRequest request){
        String acc_username = TokenUtil.getUsernameFromToken(request);
        FuDao fuDao = fuDaoService.findFuDaoByUsername(acc_username);
        String department = fuDao.getDepartment();
       List<JiaTiao> jiaTiaos =  fuDaoService.findJiaTiaoByDepartment(department);
       if (jiaTiaos == null){
           return new Result("20001","未查到数据！");
       }
        return new Result("10001","查找成功！",jiaTiaos);
    }
    /**
     * 根据可选字段查询假条列表
     * @param request
     * @param
     * @return
     */
    @VerifyToken
    @RequestMapping("/showJtBySelectiveParam")
    public Result showAllJiaTiaoByStateAndDepartment(HttpServletRequest request,@RequestBody  JiaTiao jiaTiao){
        String acc_username = TokenUtil.getUsernameFromToken(request);
        FuDao fuDao = fuDaoService.findFuDaoByUsername(acc_username);
        String department = fuDao.getDepartment();
        List<JiaTiao> list =  fuDaoService.findJiaTiaoBySelectiveParam(jiaTiao.getJt_start_time()
                ,jiaTiao.getJt_end_time()
                ,jiaTiao.getJt_state()
                ,department
                ,jiaTiao.getClassName());
        if (list == null){
            return new Result("20001","未查到数据！");
        }
        return new Result("10001","查找成功！",list);
    }
    /**
     * 根据假条id查询假条
     * 前端传入：
     * jt_id（传给前端的一大堆请假条中某一条的id字段）
     * @return
     */
    @VerifyToken
    @RequestMapping("/findJtById")
    public Result findJtById(@RequestParam(value = "id") Integer jt_id){
        JiaTiao jiaTiao = fuDaoService.findJtById(jt_id);
        if(jiaTiao == null){
            return new Result("20001","未查询到结果！");
        }
        return new Result("10001","查询成功！",jiaTiao);
    }
    /**
     * 审批假条
     * jt_id（传给前端的一大堆请假条中某一条的id字段）
     * state（代表审批是否通过）
     * note（代表辅导员的批示）
     * @param request
     * @param jiaTiao
     * @return
     */
    @VerifyToken
    @RequestMapping("/reviewJt")
    public Result reviewJiaTiao(HttpServletRequest request, @RequestBody JiaTiao jiaTiao){
        String acc_username = TokenUtil.getUsernameFromToken(request);
        String fd_realName = fuDaoService.findFuDaoByUsername(acc_username).getFd_realName();
        jiaTiao.setJt_id(jiaTiao.getJt_id());
        jiaTiao.setFd_acc_username(acc_username);
        jiaTiao.setFd_realName(fd_realName);
        fuDaoService.editJiaTiao(jiaTiao);
        return new Result("10001","审核完成！");
    }


    /**
     * 查看每个班级的签到任务
     * @param className
     * @return
     */
    @VerifyToken
    @RequestMapping("/showQdRw")
    public Result showQdRwList(@RequestParam("classname") String className){
        List<QianDaoRenWu> list = fuDaoService.findQdRwListByClassName(className);
        if(list == null){
            return new Result("20001","未查询到结果！");
        }
        return new Result("10001","查询成功！",list);
    }
    /**
     * 根据签到任务，查看签到成功学生信息
     * @param id
     * @return
     */
    @VerifyToken
    @RequestMapping("/showQdListByQdRwId")
    public Result showQdListByQdRwId(@RequestParam("id") Integer id){
        List<QianDao> list = fuDaoService.findQdListByQdRwId(id);
        if(list == null){
            return new Result("10002","未查询到结果！");
        }
        return new Result("10001","查询成功！",list);
    }
}
