package com.easydao.earlybird.service;

import com.easydao.earlybird.bean.Certificate;
import com.easydao.earlybird.bean.Passenger;

import java.util.List;

public interface CertificateService {

    void addCertificate(List<Certificate> list);

    void deleteCertificate(long passenger_id);

    List<Certificate> getCertificateList(long passenger_id);
}
