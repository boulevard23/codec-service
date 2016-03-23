package com.boulevard23.routes

import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives
import com.boulevard23.contracts._
import com.boulevard23.services.CodecService
import com.typesafe.scalalogging.LazyLogging
import com.wix.accord._
import StatusCodes._
import spray.json.{JsString, JsObject}

class DecodingRoute(codec: CodecService) extends LazyLogging with Directives {

  import DecodingJsonFormatters._
  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport.sprayJsonMarshaller

  val route = {
    pathPrefix("decoding") {
      pathEndOrSingleSlash {
        post {
          entity(as[DecodingRequest]) { request =>
            com.wix.accord.validate(request) match {
              case com.wix.accord.Success =>
                complete(DecodingResponse(codec.decode(request.text)))
              case f@Failure(_) =>
                complete(BadRequest, for {v <- f.violations} yield {
                  JsObject(Map("error" -> JsString(v.constraint),
                    "description" -> JsString(v.description.getOrElse("")),
                    "value" -> JsString(v.value.toString)
                  ))
                })
            }
          }
        }
      }
    }
  }
}
