package com.exp.service;

import java.util.List;
import java.util.Map;

import com.exp.base.BaseDao;
import com.exp.entities.Order;

public interface OrderService extends BaseDao<Order>{
	boolean cancel(String param);
	public Order getById(String id) ;
	public void delete(String id) ;
	List<?> findCustomerList(Integer id);
	public void deleteOrderAndItems(String id);
	Order editOrderStatus(Order model);
	Order saveOrUpdateOrder(Order order,Integer status,Map<String,Object> param);
}
