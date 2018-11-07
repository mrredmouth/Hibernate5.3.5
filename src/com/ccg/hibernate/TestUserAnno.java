package com.ccg.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.ccg.hibernate.entities.Name;
import com.ccg.hibernate.entities.UserAnno;

public class TestUserAnno {
	
	public Session getSession(){
		Configuration cfg = new Configuration();
		cfg.configure("/hibernate.cfg.xml");
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		return sessionFactory.openSession();
	}

	public void add(UserAnno userAnno){
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		System.out.println(userAnno);
		session.save(userAnno);
		tx.commit();
		System.out.println("新增成功，User主键：" + userAnno.getId());
		session.close();
	}
	
	@Test
	public void testCRUD(){
		add(new UserAnno(new Name("Lilly")));
	}
	
}
