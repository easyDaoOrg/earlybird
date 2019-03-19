package com.easydao.earlybird.mapper;

import com.easydao.earlybird.bean.Passenger;
import com.easydao.earlybird.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface PassengerMapper {

    long addPassenger(Passenger passenger);

    void deletePassenger(long id);

    void updatePassenger(Passenger passenger);

    List<Passenger> getPassengerList(long user_id);

    List<Passenger> getOrderPassengerList(String[] ids);

    Passenger getPassenger(long id);

}
