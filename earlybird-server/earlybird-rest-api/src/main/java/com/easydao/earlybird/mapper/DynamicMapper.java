package com.easydao.earlybird.mapper;

import com.easydao.earlybird.bean.Dynamic;
import com.easydao.earlybird.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface DynamicMapper {

    void addDynamic(Dynamic dynamic);
    Dynamic findDynamicByAccount(String user_account);
}
