package com.easydao.earlybird.mapper;

import com.easydao.earlybird.bean.OrderAircraft;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderAircraftMapper {

    int addOrderAircraft(OrderAircraft orderAircraft);

    void updateOrderAircraft(OrderAircraft orderAircraft);

    List<OrderAircraft> getOrderAircraftList(long user_id);

    OrderAircraft getOrderAircraft(long id);

}
