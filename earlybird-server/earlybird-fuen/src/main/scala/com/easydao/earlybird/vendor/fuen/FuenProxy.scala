package com.easydao.earlybird.vendor.fuen

import com.easydao.earlybird.util.Utils
import com.google.api.client.http.HttpResponseException

import scala.collection.JavaConverters._
import scala.collection.immutable.ListMap


class FuenProxy(host: String, userName: String, password: String, token: String) {

  @throws[HttpResponseException]("非200抛出异常")
  def search(dpt: String, arr: String, date: String): java.util.Map[String, Object] = {
    val url = host + FuenProxy.FLIGHT_SEARCH_API
    val param = FuenProxy.createSysParam(password, token, userName)
    param += "params" -> Map[String, String]("dpt" -> dpt, "arr" -> arr, "date" -> date).asJava
    Utils.httpPost(url, param.asJava)(FuenProxy.responseHandler)
  }

  @throws[HttpResponseException]("非200抛出异常")
  def price(dpt: String, arr: String, date: String, flightNum: String): java.util.Map[String, Object] = {
    val url = host + FuenProxy.FLIGHT_PRICE_API
    val param = FuenProxy.createSysParam(password, token, userName)
    param += "params" -> Map[String, String]("dpt" -> dpt, "arr" -> arr, "date" -> date, "flightNum" -> flightNum).asJava
    Utils.httpPost(url, param.asJava)(FuenProxy.responseHandler)
  }

  @throws[HttpResponseException]("非200抛出异常")
  def book(pid: String, cid: String): java.util.Map[String, Object] = {
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
  @throws[HttpResponseException]("非200抛出异常")
  def order(cid: String, flyFund: Boolean = false, isUseBonus: Boolean = false,
            fuelTax: Int, childFuelTax: Int, constructionFee: Int,
            printPrice: Double, yPrice: Double, childPrintPrice: Double,
            discount: Int, `type`: String = "", contact: String,
            contactPreNum: String, contactMob: String, contactEmail: String,
            invoiceType: Int, receiverTitle: String, receiverType: Int,
            taxpayerId: String, sjr: String, sjrPhone: String,
            address: String, xcd: String, xcdMethod: String = "",
            xcdPrice: Int, bxInvoice: Object, flightInfo: Object,
            passengerCount: Int,
            passengers: List[Object], bookingTag: String, xth: String

           ): java.util.Map[String, Object] = {
    val url = host + FuenProxy.FLIGHT_BOOK_API
    val param = FuenProxy.createSysParam(password, token, userName)
    param += "params" -> Map[String, String]("pid" -> pid, "cid" -> cid).asJava
    Utils.httpPost(url, param.asJava)(FuenProxy.responseHandler)
  }


}

object FuenProxy {
  val FLIGHT_SEARCH_API = "/flights/search"
  val FLIGHT_PRICE_API = "/flights/price"
  val FLIGHT_BOOK_API = "/flights/book"
  val FLIGHT_ORDER_API = "/orders/order"


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

  def responseHandler(response: java.util.Map[String, Object]): java.util.Map[String, Object] = {
    response.get("code").toString.toInt match {
      case 0 => {
        val res = response.get("result")
        if (res == null) throw new RuntimeException("返回值为空") else res.asInstanceOf[java.util.Map[String, Object]]
      }
      case 2002 => throw new RuntimeException("参数错误")
      case _ => throw new RuntimeException("请求错误")
    }
  }


