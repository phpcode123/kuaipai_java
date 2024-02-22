package cn.hutool.test;

public class Setting {
	//redis服务器
	//如果redis服务器无法连接就是此ip地址设置有问题
	public static String redisServer = "172.17.0.1";
	//rediskey前缀，用于区分不同代理服务器的进程
	public static String redisKeyPrefix = "proxy01_redis";
	//proxy代理在一秒内最大的请求个数，默认是限制5
	public static int maxLimitQueueTimes = 4;
	
	//代理速度不理想，单个请求可能会出现超时的情况，当超时时间超过300秒(5分钟)时就重置当前限制
	public static int maxLimitOverTime = 300;
	
	

}
