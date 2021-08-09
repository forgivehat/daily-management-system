package com.boot.mapper;

import com.boot.entity.FuDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author weish
 * @create 2021/5/2 23:19
 */
@Mapper
@Component("fuDaoMapper")
public interface FuDaoMapper {

    FuDao selectFuDaoByUsername(String username);
}
