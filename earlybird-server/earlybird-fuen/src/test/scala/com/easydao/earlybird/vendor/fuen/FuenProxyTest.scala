package com.easydao.earlybird.vendor.fuen

import com.easydao.earlybird.vendor.fuen.bean.{FlightInfo, Passenger}
import org.json4s.DefaultFormats
import org.scalatest.FunSuite
import org.json4s.jackson.JsonMethods._


class FuenProxyTest extends FunSuite {
  implicit val formats = DefaultFormats
  val proxy = FuenProxy("http://39.98.66.62:7777/", "TEST@Inter", "k74JRHynOTCSGK0Q", "ynOTCSGK0QX49lpQ")

  test("Price, Book, Order 接口测试") {
    //Price接口
    val price = proxy.price("PEK", "CTU", "2019-10-01", "CA1415")
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
      val xcd = ""
      val xcdMethod = ""
      val bxInvoice = ""
      val flightInfo = FlightInfo.create()
      val passengerCount = 1
      val passengers = List[Passenger](Passenger.create())
      val bookingTag = (book \ "bookingTag").extract[String]

      val newCid = (book \ "extInfo" \ "cid").extract[String]
      val ordered = proxy.order(newCid, printPrice, yPrice, contact, contactPreNum, contactMob, invoiceType, sjr, address, flightInfo, passengerCount, passengers, bookingTag)
      println(compact(render(ordered)))
    }
  }

  test("Pay 接口测试") {
    val orderNo = "3962f00561dd4bb684a2b69c077a8d44"
    val payed = proxy.pay(orderNo)
    println(compact(render(payed)))
  }

  test("OrderDetail 接口测试") {
    val orderNo = "3962f00561dd4bb684a2b69c077a8d44"
    val detail = proxy.orderDetail(orderNo)
    println(compact(render(detail)))
  }
}
