package dao;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.JedisPool;

public class RedisDAO {
	@Autowired
	private JedisPool jedisPool;
	
	public String get(String key){
		return jedisPool.getResource().get(key);
	}
	public String set(String key,String value){
		return jedisPool.getResource().set(key,value);
	}
	public String hget(String hkey,String key){
		return jedisPool.getResource().hget(hkey, key);
	}
	public Long hset(String hkey,String key,String value){
		return jedisPool.getResource().hset(hkey,key,value);
	}
	
}
