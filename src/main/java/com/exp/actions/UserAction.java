package com.exp.actions;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.exp.base.BaseAction;
import com.exp.entities.User;
import com.exp.util.AjaxUtil;
import com.exp.util.QueryHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3242890550630870873L;

	public String list() {
		new QueryHelper(User.class, "u").preparePageBean(userService, pageNum,
				pageSize);
		return list;
	}

	public String delete() {
		try {
			userService.delete(param);
			return tolist;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultBean = "该用户已经有订单";
			return "errorPage";
		}
	}

	public String edit() {
		userService.updateUser(model);
		return tolist;
	}

	public void editAjax() throws Exception {
		User p = userService.getById(param);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(p);
		AjaxUtil.renderText(json);
	}

	public String info() {
		return "info";
	}

	public String updateInfo() {
		userService.updateInfo(model);
		return "logout";
	}

	public String changePassword() {
		userService.changePwd(model);
		return "logout";
	}
}
