package com.easydao.earlybird.vendor.fuen

import com.easydao.earlybird.util.Utils
import com.easydao.earlybird.vendor.fuen.bean.{FlightInfo, Passenger}
import com.google.api.client.http.HttpResponseException
import org.json4s.{DefaultFormats, JValue}

import scala.collection.JavaConverters._
import scala.collection.immutable.ListMap


class FuenProxy(host: String, userName: String, password: String, token: String) {
  implicit val formats = DefaultFormats

  @throws[HttpResponseException]("非200抛出异常")
  def search(dpt: String, arr: String, date: String): JValue = {
    val url = host + FuenProxy.FLIGHT_SEARCH_API
    val param = FuenProxy.createSysParam(password, token, userName)
    param += "params" -> Map[String, String]("dpt" -> dpt, "arr" -> arr, "date" -> date).asJava
    Utils.httpPost(url, param.asJava)(FuenProxy.responseHandler)
  }

  @throws[HttpResponseException]("非200抛出异常")
  def price(dpt: String, arr: String, date: String, flightNum: String): JValue = {
    val url = host + FuenProxy.FLIGHT_PRICE_API
    val param = FuenProxy.createSysParam(password, token, userName)
    param += "params" -> Map[String, String]("dpt" -> dpt, "arr" -> arr, "date" -> date, "flightNum" -> flightNum).asJava
    Utils.httpPost(url, param.asJava)(FuenProxy.responseHandler)
  }

  @throws[HttpResponseException]("非200抛出异常")
  def book(pid: String, cid: String): JValue = {
    val url = host + FuenProxy.FLIGHT_BOOK_API
    val param = FuenProxy.createSysParam(password, token, userName)
    param += "params" -> Map[String, String]("pid" -> pid, "cid" -> cid).asJava
    Utils.httpPost(url, param.asJava)(FuenProxy.responseHandler)
  }

  /**
    *
    * @param cid             BK接口返回extInfo中的cid
    * @param flyFund         固定传false
    * @param isUseBonus      固定传false
    * @param fuelTax         燃油税 Booking返回
    * @param childFuelTax    儿童燃油税 Booking返回
    * @param constructionFee 机建费 Booking返回
    * @param printPrice      票面价 Booking返回的viewPrice
    * @param yPrice          公布运价 Booking返回ticketPrice
    * @param childPrintPrice 儿童票面价 Booking返回
    * @param discount        票面价折扣 Booking返回
    * @param `type`          暂时不需要，设置为空串 ---
    * @param contact         联系人姓名
    * @param contactPreNum   国家区位码
    * @param contactMob      联系人电话
    * @param contactEmail    联系人邮箱
    * @param invoiceType     发票类型, 1:旅行产品发票,2:行程单和差额发票 Booking返回
    * @param receiverTitle   发票抬头
    * @param receiverType    抬头类型, 2：个人, 3：单位,企业，4：政府机关单位
    * @param taxpayerId      纳税人识别号 若receiverType=3此值必填
    * @param sjr             收件人姓名
    * @param sjrPhone        收件人电话
    * @param address         收件人地址
    * @param xcd             是否邮寄行程单（即是否勾选行程单选项）"1"：勾选 ，""：未勾选
    * @param xcdMethod       若xcg="1"则此值必须填写"8"
    * @param xcdPrice        快递费，暂时不传递该值
    * @param bxInvoice       是有邮寄保险发票 "1"：勾选 ， ""：未勾选
    * @param flightInfo      航段信息
    * @param passengerCount  乘机人数
    * @param passengers      乘机人信息
    * @param bookingTag      booking返回结果中的 bookingTag
    * @param xth             1：代表新特惠返现，目前先不传递该值
    * @throws
    * @return
    */
  //  def order(cid: String, flyFund: Boolean = false, isUseBonus: Boolean = false,
  //            fuelTax: Int, childFuelTax: Int, constructionFee: Int,
  //            printPrice: Double, yPrice: Double, childPrintPrice: Double,
  //            discount: Int, `type`: String = "", contact: String,
  //            contactPreNum: String, contactMob: String, contactEmail: String,
  //            invoiceType: Int, receiverTitle: String, receiverType: Int,
  //            taxpayerId: String, sjr: String, sjrPhone: String,
  //            address: String, xcd: String, xcdMethod: String = "",
  //            xcdPrice: Int, bxInvoice: String, flightInfo: FlightInfo,
  //            passengerCount: Int,
  //            passengers: List[Passenger], bookingTag: String, xth: String
  //
  //           ): JValue = {
  @throws[HttpResponseException]("非200抛出异常")
  def order(cid: String,
            printPrice: Double, yPrice: Double,
            contact: String,
            contactPreNum: String, contactMob: String,
            invoiceType: Int,
            sjr: String,
            address: String,
            flightInfo: FlightInfo,
            passengerCount: Int,
            passengers: List[Passenger], bookingTag: String

           ): JValue = {
    val url = host + FuenProxy.FLIGHT_ORDER_API
    val param = FuenProxy.createSysParam(password, token, userName)
    param += "params" -> Map[String, Any](
      //flyFund 从booking都传什么过来，要求固定false
      "cid" -> cid, "flyFund" -> false, "isUseBonus" -> false,
      //非必填项，但是值从booking来，看看需不需要传递，感觉需要
      //      "fuelTax" -> fuelTax, "childFuelTax" -> childFuelTax, "constructionFee" -> constructionFee,
      "printPrice" -> printPrice, "yPrice" -> yPrice,
      //非必填，只有discount在bookink返回中
      //      "childPrintPrice" -> childPrintPrice, "discount" -> discount, "type" -> "",
      "contact" -> contact, "contactPreNum" -> contactPreNum, "contactMob" -> contactMob,
      //非必填
      //      "contactEmail" -> contactEmail,
      "invoiceType" -> invoiceType,
      //非必填
      //      "receiverTitle" -> receiverTitle, "receiverType" -> receiverType, "taxpayerId" -> taxpayerId,
      "sjr" -> sjr, "address" -> address,
      //非必填
      //      "sjrPhone" -> sjrPhone,
      "xcd" -> "", "xcdMethod" -> "",
      //非必填
      //      "xcdPrice" -> 0,
      "bxInvoice" -> "", "flightInfo" -> flightInfo, "passengerCount" -> passengerCount,
      "passengers" -> passengers.asJava, "bookingTag" -> bookingTag
      //不知道填什么，不传递了
      //      "xth" -> xth
    ).asJava
    val x = param.asJava
    Utils.httpPost(url, param.asJava)(FuenProxy.responseHandler)
  }

