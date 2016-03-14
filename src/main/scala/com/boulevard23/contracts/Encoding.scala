package com.boulevard23.contracts

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.wix.accord.dsl._
import spray.json.DefaultJsonProtocol

case class EncodingRequest(text: String)
case class EncodingResponse(encoded: String)

object EncodingJsonFormatters extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val uidRequestValidation = validator[EncodingRequest] { request =>
    request.text is notEmpty
  }

  implicit val uidRequestFormat = jsonFormat1(EncodingRequest)
  implicit val uidResponseFormat = jsonFormat1(EncodingResponse)
}