  def main(args: Array[String]): Unit = {
    val proxy = FuenProxy("http://39.98.66.62:7777/", "TEST@Inter", "k74JRHynOTCSGK0Q", "ynOTCSGK0QX49lpQ")
    val res = proxy.book("Vma/KC3/v9FG2M3TO/nM1BGxIUZniqz+kjG5zy57LmeAgsnfM4rpw6sM+uee/AjbNRW988jPQWAVtKTKuNdqt8iyWoNQb2/GJCfdx5hh3z7MYLxKmAoK/JlhsvQAD3zuR3slxgcdL1uJdITRxzIzRXAxrDgZxv0o2cE0YcVvDNWhqi+dl9YvUkUexvgRszkX8U4Za4+xoy/c48jZqJDUBx/tpKU/cA4bqIu8RNf0i2ufNpiFHGO9bpzkU4PiaHyxhHlBLQxsJs6MSd5KVB/oecoIqaSz/PCCMGJaeMPDZl83mO48n5fVzCtxRPQGGUmIRYApJopWqCVYwR0vVti5FXvzmUQew2g+k8GrHYDR7vQF4o6jp0tUFe/0II06eHwPQSqiJf8Eaa4E4NZmK/vwvnqd4RTNJrVB1NRx+r/fbX+XdIqsVQQpU2jJA+iQcP/P/EJcs59zVk1c9y1UpOPST5HnurIUCotMEJVyK4xqL8zasCepsH5r5WDDOufzs0rsQdIiYzE7kr+WlANUB6pENdW5tCPqr8L4QIzqM5qrVEbyHAsmJVNXh+02JqTbyelXh1fGM4F3S9CXW3EBJd/BbG2xmTeG2F2QowilZ9csTx7gmsgw9eVxSzDlLCIApLR9HfV16nQrioLH+2mHmdDw5AQE6vzAHJ08tvw0PlhZTgJ4U0AmInBj0a+T0IqY8yl4pHHc1rtPeMN41bHLjNs8xeVNQo+86JiQ/2s1NoliIxxMJUddJmvd9GhqGblzMhGNJ20xoqR0UtZ/pS8o1sSKr4q8eohpQaoo5GOetMHXLFeaNjW8XWp8q+ln/VYac6Kt2h6LNmVx8hrNvBghqrlion0ufuJm1VOsfkZ+3WO/0glu1VGU28hxH0OW/r2VmYwUne5p03EXGKTlcFqFdVjoy0eB//3k502YZa7mxtuTX7DpVkriddTcUAFuNUliHLrTA9oqNs1Vle0J8z7GV5OeXFKk9Dbq/f4aGYvkNsUXJP4jEBlEYK9mT+MJ6eu5ySZsGwbWWLIvUOukW2PDn3I6LxP4qYBgTOTH2ui3Bh0WBAqG2GUFGoIsz1uxod7cNp15Er2fMVd+KjnvuWbYSAXj+w4Cwo9PWdWiTEe/QdQ9ymxNGfkQkVGJp1ExaaV2oWLkZSX3TFIluojTEWmMocwsMqMy5vCV0nf4znOSHZIKHBZljb37RnM3472QhVE3U+yO+Yf1kwkreb6q8o3z7k7Bkl2aQJ4PkwNgnGOfGw9yhd4SwwyJGBFbYg/IjtjsO+wnMCd3qClQYBKmRylQkCPfclYAXfQtigAfA/+e2nPS43M/gxO/ftHZVwqvztHrqsNDzugBx6AMh0yfGQTwxDa+N56lCbRknQzZt3xGSMTjPpzXatXHHbnElvRmvlHw6gZkSYbbffphN5+EbI0hRcYVwtOaCbF9uus+heAKCKR/Qj9+FY+XAvP8FOVr66cz8b7NJCACOUQ/BGKnSGfLmJ3i7KFVK2z+d5gNBl6GemFcfKBsdkibHZDhxgPKgKbSxUYt4SvOP41zYqTlSopkapdk8FiAbJYO3Dzil6uhBNg8eoX8lakRosP1WYno3k/WAPtr", "5424b435d85b0ae903a26d761f1ea7e0ff0e6090aa34325053955b7537ff0c311ccbca3b10b2743745fe4ef0a576c089859d8c9989e7f368d5953ae047b7d2eeda86b7c82f813921cb59f9aeaf385013c45bb4b0f0cd1c2b2a569b1555a7b06892fdd533b659b94352c8ea8b4dfc6adf7eacb20dcb66516612438c2a95501af798457a59f76d5e7711a337e34ab48be83e242bf1dd864de46aeba8a5ba0e4242fe83ce4dbb36c94b67fe1485ab7ba143ddced297495747f5ce85359ba68724129fee715ff7e9917e63c603567d2034d1572215a6bc88dbadc6bbeff2e168789c4ac77827b2ea187a04328bcb698e6c26cd4d56fef12ee44a238ed20d7bfc6f5039f52757a5c19dd5667bd83daae85c347f398fc81ea1b425eb3f9c27d605af6fbbce9e77502747c7d99b0180bc3dfce4e602cf36283b5e71df7f34def1da26cf8d68c7554217d291dc7f51d02765a6bf1a5433be93a0246b38a617beaea6e2e4efabdd339dd9721fa16128c1f5712a661f7265612e630bc9ee8aedaa2ebb2196")
    println(res)
  }
}