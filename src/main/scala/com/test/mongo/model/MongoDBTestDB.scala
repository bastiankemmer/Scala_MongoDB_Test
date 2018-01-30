package com.test.mongo.model

import com.typesafe.config.{Config, ConfigFactory}
import org.bson.codecs.configuration.CodecRegistry
import org.mongodb.scala.{MongoClient, MongoDatabase}

object MongoDBTestDB {
  val config: Config = ConfigFactory.load()
  println(config)

  import org.mongodb.scala.bson.codecs.Macros._
  import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
  import org.bson.codecs.configuration.CodecRegistries.{ fromRegistries, fromProviders }
  val codecRegistry: CodecRegistry = fromRegistries(
    fromProviders(classOf[Person]), fromRegistries(
      fromProviders(classOf[Address]), DEFAULT_CODEC_REGISTRY))

  val mongoClient: MongoClient = MongoClient()

  // get handle to "mydb" database
  val testDatabase: MongoDatabase = mongoClient.getDatabase(config.getString("mongo.db")).withCodecRegistry(codecRegistry)
}
