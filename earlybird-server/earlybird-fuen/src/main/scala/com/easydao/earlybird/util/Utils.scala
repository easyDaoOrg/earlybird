package com.easydao.earlybird.util

import java.io._
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

import com.google.api.client.http.GenericUrl
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.http.json.JsonHttpContent
import com.google.api.client.json.JsonObjectParser
import com.google.api.client.json.jackson2.JacksonFactory

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

  def httpPost(url: String, json: java.util.Map[String, Object])(handler: java.util.Map[String, Object] => java.util.Map[String, Object]): java.util.Map[String, Object] = {
    val factory = new NetHttpTransport().createRequestFactory()
    val request = factory.buildPostRequest(new GenericUrl(url), new JsonHttpContent(new JacksonFactory(), json))
    request.setConnectTimeout(10 * 1000)
    request.setReadTimeout(10 * 1000)
    request.setParser(new JsonObjectParser(new JacksonFactory()))
    val res = request.execute.parseAs(classOf[java.util.Map[String, Object]])
    handler.apply(res)
  }
}
