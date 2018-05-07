package redis;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisDemo {
	private static JedisPool jedisPool = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init();
		string();
//		list();
	}
	private static void init(){
		JedisPoolConfig conf = new JedisPoolConfig();
		conf.setMaxIdle(5);
		conf.setMaxTotal(8);
		conf.setMaxWaitMillis(10001);
		conf.setTestOnBorrow(false);
		conf.setTestOnReturn(true);
		jedisPool = new JedisPool(conf,"localhost",6379);
	}
	
	private static void string(){
		Jedis jedis = jedisPool.getResource();
		jedis.set("test", "124");
		System.out.println("aaaaaaaa: "+jedis.get("test"));
		
	}
	
	private static void list(){
		Jedis jedis = jedisPool.getResource();
//		jedis.flushAll();
//		jedis.lpush("a", "1");
//		jedis.lpush("a", "2");
		List<String> list = jedis.lrange("a", 0, 1);
		for(int i=0;i<list.size();i++){
			System.out.println("aaaaaaaa: "+list.get(i));
		}
	}
}
