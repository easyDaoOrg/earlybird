package com.easydao.earlybird.vendor.fuen.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Passenger {
    public static Passenger create() {
        Passenger passenger = new Passenger();
        passenger.name = "姜磊";
        passenger.ageType = 0;
        passenger.cardType = "NI";
        passenger.cardNo = "110104198904123517";
        passenger.sex = 1;
        passenger.birthday = "1989-04-12";
        passenger.passengerPriceTag = "OPL4";
        passenger.bx = false;
        passenger.flightDelayBx = false;
        passenger.tuipiaoBx = false;
        return passenger;
    }

    @JsonProperty
    public String name = null;
    //接口传入    0：成人；1：儿童；2：婴儿
    @JsonProperty
    public int ageType = 0;
    //接口传入    NI:身份证,PP:护照,ID:其他
    @JsonProperty
    public String cardType = null;
    //接口传入    证件号码
    @JsonProperty
    public String cardNo = null;
    //接口传入    0: 女，1: 男
    @JsonProperty
    public int sex = 0;
    //接口传入    如1986-10-19
    @JsonProperty
    public String birthday = null;
    //Booking返回   乘机人报价tag，若为儿童，必填儿童tag，例如CHI1；若为婴儿，则填写婴儿tag 既然是Booking返回的那就不用管有什么具体值了
    //(book \"priceInfo" \"priceTag") .key
    @JsonProperty
    public String passengerPriceTag = null;
    //接口传入    是否勾选航意险 false不勾选, true勾选
    @JsonProperty
    public boolean bx = false;
    //接口传入    是否勾选航延险 false不勾选 true勾选
    @JsonProperty
    public boolean flightDelayBx = false;
    //接口传入    保险产品类型(航延险/退票险)可以为空
//    public String flightDelayType = null;
    //Booking返回   保险产品编码
//    public String flightDelayCode = null;
    //接口传入    是否勾选退票险 false不勾选, true勾选
    @JsonProperty
    public boolean tuipiaoBx = false;
    //Booking返回   退票险产品编码
//    public String tuipiaoCode = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAgeType() {
        return ageType;
    }

    public void setAgeType(int ageType) {
        this.ageType = ageType;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPassengerPriceTag() {
        return passengerPriceTag;
    }

    public void setPassengerPriceTag(String passengerPriceTag) {
        this.passengerPriceTag = passengerPriceTag;
    }

    public boolean isBx() {
        return bx;
    }

    public void setBx(boolean bx) {
        this.bx = bx;
    }

    public boolean isFlightDelayBx() {
        return flightDelayBx;
    }

    public void setFlightDelayBx(boolean flightDelayBx) {
        this.flightDelayBx = flightDelayBx;
    }

    public boolean isTuipiaoBx() {
        return tuipiaoBx;
    }

    public void setTuipiaoBx(boolean tuipiaoBx) {
        this.tuipiaoBx = tuipiaoBx;
    }
}
