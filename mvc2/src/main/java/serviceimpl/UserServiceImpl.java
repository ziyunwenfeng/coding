package serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IUserDao;
import dao.UserDao;
import model.User;
import model.UserExtend;
import service.IUserService;



//@Service("userService")
@Service
public class UserServiceImpl implements IUserService{
    
    //自动装配
    @Autowired
    private IUserDao userDao;

	@Override
	public void add(User u) {
		// TODO Auto-generated method stub
		userDao.addUser(u);
	}

//	@Override
//	public List<UserExtend> queryUserPager(int pageNO, int size) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public int queryUserCount() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public User queryUserById(int userid) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public int insertUser(User user) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int updateUser(User user) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int deleteUserById(int user_id) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int deleteUsers(int[] userIds) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//    
    /**
     * 分页
     */
//    public List<UserExtend> queryUserPager(int pageNO, int size) {
//        int skip=(pageNO-1)*size;
//        return userDao.queryUserPager(skip, size);
//    }
//    
//    /**
//     * 查询用户总数
//     */
//    public int queryUserCount() {
//        return userDao.queryUserCount();
//    }
//    
//    /**
//     * 根据用户id查询用户
//     */
//    public User queryUserById(int userid){
//        return userDao.queryUserById(userid);
//    }
//    
//    public IUserDao getUserDao() {
//		return userDao;
//	}
//
//	public void setUserDao(UserDao userDao) {
//		this.userDao = userDao;
//	}
//
//	/**
//     * 新增用户
//     */
//    public int insertUser(User user){
//        return userDao.insertUser(user);
//    }
//    
//    /**
//     * 修改用户
//     */
//    public int updateUser(User user){
//        return userDao.updateUser(user);
//    }
//    
//    /**
//     * 根据用户id删除用户
//     */
//    public int deleteUserById(int user_id){
//        return userDao.deleteUserById(user_id);
//    }
//    
//    /**
//     * 删除多个用户
//     */
//    public int deleteUsers(int[] userIds){
//        return userDao.deleteUsers(userIds);
//    }
}