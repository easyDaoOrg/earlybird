package com.easydao.earlybird.util

import java.io._
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.http.{ByteArrayContent, GenericUrl}
import org.json4s._
import org.json4s.jackson.JsonMethods._


object Utils {
  def tryWithResource[R <: Closeable, T](createResource: => R)(f: R => T): T = {
    val resource = createResource
    try f.apply(resource) finally resource.close()
  }

  def md5Str(str: String): String = {
    val msgDigest: MessageDigest = MessageDigest.getInstance("MD5")
    val MD5Hash = msgDigest.digest(str.getBytes(StandardCharsets.UTF_8)).map(0xFF & _).map {
      "%02x".format(_)
    }.foldLeft("") {
      _ + _
    }
    MD5Hash.toLowerCase
  }

  def httpPost(url: String, data: java.util.Map[String, Object])(handler: JValue => JValue): JValue = {
    val mapper = new ObjectMapper() with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)
    val json = mapper.writeValueAsString(data)
    val factory = new NetHttpTransport().createRequestFactory()
    val request = factory.buildPostRequest(new GenericUrl(url), ByteArrayContent.fromString("application/json", json))
    request.setConnectTimeout(10 * 1000)
    request.setReadTimeout(10 * 1000)
    val res = parse(request.execute().parseAsString())
    handler.apply(res)
  }

  def toJson(json: JValue): Map[String, Any] = {
    implicit val formats = DefaultFormats
    json.extract[Map[String, Any]]
  }
}
