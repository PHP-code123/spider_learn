package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class yingjiesheng {

public static void main(String[] args) {
	for(int zz=1;zz<=30;zz++)
	{
		try
		{
			URL url = new URL("http://m.yingjiesheng.com/result.php?city=0&word=nlp&do=1&keyword=nlp&jobtype=&Page="+zz);
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-Type", "text/html; charset=utf-8");
	        conn.setRequestProperty("Host", "m.yingjiesheng.com");
	        conn.setRequestProperty("Referer", "http://m.yingjiesheng.com");
	        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html£©");
	        
	        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
	         
	        StringBuilder sb = new StringBuilder();
	        for (int c; (c = in.read()) >= 0;)
	            sb.append((char)c);
	        String html = sb.toString();
	        
	        Document doc = Jsoup.parse(html);
	        Elements titles = doc.select("a.clearfix");
	        for (Element title : titles)
	        {
	        	//System.out.println(title.attr("href"));
	        	
	        	String text=title.text();
	        	System.out.println(text);
	        	detail_text.detail(title.attr("href"));
	        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
}
}
