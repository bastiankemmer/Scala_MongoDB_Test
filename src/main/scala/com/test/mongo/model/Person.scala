package com.test.mongo.model

import org.mongodb.scala.MongoCollection
import org.mongodb.scala.bson.ObjectId

object Person {
  case class NewPerson(firstName: String, lastName: String, address: ObjectId)
  val name: String = "person"

  def getDatabase: MongoCollection[Person] = MongoDBTestDB.testDatabase.getCollection(name)

  def apply(firstName: String, lastName: String, address: ObjectId): Person = new Person(new ObjectId(), firstName, lastName, address)
}

//  name: { type: String, required: true },
//  project: { type: ObjectId, ref: "Project", required: true, index: true }
case class Person(_id: ObjectId, firstName: String, lastName: String, address: ObjectId)