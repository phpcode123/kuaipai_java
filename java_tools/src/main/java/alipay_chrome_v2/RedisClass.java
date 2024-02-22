package alipay_chrome_v2;

import cn.hutool.crypto.SecureUtil;
import redis.clients.jedis.Jedis;

public class RedisClass {

	public static Jedis getConn() {
		String host = Settings.REDIS_HOST;
		int port = Settings.REDIS_PORT;
		Jedis jedis = new Jedis(host,port);
		return jedis;
	}
	
	//设置关键词的redis的key值，如：sogou_pc_md5(关键词)
	public static String getKey(String str) {
		String redisKey  = Settings.mainName+"_"+SecureUtil.md5(str);
		return redisKey;	
	}
	
	public static String getRedisValue(String keyword) {
		String key = RedisClass.getKey(keyword);
		Jedis jedis = RedisClass.getConn();
		String value = jedis.get(key);
		jedis.close();
		return value;
	}
	
	
	//jedis.set如果设置成功返回的值是"OK",String类型
	public static Boolean setRedisValue(String keyword, String num) {
		String key = RedisClass.getKey(keyword);
		Jedis jedis = RedisClass.getConn();
		String status = jedis.set(key,num);

		jedis.close();
		if(status.equals("OK")) {
			return true;
		}else {
			return false;
		}
	}
	
	public static void redisFuncSecond(String searchKeyword) {
		// ---------------- 用关键词作为KEY请求REDIS 开始 ----------------------------
		String redisKey = RedisClass.getKey(searchKeyword);
		System.out.println(">> redisKey:"+redisKey);
		
		try {
			if(RedisClass.getRedisValue(searchKeyword) == null) {
				
				if(RedisClass.setRedisValue(searchKeyword, "0")) {
					System.out.println(">> redis set keyword success.");
				}else {
					System.out.println(">> redis set keyword fail.");
				}
				
			}else {
				//如果点击成功就更新redis，设置当前searchKeyword的值为0，因为有些关键词不同的地区排名不一样，有的地区有排名，有的没有。
				if(RedisClass.setRedisValue(searchKeyword, "0")) {
					System.out.println(">> redis value set 0 success.");
				}else {
					System.out.println(">> redis value set 0 fail.");
				}
			}
	
		}catch(Exception e) {
			e.printStackTrace();;
		}
		// ---------------- 用关键词作为KEY请求REDIS 结束 ----------------------------
	}
	
	
	public static void redisFuncThird(int baiduPageNum, int findStatusNum, String redisKey, String searchKeyword) {
		//如果当前页面寻找次数为5页而且查找状态为0，则说明当前关键词在页面中没有排名，将此关键词的redisValue值+1
		if(baiduPageNum > Settings.maxBaiduPageNum-1 && findStatusNum == 0) {
			
			
			// ---------------- 用关键词作为KEY请求REDIS 开始 ----------------------------
			System.out.println(">> redisKey:"+redisKey);
			
			try {
				if(RedisClass.getRedisValue(searchKeyword) == null) {
					
					if(RedisClass.setRedisValue(searchKeyword, "0")) {
						System.out.println(">> redis set keyword success.");
					}else {
						System.out.println(">> redis set keyword fail.");
					}
					
					
				
				}else {
					
					//redisValule为String类型，要转为int类型
					int redisValue = Integer.valueOf(RedisClass.getRedisValue(searchKeyword));
					//System.out.println(">> redisValue:"+redisValue);
					
					
					//将redisValue的值+1，只要值超过Settings.MAX_SEARCH_TIMES，再次循环时，程序会自动跳过当前关键词
					redisValue += 1;
					System.out.println(">> set RedisValue:"+redisValue);
					if(RedisClass.setRedisValue(searchKeyword, String.valueOf(redisValue))) {
						System.out.println(">> redis set keyword success.");
					}else {
						System.out.println(">> redis set keyword fail.");
					}
				}
				
				
			}catch(Exception e) {
				e.printStackTrace();;
			}
			// ---------------- 用关键词作为KEY请求REDIS 结束 ----------------------------
		}
	}
	
	
}
