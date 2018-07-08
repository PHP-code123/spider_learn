#-*-coding:utf-8-*-

import pymongo

client=pymongo.MongoClient(host="localhost",port=27017)

db=client.shixisheng
coll=db.sx
result=coll.insert({"id":"2015015098"})
print(result)
result=coll.find_one({"id":"2015015098"})
print(result)
print(coll.remove({"id":"2015015098"}))
print(coll.find().count())