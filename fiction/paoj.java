import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class paoj {

	public static void main(String[] args) throws IOException {
		getnext("https://www.81zw.net/book/1604/");//1604为小说代码
	}
		public static void getnext(String url1) throws IOException
	{
		URL url = new URL(url1);
    	HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "text/html; charset=gbk");
		conn.setRequestProperty("Host", "www.81zw.net");

		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "gbk"));

		StringBuilder sb = new StringBuilder();
		for (int c; (c = in.read()) >= 0;)
			sb.append((char)c);
		String html = sb.toString();
		Document doc = Jsoup.parse(html);
     
        Element next1=doc.getElementById("list");
        Elements list1 =next1.getElementsByTag("dd").select("a");
        int num=1;
        for(Element temp:list1)
        {
        	
        	String link="https://www.81zw.net"+temp.attr("href");
        	System.out.println(link);
        	new Thread(new hah(link,num)).start();
        	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	num++;
        }
		
	}

}
