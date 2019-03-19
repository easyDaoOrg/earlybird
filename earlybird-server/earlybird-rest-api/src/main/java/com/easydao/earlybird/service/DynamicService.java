package com.easydao.earlybird.service;

import com.easydao.earlybird.bean.Dynamic;

public interface DynamicService {

    void addDynamic(Dynamic dynamic);
    Dynamic findDynamicByAccount(String user_account);
}
