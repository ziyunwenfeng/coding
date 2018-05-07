package ssmdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import entity.Stu;
import service.StuService;
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath:config/spring-*.xml"}) 
public class Test1 {
		// TODO Auto-generated method stub
		@Autowired
		StuService service;
		@Test
		public void test(){
			Stu stu = new Stu();
			stu.setAge((short) 1);
			stu.setName("ma");
			service.insertStu(stu);
		}

}
