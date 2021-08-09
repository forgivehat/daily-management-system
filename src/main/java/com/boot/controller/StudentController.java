package com.boot.controller;
import com.boot.entity.*;
import com.boot.service.AccountService;
import com.boot.service.StudentService;
import com.boot.util.Result;
import com.boot.util.DateUtil;
import com.boot.util.TokenUtil;
import com.boot.util.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author weish
 * @create 2021/4/29 15:05
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    AccountService accountService;
    @Autowired
    StudentService studentService;


    /**
     * 根据学生账号名查看请假历史
     * @param request
     *
     * @return
     */
    @VerifyToken
    @RequestMapping("/findAllJt")
    public Result StuFindAllJt(HttpServletRequest request){
        String acc_username = TokenUtil.getUsernameFromToken(request);
        List<JiaTiao> list = studentService.findJtListByUsername(acc_username);
        if (list == null){
            return new Result("20001","无请假记录");
        }
        return new Result("10001","查询成功",list);

    }

    /**
     * 根据假条id查看请假信息
     * @param request
     *
     * @return
     */
    @VerifyToken
    @RequestMapping("/findJtById")
    public Result findJtById(HttpServletRequest request,@RequestParam(value = "id") Integer id){
        JiaTiao jt = studentService.findJtById(id);
        if ( jt== null){
            return new Result("20001","无请假记录");
        }
        return new Result("10001","查询成功",jt);
    }

    /**
     * 提交假条
     * @param request---请假起始时间、结束时间、请假理由、联系电话
     * @param jiaTiao
     * @return
     */
    @VerifyToken
    @RequestMapping("/addJt")
    public Result addJt(HttpServletRequest request,@RequestBody JiaTiao jiaTiao){
        String acc_username = TokenUtil.getUsernameFromToken(request);
        Student student = studentService.findStudentByUsername(acc_username);
        jiaTiao.setJt_state("待审核");
        jiaTiao.setDepartment(student.getDepartment());
        jiaTiao.setClassName(student.getClassName());
        jiaTiao.setXs_realName(student.getXs_realName());
        jiaTiao.setXs_acc_username(acc_username);
        if (!studentService.addJiaTiao(jiaTiao)){
            return new Result("20001","上传失败");
        }
        return new Result("10001","上传成功",jiaTiao);
    }

    /**
     * 根据班级名称查看签到任务
     * @param request
     * @param
     * @return
     */
    @VerifyToken
    @RequestMapping("/findQdRwListByClassName")
    public Result showQdReByClassName(HttpServletRequest request){
        String acc_username = TokenUtil.getUsernameFromToken(request);
        Student student = studentService.findStudentByUsername(acc_username);
        String className = student.getClassName();
        List<QianDaoRenWu> qianDaoRenWuList = studentService.findQdRwListByClassName(className);
        return new Result("10001","签到任务",qianDaoRenWuList);
    }
    /**
     * 查询每次签到任务的签到状态
     * @param request
     * @param
     * @return
     */
    @VerifyToken
    @RequestMapping("/findQdStateList")
    public Result findQdStateList(HttpServletRequest request){
        String acc_username = TokenUtil.getUsernameFromToken(request);
        Student student = studentService.findStudentByUsername(acc_username);
        String className = student.getClassName();
        List<QianDaoRenWu> qianDaoRenWuList = studentService.findQdRwListByClassName(className);
        List<String> stateList = new ArrayList<>();
        for(int i=0; i<qianDaoRenWuList.size();i++){
            QianDao qianDao = studentService
                    .findQianDaoByUsernameAndQdRwId(acc_username,qianDaoRenWuList.get(i).getQd_rw_id());
            if (qianDao == null){
                stateList.add("未签到");
            }else {
                stateList.add("已签到");
            }
        }
        return new Result("10001","签到任务",stateList);
    }
    /**
     * 根据签到任务id查询签到任务
     * @return
     */
    @VerifyToken
    @RequestMapping("/findQdRwById")
    public Result findQdRwById(HttpServletRequest request,@RequestParam(value = "id") Integer id){
        {
            String acc_username = TokenUtil.getUsernameFromToken(request);
            QianDao qianDao = studentService.findQianDaoByUsernameAndQdRwId(acc_username,id);
            QianDaoRenWu qianDaoRenWu = studentService.findQdRwById(id);

            if (qianDaoRenWu == null){
                return new Result("20001","未查询到数据");
            }
            if (qianDao == null){
                return new Result("20002","未签到",qianDaoRenWu);
            }
            return new Result("10001","已签到",qianDaoRenWu);
        }
    }
    /**
     * 根据任务id提交签到
     * @param request
     * @param id
     * @return
     * @throws ParseException
     */
    @VerifyToken
    @RequestMapping("/addQd")
    public Result studentQianDao(HttpServletRequest request,@RequestParam(value = "id") Integer id) throws ParseException {
        String acc_username = TokenUtil.getUsernameFromToken(request);  //获取token中的用户名并获取完整学生信息
        Student student = studentService.findStudentByUsername(acc_username);
        QianDao q = studentService.findQianDaoByUsernameAndQdRwId(acc_username,id); //查询是否已经签过到了
        if (q != null){
            return new Result("20004","已经签到过了！");
        }
        QianDao qianDao = new QianDao();
        qianDao.setQd_rw_id(id);                        //此次签到对应的签到任务的id
        qianDao.setDepartment(student.getDepartment());
        qianDao.setClassName(student.getClassName());
        qianDao.setXs_acc_username(acc_username);        //此次签到学生的用户名
        qianDao.setXs_realName(student.getXs_realName());
        QianDaoRenWu qianDaoRenWu = studentService.findQdRwById(id);  //根据前端传入的id查询对应的签到任务
        qianDao.setLs_acc_username(qianDaoRenWu.getLs_acc_username()); //从签到任务得到发布该签到的老师的用户名与真实姓名
        qianDao.setLs_realName(qianDaoRenWu.getLs_realName());
        Date nowTime = DateUtil.returnNowTime();  //接收到前端时，当前时间视为签到时间
        qianDao.setQd_created_time(nowTime);
        Date start_time = qianDaoRenWu.getQd_rw_start_time(); //从签到任务获得起始与截止时间
        Date end_time = qianDaoRenWu.getQd_rw_end_time();
        //判断签到是否超时
        if (DateUtil.belongCalendar(nowTime,start_time,end_time)) {
            qianDao.setQd_state("已签到");
            if (studentService.addQianDao(qianDao)){
                return new Result("10001","签到成功",qianDao);
            }
            return new Result("20001","签到失败");

        }else {
            qianDao.setQd_state("未签到");
            return new Result("20003","签到已超时");
        }
    }




}
