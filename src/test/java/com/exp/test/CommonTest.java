package com.exp.test;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.exp.entities.Item;
import com.exp.entities.Order;
import com.exp.entities.User;
import com.exp.util.MailUtil;
import com.exp.util.PropertyUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-common.xml")
public class CommonTest {
	@Resource
	private SessionFactory sessionFactory;

	@Test
	public void test144() throws Exception {
		Map<?, ?> map = PropertyUtil.getMapProperties("/mail.properties");
		System.out.println(map);
	}

	@Test
	public void testMail() {
		MailUtil.simpleSender("253498229@qq.com", "这是主题", "这是内容");
	}

	@Test
	@Transactional
	public void testHql() {
		Session session = sessionFactory.getCurrentSession();
		int id = (int) session.createQuery("select max(o.id)  FROM Order o")
				.uniqueResult();
		String result = "";
		result = "o00" + id;
		System.out.println(result);
	}

	@SuppressWarnings("unchecked")
	@Test
	@Transactional
	public void testHql1() {
		Session session = sessionFactory.getCurrentSession();
		Integer createUser = ((User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal()).getId();
		String hql = "from Order o where o.createUser = ?";
		List<Item> list = session.createQuery(hql).setParameter(0, createUser).list();
		System.out.println(list);
	}

	@SuppressWarnings("unchecked")
	@Test
	@Transactional
	public void testHql2() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select distinct i From Item i where i.order.id=:orderId";
		List<Item> list = session.createQuery(hql)
				.setParameter("orderId", "O00002").list();
		System.out.println(list);
	}

	@SuppressWarnings("unchecked")
	@Test
	@Transactional
	public void testHql3() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Order o WHERE  o.createUser.id = ?";
		List<Item> list = session.createQuery(hql).setParameter(0, 2).list();
		System.out.println(list);
	}

	@Test
	@Transactional
	public void testHql4() {
		Session session = sessionFactory.getCurrentSession();
		String orderId = "O00001";
		Order order = (Order) session
				.createQuery("from Order o where o.id = ?")
				.setParameter(0, orderId).uniqueResult();
		// String hql = "from Order o where o.id = ?";
		// Order order = (Order) session.createQuery(hql).setParameter(0,
		// "O00001").uniqueResult();
		System.out.println(order);
	}

	@SuppressWarnings("rawtypes")
	@Test
	@Transactional
	public void testHql5() {
		Session session = sessionFactory.getCurrentSession();
		List list = session.createQuery("from Order").list();
		System.out.println(list.size());
	}
}
