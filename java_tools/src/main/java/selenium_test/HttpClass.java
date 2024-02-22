package selenium_test;

import java.util.HashMap;
import java.util.Map;
import cn.hutool.http.HttpRequest;
 
public class HttpClass {
	public static Map<String, Object> param(){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("endDateInput","2021-06-19 00:00:00");
		data.put("precisionQueryKey","tradeNo");
		data.put("precisionQueryValue","");
		data.put("showType","0");
		data.put("startDateInput","2021-06-18 00:00:00");
		data.put("billUserId","2088502136886997");
		data.put("pageNum","1");
		data.put("pageSize","20");
		data.put("startTime","2021-06-18 00:00:00");
		data.put("endTime","2021-06-19 00:00:00");
		data.put("status","0");
		data.put("queryEntrance","1");
		data.put("sortTarget","tradeTime");
		data.put("activeTargetSearchItem","tradeNo");
		data.put("accountType","");
		data.put("startAmount","");
		data.put("endAmount","");
		data.put("targetMainAccount","");
		data.put("precisionValue","");
		data.put("goodsTitle","");
		data.put("sortType","0");
		data.put("total","");
		data.put("_input_charset","gbk");
        return data;
	}
	
	public static void main(String[] args) {
		String url = "https://mbillexprod.alipay.com/enterprise/fundAccountDetail.json?ctoken=iQt2RQ-jK8uZX8bk";
		String result2 = HttpRequest.post(url)
			.header("authority","mbillexprod.alipay.com")
			.header("method","POST")
			.header("path","/enterprise/fundAccountDetail.json?ctoken=iQt2RQ-jK8uZX8bk")
			.header("scheme","https")
			.header("accept","application/json, text/javascript, */*; q=0.01")
			.header("accept-encoding","gzip, deflate, br")
			.header("accept-language","zh-CN,zh;q=0.9")
			.header("content-type","application/x-www-form-urlencoded; charset=UTF-8")
			.header("cookie","JSESSIONID=RZ41elzFRUQrlaF9ngzUEcbOyz7toXauthRZ42GZ00; mobileSendTime=-1; credibleMobileSendTime=-1; ctuMobileSendTime=-1; riskMobileBankSendTime=-1; riskMobileAccoutSendTime=-1; riskMobileCreditSendTime=-1; riskCredibleMobileSendTime=-1; riskOriginalAccountMobileSendTime=-1; cna=GzxSGfB/l2QCAXdkYOXU+w+P; LoginForm=alipay_login_auth; CLUB_ALIPAY_COM=2088502136886997; ali_apache_tracktmp=\"uid=2088502136886997\"; session.cookieNameId=ALIPAYJSESSIONID; JSESSIONID=FC37F96D5CD7544DF7641117A1AB73AF; spanner=idj5S2/e+kEOy1dCyZ/e4BJ3SVSuJtIp4EJoL7C0n0A=; UM_distinctid=17a1a2965e9665-0361fdd76921b2-c3f3568-1fa400-17a1a2965eab00; ctoken=iQt2RQ-jK8uZX8bk; ALIPAYJSESSIONID=RZ41elzFRUQrlaF9ngzUEcbOyz7toXauthRZ41GZ00; rtk=OdWppmcFcLNEHklcRdXDH3ffgiEY84VbHdIZCpxmic1y84nXCWb; CNZZDATA1278858046=1012595212-1623935156-%7C1623983703; zone=GZ00C; CNZZDATA1253107971=664470475-1623931798-%7C1623984903")
			.header("origin","https://mbillexprod.alipay.com")
			.header("referer","https://mbillexprod.alipay.com/enterprise/fundAccountDetail.htm")
			.header("sec-ch-ua"," Not;A Brand\";v=\"99\", \"Google Chrome\";v=\"91\", \"Chromium\";v=\"91\"")
			.header("sec-ch-ua-mobile","?0")
			.header("sec-fetch-dest","empty")
			.header("sec-fetch-mode","cors")
			.header("sec-fetch-site","same-origin")
			.header("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36")
			.header("x-requested-with","XMLHttpRequest")
		    .form(HttpClass.param())
		    .timeout(20000)
		    .execute()
		    .body();
		
		
		System.out.println(result2);

	}

}
