#-*-coding:utf-8-*-
from redis import StrictRedis

redis=StrictRedis(host="127.0.0.1",port=6379,db=0,password="123")
redis.set("name","lty")
print(redis.get("name"))
print(redis.dbsize())