package com.exp.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MapUtil extends ActionSupport{
	@SuppressWarnings("all")
	public static Map<String, String> getParam() {
		ActionContext ac = ActionContext.getContext();
		Map<String, Object> param = ac.getParameters();
		Map map = new HashMap();
		Set set = param.entrySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry mapentry = (Map.Entry) iterator.next();
			map.put(mapentry.getKey(), ((String[]) mapentry.getValue())[0]);
		}
		return map;
	}
}