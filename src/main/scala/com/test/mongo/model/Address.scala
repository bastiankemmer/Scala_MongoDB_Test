package com.test.mongo.model

import org.mongodb.scala.MongoCollection
import org.mongodb.scala.bson.ObjectId

object Address {
  case class NewAddress(street: String, housenumber: Int, zipCode: Int, city: String)

  val name: String = "address"

  def getDatabase: MongoCollection[Address] = MongoDBTestDB.testDatabase.getCollection(name)

  def apply(street: String, housenumber: Int, zipCode: Int, city: String): Address = new Address(new ObjectId(), street, housenumber, zipCode, city)
}

//  name: { type: String, required: true },
//  project: { type: ObjectId, ref: "Project", required: true, index: true }
case class Address(_id: ObjectId, street: String, housenumber: Int, zipCode: Int, city: String)