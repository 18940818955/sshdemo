package com.exp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.base.BaseDaoImpl;
import com.exp.entities.Basedata;
import com.exp.entities.Item;
import com.exp.entities.Order;
import com.exp.entities.User;
import com.exp.service.BasedataService;
import com.exp.service.ItemService;
import com.exp.service.OrderService;
import com.exp.util.MailUtil;
import com.exp.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;

@Service
@Transactional
public class OrderServiceImpl extends BaseDaoImpl<Order> implements
		OrderService {
	@Resource
	private BasedataService basedataService;
	@Resource
	private ItemService itemService;
	

	public boolean cancel(String param) {
		Order order = getById(param);
		if (order.getStatus().getId() == 1||order.getStatus().getId() == 3) {
			delete(param);
			getSession().createQuery("delete from Item i where i.order.id=?").setParameter(0, param).executeUpdate();
			Map<?, ?> map  = (Map<?, ?>) ActionContext.getContext().getApplication().get("mail");
			String mailAddr = (String) map.get("username");
			MailUtil.simpleSender(mailAddr, "订单被取消--"+order.getId(),
					"用户:"+order.getCreateUser().getName()+"的订单:\"" + order.getId() + "\"已经被取消，详细情况请联系订货方。");
			return true;
		}
		return false;
	}

	public Order getById(String id) {
		if (id == null) {
			return null;
		} else {
			return (Order) getSession().get(Order.class, id);
		}
	}

	public void delete(String id) {
		// TODO Auto-generated method stub
		if (id == null) {
		} else {
			Order order = getById(id);
			getSession().delete(order);
		}
	}

	public List<?> findCustomerList(Integer id) {
		// TODO Auto-generated method stub
		
		String hql = "from Order o where o.createUser.id = ?";
		List<?> list = getSession().createQuery(hql).setParameter(0, id).list();
		return list;
	}


	public void deleteOrderAndItems(String id) {
		
		Order order = getById(id);
		if(order.getStatus().getId()==1){
			itemService.deleteItemByOrderId(order.getId());
			delete(id);
		}
	}

	public Order editOrderStatus(Order model) {
		Order order = null;
		try {
			if(model.getStatus()!=null){
				 order = getById(model.getId());
				Basedata bd = basedataService.getById(model.getStatus().getId());
				order.setStatus(bd);
				update(order);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}


	public Order saveOrUpdateOrder(Order model, Integer statusId,Map<String,Object> param) {
		Basedata bd = (Basedata) getSession().get(Basedata.class, statusId);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Date deliverTime = (Date) ServletActionContext.getRequest().getAttribute("deliverTime"); 
		String orderId=null;
		if(StringUtil.isValidStr(((String[])param.get("orderId"))[0])){
			orderId = String.valueOf(((String[])param.get("orderId"))[0]);
		}
//		param.get("orderId");
		if(StringUtil.isNotValidStr(orderId)){
			//创建订单
			// 获取最大id
			Object o=getSession().createQuery(
					"select max(o.id)  FROM Order o").uniqueResult();
			int maxId = 0;
			if(o!=null){
				String s=(String)o;
				maxId=Integer.valueOf(s.substring(1));
			}
			//设置属性
			model.setId("O0000" + (maxId+1));
			model.setStatus(bd);
			model.setCreateTime(new Date());
			model.setUpdateTime(new Date());
			model.setCreateUser(user);
			model.setUpdateUser(user);
			model.setDeliverTime(deliverTime);
			save(model);
		}else{
			//修改订单
			model = getById(orderId);
			model.setStatus(bd);
			model.setUpdateTime(new Date());
			model.setUpdateUser(user);
			model.setDeliverTime(deliverTime);
			update(model);
		}
		
		for(int i=0;i<((String[])param.get("amount")).length;i++){
			Item itm= null;
			if((((String[])param.get("id"))[i])==null ||"".equals((((String[])param.get("id"))[i])))
			{
				itm= new Item();
			}else{
				itm=(Item) getSession().get(Item.class, Integer.valueOf(((String[])param.get("id"))[i]));
			}	
			
			if(StringUtil.isValidStr(String.valueOf((String[])param.get("amount")))){
				itm.setAmount(StringUtil.getObjhectToDouble(((String[])param.get("amount"))[i]));
			}
			if(StringUtil.isValidStr(String.valueOf((String[])param.get("realAmount")))){
				itm.setRealAmount(StringUtil.getObjhectToDouble(((String[])param.get("realAmount"))[i]));
			}
			if(StringUtil.isValidStr(String.valueOf((String[])param.get("receiveAmount")))){
				itm.setReceiveAmount(StringUtil.getObjhectToDouble(((String[])param.get("receiveAmount"))[i]));
			}
			if(StringUtil.isValidStr(String.valueOf((String[])param.get("reason")))){
				itm.setReason(((String[])param.get("reason"))[i]);
			}
			if(StringUtil.isValidStr(String.valueOf((String[])param.get("price")))){
				itm.setPrice(StringUtil.getObjhectToDouble(((String[])param.get("price"))[i]));
			}
			if(StringUtil.isValidStr(String.valueOf((String[])param.get("productName")))){
				itm.setProductName(((String[])param.get("productName"))[i]);
			}
			itm.setOrder(model);

			itemService.saveOrUpdate(itm);

		}
		return model;
	}

}
