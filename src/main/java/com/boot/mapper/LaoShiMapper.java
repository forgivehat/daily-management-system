package com.boot.mapper;

import com.boot.entity.LaoShi;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author weish
 * @create 2021/5/3 23:44
 */
@Mapper
@Component("laoShiMapper")
public interface LaoShiMapper {
    LaoShi selectLaoShiByUsername(String username);
}
