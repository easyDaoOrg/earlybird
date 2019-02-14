package com.easydao.earlybird.vendor.fuen

class FlightInfo {
  //接口传入    航班号
  var flightNum: String = null
  //Booking返回   共享航班号
  var gx: String = null
  //Booking返回   行程类型 1:单程，2：往返
  var flightType: Int = null
  //Booking返回   经停数
  var stopInfo: Int = null
  //接口传入    出发机场，大写，三字码
  var deptAirportCode: String = null
  //接口传入    到达机场，大写，三字码
  var arriAirportCode: String = null
  //接口传入    出发城市，汉字
  var deptCity: String = null
  //接口传入    到达城市，汉字
  var arriCity: String = null
  //接口传入    出发日期
  var deptDate: String = null
  //接口传入    出发时间
  var deptTime: String = null
  //接口传入    到达时间
  var arriTime: String = null
  //Booking返回   Booking返回，舱位
  var cabin: String = null
  //Booking返回   Booking返回，当BK此字段返回为null时，生单时此字段必传Y; 儿童舱位
  var childCabin: String = null
}

class Passenger {
  //接口传入
  var name: String = null
  //接口传入    0：成人；1：儿童；2：婴儿
  var ageType: Int = null
  //接口传入    NI:身份证,PP:护照,ID:其他
  var cardType: String = null
  //接口传入    证件号码
  var cardNo: String = null
  //接口传入    0: 女，1: 男
  var sex: Int = null
  //接口传入    如1986-10-19
  var birthday: String = null
  //Booking返回   乘机人报价tag，若为儿童，必填儿童tag，例如CHI1；若为婴儿，则填写婴儿tag 既然是Booking返回的那就不用管有什么具体值了
  var passengerPriceTag: String = null
  //接口传入    是否勾选航意险 false不勾选, true勾选
  var bx: Boolean = null
  //接口传入    是否勾选航延险 false不勾选 true勾选
  var flightDelayBx: Boolean = null
  //接口传入    保险产品类型(航延险/退票险)可以为空
  var flightDelayType: String = null
  //Booking返回   保险产品编码
  var flightDelayCode: String = null
  //接口传入    是否勾选退票险 false不勾选, true勾选
  var tuipiaoBx: Boolean = null
  //Booking返回   退票险产品编码
  var tuipiaoCode: String = null
}