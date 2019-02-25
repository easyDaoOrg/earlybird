package com.easydao.earlybird.vendor.fuen

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
