package test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis.User;

public class CURDTest {
	public void addUser(){
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		String statement = "sqlmaping.userMapper.addUser";
		User user = new User();
		
		user.setName("tangwenfeng");
		user.setAge(20);
		int result = sqlSession.insert(statement, user);
		sqlSession.close();
		System.out.println(result);
	}
	
	public void deleteUser(){
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		String statement = "sqlmaping.userMapper.deleteUser";
		int result = sqlSession.delete(statement, 5);
		sqlSession.close();
		System.out.println(result);
	}
	
	public void updateUser(){
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		String statement = "sqlmaping.userMapper.updateUser";
		User user = new User();
		user.setId(3);
		user.setName("tangwenfeng");
		user.setAge(20);
		int result = sqlSession.delete(statement, user);
		sqlSession.close();
		System.out.println(result);
	}
	public void getAlluser(){
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		String statement = "sqlmaping.userMapper.getAllUser";
		List<User> users = sqlSession.selectList(statement);
		sqlSession.close();
		System.out.println(users);
	}
}
