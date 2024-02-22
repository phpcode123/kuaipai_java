package cn.hutool.test;


import cn.hutool.http.HttpUtil;
import cn.hutool.core.util.CharsetUtil;

public class HttpUtilTest {

	public static void main(String[] args) {
		
		String result = HttpUtil.get("http://192.168.0.101:8083/keyword.html", CharsetUtil.CHARSET_UTF_8);
		//System.out.println(result);
		String[] a = result.split("\\r|\\n");
		//System.out.println(a);
		int countNum = 0;
		for(String i : a) {
			countNum += 1;
			System.out.println(countNum+i);
		}
	}

}
