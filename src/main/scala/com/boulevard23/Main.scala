package com.boulevard23

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.boulevard23.routes.{DecodingRoute, EncodingRoute}
import com.boulevard23.services.Base64Service
import com.typesafe.scalalogging.LazyLogging
import akka.http.scaladsl.server.Directives._

object Main extends App with LazyLogging {
  implicit val system = ActorSystem("encoding-service")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val encodingRoute = new EncodingRoute(new Base64Service).route
  val decodingRoute = new DecodingRoute(new Base64Service).route
  val handler = encodingRoute ~ decodingRoute
  val (host, port) = ("localhost", 8110)
  val bindingFuture = Http().bindAndHandle(handler, host, port)

  bindingFuture onFailure {
    case ex: Exception =>
      logger.error(s"Failed to bind to $host:$port!", ex)
  }

  sys.addShutdownHook(system.terminate())
}
