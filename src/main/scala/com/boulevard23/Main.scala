package com.boulevard23

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.boulevard23.routes.EncodingRoute
import com.boulevard23.services.Base64Service
import com.typesafe.scalalogging.LazyLogging

/**
  * Created by xiaowenwang on 3/13/16.
  */
object Main extends LazyLogging with App {
  implicit val system = ActorSystem("encoding-service")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val handler = new EncodingRoute(new Base64Service).route
  val (host, port) = ("localhost", 8110)
  val bindingFuture = Http().bindAndHandle(handler, host, port)

  bindingFuture onFailure {
    case ex: Exception =>
      logger.error(s"Failed to bind to $host:$port!", ex)
  }

  sys.addShutdownHook(system.terminate())
}
