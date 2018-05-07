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
		String hql = "from user";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		return (List<User>)query.list();
	}
}
