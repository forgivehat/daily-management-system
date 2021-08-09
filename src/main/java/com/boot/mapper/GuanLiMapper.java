package com.boot.mapper;

import com.boot.entity.FuDao;
import com.boot.entity.GuanLi;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author weish
 * @create 2021/5/7 20:34
 */
@Mapper
@Component("guanLiMapper")
public interface GuanLiMapper {
    GuanLi selectGuanLiByUsername(String username);
}
