package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class detail_text {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static String detail(String link) throws UnsupportedEncodingException, IOException
	{
		URL url = new URL("http://m.yingjiesheng.com"+link);
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
        Elements texts = doc.select("section.jobdetail");
        for (Element text : texts)
        {
        	System.out.println(text.text());
        }
		return "";
	}

}
