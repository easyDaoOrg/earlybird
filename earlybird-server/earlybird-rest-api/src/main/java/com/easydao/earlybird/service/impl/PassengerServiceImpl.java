package com.easydao.earlybird.service.impl;

import com.alibaba.fastjson.JSON;
import com.easydao.earlybird.bean.Certificate;
import com.easydao.earlybird.bean.Passenger;
import com.easydao.earlybird.bean.PassengerExtends;
import com.easydao.earlybird.mapper.CertificateMapper;
import com.easydao.earlybird.mapper.PassengerMapper;
import com.easydao.earlybird.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerMapper passengerMapper;

    @Autowired
    private CertificateMapper certificateMapper;

    @Override
    public void addPassenger(PassengerExtends passengerExtends) {

        List<Certificate> certificateList = passengerExtends.getCertificateList();
        //添加旅客
        passengerMapper.addPassenger(passengerExtends);
        System.out.println("passenger_id:["+passengerExtends.getId()+"]");
        for(Certificate bean : certificateList){
            bean.setPassenger_id(passengerExtends.getId());
        }
        //添加用户证件信息
        certificateMapper.addCertificate(certificateList);
    }

    @Override
    public void deletePassenger(long id) {

        passengerMapper.deletePassenger(id);
        certificateMapper.deleteCertificate(id);
    }

    @Override
    public void updatePassenger(PassengerExtends passengerExtends) {

        List<Certificate> certificateList = passengerExtends.getCertificateList();
        for(Certificate bean : certificateList){
            bean.setPassenger_id(passengerExtends.getId());
        }
        passengerMapper.updatePassenger(passengerExtends);
        certificateMapper.deleteCertificate(passengerExtends.getId());
        //添加用户证件信息
        certificateMapper.addCertificate(certificateList);
    }

    @Override
    public List<Passenger> getPassengerList(long user_id) {
        return passengerMapper.getPassengerList(user_id);
    }

    @Override
    public Passenger getPassenger(long id) {
        return passengerMapper.getPassenger(id);
    }
}
