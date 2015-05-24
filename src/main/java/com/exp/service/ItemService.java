package com.exp.service;

import java.util.List;
import com.exp.base.BaseDao;
import com.exp.entities.Item;

public interface ItemService extends BaseDao<Item>{

	void deleteItemByOrderId(String orderId);
	List<?> getItemByOrderId(String orderId);
}
