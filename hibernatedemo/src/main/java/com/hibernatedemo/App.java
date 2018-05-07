package com.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Configuration cfg = new Configuration().configure();
        StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
        StandardServiceRegistry sr = srb.build();
        SessionFactory factory = cfg.buildSessionFactory(sr);
        Session session = factory.openSession();
        session.beginTransaction();
//        WorkerMan workerMan = new WorkerMan();
//        workerMan.setId(12);
//        workerMan.setName("b");
//        session.save(workerMan);
        
//        Stu stu = new Stu();
//        stu.setId(1);
//        stu.setName("jac");
//        session.save(stu);
        
        Addr addr = new Addr();
        addr.setCity("sh");
        
        Emp emp = new Emp();
        emp.setName("bg");
        emp.setAddr(addr);
        
        addr.setEmp(emp);
        
        session.save(addr);
        session.save(emp);
        
        session.getTransaction().commit();
        session.close();
        factory.close();
        System.out.println( "Hello World!" );
        
    }
}
