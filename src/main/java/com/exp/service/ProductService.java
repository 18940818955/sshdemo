package com.exp.service;

import java.util.List;

import com.exp.base.BaseDao;
import com.exp.entities.Product;

public interface ProductService extends BaseDao<Product>{

	void addModel(List<Integer> ids);

	List<?> findByUserId();

	
}
