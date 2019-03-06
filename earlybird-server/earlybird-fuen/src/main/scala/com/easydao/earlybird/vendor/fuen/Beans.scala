package com.easydao.earlybird.vendor.fuen

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}

//search
@JsonCreator
case class SearchParam(@JsonProperty("dpt") dpt: String, @JsonProperty("arr") arr: String, @JsonProperty("date") date: String) {

}

//price
@JsonCreator
case class PriceParam(@JsonProperty("dpt") dpt: String, @JsonProperty("arr") arr: String,
                      @JsonProperty("date") date: String, @JsonProperty("flightNum") flightNum: String) {

}

//book
@JsonCreator
case class BookParam(@JsonProperty("pid") pid: String, @JsonProperty("cid") cid: String,
                    //该参数后续的Refund Apply会使用
                     @JsonProperty("cabinType") cabinType: Int) {
}

//order
case class OrderParam(@JsonProperty("cid") cid: String,
                      @JsonProperty("printPrice") printPrice: Double,
                      @JsonProperty("yPrice") yPrice: Double,
                      @JsonProperty("contact") contact: String,
                      @JsonProperty("contactPreNum") contactPreNum: String,
                      @JsonProperty("contactMob") contactMob: String,
                      @JsonProperty("invoiceType") invoiceType: Int,
                      @JsonProperty("sjr") sjr: String,
                      @JsonProperty("address") address: String,
                      @JsonProperty("flightInfo") flightInfo: OrderFlightInfo,
                      @JsonProperty("passengerCount") passengerCount: Int,
                      @JsonProperty("passengers") passengers: List[OrderPassenger],
                      @JsonProperty("bookingTag") bookingTag: String
                     )

case class OrderFlightInfo(@JsonProperty("flightNum") flightNum: String,
                           @JsonProperty("flightType") flightType: Int,
                           @JsonProperty("stopInfo") stopInfo: Int,
                           @JsonProperty("deptAirportCode") deptAirportCode: String,
                           @JsonProperty("arriAirportCode") arriAirportCode: String,
                           @JsonProperty("deptCity") deptCity: String,
                           @JsonProperty("arriCity") arriCity: String,
                           @JsonProperty("deptDate") deptDate: String,
                           @JsonProperty("deptTime") deptTime: String,
                           @JsonProperty("arriTime") arriTime: String,
                           @JsonProperty("cabin") cabin: String,
                           @JsonProperty("childCabin") childCabin: String,

                          //后续Refund Apply会使用
//                           @JsonProperty("actFlightNum") actFlightNum: String,
//                           @JsonProperty("codeShare") codeShare: Boolean
                           @JsonProperty("gx") codeShare: String
                          ) {
}

case class OrderPassenger(@JsonProperty("name") name: String,
                          @JsonProperty("ageType") ageType: Int,
                          @JsonProperty("cardType") cardType: String,
                          @JsonProperty("cardNo") cardNo: String,
                          @JsonProperty("sex") sex: Int,
                          @JsonProperty("birthday") birthday: String,
                          @JsonProperty("passengerPriceTag") passengerPriceTag: String,
                          @JsonProperty("bx") bx: Boolean,
                          @JsonProperty("flightDelayBx") flightDelayBx: Boolean,
                          @JsonProperty("tuipiaoBx") tuipiaoBx: Boolean) {

}

//pay
case class PayParam(@JsonProperty("orderNo") orderNo: String) {

}

//order detail
case class OrderDetailParam(@JsonProperty("orderNo") orderNo: String) {

}

//refund apply
case class RefundApplyParam(@JsonProperty("orderNo") orderNo: String,
                            @JsonProperty("passengers") passengers: List[RefundApplyPassenger],
                            @JsonProperty("flightInfo") flightInfo: RefundApplyFlightInfo,
                            @JsonProperty("refundOrderType") refundOrderType: String) {
}


case class RefundApplyPassenger(name: String, ageType: String, cardType: String, cardNo: String, ticketNo: String) {

}

case class RefundApplyFlightInfo(dpt: String, arr: String, flightNum: String, flightDate: String, carrier: String,
                                 cabin: String, cabinType: String, codeShare: Boolean, actFlightNum: String = null) {

}

//refund notice
case class RefundNoticePassenger(name: String, ageType: String, cardType: String, cardNo: String, ticketNo: String,
                                 evidenceList: List[RefundNoticeEvidence]) {

}

case class RefundNoticeEvidence(evidenceUrl: String, evidenceExplain: String) {

}
