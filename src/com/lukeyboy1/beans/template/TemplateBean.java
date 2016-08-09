package com.lukeyboy1.beans.template;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class TemplateBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 170834051429074180L;
	
	private Integer userID;
	private HttpSession session;
	private HttpServletRequest request;
	
	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	

	public TemplateBean() {
		
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}

