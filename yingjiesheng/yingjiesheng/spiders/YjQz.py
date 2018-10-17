# -*- coding: utf-8 -*-
import scrapy
from yingjiesheng.items import YingjieshengItem

class YjqzSpider(scrapy.Spider):
    """
        功能：爬取应届生求职网职位信息。
            使用scrapy startproject Tencent创建scrapy项目。
            使用scrapy genspider tencentPostion "tencent.com"
            创建基础爬虫类，即本类
            使用scrapy crwal tencentPosition开始项目爬取
    """
    
    #爬虫名称
    name = 'YjQz'
    
    #爬虫范围
    allowed_domains = ['yingjiesheng.com']
    
    #开始链接
    page=1
    url='http://www.yingjiesheng.com/commend-parttime-'+str(page)+'.html'
    start_urls = [url]

    def parse(self, response):
        
        for each in response.xpath("//tr[@class='bg_0'] | //tr[@class='bg_1']"):
            item=YingjieshengItem()
            
            item['positionLoc']=each.xpath("./td/a/span[@style='color: #008000;']/text()").extract()
            
            item['positionName']=each.xpath("./td/a/text()").extract()
            
            
            link=each.xpath("./td/a/@href").extract()
            item['positionLink']=link[0]
            if(link[0].rfind("/job")==0):
                item['positionLink']="http://www.yingjiesheng.com"+link[0]
            
            
            item['positionTime']=each.xpath("./td[@class='date']/text()").extract()
            
            yield item
        
        
        self.page+=1
        self.url='http://www.yingjiesheng.com/commend-parttime-'+str(self.page)+'.html'
        print("aaaaaaaaaaaaaahah:"+self.url)
        yield scrapy.Request(self.url, callback = self.parse,dont_filter=True)
        
        
            
        
        
