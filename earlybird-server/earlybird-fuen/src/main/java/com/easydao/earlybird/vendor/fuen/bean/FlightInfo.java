package com.easydao.earlybird.vendor.fuen.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightInfo {

    public static FlightInfo create() {
        FlightInfo info = new FlightInfo();
        info.flightNum = "CA1415";
        info.flightType = 1;
        info.stopInfo = 0;
        info.deptAirportCode = "PEK";
        info.arriAirportCode = "CTU";
        info.deptCity = "北京";
        info.arriCity = "成都";
        info.deptDate = "2019-10-01";
        info.deptTime = "0955";
        info.arriTime = "1300";
        info.cabin = "Y";
        info.childCabin = "Y";
        return info;
    }

    //接口传入    航班号
    //  (book \ "flightInfo" \ "flightNum")
    //  (book \ "flightInfo" \ "actFlightNum")
    @JsonProperty
    public String flightNum = null;
    //Booking返回   共享航班号
//    public String gx = null;
    //Booking返回   行程类型 1:单程，2：往返
    //(book \ "extInfo"  \ "flightType" )
    @JsonProperty
    public int flightType = 0;
    //Booking返回   经停数
//  (book \ "flightInfo" \ "stops" )
    @JsonProperty
    public int stopInfo = 0;
    //接口传入    出发机场，大写，三字码
    @JsonProperty
    public String deptAirportCode = null;
    //接口传入    到达机场，大写，三字码
    @JsonProperty
    public String arriAirportCode = null;
    //接口传入    出发城市，汉字
    @JsonProperty
    public String deptCity = null;
    //接口传入    到达城市，汉字
    @JsonProperty
    public String arriCity = null;
    //接口传入    出发日期
    @JsonProperty
    public String deptDate = null;
    //接口传入    出发时间
    @JsonProperty
    public String deptTime = null;
    //接口传入    到达时间
    @JsonProperty
    public String arriTime = null;
    //Booking返回   Booking返回，舱位
    //(book \ "flightInfo"  \ "cabin" )
    @JsonProperty
    public String cabin = null;
    //Booking返回   Booking返回，当BK此字段返回为null时，生单时此字段必传Y; 儿童舱位
    //(book \ "flightInfo"  \ "childCabin" )
    @JsonProperty
    public String childCabin = null;
}
