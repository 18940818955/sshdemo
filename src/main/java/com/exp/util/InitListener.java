package com.exp.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class InitListener
 * 
 */
public class InitListener implements ServletContextListener {
	private Map<String, String> mail = new HashMap<>();

	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext context = arg0.getServletContext();
		try {
			mail = PropertyUtil.getMapProperties("/mail.properties");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e+"初始化mailproperties出错-------");
		}
		context.setAttribute("mail", mail);
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}

}
