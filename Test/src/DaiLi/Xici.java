package DaiLi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Xici {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		URL url = new URL("http://www.xicidaili.com/nn/");
    	HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "text/html; charset=utf-8");
		conn.setRequestProperty("Host", "www.xicidaili.com");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 UBrowser/6.2.3964.2 Safari/537.36");
		
		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		 
		StringBuilder sb = new StringBuilder();
		for (int c; (c = in.read()) >= 0;)
			sb.append((char)c);
		String html = sb.toString();
//		System.out.println(html);
		Document doc = Jsoup.parse(html);
        Elements titles = doc.select("tr");
        int c=0;
    	for(Element title : titles)
		{
    		c++;
    		if (c==1)continue;
			String text=title.text();
			String[] tab= text.split(" ");
			//System.out.println(tab[0]+"       "+tab[1]);
			try {
				//			 创建代理服务器            
					InetSocketAddress addr = new InetSocketAddress(tab[0],Integer.parseInt(tab[1]));            
					Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理
					URL url1 = new URL("http://ip.chinaz.com/getip.aspx");
			    	HttpURLConnection test = (HttpURLConnection)url1.openConnection(proxy);
			    	test.setRequestMethod("GET");
			    	test.setConnectTimeout(2000);
			    	test.setRequestProperty("Content-Type", "text/html; charset=utf-8");
			    	test.setRequestProperty("Host", "www.xicidaili.com");
			    	test.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 UBrowser/6.2.3964.2 Safari/537.36");
			    	Reader in1 = new BufferedReader(new InputStreamReader(test.getInputStream(), "utf-8"));
			    	sb = new StringBuilder();
					for (int ch; (ch = in1.read()) >= 0;)
						sb.append((char)ch);
					String html1 = sb.toString();
					if(html1.indexOf("无效用户") >0)continue;
					//System.out.println(html1);
					System.out.println(tab[0]+":"+tab[1]+"@" +tab[4]);
			}catch(Exception e)
			{
				continue;
			}
			}
		}
	}


