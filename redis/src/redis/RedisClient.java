package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisClient {
	private Jedis jedis;
	private JedisPool jedisPool;
	private ShardedJedis sharedJedis;
	private ShardedJedisPool sharedJedisPool;
	
	public RedisClient(){
		JedisPoolConfig conf = new JedisPoolConfig();
		conf.setMaxIdle(5);
		conf.setMaxWaitMillis(10001);
		conf.setTestOnBorrow(false);
		jedisPool = new JedisPool(conf,"127.0.0.1",6379);
		
		
	}
}	
