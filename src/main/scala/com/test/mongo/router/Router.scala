package com.test.mongo.router

import akka.actor.ActorSystem
import akka.event.LoggingAdapter
import akka.http.scaladsl.server.Directives._
import akka.stream.Materializer
import com.typesafe.config.Config

import scala.concurrent.ExecutionContextExecutor

trait Router {
  implicit val system: ActorSystem

  implicit def executor: ExecutionContextExecutor

  implicit val materializer: Materializer

  def config: Config

  val logger: LoggingAdapter

  val routes = {
    logRequestResult("akka-http-microservice") {
      pathPrefix("api") {
        pathPrefix("v1") {
          MongoDBTestRoute.route
        }
      }
    }
  }
}
