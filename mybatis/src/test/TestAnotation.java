package test;

import org.apache.ibatis.session.SqlSession;

import mapping.IUsermapper;
import mybatis.User;

public class TestAnotation {
	public void testAdd(){
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		IUsermapper mapper = sqlSession.getMapper(IUsermapper.class);
		User user = new User();
		user.setAge(23);
		user.setName("jing");
		int add = mapper.add(user);
		sqlSession.clearCache();
		System.out.println(add);
	}
}
