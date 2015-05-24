package com.exp.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.exp.entities.Basedata;
import com.exp.entities.Product;
import com.exp.entities.Role;
import com.exp.entities.User;
@Component
public class InitialData {
	@Resource
	private SessionFactory sessionFactory;
	@Transactional
	public void initData(){
		Session s = sessionFactory.getCurrentSession();
		Role r1 = new Role("ROLE_USER","注册用户");
		Role r2 = new Role("ROLE_ADMIN","管理员");
		Role r3 = new Role("ROLE_CUSTOMER","客户");
		s.save(r1);
		s.save(r2);
		s.save(r3);

		Set<Role> roles1 = new HashSet<Role>();
		roles1.add(r1);
		roles1.add(r2);
		Set<Role> roles2 = new HashSet<Role>();
		roles2.add(r1);
		roles2.add(r3);

		User u1 = new User("admin", "admin", "253498229@qq.com","111", roles1);
		User u2 = new User("user", "user",  "253498229@qq.com", "111", roles2);
		s.save(u1);
		s.save(u2);

		Basedata b = new Basedata("已下单", 1);
		Basedata b1 = new Basedata("已接受", 1);
		Basedata b2 = new Basedata("驳回", 1);
		Basedata b3 = new Basedata("订单完成", 1);
		s.save(b);
		s.save(b1);
		s.save(b2);
		s.save(b3);
		List<Product> products = new ArrayList<Product>();
		Product product ;
		product= new Product("正丝φ14");
		products.add(product);
		product= new Product("正丝φ16");
		products.add(product);
		product= new Product("正丝φ18");
		products.add(product);
		product= new Product("正丝φ10");
		products.add(product);
		product= new Product("正丝φ22");
		products.add(product);
		product= new Product("正丝φ25");
		products.add(product);
		product= new Product("正丝φ28");
		products.add(product);
		product= new Product("正丝φ32");
		products.add(product);
		product= new Product("正丝φ36");
		products.add(product);
		product= new Product("正丝φ40");
		products.add(product);
		
		
		product= new Product("反丝φ14");
		products.add(product);
		product= new Product("反丝φ16");
		products.add(product);
		product= new Product("反丝φ18");
		products.add(product);
		product= new Product("反丝φ10");
		products.add(product);
		product= new Product("反丝φ22");
		products.add(product);
		product= new Product("反丝φ25");
		products.add(product);
		product= new Product("反丝φ28");
		products.add(product);
		product= new Product("反丝φ32");
		products.add(product);
		product= new Product("反丝φ36");
		products.add(product);
		product= new Product("反丝φ40");
		products.add(product);
		
		
		product= new Product("变径φ14-16");
		products.add(product);
		product= new Product("变径φ14-18");
		products.add(product);
		product= new Product("变径φ14-20");
		products.add(product);
		product= new Product("变径φ14-22");
		products.add(product);
		product= new Product("变径φ14-25");
		products.add(product);
		product= new Product("变径φ14-28");
		products.add(product);
		product= new Product("变径φ14-32");
		products.add(product);
		product= new Product("变径φ14-36");
		products.add(product);
		product= new Product("变径φ14-40");
		products.add(product);
		product= new Product("变径φ16-18");
		products.add(product);
		product= new Product("变径φ16-20");
		products.add(product);
		product= new Product("变径φ16-22");
		products.add(product);
		product= new Product("变径φ16-25");
		products.add(product);
		product= new Product("变径φ16-28");
		products.add(product);
		product= new Product("变径φ16-32");
		products.add(product);
		product= new Product("变径φ16-36");
		products.add(product);
		product= new Product("变径φ16-40");
		products.add(product);
		product= new Product("变径φ18-20");
		products.add(product);
		product= new Product("变径φ18-22");
		products.add(product);
		product= new Product("变径φ18-25");
		products.add(product);
		product= new Product("变径φ18-28");
		products.add(product);
		product= new Product("变径φ18-32");
		products.add(product);
		product= new Product("变径φ18-36");
		products.add(product);
		product= new Product("变径φ18-40");
		products.add(product);
		product= new Product("变径φ20-22");
		products.add(product);
		product= new Product("变径φ20-25");
		products.add(product);
		product= new Product("变径φ20-28");
		products.add(product);
		product= new Product("变径φ20-32");
		products.add(product);
		product= new Product("变径φ20-36");
		products.add(product);
		product= new Product("变径φ20-40");
		products.add(product);
		product= new Product("变径φ22-25");
		products.add(product);
		product= new Product("变径φ22-28");
		products.add(product);
		product= new Product("变径φ22-32");
		products.add(product);
		product= new Product("变径φ22-36");
		products.add(product);
		product= new Product("变径φ22-40");
		products.add(product);
		product= new Product("变径φ25-28");
		products.add(product);
		product= new Product("变径φ25-32");
		products.add(product);
		product= new Product("变径φ25-36");
		products.add(product);
		product= new Product("变径φ25-40");
		products.add(product);
		product= new Product("变径φ28-32");
		products.add(product);
		product= new Product("变径φ28-36");
		products.add(product);
		product= new Product("变径φ28-40");
		products.add(product);
		product= new Product("变径φ32-36");
		products.add(product);
		product= new Product("变径φ32-40");
		products.add(product);
		product= new Product("变径φ36-40");
		products.add(product);
		
		for(Product p : products){
			s.save(p);
		}
		
	}
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-common.xml");
		InitialData initialData = (InitialData) ac.getBean("initialData");
		initialData.initData();
	}
}
