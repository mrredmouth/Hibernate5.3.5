package com.ccg.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ccg.hibernate.entities.User;

public class UserCacheDao {

	public UserCacheDao(){
		System.out.println("new UserCacheDao()");
	}
	/**
	 * 一级缓存：
	 * 		session不关闭close(),且使用load方法，会先从缓存中读取。
	 */
	private Session session = null;
	
	{
		System.out.println("new Session");
		//每次new对象的时候，在构造器之前先调用。
		session = getSession();
		
	}

	public Session getSession(){
		Configuration cfg = new Configuration();
		cfg.configure("/hibernate.cfg.xml");
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		return sessionFactory.openSession();
	}
	
	public User getUserByIdLoad(Integer user_code){
		/**
		 * 每次操作都需要getSession获取新的session，用完后关闭
		 * Session对象的get方法：直接通过主键获取数据
		 */
		User user = session.load(User.class, user_code);
		return user;
	}
	public User getUserByIdGet(Integer user_code){
		/**
		 * 每次操作都需要getSession获取新的session，用完后关闭
		 * Session对象的get方法：直接通过主键获取数据
		 */
		User user = session.get(User.class, user_code);
		return user;
	}
	
	public List<User> getAll(){
		
		@SuppressWarnings("unchecked")
		List<User> userList = session.createQuery("from User ").list();
		return userList;
	}
}
