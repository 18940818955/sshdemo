package com.exp.actions;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.exp.base.BaseAction;
import com.exp.entities.Product;
import com.exp.util.AjaxUtil;
import com.exp.util.QueryHelper;

@Controller
@Scope("prototype")
public class ProductAction extends BaseAction<Product> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5097885700432461768L;
	private List<Integer> ids;

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public String list() {
		new QueryHelper(Product.class, "u").preparePageBean(productService,
				pageNum, pageSize);
		return list;
	}

	public String delete() {
		productService.delete(param);
		return tolist;
	}

	public String edit() {
		Product product = new Product();
		if (model.getId() != null)
			product = productService.getById(model.getId());
		product.setProductName(model.getProductName());
		product.setPrice(model.getPrice());
		productService.saveOrUpdate(product);
		return tolist;
	}

	public void editAjax() {
		Product p = productService.getById(param);
		AjaxUtil.renderText(p);
	}

	public String initProductModel() {
		resultList = productService.findAll();
		return "modelUI";
	}

	public String addModel() {
		productService.addModel(ids);
		return "toModelUI";
	}
}
