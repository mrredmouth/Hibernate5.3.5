package com.ccg.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.ccg.hibernate.entities.Book;

public class TestBook {

	public Session getSession(){
		Configuration cfg = new Configuration();
		cfg.configure("/hibernate.cfg.xml");
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		return sessionFactory.openSession();
	}
	
	
	@Test
	public void testBook(){
		Session session = getSession();
		
		@SuppressWarnings("unchecked")
		List<Book> bookList = session.createQuery("from Book ").list();
		
		for(Book book:bookList){
			System.out.println(book);
		}
		
		
		session.close();
	}
}
