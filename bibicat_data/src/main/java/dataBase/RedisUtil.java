package dataBase;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisUtil {

	private final static Jedis jedis = new Jedis("localhost",6379);
	
	public final static String ip = "ip";
	
	public final static String usable_ip = "usable_ip";
	
	public final static String link = "link";
	
	public final static String content = "content";
	
	
	/**
	 * 删除key
	 * @param key
	 * @return
	 */
	public static Long del(String key){
		return jedis.del(key);
	}
	
	/**
	 * 返回集合中所有成员数量
	 * @param key
	 * @return
	 */
	public static Long scard(String key){
		return jedis.scard(key);
	}
	
	/**
	 * 返回集合中所有成员
	 * @param key
	 * @return
	 */
	public static Set<String> smembers(String key){
		return jedis.smembers(key);
	}
	
	/**
	 * 从指定无序集合中随机移除并返回一个元素
	 * @param key
	 * @return
	 */
	public static String sPop(String key){
		return jedis.spop(key);
	}
	
	/**
	 * 向指定集合中添加一个元素
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
