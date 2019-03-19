package com.easydao.earlybird.service.impl;

import com.easydao.earlybird.bean.Dynamic;
import com.easydao.earlybird.mapper.DynamicMapper;
import com.easydao.earlybird.service.DynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class DynamicServiceImpl implements DynamicService {

    @Autowired
    private DynamicMapper dynamicMapper;

    @Override
    public void addDynamic(Dynamic dynamic) {
        dynamic.setCreate_date(new Date());
        dynamicMapper.addDynamic(dynamic);
    }

    @Override
    public Dynamic findDynamicByAccount(String user_account) {
        return dynamicMapper.findDynamicByAccount(user_account);
    }
}
