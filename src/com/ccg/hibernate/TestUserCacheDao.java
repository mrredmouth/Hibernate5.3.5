package com.ccg.hibernate;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.ccg.hibernate.dao.UserCacheDao;
import com.ccg.hibernate.entities.Book;

@WebServlet(urlPatterns="/testUserCache.do")
public class TestUserCacheDao extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private UserCacheDao userCacheDao = null;
	
	{
		/**
		 * 无论从哪个浏览器，用servlet进入，只new一次UserCacheDao(),
		 * new一次对象只跑一次UserCacheDao里面的实例块，只有一个session，session不关闭，则缓存一直保持着。
		 * session不关闭，则用session查询数据时，语句一样，则从缓存中取。
		 */
		userCacheDao = new UserCacheDao();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		/**
		 * 测试一级缓存，同一个session
		 */
		req.setAttribute("user", userCacheDao.getUserByIdLoad(4));
		req.setAttribute("user2", userCacheDao.getUserByIdGet(5));
		req.setAttribute("userList", userCacheDao.getAll());
		/**
		 * 测试二级缓存，不同的session
		 */
		UserCacheDao userCacheDao2 = new UserCacheDao();
		Session session2 = userCacheDao2.getSession();

		@SuppressWarnings("unchecked")
		List<Book> bookList = session2.createQuery("from Book ").list();
		req.setAttribute("bookList", bookList);
		
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
