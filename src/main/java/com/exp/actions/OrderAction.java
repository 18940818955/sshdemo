package com.exp.actions;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.exp.base.BaseAction;
import com.exp.entities.Basedata;
import com.exp.entities.Order;
import com.exp.entities.Product;
import com.exp.entities.User;
import com.exp.util.AjaxUtil;
import com.exp.util.DateUtil;
import com.exp.util.MailUtil;
import com.exp.util.QueryHelper;
import com.exp.util.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class OrderAction extends BaseAction<Order> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3432916808886064107L;
	private Integer statusid;

	public Integer getStatusid() {
		return statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	private List<?> statusList;

	public List<?> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<?> statusList) {
		this.statusList = statusList;
	}

	@SuppressWarnings("unchecked")
	public String list() {
		Collection<? extends GrantedAuthority> col = SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		String role = String.valueOf(col);
		Integer createUser = ((User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal()).getId();
		resultList = productService.findAll();
		statusList = basedataService.findAll();
		if (StringUtil.isValidStr(str)) {
			for (Product p : (List<Product>) resultList) {
				if (str.equals(p.getProductName())) {
					ServletActionContext.getRequest().setAttribute("productId",
							p.getId());
					ServletActionContext.getRequest().setAttribute(
							"productName", p.getProductName());
				}
			}
		}
		if (statusid != null && statusid != 0) {
			Basedata bd = basedataService.getById(statusid);
			ServletActionContext.getRequest().setAttribute("statusId",
					bd.getId());
			ServletActionContext.getRequest().setAttribute("statusName",
					bd.getName());
		}
		try {
			new QueryHelper(Order.class, "o")
					.addCondition(role.indexOf("ROLE_ADMIN") == -1,
							" o.createUser.id = ?", createUser)
					.addCondition(model.getId() != null, " o.id like ? ",
							"%" + model.getId() + "%")
					.addCondition(str != null, " o.productName like ? ",
							"%" + str + "%")
					.addCondition(statusid != null, " o.status.id=? ", statusid)
					.preparePageBean(orderService, pageNum, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public String delete() throws Exception {

		orderService.deleteOrderAndItems(str);
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		MailUtil.simpleSender(user.getEmail(), "订单信息", "您的订单:\"" + str
				+ "\"已经取消");
		return tolist;
	}

	public String changeStatus() throws Exception {
		orderService.editOrderStatus(model);
		return tolist;
	}

	public String add() throws Exception {
		ActionContext ac = ActionContext.getContext();
		Map<String, Object> param = ac.getParameters();
		Order order = orderService.saveOrUpdateOrder(model, 1, param);
		Map<?, ?> mail = (Map<?, ?>) ActionContext.getContext()
				.getApplication().get("mail");
		String mailAddr = (String) mail.get("username");
		MailUtil.simpleSender(mailAddr, "新建订单信息",
				"用户：" + order.getCreateUser().getName() + " 已经订货，订单号为:\""
						+ order.getId() + "\",请及时登录系统查看详情。");
		return tolist;
	}

	public String addUI() {
		Date deliverTime = DateUtil.addDate(new Date(), +3);
		ServletActionContext.getRequest().setAttribute("deliverTime",
				deliverTime);
		try {
			resultList = productService.findByUserId();
			if (resultList == null || resultList.isEmpty()) {
				resultList = productService.findAll();
			}
			addFlg = 1;
			return "addUI";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "errorPage";
		}

	}

	public String editUI() {

		try {
			resultList = itemService.getItemByOrderId(str);
			ServletActionContext.getRequest().setAttribute("orderId", str);
			Order order = orderService.getById(str);
			ServletActionContext.getRequest().setAttribute("status",
					order.getStatus());
			return "editUI";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "errorPage";
		}

	}

	public void editAjax() {
		if (StringUtil.isValidStr(str)) {
			Order o = orderService.getById(str);
			if (o.getStatus().getId() == 1 || o.getStatus().getId() == 3)
				AjaxUtil.renderText("OK");
		}
	}

	public void getProductPriceById() throws Exception {
		if (param != null) {
			Product p = productService.getById(param);
			ObjectMapper m = new ObjectMapper();
			String json = m.writeValueAsString(p);
			AjaxUtil.renderText(json);
		} else {
			AjaxUtil.renderText("FAILED");
		}
	}

	/**
	 * 取消订单
	 */
	public String cancel() throws Exception {
		if (orderService.cancel(str)) {
			return tolist;
		}
		return sessionOut;
	}

	public String initReport() {
		resultList = productService.findAll();
		return "report";
	}

	/**
	 * 驳回订单
	 */
	public String regectOrder() {
		model.setStatus(new Basedata(3));
		orderService.editOrderStatus(model);
		Order order = orderService.getById(model.getId());
		MailUtil.simpleSender(order.getCreateUser().getEmail(), "订单被驳回--"
				+ order.getId(), "您的订单:\"" + order.getId()
				+ "\"已经被驳回，详细情况请联系发货方。");
		return tolist;
	}

	/**
	 * 接受订单(管理员)，发信给客户
	 */
	public String accept() {
		Map<String, Object> param = ActionContext.getContext().getParameters();
		Order order = orderService.saveOrUpdateOrder(model, 2, param);
		// String mailAddr = (String) ((Map)
		// ActionContext.getContext().getApplication().get("mail")).get("username");
		MailUtil.simpleSender(order.getCreateUser().getEmail(), "订单信息",
				"您的订单:\"" + order.getId() + "\"发货方已经接受，请及时登录系统查看详情。");
		return tolist;
	}

	/**
	 * 确认收货
	 */
	public String confirmOrder() {
		model.setStatus(new Basedata(4));
		ActionContext ac = ActionContext.getContext();
		Map<String, Object> param = ac.getParameters();
		orderService.saveOrUpdateOrder(model, 4, param);
		Order order = orderService
				.getById(((String[]) param.get("orderId"))[0]);
		Map<?, ?> map = (Map<?, ?>) ActionContext.getContext().getApplication()
				.get("mail");
		String mailAddr = (String) map.get("username");
		MailUtil.simpleSender(mailAddr, "订单信息", "用户:"
				+ order.getCreateUser().getName() + "的订单:\"" + order.getId()
				+ "\"已经完成交易。");
		return tolist;
	}

	/**
	 * 重新下单
	 */
	public String reorder() {
		ActionContext ac = ActionContext.getContext();
		Map<String, Object> param = ac.getParameters();
		Order order = orderService.saveOrUpdateOrder(model, 1, param);
		Map<?, ?> map = (Map<?, ?>) ActionContext.getContext().getApplication()
				.get("mail");
		String mailAddr = (String) map.get("username");
		MailUtil.simpleSender(mailAddr, "重新下单信息", "用户:"
				+ order.getCreateUser().getName() + "的订单订单\"" + order.getId()
				+ "\"已经重新下单");
		return tolist;
	}
}
