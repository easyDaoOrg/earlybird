package com.easydao.earlybird.vendor.fuen

import com.easydao.earlybird.vendor.fuen.bean.{FlightInfo, Passenger}
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
case class BookParam(@JsonProperty("pid") pid: String, @JsonProperty("cid") cid: String) {

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
                      @JsonProperty("flightInfo") flightInfo: FlightInfo,
                      @JsonProperty("passengerCount") passengerCount: Int,
                      @JsonProperty("passengers") passengers: List[Passenger],
                      @JsonProperty("bookingTag") bookingTag: String
                     )

//pay
case class PayParam(@JsonProperty("orderNo") orderNo: String) {

}

//order detail
case class OrderDetailParam(@JsonProperty("orderNo") orderNo: String) {

}

//refund apply
case class RefundApplyParam (@JsonProperty("orderNo") orderNo: String,
                       @JsonProperty("passengers") passengers: List[RefundApplyPassenger],
                       @JsonProperty("flightInfo") flightInfo: RefundApplyFlightInfo,
                       @JsonProperty("refundOrderType") refundOrderType: String) {
}


case class RefundApplyPassenger(name: String, ageType: String, cardType: String, cardNo: String, ticketNo: String) {

}

case class RefundApplyFlightInfo(dpt: String, arr: String, flightNum: String, flightDate: String, carrier: String,
                                 cabin: String, cabinType: String, codeShare: Boolean, actFlightNum: String = null) {

}


case class RefundNoticePassenger(name: String, ageType: String, cardType: String, cardNo: String, ticketNo: String,
                                 evidenceList: List[RefundNoticeEvidence]) {

}

case class RefundNoticeEvidence(evidenceUrl: String, evidenceExplain: String) {

}
