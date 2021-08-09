package com.boot.mapper;

import com.boot.entity.QianDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author weish
 * @create 2021/5/5 23:21
 */
@Mapper
@Component("qianDaoMapper")
public interface QianDaoMapper {

    int insertQd(QianDao qianDao);

    List<QianDao> selectQdListByQdRwId(Integer id);

    List<QianDao> selectQdByUsername(String acc_username);
    QianDao selectQdById(Integer id);

    QianDao selectQdByQdRwIdAndUsername(@Param("username") String xs_username
            ,@Param("id") Integer qd_rw_id);




}

