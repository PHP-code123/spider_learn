#-*-coding:utf-8-*-
import requests
from pyquery import PyQuery as pq
import json
from threading import Thread
import pymongo

client=pymongo.MongoClient(host="localhost",port=27017)

db=client.shixisheng
coll=db.java

headers={
    "User-Agent":"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:61.0) Gecko/20100101 Firefox/61.0"
}
#key=quote(input())
key="java"
def joblist(i):
    r=requests.get("https://androidapi.shixiseng.com/app/interns/search?k="+key+"&c=%E5%85%A8%E5%9B%BD&s=-0&d=&page="+str(i)+"&m=&x=&z=&st=intern&ft=&t=zj",headers=headers)
    json_str=r.text.encode('utf-8').decode('unicode_escape')
    #print(json_str)
    res = json.loads(json_str)
    print("======="+"第"+str(i)+"次"+"joblist-code:"+str(res["code"])+"======")
    for msg in res["msg"]:
        if msg==[]:
            break
        uuid=msg["uuid"];
        minsal=msg["minsal"]
        maxsal=msg["maxsal"]
        city=msg["city"]
        url=msg["url"]
        refresh=msg["refresh"]
        name=msg["name"]
        cname=msg["cname"]
        info=detail(uuid)
        res_info={"uuid":uuid,"minsal":minsal,"maxsal":maxsal,"city":city,"url":url,"refresh":refresh,"name":name,"cname":cname,"info":info}
        result = coll.insert(res_info)
        print(uuid)
        print("===========================")


def detail(uuid):
    r=requests.get("https://androidapi.shixiseng.com/app/intern/info?uuid="+uuid,headers=headers)
    json_str = r.text
    res = json.loads(json_str)
    print("=======detail--code:" + str(res["code"]) + "======")
    msg=res["msg"]
    #print(msg)
    info=pq(msg["info"]).text().replace("\n","");
    return info

if __name__ == '__main__':

    for i in range(1,1000):
        t = Thread(target=joblist, args=(i,))
        t.start()