package com.ccg.hibernate;

import java.util.List;

import org.junit.Test;

import com.ccg.hibernate.dao.UserDao;
import com.ccg.hibernate.entities.User;

public class TestUserDao {
	
	private UserDao userDao;
	
	{
		userDao = new UserDao();
	}

	@Test
	public void testCRUD(){
		
		/**
		 * 构建瞬时数据，数据存在内存的实体对象中，与数据库无关
		 * 主键采用：hibernate_sequence.nextval
		 * 即使给对象设置主键值14，插入的时候此值无效，一般不设置
		 */
		//User user = new User(14,"德鲁伊","123456","druid@com",159520);
		User user2 = new User("德鲁伊","123456","druid@com",159520);
		userDao.add(user2);
		
		userDao.delete(7);
		
		User user = userDao.getUserById(12);
		System.out.println(user);
		
		user.setUserName("hahaha");
		userDao.update(user);
		
		List<User> userList = userDao.getAll();
		for(User userTemp:userList){
			System.out.println(userTemp);
		}
	}
	
}
