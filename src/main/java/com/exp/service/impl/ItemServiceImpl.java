package com.exp.service.impl;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.base.BaseDaoImpl;
import com.exp.entities.Item;
import com.exp.entities.Order;
import com.exp.service.ItemService;

@Service
@Transactional
public class ItemServiceImpl extends BaseDaoImpl<Item> implements ItemService {

	@SuppressWarnings("unchecked")
	public void deleteItemByOrderId(String orderId) {
		try {
			if (orderId == null) {
			} else {
				List<Item> lst = getSession()
						.createQuery(
								"select distinct i From Item i where i.order.id=:orderId")
						.setParameter("orderId", orderId).list();
				for (Item itm : lst) {
					if (itm != null)
						getSession().delete(itm);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Item> getItemByOrderId(String orderId) {
		Order order = (Order) getSession()
				.createQuery("from Order o where o.id = ?")
				.setParameter(0, orderId).uniqueResult();
		if (order.getDeliverTime() != null) {
			ServletActionContext.getRequest().setAttribute("deliverTime",
					order.getDeliverTime());
		}
		List<Item> lst = null;
		try {
			if (orderId == null) {
			} else {
				lst = getSession()
						.createQuery(
								"select distinct i From Item i where i.order.id=:orderId")
						.setParameter("orderId", orderId).list();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lst;
	}

}
