package com.boot.mapper;

import com.boot.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author weish
 * @create 2021/4/29 14:57
 */
@Mapper
@Component(value = "studentMapper")
public interface StudentMapper {

    Student selectStudentByUsername(String username);
    /*
     * 学生注册账号后需要提交个人信息，
     *
     * */
    int insertStudent(Student student);

    List<Student> selectXsListBySelectiveParam(
            @Param("acc_username") String xs_username
            ,@Param("xs_gender") String xs_gender
            ,@Param("department") String dep
            ,@Param("className") String cls);
}
