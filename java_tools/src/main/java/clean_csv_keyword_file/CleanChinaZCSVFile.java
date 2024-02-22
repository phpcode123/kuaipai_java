package clean_csv_keyword_file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;



public class CleanChinaZCSVFile {

	public static void main(String[] args) {
		//ChinaZ下载关键词另存为CSV文件，关键词,指数,排名,上期排名,标题,网址
		
		
		
		File csvFile = new File(Settings.csvFilePath);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile),"UTF-8"));
			String line;
			int countNum = 0;
			
			while((line = br.readLine()) != null) {
				countNum += 1;
				//System.out.println(">> countNum:"+String.valueOf(countNum)+" line:"+line);
				String[] lineList = line.split(",");
				//lineList[0]  搜索关键词
				//lineList[4]  标题
				//lineList[5]  网址
				
				
				System.out.println(">> countNum:"+String.valueOf(countNum)+" lineList:"+lineList[0]+" "+lineList[4]+" "+lineList[5]);
				
				
				
				//在数据库中请求代理ip
				try {
					// ---------------- 数据库中请求IP 开始 ----------------------------
					String proxyIpString = "";
					
					while(Settings.ableProxy) {
						
						String getOneSql = MysqlClass.getOneSql();
						System.out.println(">> getOneSql:"+getOneSql);
						
						ArrayList<Object> result = MysqlClass.getOne(getOneSql);
						System.out.println(">> getOneResult:"+result);
						int itemid;
						if(result.size() > 0) {
							itemid = (Integer) result.get(0);
							proxyIpString = (String) result.get(1);
							System.out.println(">> itemid:"+itemid);
							System.out.println(">> proxyIpString:"+proxyIpString);
							
							
							
							
							String updateSql = MysqlClass.updateSql(itemid);
							if(MysqlClass.update(updateSql)) {
								System.out.println(">> updateSql:"+updateSql);
								break;
							}else {
								OtherClass.getRandomTimeSleep(">> updateSql error.");
							}
							
						}else{
							OtherClass.getRandomTimeSleep(">> ProxyIpString get error");
						}
					}
					
		
					String ip = OtherClass.getProxyIp(proxyIpString);
					int port = OtherClass.getProxyPort(proxyIpString);
					System.out.println(">> ip:"+ip);
					System.out.println(">> port:"+port);
			
					
					String fromurl = lineList[5];
					
					System.out.println(">> linkurl:"+ fromurl);
					
					
					//链式构建请求: baiduRemoteWGifUrl
					String htmlcode = HttpRequest.get(fromurl)						
					    .header(Header.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; Hot Lingo 2.0)")
					    .setHttpProxy(ip, port)
					    .timeout(15000)
					    .execute().body();
					
					htmlcode = OtherClass.cleanLine("\\r+|\\n+", htmlcode);
					
					//System.out.println(">> htmlcode:"+htmlcode);
					FileWriter fw = new FileWriter(Settings.newCsvFilePath, true);
					BufferedWriter bw = new BufferedWriter(fw);
					
					
					if(!htmlcode.equals("404")) {
						bw.write(lineList[0]+","+lineList[4]+","+lineList[5]+"\n");
						System.out.println(">> fileWriter:"+lineList[0]+","+lineList[4]+","+lineList[5]);
					}
					
					bw.close();
					System.out.println(">>  ==========================================");
					OtherClass.timeSleep(1);
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		
		
	}

}
