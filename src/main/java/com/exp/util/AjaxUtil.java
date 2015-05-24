package com.exp.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AjaxUtil {

	public static HttpServletResponse response;
	public static String renderText(final String text) {
		try {
			getHttpResponse().setContentType("text/plain;charset=UTF-8");
			getHttpResponse().getWriter().write(text);
		} catch (final IOException e) {
			// LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	public static <T> String renderText(final T entity) {
		try {
			getHttpResponse().setContentType("text/plain;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper(); 
			String json = mapper.writeValueAsString(entity);
			getHttpResponse().getWriter().write(json);
		} catch (final IOException e) {
			// LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	/**
	 * getHttpResponse
	 * 
	 * @return response
	 */
	public static HttpServletResponse getHttpResponse() {
		response = ServletActionContext.getResponse();
		return response;
	}
}
