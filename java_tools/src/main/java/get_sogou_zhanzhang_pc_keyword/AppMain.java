package get_sogou_zhanzhang_pc_keyword;

import java.util.HashMap;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;

public class AppMain {
	

	// ---------------------- main begin ----------------------

	public static void main(String[] args) {

		int pageNum = 0;
		while(pageNum < Settings.maxPageNum) {
			pageNum += 1;
					
//			page: 2
//			pageSize: 20
//			query: ""
//			sid: 45196817
//			siteAddress: "m.panduoduo.me"
//			start: "2021-09-30"
//			end: "2021-10-30"
//			terminal: "wap"
			
			
			//可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("page",pageNum);
			paramMap.put("pageSize",Settings.pageSize);
			paramMap.put("query",Settings.query);
			paramMap.put("sid",Settings.sid);
			paramMap.put("siteAddress",Settings.siteAddress);
			paramMap.put("start",Settings.startDate);
			paramMap.put("end",Settings.endDate);
			paramMap.put("terminal",Settings.terminal);

			
			System.out.println(">> paramMap:"+String.valueOf(paramMap));
			
//			Accept: application/json
//			Accept-Encoding: gzip, deflate, br
//			Accept-Language: zh-CN,zh;q=0.9,zh-TW;q=0.8
//			Cache-Control: no-cache
//			Connection: keep-alive
//			Content-Length: 138
//			Content-Type: application/json;charset=UTF-8
//			Cookie: SUV=0001B47D776460E560CAEB11E3E1E890; front_screen_resolution=1920*1080; front_screen_dpi=1; SUID=E56064771431A40A0000000060CAEB1A; ssuid=7838931019; CXID=CB44248B0137B9703F24837A954C0AB1; mid=54b4861005033004489; usid=oT8WO2wokZjEJOI6; wuid=AAE3XXGkOAAAAAqgDFJPxgEAkwA=; FREQUENCY=1623911185109_29; IPLOC=CN4202; SNUID=06BDA77A1511DA49E11F1CD415EFBA81; ld=3lllllllll2khm7SlllllpVDE1GlllllNLSkiyllllUlllll4klll5@@@@@@@@@@; LSTMV=292%2C186; LCLKINT=1064; __ZHANZHANG_SID__=snXavvXcpP-w49qIBTwJ9Y9voD4sDDS_; __ZHANZHANG_SID__.sig=ZMY8dJXhDkLc76hMvmm-KpxgsB4; sgid=11-49366979-AWF871zLnvqsfVYc5VEyyEs; sgid.sig=5glSILbyVx5s4tVbTqAv2vHkmt0; username=tony986; username.sig=HNFouuj8j53T8VToWjLF8xPW_0w; user_id=2196139; user_id.sig=BrXcYwKD8AmI3Bc1zrF1n2JAWFg; show_wxapp=0
//			Host: zhanzhang.sogou.com
//			Origin: https://zhanzhang.sogou.com
//			Pragma: no-cache
//			Referer: https://zhanzhang.sogou.com/index.php/keyword/index?fr=more&sid=19816885
//			sec-ch-ua: "Google Chrome";v="95", "Chromium";v="95", ";Not A Brand";v="99"
//			sec-ch-ua-mobile: ?0
//			sec-ch-ua-platform: "Windows"
//			Sec-Fetch-Dest: empty
//			Sec-Fetch-Mode: cors
//			Sec-Fetch-Site: same-origin
//			User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36
//			
			
			//HttpCookie httpCookie = new HttpCookie(null, null); 
			
			
			String jsonData = HttpRequest.post(Settings.requestUrl)
		    .header(Header.ACCEPT, "application/json")
		    .header(Header.ACCEPT_ENCODING, "gzip, deflate, br")
		    .header(Header.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.9,zh-TW;q=0.8")
		    .header(Header.CACHE_CONTROL, "no-cache")
		    .header(Header.CACHE_CONTROL, "keep-alive")
		    //.header(Header.CONTENT_LENGTH, "138")
		    .header(Header.CONTENT_TYPE, "application/json;charset=UTF-8")
		    .header(Header.COOKIE, Settings.cookieString)
		    .header(Header.HOST, "zhanzhang.sogou.com")
		    .header(Header.ORIGIN, "https://zhanzhang.sogou.com")
		    .header(Header.PRAGMA, "no-cache")
		    .header(Header.REFERER, "https://zhanzhang.sogou.com/index.php/keyword/index?fr=more&sid=19816885")
		    .header(Header.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36")
		    .form(paramMap)//表单内容
		    //      .cookie(null)
		    .timeout(20000)//超时，毫秒
		    .execute().body();
			
			System.out.println(">> jsonData:"+jsonData);
			
			OtherClass.timeSleep(10);
			
		}

	}
}
