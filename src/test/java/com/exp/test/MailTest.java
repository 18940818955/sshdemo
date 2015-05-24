package com.exp.test;

import java.util.Properties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailTest {
	public static void main(String[] args) throws Exception {
		String to = "****";// 收件人
		String title = "主题11";// 主题
		String content = "内容11";// 内容
		String username = "****";// 用户名
		String password = "****";// 密码
		String host = "smtp.qq.com";// SMTP地址
		sendMail(to, title, content, username, password, host);
	}

	public static void sendMail(String to, String title, String content,
			String username, String password, String smtp) {
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		mailSenderImpl.setHost(smtp);
		mailSenderImpl.setUsername(username);
		mailSenderImpl.setPassword(password);
		Properties javaMailProperties = new Properties();
		javaMailProperties.setProperty("mail.smtp.auth", "true");
		mailSenderImpl.setJavaMailProperties(javaMailProperties);
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to);
		msg.setSubject(title);
		msg.setText(content);
		msg.setFrom(username);
		mailSenderImpl.send(msg);
	}
}
