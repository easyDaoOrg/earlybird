package com.easydao.earlybird.service;

import com.easydao.earlybird.bean.OrderAircraft;

import java.util.List;

public interface OrderAircraftService {

    int addOrderAircraft(OrderAircraft orderAircraft);

    void updateOrderAircraft(OrderAircraft orderAircraft);

    List<OrderAircraft> getOrderAircraftList(long user_id);

    OrderAircraft getOrderAircraft(long id);
}
