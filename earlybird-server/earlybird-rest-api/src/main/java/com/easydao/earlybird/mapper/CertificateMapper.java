package com.easydao.earlybird.mapper;

import com.easydao.earlybird.bean.Certificate;
import com.easydao.earlybird.bean.Passenger;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CertificateMapper {

    void addCertificate(List<Certificate> list);

    void deleteCertificate(long passenger_id);

    List<Certificate> getCertificateList(long passenger_id);

}
