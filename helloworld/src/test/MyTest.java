package test;



import java.util.Iterator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.Customer;
import com.Hello;
import com.OutHelper;
import com.Person;

import pkl.Book;

public class MyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		OutHelper out = (OutHelper)context.getBean("OutHelper");
		out.genarateOutput(); 
		
		Customer customer = (Customer)context.getBean("Customer");
	//	customer.output();
//		customer.getPerson().setId(244);
//		System.out.println(customer.getPerson().getId());
		Customer customer2 = (Customer)context.getBean("Customer");
///		System.out.println(customer.getPerson().getId());
		
		Book book = (Book)context.getBean("Book");
		System.out.println(book.getList());
		Person p = (Person) book.getList().get(1);
		System.out.println(p.getId());
		System.out.println(book.getSet());
		Person p2 = (Person) book.getList().get(1);
		System.out.println(p2.getId());
		System.out.println(book.getMap());
		
	}

}
