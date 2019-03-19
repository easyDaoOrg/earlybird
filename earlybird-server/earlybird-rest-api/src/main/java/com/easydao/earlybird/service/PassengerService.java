package com.easydao.earlybird.service;

import com.easydao.earlybird.bean.Passenger;
import com.easydao.earlybird.bean.PassengerExtends;
import com.easydao.earlybird.bean.User;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface PassengerService {

    void addPassenger(PassengerExtends passengerExtends);

    void deletePassenger(long id);

    void updatePassenger(PassengerExtends passengerExtends);

    List<Passenger> getPassengerList(long user_id);

    Passenger getPassenger(long id);
}
