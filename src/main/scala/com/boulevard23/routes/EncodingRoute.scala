package com.boulevard23.routes

import java.util.Base64

import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives
import com.boulevard23.contracts._
import com.boulevard23.services.CodecService
import com.typesafe.scalalogging.LazyLogging
import com.wix.accord._
import StatusCodes._
import spray.json.{JsString, JsObject}

class EncodingRoute(encodingService: CodecService) extends LazyLogging with Directives {

  import EncodingJsonFormatters._
  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport.sprayJsonMarshaller

  val route = {
    pathPrefix("encoding") {
      pathEndOrSingleSlash {
        post {
          entity(as[EncodingRequest]) { request =>
            com.wix.accord.validate(request) match {
              case com.wix.accord.Success =>
                complete(EncodingResponse(encodingService.encode(request.text)))
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
