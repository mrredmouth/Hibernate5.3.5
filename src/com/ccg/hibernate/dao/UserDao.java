package com.ccg.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ccg.hibernate.entities.User;

/**
 * hibernate原理：hibernate.cfg.xml、*.hbm.xml、hibernate.properties 
 * 		-> Configuration实例
	 	  	-> SessionFactory实例 
	 	  	->  Session实例 
	 	  	-> Criteria、Query、Transaction等操作
 */
public class UserDao {
	
	public UserDao(){
		System.out.println("UserDao.UserDao()");
	}
	
	
	{
		System.out.println("实例块:new对象时先进入,在任意构造器之前,同一个对象只进入一次;");
		System.out.println("静态块:是class文件加载的时候就进入,new对象时不调用");
	}
	
	/**
	 * hibernate原理：hibernate.cfg.xml、*.hbm.xml、hibernate.properties 
	 * 		-> Configuration实例
	 	  	-> SessionFactory实例 
	 	  	->  Session实例 
	 	  	-> Criteria、Query、Transaction等操作
	 */
	public Session getSession(){
		Configuration cfg = new Configuration();
		cfg.configure("/hibernate.cfg.xml");
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		return sessionFactory.openSession();
	}
	
	public User getUserById(Integer user_code){
		/**
		 * 每次操作都需要getSession获取新的session，用完后关闭
		 * Session对象的get方法：直接通过主键获取数据
		 */
		Session session = getSession();
		User user = session.get(User.class, user_code);
		session.close();
		return user;
	}
	
	/**
	 * 1、hibernate对数据库的操作都是转化为对entity对象的操作。
	 * 2、增删改要借助于事务进行操作
	 */
	public void add(User user){

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		
		//1、临时状态：传进来的对象user
		//2、持久状态：瞬时数据保存为持久化数据
		session.save(user);
		tx.commit();
		
		/**
		 * save之后，就可以获得主键值，
		 * 但要commit之后数据才存入到数据库中，
		 * session关闭也同样可以获得
		 */
		System.out.println("新增成功，User主键：" + user.getUserCode());
		
		//3、游离状态：持久化数据变成游离数据
		session.close();

	}
	
	public void delete(Integer userCode){

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		
		//先得到持久化的数据，在delete
		User user = session.get(User.class, userCode);
		if(user!=null){
			session.delete(user);
		}
		
		tx.commit();
		session.close();
	}
	
	public void update(User user){
		Session session = getSession();
		Transaction tx = session.beginTransaction();

		session.saveOrUpdate(user);
		tx.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAll(){
		Session session = getSession();
		
		List<User> userList = session.createQuery("from User ").list();
		
		session.close();
		return userList;
	}
}
