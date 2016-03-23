package com.boulevard23.contracts

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.wix.accord.dsl._
import spray.json.DefaultJsonProtocol

case class EncodingRequest(text: String)

case class EncodingResponse(encoded: String)

object EncodingJsonFormatters extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val encodingRequestValidation = validator[EncodingRequest] { request =>
    request.text is notEmpty
  }

  implicit val encodingRequestFormat = jsonFormat1(EncodingRequest)

  implicit val encodingResponseFormat = jsonFormat1(EncodingResponse)
}
