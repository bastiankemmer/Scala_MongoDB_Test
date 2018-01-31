package com.test.mongo

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.test.mongo.model.MongoDBTestDB
import com.test.mongo.router.Router
import com.typesafe.config.ConfigFactory

object Boot extends App with Router {
  override implicit val system = ActorSystem()
  override implicit val executor = system.dispatcher
  override implicit val materializer = ActorMaterializer()

  override val config = ConfigFactory.load()
  override val logger = Logging(system, getClass)
  println("CONFIG: " + config)
  Http().bindAndHandle(routes, config.getString("http.interface"), config.getInt("http.port"))

  MongoDBTestDB.testDatabase
}
