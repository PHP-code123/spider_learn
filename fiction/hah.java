import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class hah implements Runnable {
	String link;
	int num;
	public hah(String link,int num) {
		this.link = link;
		this.num=num;
	}

	@Override
	public void run()  {
		try {
		URL url = new URL(link);
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
        Element content = doc.getElementById("content");
        Elements title=doc.getElementsByClass("bookname");
        BufferedWriter br=new BufferedWriter(new FileWriter(new File("D:\\1604\\"+num+".txt"),false));//´æ´¢Â·¾¶
//        System.out.println(title.select("h1").text());
        br.newLine();
        br.write(title.select("h1").text());
        br.newLine();
        br.write(content.text());
        br.flush();
        br.close();
		}
		catch (IOException e)
		{
			System.out.println(num);
		}
		
	}
	
}
