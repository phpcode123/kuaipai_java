package alipay_chrome_v2;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.sound.midi.Soundbank;

import org.openqa.selenium.Cookie;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;


public class ThreadGetData extends Thread{
	public Set<Cookie> cookie;
	
	public void run() {
		
		int countNum = 0;
		int countNum_ = 0;
		//用于订单号去重
		HashSet<String> tradeNoSet = new HashSet<String>();
		while(true) {
			countNum += 1;
			System.out.println(">> Thread2 this cookie:"+this.cookie);
			System.out.println(">> Thread2 cookie:"+cookie);
			try {
				
				// ---------------- 数据库中请求IP 开始 ----------------------------
				String proxyIp = null;
				int proxyPort = 0;
				
				//从数据库中请求ip
				while(true) {
					
					String getOneSql = MysqlClass.getOneSql();
					System.out.println(">> GetOneSql:"+getOneSql);
					
					ArrayList<Object> result = null;
					try {
						result = MysqlClass.getOne(getOneSql);
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					System.out.println(">> GetOneResult:"+result);
					int itemid;
					if(result.size() > 0) {
						itemid = (Integer) result.get(0);
						String proxyIpString = (String) result.get(1);
						//System.out.println(">> itemid:"+itemid);
						System.out.println(">> ProxyIpString:"+proxyIpString);
						String[] proxyArray = proxyIpString.split(":");
						proxyIp = proxyArray[0];
						proxyPort = Integer.valueOf(proxyArray[1]);
						
						
						
						String updateSql = MysqlClass.updateSql(itemid);
						try {
							if(MysqlClass.update(updateSql)) {
								System.out.println(">> UpdateSql:"+updateSql);
								break;
							}else {
								OtherClass.getRandomTimeSleep(">> UpdateSql error.");
							}
						} catch (SQLException e) {
							
							e.printStackTrace();
						}
						
					}else{
						OtherClass.getRandomTimeSleep(">> ProxyIpString get error");
					}
				}
				
				// ---------------- 数据库中请求IP 结束 ----------------------------
				
				
				
				
				
				
				
				
				
				
				
//				String ctoken = "";
//				String billUserId = "";
//				for(Cookie i : cookie) {
//					if(i.getName().equals("ctoken")) {
//						ctoken = i.getValue();
//					}
//					
//					if(i.getName().equals("CLUB_ALIPAY_COM")){
//						billUserId = i.getValue();
//					}
//				}
//				
//				String getJsonDataUrl = Settings.getJsonDataUrl+"?ctoken="+ctoken;
//				
//				
//				String endDate = DateUtil.now();
//				String startDate = DateUtil.yesterday().toString();
//						
//				
//				HashMap<String, Object> postParam = new HashMap<String, Object>();
//				postParam.put("endDateInput", endDate);
//				postParam.put("precisionQueryKey", "tradeNo");
//				postParam.put("precisionQueryValue", "");
//				postParam.put("showType", "1");
//				postParam.put("startDateInput", startDate);
//				postParam.put("billUserId", billUserId);
//				postParam.put("pageNum", "1");
//				postParam.put("pageSize", "20");
//				postParam.put("startTime", startDate);
//				postParam.put("endTime", endDate);
//				postParam.put("status", "0");
//				postParam.put("queryEntrance", "1");
//				postParam.put("sortTarget", "tradeTime");
//				postParam.put("activeTargetSearchItem", "tradeNo");
//				postParam.put("accountType", "");
//				postParam.put("startAmount", "");
//				postParam.put("endAmount", "");
//				postParam.put("targetMainAccount", "");
//				postParam.put("precisionValue", "");
//				postParam.put("goodsTitle", "");
//				postParam.put("sortType", "0");
//				postParam.put("total", "");
//				postParam.put("_input_charset", "gbk");
//				
				//System.out.println("Thread2 postParam:"+postParam);
				Set<Cookie> newCookie = new HashSet<Cookie>();
				
				//循环cookies，并将数据添加到新容器中
				for(Cookie i : this.cookie) {
					try {
						Cookie seleniumCookie = new Cookie(i.getName(), i.getValue());
						System.out.println(">> Thread2 i.getName()"+i.getName()+" i.getValue():"+i.getValue());
						newCookie.add(seleniumCookie);
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
				System.out.println(">> newCookie:"+newCookie);
				System.out.println(">> cookie.toString:"+newCookie.toString());
				
				String htmlcode= HttpRequest.post(Settings.alipayAccountUrl)
				.setHttpProxy(proxyIp, proxyPort)
			    .header(Header.USER_AGENT, UserAgent.randPcUserAgent())//头信息，多个头信息多次调用此方法即可
			    .header(Header.REFERER, "https://my.alipay.com/")//头信息，多个头信息多次调用此方法即可
			    .cookie(newCookie.toString())
			    .timeout(4000)//超时，毫秒
			    .execute().body();
				
				System.out.println(">> Htmlcode:"+htmlcode);
				
				if(htmlcode.contains("资产明细")){
				//System.out.println(">> jsonResult:"+result);
	
//				Map<String,Object> jsonMap = OtherClass.jsonObjectToMap(result);
//				
//				System.out.println(">> GetJsonStatus:"+jsonMap.get("status"));
//			
//				
//				
//				// ------------------ 解析json数据 Begin------------------------------
//				if(jsonMap.get("status").equals("succeed")) {
//					//开始遍历map,共有三层数据，分三次遍历
//					
//					//第一次
//					for(Map.Entry<String, Object> x: jsonMap.entrySet()) {
//						if(x.getKey().equals("result")) {
//							Map<String, Object>  resultMap = OtherClass.jsonObjectToMap(x.getValue());
//							
//							//第二次
//							for(Map.Entry<String, Object> y: resultMap.entrySet()) {
//								if(y.getKey().equals("detail")) {
//									JSONArray detailArray = JSONUtil.parseArray(y.getValue());
//									Map<String, Object>  orderMap = new HashMap<String, Object>();
//									
//									//第三次
//									if(detailArray.size() > 0) {
//										for(Object z : detailArray) {
//											
//											orderMap = OtherClass.jsonObjectToMap(z);
//											String dateTime = OtherClass.getDateTime();
//											//System.out.println(">> Thread2 dateTime:"+dateTime+" balance:"+orderMap.get("balance")+" tradeNo:"+orderMap.get("tradeNo")+" tradeAmount:"+orderMap.get("tradeAmount")+" tradeTime:"+orderMap.get("tradeTime"));
//											
//											String tradeNo = orderMap.get("tradeNo").toString();
//											String balance = orderMap.get("balance").toString();
//											String tradeAmount = orderMap.get("tradeAmount").toString();
//											String tradeTime = orderMap.get("tradeTime").toString();
//											
//											if(tradeNoSet.contains(tradeNo)){
//												//old order
//												System.out.println(">> Thread2 Date:"+dateTime+" balance:"+balance+" tradeAmount:"+tradeAmount+" tradeTime:"+tradeTime+" Old");
//											}else {
//												//new order
//												//{"code":"-1","msg":"Api password error."}
//												//{"code":"0","msg":"No such order in database."}
//												//{"code":"1","msg":"New order,Update database success."}
//												//{"code":"2","msg":"New order,Update database failed."}
//												tradeNoSet.add(tradeNo);
//												Map<String, Object> postData = new HashMap<String, Object>();
//												postData.put("apiPassword", Settings.apiPassword);
//												postData.put("tradeNo",tradeNo);
//												postData.put("tradeAmount",tradeAmount);
//												postData.put("tradeTime",tradeTime);
//												if(countNum > 3) {
//													System.out.println(">> postData:"+postData);
//												}
//												String nofityResult= HttpRequest.post(Settings.webApiUrl)
//													    .header(Header.USER_AGENT, UserAgent.randPcUserAgent())//头信息，多个头信息多次调用此方法即可
//													    .form(postData)//表单内容
//													    .timeout(4000)//超时，毫秒
//													    .execute().body();
//												if(countNum > 3) {
//													System.out.println(">> nofityResult:"+nofityResult);
//													Map<String, Object> nofityResultJsonObject = OtherClass.jsonObjectToMap(nofityResult);
//												
//													String codeString = nofityResultJsonObject.get("code").toString();
//													System.out.println(">> Thread2 Date:"+dateTime+" balance:"+balance+" tradeAmount:"+tradeAmount+" tradeTime:"+tradeTime+" New "+codeString);
//												}
//
//											}
//
//										}
//									}
//								}
//							}
//							
//							
//							
//						}
//					}
//					
					//更新status_code状态
					//statusCode 0表示已掉线，1表示在线 
					//{"code":"-1","msg":"Api password error."}
					//{"code":"0","msg":"Pc status update failed."}
					//{"code":"1","msg":"Pc status update success."}
					
					Map<String, Object> statusCodePostData = new HashMap<String, Object>();
					statusCodePostData.put("apiPassword", Settings.apiPassword);
					statusCodePostData.put("statusCode","1");
					
					String statusCodeNofityResult= HttpRequest.post(Settings.webApiStatusCodeUrl)
						    .header(Header.USER_AGENT, UserAgent.randPcUserAgent())//头信息，多个头信息多次调用此方法即可
						    .form(statusCodePostData)//表单内容
						    .timeout(4000)//超时，毫秒
						    .execute().body();
					System.out.println(">> SucceedStatus:"+statusCodeNofityResult);
					//Map<String, Object> nofitystatusCodeJsonObject = OtherClass.jsonObjectToMap(statusCodeNofityResult);
					//String statusCodeString = nofitystatusCodeJsonObject.get("code").toString();
					//System.out.println(">> webStatuscode:"+statusCodeString);
					
					
				}else {
					if(countNum > 2) {
						
						
						//statusCode 0表示已掉线，1表示在线 
						//{"code":"-1","msg":"Api password error."}
						//{"code":"0","msg":"Pc status update failed."}
						//{"code":"1","msg":"Pc status update success."}
						
						Map<String, Object> statusCodePostData = new HashMap<String, Object>();
						statusCodePostData.put("apiPassword", Settings.apiPassword);
						statusCodePostData.put("statusCode","0");
						
						String statusCodeNofityResult= HttpRequest.post(Settings.webApiStatusCodeUrl)
							    .header(Header.USER_AGENT, UserAgent.randPcUserAgent())//头信息，多个头信息多次调用此方法即可
							    .form(statusCodePostData)//表单内容
							    .timeout(4000)//超时，毫秒
							    .execute().body();
						System.out.println(">> ErrorStatus:"+statusCodeNofityResult);
						Map<String, Object> nofityResultJsonObject = OtherClass.jsonObjectToMap(statusCodeNofityResult);
						String statusCodeString = nofityResultJsonObject.get("code").toString();
						
						System.out.println(">> Thread2 "+OtherClass.getDateTime()+" 支付宝可能已掉线! webStatusCode:"+statusCodeString);
						
						
						
						//当掉线次数大于5时就停止程序
						countNum_ += 1;
						if(countNum_ >5) {
							System.out.println("Thread2 Program stop.");
							break;
						}
						
					}
				}
				
				// ------------------ 解析json数据 End------------------------------
				
				
				
				System.out.println(">> Thread2 ---------------------------------------");
				OtherClass.timeSleep(5);
			}catch(Exception e) {
				e.printStackTrace();
			}
	
		}
	}
	
	public ThreadGetData(Set<Cookie> cookie) {
		this.cookie  = Aliapy_chrome.cookie;
	}
}
