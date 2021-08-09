package com.boot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JiaTiao {
        private Integer jt_id;  //假条id

        @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date jt_start_time; //起始时间

        @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date jt_end_time; //销假时间

        private String jt_reason; //请假理由
        private String  jt_tel;  //假条联系电话
        private String department; //请假学生的学院
        private String className; //请假学生的班级

        private String xs_acc_username;      //学生账户用户名
        private String xs_realName;  //学生真名

        private String jt_state; //假条审核状态

        private String fd_acc_username;      //辅导员账户用户名
        private String fd_realName;  //辅导员真名

        private String jt_note;  //假条批示

}
