import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class coll {

	public static void main(String[] args) throws IOException {
		BufferedWriter br1=new BufferedWriter(new FileWriter(new File("D:\\1604\\all.txt"),true));
		for(int i=1;i<=3486;i++)
		{
			StringBuilder result = new StringBuilder();
	            BufferedReader br = new BufferedReader(new FileReader(new File("D:\\1604\\"+i+".txt")));//����һ��BufferedReader������ȡ�ļ�
	            String s = null;
	            while((s = br.readLine())!=null){//ʹ��readLine������һ�ζ�һ��
	                result.append(System.lineSeparator()+s);
	            }
	            br.close();
	            br1.write(result.toString());
	            br1.flush();
		}
		br1.close();
	}

}
