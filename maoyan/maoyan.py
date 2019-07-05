# -*- coding: utf-8 -*-
"""
Created on Thu Jul  4 21:11:20 2019

@author: coding.
@price:  50
@introduction:
            爬取猫眼即将上瘾电影并进行分析。
"""



import urllib.request

from lxml import etree
import matplotlib.pyplot as plt
import numpy as np
from matplotlib import cm
import json



def get_response(url, headers):
    """
    获取响应对象
    :param url: 请求的url
    :param headers: 请求头信息
    :return: 返回服务器的响应信息
    """
    req = urllib.request.Request(url, headers=headers)
    resp = urllib.request.urlopen(req)
    return resp.read()


def parse_html(result):
    """
    解析html信息，获取电影信息
    :param result: html信息
    :return:       item :格式化后数据
    """
    # 通过etree库将html信息转成对象
    
    html = etree.HTML(result)
    #通过xpath解析规则，获取需求信息
    rows = html.xpath('//table[@class="coming_list"]//tr')
    item = []
    for row in rows:
        td = row.xpath('td')
        if len(td) == 0:
            continue
        
        # 07月05日
        date = td[0].text.strip()
        # 爱宠大机密2
        name = td[1].xpath('a')[0].text.strip()
        #原始数据：喜剧 / 动画 / 冒险  清洗后数据： ['喜剧', '动画', '冒险']
        type = td[2].text.strip().split(' / ')
        #原始数据：中国大陆 / 香港     清洗后数据：['中国大陆', '香港']
        region = td[3].text.strip().split(' / ')
        #原始数据：25768人           清洗后数据：25768
        num = int(td[4].text.strip()[:-1])
        
        temp = [date,name,type,region,num]
        item.append(temp)
    datas = json.dumps(item,ensure_ascii=False,indent=4)  #ensure_ascii：使用中文保存，缩进为4个空格
    with open('maoyan.json','w+') as f: 
        f.write(datas)
    return item


def count_num(item):
    """
    解析电影信息数据，分析统计关注量并绘制横向柱状图图表
    :param item:   即将上映信息数据 
    :return:
    """
    # 按第四列进行排序
    item.sort(reverse=True,key=takecol4)
    labels = []
    nums = []
    temp = 0 
    # 关注度最高的10部电影
    for i in item:
        labels.append(i[1])
        nums.append(i[4])
        temp += 1
        if temp > 9:
            break
        
    #为实现图表更加美观进行数据翻转
    labels.reverse()
    nums.reverse()
    x = nums
    idx = np.arange(len(x))
    color = cm.jet(np.array(x)/max(x))
    plt.barh(idx, x, color=color)
    plt.yticks(idx+0.4,labels)
    plt.grid(axis='x')
    
    #设置数据标签
    for a,b in zip(idx,x):
        plt.text(b+1800,a-0.4,'%d'%int(b),ha='center',va='bottom')
    
    #添加行列标签和标题
    plt.xlabel('关注人数')
    plt.ylabel('电影名称')
    plt.title('电影关注排行榜')
    plt.show()
    

# 获取列表的第五个元素
def takecol4(elem):
    return elem[4]


def count_type(item):
    """
    解析电影信息数据，分析统计上映类型信息并绘制饼图
    :param item:   即将上映信息数据 
    :return:
    """
    
    types = []
    # 关注度最高的10部电影
    for i in item:
        types += i[2]
    count_dict = dict()
    for t in types:
        if t in count_dict:
            count_dict[t] += 1
        else:
            count_dict[t] = 1
    labels = count_dict.keys()
    types = count_dict.values()
    
    #color = cm.jet(np.array(types)/max(types))
    plt.pie(types, labels=labels, 
             autopct='%1.1f%%', startangle=90)
    plt.axis('equal')
    plt.show()
    


if __name__ == '__main__':
    """
        目标网站是：https://movie.douban.com/coming
    """

    # 需要爬取的url
    url = 'https://movie.douban.com/coming'
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.62 Safari/537.36'
    }
    # 将请求url的响应信息，通过xpath解析规则解析
    item = parse_html(get_response(url, headers))
    count_num(item)
    count_type(item)
    