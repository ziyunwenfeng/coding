package dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import model.User;
import model.UserExtend;

public class UserDao implements IUserDao {
	@Autowired
	SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		System.out.println("add");
	}
//
//	@Override
//	public List<UserExtend> queryUserPager(int skip, int size) {
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

}
