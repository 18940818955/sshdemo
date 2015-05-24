package com.exp.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.base.BaseDaoImpl;
import com.exp.entities.Product;
import com.exp.entities.User;
import com.exp.service.ProductService;

@Transactional
@Service
public class ProductServiceImpl extends BaseDaoImpl<Product> implements
		ProductService {

	public void addModel(List<Integer> ids) {
		try {
			Product product = null;
			User u = (User) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			User user = (User) getSession().get(User.class, u.getId());
			Set<Product> products = new HashSet<Product>();
			for (Integer id : ids) {
				product = getById(id);
				products.add(product);
			}
			user.setProducts(products);
			getSession().update(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<?> findByUserId() {
		// TODO Auto-generated method stub
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return getSession().createQuery("select distinct p from Product p inner join p.users u where u.id=?").setParameter(0, user.getId()).list();
	}

}
