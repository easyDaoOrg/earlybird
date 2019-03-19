package com.easydao.earlybird.service.impl;

import com.easydao.earlybird.bean.Certificate;
import com.easydao.earlybird.mapper.CertificateMapper;
import com.easydao.earlybird.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private CertificateMapper certificate;


    @Override
    public void addCertificate(List<Certificate> list) {
        certificate.addCertificate(list);
    }

    @Override
    public void deleteCertificate(long passenger_id) {
        certificate.deleteCertificate(passenger_id);
    }

    @Override
    public List<Certificate> getCertificateList(long passenger_id) {
        return certificate.getCertificateList(passenger_id);
    }
}
