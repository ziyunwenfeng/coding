package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.User;

public class UserDAO {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public List<User> getAllUser(){
		String hql = "FROM User";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		return (List<User>)query.setCacheable(true).list();
	}
	
	public List<User> getById(String id){
		System.out.println("from database:::::");
		String hql = "FROM User where id = id";
		if(id!=null&&"".equals(id)){
			
		}
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		return (List<User>)query.list();
	}
}
