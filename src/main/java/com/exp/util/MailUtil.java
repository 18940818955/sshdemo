package com.exp.util;

import java.util.Map;
import java.util.Properties;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.opensymphony.xwork2.ActionContext;


public class MailUtil { 

	public static boolean simpleSender(String to,String title,String content){
		try {
			Map<?, ?> map = (Map<?, ?>) ActionContext.getContext().getApplication().get("mail");
			String smtp = (String) map.get("smtp");
			String username = (String) map.get("username");
			String password = (String) map.get("password");
			JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
			mailSenderImpl.setHost(smtp);
			mailSenderImpl.setUsername(username);
			mailSenderImpl.setPassword(password);
			Properties javaMailProperties = new Properties() ;
			javaMailProperties.setProperty("mail.smtp.auth", "true");
			mailSenderImpl.setJavaMailProperties(javaMailProperties );
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(to);
			msg.setSubject(title);
			msg.setText(content);
			msg.setFrom(username);
			mailSenderImpl.send(msg);
			return true;
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
} 

