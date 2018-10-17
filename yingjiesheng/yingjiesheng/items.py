# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class YingjieshengItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    
    #工作地点
    positionLoc=scrapy.Field()
    
    #工作名称
    positionName=scrapy.Field()
    
    #职位链接
    positionLink=scrapy.Field()
    
    #发布时间
    positionTime=scrapy.Field()
    
    
