package com.easydao.earlybird.vendor.fuen

import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods._
import org.scalatest.FunSuite


class FuenProxyTest extends FunSuite {
  implicit val formats = DefaultFormats
  val proxy = FuenProxy("http://39.98.66.62:7777/", "TEST@Inter", "k74JRHynOTCSGK0Q", "ynOTCSGK0QX49lpQ")

  test("Search接口 接口测试") {
    //Search接口
    val search = proxy.search("PEK", "PVG", "2019-10-02")
    println(compact(render(search)))
    "CA1415"
  }

  test("Price, Book, Order 接口测试") {
    //Price接口
    val price = proxy.price("PEK", "PVG", "2019-10-01", "MU5152")
    println(compact(render(price)))

    //Book接口
    val pid = ((price \ "vendors") (0) \ "pid").extract[String]
    val cid = ((price \ "vendors") (0) \ "cid").extract[String]
    val book = proxy.book(pid, cid)
    println(compact(render(book)))

    //Order接口
    {
      val printPrice = ((book \ "priceInfo" \ "priceTag" \ "ADU") (0) \ "viewPrice").extract[Double]
      val yPrice = (book \ "extInfo" \ "ticketPrice").extract[String].toDouble
      val contact = "姜磊"
      val contactPreNum = "86"
      val contactMob = "18600994598"
      val invoiceType = (book \ "expressInfo" \ "id").extract[Int]
      val sjr = "jiangjiang"
      val address = "addresssss"
      val flightInfo = OrderFlightInfo("MU5152", 1, 0, "PEK", "SHA", "北京", "上海", "2019-10-01", "1130", "1345", "B", "Y", "")
      val passengerCount = 1
      val passengers = List[OrderPassenger](OrderPassenger("姜磊", 0, "NI", "110104198904123517", 1, "1989-04-12", "OPL4", false, false, false))
      val bookingTag = (book \ "bookingTag").extract[String]

      val newCid = (book \ "extInfo" \ "cid").extract[String]
      val ordered = proxy.order(newCid, printPrice, yPrice, contact, contactPreNum, contactMob, invoiceType, sjr, address, flightInfo, passengerCount, passengers, bookingTag)
      println(compact(render(ordered)))
    }
  }

  test("Pay, PayValidate 接口测试") {
    val orderNo = "7a4f35680d8542fc8d2fa6d6a6b9af95"
    val payValidate = proxy.payValidate(orderNo)
    println(compact(render(payValidate)))

    val payed = proxy.pay(orderNo)
    println(compact(render(payed)))
  }

  test("OrderDetail 接口测试") {
    val orderNo = "7a4f35680d8542fc8d2fa6d6a6b9af95"
    val detail = proxy.orderDetail(orderNo)
    println(compact(render(detail)))

    {
      val name = ((detail \ "passengers") (0) \ "name").extract[String]
      val ageType = ((detail \ "passengerTypes") (0) \ "ageType").extract[String]
      val cardType = ((detail \ "passengers") (0) \ "cardType").extract[String]
      val cardNum = ((detail \ "passengers") (0) \ "cardNum").extract[String]
      val ticketNo = ((detail \ "passengers") (0) \ "ticketNo").extract[String]
      val passenger = RefundApplyPassenger(name, ageType, cardType, cardNum, ticketNo)

      val dptAirportCode = ((detail \ "flightInfo") (0) \ "dptAirportCode").extract[String]
      val arrAirportCode = ((detail \ "flightInfo") (0) \ "arrAirportCode").extract[String]
      val flightNum = ((detail \ "flightInfo") (0) \ "flightNum").extract[String]
      val deptTime = ((detail \ "flightInfo") (0) \ "deptTime").extract[String].substring(0, 11)
      val carrier = ((detail \ "flightInfo") (0) \ "flightNum").extract[String].substring(0, 2)
      val cabin = ((detail \ "flightInfo") (0) \ "cabin").extract[String]
      // price 接口返回
      val cabinType = "0"
      // price 接口返回
      val codeShare = false
      // price 接口返回 actCode
      val actFlightNum = null
      val flight = RefundApplyFlightInfo(dptAirportCode, arrAirportCode, flightNum, deptTime, carrier, cabin, cabinType, codeShare, actFlightNum)
      val refundApply = proxy.refundApply(orderNo, List(passenger), flight, "1")
      println(compact(render(refundApply)))
    }
  }
}
