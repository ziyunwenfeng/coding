package service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import dao.RedisDAO;
import dao.UserDAO;
import entity.User;
@Service
public class UserService {
	@Autowired
	private UserDAO userDAO ;
	@Autowired 
	private RedisDAO redisDAO;
	@Autowired
	private StringRedisTemplate redisTemplate;
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public int userCount(){
		return userDAO.getAllUser().size();
	}
	//redis 缓存
	@Cacheable(value="common",key="'id_'+#id")
	public List<User> getById(String id){
		System.out.println("from database");
//		return userDAO.getById(id);
		List<User> users = new LinkedList<User>();
		User tang = new User();
		tang.setName("tang");
		tang.setPwd("123");		
		return users;
	}
	@CachePut(value="common",key="#user.getUserName")
	public void insertUser(User user){
		
	}
	@CacheEvict(value="common",key="'id_'+#id")
	public void deleteById(int id){
		
	}
	
	//redisTemplate
	public void addUser(User u){
		redisTemplate.opsForValue().set(u.getName(), u.getPwd());
		//使用最基本的方式
        /*redisTemplate.execute(new RedisCallback<object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(redisTemplate.getStringSerializer().serialize(user.getUsername()),
                        redisTemplate.getStringSerializer().serialize(user.getPassword()));
                return null;
            }
        });*/
		/*redisTemplate.execute(new RedisCallback<object>() {
        @Override
        public Object doInRedis(RedisConnection connection) throws DataAccessException {
            connection.set(redisTemplate.getStringSerializer().serialize(user.getUsername()),
                    redisTemplate.getStringSerializer().serialize(user.getPassword()));
            return null;
        }
    });*/
		//利用了StringRdisTemplate的特性 通过绑定的方式
        BoundValueOperations<String,String> bound = redisTemplate.boundValueOps(u.getName());
        bound.set(u.getPwd());
        //bound.append(user.getPassword());//追加，和StringBuilder的append一样功能
	}
	public void deleteUser(String id){
		redisTemplate.delete(id);
	}
	public void uodateUser(User u){
		ValueOperations<String,String> ops = redisTemplate.opsForValue();
		if(ops.get(u.getName())!=null){
			ops.set(u.getName(), u.getPwd());
		}
	}
	
//	//redis
//	public User getU(String id){
//		if(redisDAO.get(id).isEmpty()){
//			return (User) userDAO.getById(id);
//		}else{
//			return redisDAO.get(id);
//		}
//	}
	
	
}
