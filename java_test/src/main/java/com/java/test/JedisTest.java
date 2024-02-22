package com.java.test;

import redis.clients.jedis.Jedis;

import cn.hutool.crypto.SecureUtil;
public class JedisTest {

	public static void main(String[] args) {
		
		

		String md5Str = SecureUtil.md5("服务正在运行");
        System.out.println(md5Str);
		
		
	        
        Jedis jedis = new Jedis("192.168.0.101",6379);
        System.out.println("服务正在运行: "+jedis.ping());
        String a = jedis.set("runoobkey", "www.runoob.com");
        System.out.println(">> a:"+a);
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey1"));
        
        
        //请求失败返回的是null
        if(jedis.get("runoobkey1") == null) {
        	System.out.println("yes");
        }else {
        	System.out.println("no");
        }
        jedis.close();


	        
	}

}
