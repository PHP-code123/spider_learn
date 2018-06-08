package shixisheng;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import dao.DAO;

public class shixiseng {
	public static Connection con=null;
	public static void main(String[] args) throws IOException, SQLException {
		con=DAO.getConnection();
	for(int i=1;i<=1000;i++)
		call("https://androidapi.shixiseng.com/app/interns/search?k=java&c=%E5%85%A8%E5%9B%BD&s=-0&d=&page="+i+"&m=&x=&z=&st=intern&ft=&t=zj");
	con.close();
	}
	public static void call(String link)throws IOException
	{
		URL url = new URL(link);
    	HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Host", "androidapi.shixiseng.com");
		conn.setRequestProperty("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 7.0; Meizu S6 Build/NRD90M)");
		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

		StringBuilder sb = new StringBuilder();
		for (int c; (c = in.read()) >= 0;)
			sb.append((char)c);
		String html = sb.toString();
		analyse(html);
	}
	public static  void analyse (String json1)
	{
		try {
            JsonParser parser=new JsonParser();  //创建JSON解析器
            JsonObject object=(JsonObject) parser.parse(json1);  //创建JsonObject对象
            System.out.println("code="+object.get("code").getAsInt()); //将json数据转为为int型的数据
            JsonArray array=object.get("msg").getAsJsonArray();    //得到为json的数组
            for(int i=0;i<array.size();i++){
                System.out.println("---------------");
                JsonObject subObject=array.get(i).getAsJsonObject();
                
                String uuid=subObject.get("uuid").getAsString();
                String name=subObject.get("name").getAsString();//职位名称
                String cname=subObject.get("cname").getAsString();//公司名称
                String city=subObject.get("city").getAsString();
                int minsal=subObject.get("minsal").getAsInt();//岗位最低日薪
                int maxsal=subObject.get("maxsal").getAsInt();//岗位最高日薪
                String refresh=subObject.get("refresh").getAsString();
                
                city=city.replace(",", "");
                city=city.substring(0, 2);
                System.out.println("uuid="+uuid);
                
                String sql="insert into php values(?,?,?,?,?,?,?)";
                // 预编译
                
                try {
                	PreparedStatement ps =con.prepareStatement(sql);
					ps.setString(1, uuid);
					ps.setString(2, name);
					ps.setString(3, cname);
					ps.setString(4, city);
					ps.setInt(5, minsal);
					ps.setInt(6, maxsal);
					ps.setString(7, refresh);
					ps.execute();//执行sql语句
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
          
            }
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
	}
	
	/*
	public static  void detail (String json1)
	{
		"https://androidapi.shixiseng.com/app/intern/info?uuid="//详细要求链接
           
	}
	*/
}