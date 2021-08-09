package com.boot.controller;

import com.boot.entity.Student;
import com.boot.entity.ZuoYe;
import com.boot.entity.ZuoYeRenWu;
import com.boot.service.StudentService;
import com.boot.util.DateUtil;
import com.boot.util.Result;
import com.boot.util.TokenUtil;
import com.boot.util.VerifyToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * @author weish
 * @create 2021/5/8 16:37
 */


@RequestMapping("/student")
@RestController
@Slf4j
public class UploadFileController {

    @Value("${file-save-path}")
    private String fileSavePath;
    /**
     * 图片保存路径，自动从yml文件中获取数据
     * 示例： E:/files/
     */
    @Autowired
    StudentService studentService;

    /**
     * 根据班级名称查看作业任务
     * @param request
     * @param
     * @return
     */
    @VerifyToken
    @RequestMapping("/showZyRwByClassName")
    public Result showZyRwByClassName(HttpServletRequest request){
        String acc_username = TokenUtil.getUsernameFromToken(request);
        Student student = studentService.findStudentByUsername(acc_username);
        String className = student.getClassName();
        List<ZuoYeRenWu> list = studentService.findZyRwByClassName(className);
        return new Result("10001","作业任务",list);
    }

    /**
     * 根据学生用户名查询作业信息列表
     * @param request
     * @param
     * @return
     */
    @VerifyToken
    @RequestMapping("/showZyByXsUsername")
    public Result showZyByXsUsername(HttpServletRequest request){
        String acc_username = TokenUtil.getUsernameFromToken(request);

        List<ZuoYe> list = studentService.findZyListByUsername(acc_username);
        return new Result("10001","作业提交记录",list);
    }

    /**
     * 根据作业任务id查询作业任务
     * @param id
     * @return
     */
    @VerifyToken
    @RequestMapping("/findZyRwById")
    public Result findZyRwById(@RequestParam(value = "id") Integer id){
        ZuoYeRenWu zuoYeRenWu = studentService.findZyRwById(id);
        if (zuoYeRenWu == null){
            return new Result("10001","未查询到作业任务");
        }
        return new Result("10001","查询成功",zuoYeRenWu);

    }

    /**
     * 根据作业任务id提交作业
     * @param file
     * @param request
     * @param id
     * @return
     * @throws ParseException
     */
    @VerifyToken
    @PostMapping(path = "/uploadSchoolworkByZyRwId")
    public Result uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, @RequestParam(value = "id") Integer id) throws ParseException {
        String acc_username = TokenUtil.getUsernameFromToken(request);
        Student student = studentService.findStudentByUsername(acc_username);
        ZuoYe z = studentService.findZyByUsernameAndZyRwId(acc_username,id);
        if (z != null){
            return new Result("20001","作业请勿重复提交！");
        }
         ZuoYe zuoYe = new ZuoYe();
        zuoYe.setZy_rw_id(id);
        zuoYe.setDepartment(student.getDepartment());
        zuoYe.setClassName(student.getClassName());
        zuoYe.setXs_acc_username(acc_username);
        zuoYe.setXs_realName(student.getXs_realName());
        ZuoYeRenWu zuoYeRenWu = studentService.findZyRwById(id);
        zuoYe.setLs_acc_username(zuoYeRenWu.getLs_acc_username());
        zuoYe.setLs_realName(zuoYeRenWu.getLs_realName());
        zuoYe.setZy_rw_theme(zuoYeRenWu.getZy_rw_theme());
        Date nowTime = DateUtil.returnNowTime();
        zuoYe.setZy_created_time(nowTime);
        Date start_time = zuoYeRenWu.getZy_rw_start_time();
        Date end_time = zuoYeRenWu.getZy_rw_end_time();
        if (!DateUtil.belongCalendar(nowTime,start_time,end_time)){
            return new Result("10001","超过了作业截止日期！");
        }
        /**
         *  2.文件保存目录  E:/files/2020/03/15/
         *  如果目录不存在，则创建
         */
        File dir = new File(fileSavePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        log.info("文件上传，保存位置：" + fileSavePath);
        //3.给文件重新设置一个名字
        //后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//        String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
        String newFileName ="zy_rw_id_"+zuoYeRenWu.getZy_rw_id()+"-"+acc_username+suffix;
        //4.创建这个新文件
        File newFile = new File( fileSavePath + newFileName);
        //5.复制操作
        try {
            file.transferTo(newFile);
            //协议 :// ip地址 ：端口号 / 文件目录(/files/2020/03/15/xxx.xxx)
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/files/" + newFileName;
            log.info("图片上传，访问URL：" + url);
            zuoYe.setZy_path_url(url);
            studentService.addZuoYe(zuoYe);
            return new Result("10001","上传成功！",url);
        } catch (IOException e) {
            return new Result("20001","上传异常！");
        }
    }
}
