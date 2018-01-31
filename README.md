# Scala_MongoDB_Test
Can not retrieve a mongodb collection on a Cloud Server.

See here https://github.com/bastiankemmer/Scala_MongoDB_Test/blob/master/src/main/scala/com/test/mongo/model/MongoDBTestDB.scala

MongoDatabase.getCollection(collectionName) will throw following Exception:

```javascript
Exception in thread "main" java.lang.VerifyError: Expecting a stackmap frame at branch target 50
Exception Details:
  Location:
    com/mongodb/async/client/MongoCollectionImpl.count(Lorg/bson/conversions/Bson;Lcom/mongodb/client/model/CountOptions;Lcom/mongodb/async/SingleResultCallback;)V @29: ifeq
  Reason:
    Expected stackmap frame at this location.
  Bytecode:
    0x0000000: 1405 ae37 0401 3a06 0236 0701 3a08 013a
    0x0000010: 0901 3a0a 013a 0b01 3a0c b205 ba99 0015
    0x0000020: 1604 b805 c037 0416 04b8 05c4 3a06 a700
    0x0000030: 0457 1906 c600 6319 06b8 05ca 3607 1604
    0x0000040: 1906 1405 ae15 0714 05cb 0902 b805 d037
    0x0000050: 042a b601 503a 0819 08b6 05d3 3a09 1908
    0x0000060: b605 d63a 0a2a b605 d83a 0b16 0419 062a
    0x0000070: 1305 d919 0919 0a2a b405 9b19 0b2b 012c
    0x0000080: 2db8 05df 4ea7 0012 3a0d 1604 1906 190d
    0x0000090: 1305 d92a b805 e52a 012b 2c2d b700 2319
    0x00000a0: 06c6 000f 1604 1906 1405 ae15 07b8 05b5
    0x00000b0: b13a 0e19 06c6 0011 1604 1906 1405 ae15
    0x00000c0: 0719 0eb8 05eb 190e bf                 
  Exception Handler Table:
    bci [32, 46] => handler: 49
    bci [151, 177] => handler: 177
    bci [81, 133] => handler: 136

	at com.mongodb.async.client.MongoDatabaseImpl.getCollection(MongoDatabaseImpl.java:168)
	at org.mongodb.scala.MongoDatabase.getCollection(MongoDatabase.scala:120)
	at com.test.mongo.model.MongoDBTestDB$.<init>(MongoDBTestDB.scala:23)
	at com.test.mongo.model.MongoDBTestDB$.<clinit>(MongoDBTestDB.scala)
	at com.test.mongo.Boot$.delayedEndpoint$com$test$mongo$Boot$1(Boot.scala:21)
	at com.test.mongo.Boot$delayedInit$body.apply(Boot.scala:11)
	at scala.Function0.apply$mcV$sp(Function0.scala:34)
	at scala.Function0.apply$mcV$sp$(Function0.scala:34)
	at scala.runtime.AbstractFunction0.apply$mcV$sp(AbstractFunction0.scala:12)
	at scala.App.$anonfun$main$1$adapted(App.scala:76)
	at scala.collection.immutable.List.foreach(List.scala:389)
	at scala.App.main(App.scala:76)
	at scala.App.main$(App.scala:74)
	at com.test.mongo.Boot$.main(Boot.scala:11)
	at com.test.mongo.Boot.main(Boot.scala)
```
