package cn.aopdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//        PersonService personService = (PersonService)appContext.getBean("PersonService");
//        String name = "jim";
//        personService.addPerson(name);
        
        Car car = (Car)appContext.getBean("Car");
        car.go(12345);
        ((ClassPathXmlApplicationContext)appContext).close();
    }
}