  @throws[HttpResponseException]("非200抛出异常")
  def pay(orderNo: String): JValue = {
    val url = host + FuenProxy.FLIGHT_PAY_API
    val param = FuenProxy.createSysParam(password, token, userName)
    param += "params" -> Map[String, String]("orderNo" -> orderNo, "curId" -> "CNY").asJava
    Utils.httpPost(url, param.asJava)(FuenProxy.responseHandler)
  }

  @throws[HttpResponseException]("非200抛出异常")
  def payValidate(orderNo: String): JValue = {
    val url = host + FuenProxy.FLIGHT_PAY_VALIDATE_API
    val param = FuenProxy.createSysParam(password, token, userName)
    param += "params" -> Map[String, String]("orderNo" -> orderNo).asJava
    Utils.httpPost(url, param.asJava)(FuenProxy.responseHandler)
  }

  @throws[HttpResponseException]("非200抛出异常")
  def orderDetail(orderNo: String): JValue = {
    val url = host + FuenProxy.FLIGHT_ORDER_DETAIL_API
    val param = FuenProxy.createSysParam(password, token, userName)
    param += "params" -> Map[String, String]("orderNo" -> orderNo).asJava
    Utils.httpPost(url, param.asJava)(FuenProxy.responseHandler)
  }
}

object FuenProxy {
  implicit val formats = DefaultFormats
  val FLIGHT_SEARCH_API = "/flights/search"
  val FLIGHT_PRICE_API = "/flights/price"
  val FLIGHT_BOOK_API = "/flights/book"
  val FLIGHT_ORDER_API = "/orders/order"
  val FLIGHT_PAY_API = "/pay/pay"
  val FLIGHT_ORDER_DETAIL_API = "/orders/orderDetail"
  val FLIGHT_PAY_VALIDATE_API = "/pay/payValidate"

  /**
    *
    * @param host 形如 http://39.98.66.62:7777/
    * @param userName
    * @param password
    * @param token
    * @return
    */
  def apply(host: String, userName: String, password: String, token: String): FuenProxy = {
    new FuenProxy(host, userName, password, token)
  }

  /**
    * sign的生成规则需要安key进行字典排序
    * 如：createTime=1549184420749password=k74JRHynOTCSGK0Qtoken=ynOTCSGK0QX49lpQuserName=TEST@Inter
    *
    * @param password
    * @param token
    * @param userName
    * @return
    */
  def genSign(createTime: String, password: String, token: String, userName: String): String = {
    val kv = ListMap[String, String](
      "createTime" -> createTime,
      "password" -> password,
      "token" -> token,
      "userName" -> userName)
    val str = kv.map(kv => kv._1 + "=" + kv._2).mkString
    Utils.md5Str(str)
  }

  def createSysParam(password: String, token: String, userName: String): scala.collection.mutable.Map[String, Object] = {
    val createTime = System.currentTimeMillis().toString
    scala.collection.mutable.Map[String, Object](
      "createTime" -> createTime,
      "token" -> token,
      "userName" -> userName,
      "sign" -> genSign(createTime, password, token, userName))
  }

  def responseHandler(response: JValue): JValue = {
    val code = (response \ "code").extractOrElse[Int](-1)
    val message = (response \ "message").extractOrElse[String](null)
    code match {
      case 0 => {
        val res = (response \ "result")
        if (res == null) throw new RuntimeException("返回值为空") else res
      }
      case 2002 => throw new RuntimeException(s"参数错误: ${message}")
      case _ => throw new RuntimeException(s"请求错误: ${message}")
    }
  }

  def main(args: Array[String]): Unit = {

  }
}