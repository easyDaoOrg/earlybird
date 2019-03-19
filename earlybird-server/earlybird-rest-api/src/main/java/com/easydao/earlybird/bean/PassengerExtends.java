package com.easydao.earlybird.bean;

import java.util.List;

public class PassengerExtends extends Passenger {

    private static final long serialVersionUID = 5784382425729731715L;
    private List<Certificate> certificateList;

    public List<Certificate> getCertificateList() {
        return certificateList;
    }

    public void setCertificateList(List<Certificate> certificateList) {
        this.certificateList = certificateList;
    }
}
