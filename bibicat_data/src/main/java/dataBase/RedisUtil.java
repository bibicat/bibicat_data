package dataBase;

import redis.clients.jedis.Jedis;

public class RedisUtil {

	private final static Jedis jedis = new Jedis("localhost",6379);
	
	/**
	 * 从指定无序集合中移除并返回一个元素
	 * @param key
	 * @return
	 */
	public static String sPop(String key){
		return jedis.spop(key);
	}
	
	/**
	 * 向集合中添加一个元素
	 * @param key
	 * @return
	 */
	public static void sAdd(String key,String value){
		jedis.sadd(key, value);
	}
	
	/**
	 * 移除并获取队列右边第一个元素
	 * @param key
	 * @return
	 */
	public static String rPop(String key){
		return jedis.rpop(key);
	}
	
	/**
	 * 从队列左边添加一个元素
	 * @param key
	 * @return
	 */
	public static void lPush(String key,String value){
		jedis.lpush(key, value);
	}
}
