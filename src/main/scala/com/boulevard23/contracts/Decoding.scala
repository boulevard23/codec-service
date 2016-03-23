package com.boulevard23.contracts

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol
import com.wix.accord.dsl._

case class DecodingRequest(text: String)

case class DecodingResponse(decoded: String)

object DecodingJsonFormatters extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val decodingRequestValidation = validator[DecodingRequest] { request =>
    request.text is notEmpty
  }

  implicit val decodingRequestFormat = jsonFormat1(DecodingRequest)

  implicit val decodingResponseFormat = jsonFormat1(DecodingResponse)
}
