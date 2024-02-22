package cn.hutool.test;



import redis.clients.jedis.Jedis;

public class JavaRedisTest {

	public static void main(String[] args) {
		
		Jedis jedis = new Jedis(Setting.redisServer);
		jedis.select(0);
		int countNum = 0;
		while(countNum < Setting.maxLimitQueueTimes) {
			countNum += 1;
			jedis.set(Setting.redisKeyPrefix+"_"+String.valueOf(countNum),"key123_value_"+String.valueOf(countNum));
			System.out.println(jedis.get(Setting.redisKeyPrefix+"_"+String.valueOf(countNum)));
		}
		System.out.println(jedis.get("abcd"));
		jedis.set(Setting.redisKeyPrefix+"key123","key123_value");
		System.out.println(jedis.get(Setting.redisKeyPrefix+"key123"));
		jedis.close();
	}
}


