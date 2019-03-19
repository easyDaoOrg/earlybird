package com.easydao.earlybird.service.impl;

import com.easydao.earlybird.bean.OrderAircraft;
import com.easydao.earlybird.mapper.ContactMapper;
import com.easydao.earlybird.mapper.OrderAircraftMapper;
import com.easydao.earlybird.mapper.PassengerMapper;
import com.easydao.earlybird.service.OrderAircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderAircraftServiceImpl implements OrderAircraftService {

    @Autowired
    private OrderAircraftMapper orderAircraftMapper;

    @Autowired
    private PassengerMapper passengerMapper;

    @Autowired
    private ContactMapper contactMapper;



    @Override
    public int addOrderAircraft(OrderAircraft orderAircraft) {
        Date date = new Date();
        orderAircraft.setCreate_date(date);
        orderAircraft.setUpdate_date(date);
        int random = (int)((Math.random()*9+1)*1000);
        orderAircraft.setOrder_no(System.currentTimeMillis()+""+random);
        return orderAircraftMapper.addOrderAircraft(orderAircraft);
    }

    @Override
    public void updateOrderAircraft(OrderAircraft orderAircraft) {
        Date date = new Date();
        orderAircraft.setUpdate_date(date);
        orderAircraftMapper.updateOrderAircraft(orderAircraft);
    }

    @Override
    public List<OrderAircraft> getOrderAircraftList(long user_id) {
        List<OrderAircraft> list = orderAircraftMapper.getOrderAircraftList(user_id);
        return list;
    }

    @Override
    public OrderAircraft getOrderAircraft(long id) {
        OrderAircraft orderAircraft = orderAircraftMapper.getOrderAircraft(id);
        String[] passengerIds = orderAircraft.getPassenger_id().split(",");
        String[] contactIds = orderAircraft.getContact_id().split(",");
        if(passengerIds.length>0){
            orderAircraft.setPassengerList(passengerMapper.getOrderPassengerList(passengerIds));
        }
        if(contactIds.length>0){
            orderAircraft.setContactList(contactMapper.getOrderContactList(contactIds));
        }
        return orderAircraft;
    }

}
