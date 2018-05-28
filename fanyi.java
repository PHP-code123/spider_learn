package youdao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class fanyi {

	public static void main(String[] args) throws IOException  {
		Scanner in=new Scanner(System.in);
		
		while(in.hasNextLine())
		{
			String word=in.nextLine();
			call(word);
		}
       
	}
	public static void call(String word)throws IOException
	{
		URL url = new URL("http://m.youdao.com/dict?le=eng&q="+word);
    	HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "text/html; charset=gbk");
		conn.setRequestProperty("Host", "www.81zw.net");

		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

		StringBuilder sb = new StringBuilder();
		for (int c; (c = in.read()) >= 0;)
			sb.append((char)c);
		String html = sb.toString();
		Document doc = Jsoup.parse(html);
     
        Element res=doc.getElementById("bd");
        for(Element li:res.select("li"))
        {
        	 System.out.println(li.text());
        }
	}

}
