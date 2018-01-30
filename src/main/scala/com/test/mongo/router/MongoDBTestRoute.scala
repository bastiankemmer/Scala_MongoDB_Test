package com.test.mongo.router

import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.test.mongo.model.{Address, Person}

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}
import net.liftweb.json._
import net.liftweb.json.Serialization.write
import org.mongodb.scala.Completed

import scala.concurrent.ExecutionContext.Implicits.global

class MongoDBTestRoute {
  implicit val defaultFormats = DefaultFormats

  def getData(collectionName: String): Future[Any] = {
    if(collectionName.equals(Address.name)) {
      Address.getDatabase.find().toFuture()
    } else {
      Person.getDatabase.find().toFuture()
    }
  }

  def extractCollection(body: JValue): Either[Person.NewPerson, Address.NewAddress] = {
    body.extractOpt[Person.NewPerson] match {
      case Some(newPerson) => Left(newPerson)
      case None => Right(body.extractOrElse[Address.NewAddress](throw new Exception("Bad Params")))
    }
  }

  def createData(collectionName: String, body: JValue): Future[Completed] = {
    extractCollection(body) match {
      case Left(newPerson) => Person.getDatabase.insertOne(Person(newPerson.firstName, newPerson.lastName, newPerson.address)).toFuture()
      case Right(newAddress) => Address.getDatabase.insertOne(Address(newAddress.street, newAddress.housenumber, newAddress.zipCode, newAddress.city)).toFuture()
    }
  }

  val route: Route =
    pathPrefix("mongo") {
      pathPrefix("test") {
        (get & path(Segment)) { collectionName =>
          onComplete(getData(collectionName)) {
            case Success(value) =>
              complete(write(value))
            case Failure(e) => complete(e)
          }
        } ~
          (post & path(Segment) & entity(as[String])) { (collectionName, body)=>
            onComplete(createData(collectionName, parse(body))) {
              case Success(value) => complete(value.toString)
              case Failure(e) => complete(e)
            }
          }
      }
    }
}

object MongoDBTestRoute extends MongoDBTestRoute {
  case class PersonCreation()
}