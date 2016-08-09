package com.lukeyboy1.beans;
import com.lukeyboy1.beans.template.TemplateBean;
import com.lukeyboy1.utility.support.FitbaConstants;

public class NavigationBean extends TemplateBean {
	private String homeClass;
	private String projectClass;
	private String contentClass;
	private String seoClass;
	private String teamClass;
	private String otherClass;
	private String requestedPage;
	private String baseClass;
	private String processesClass;
	private String logoutClass;
	private String adminClass;
	private final String loggedOutClass = "class='"+FitbaConstants.BUTTON_CLASS+"'";
	private final String pressedClass = "class='"+FitbaConstants.BUTTON_CLASS_PRESSED+"'";
	
	
	public NavigationBean() {
			
		homeClass = "class='"+FitbaConstants.BUTTON_CLASS_PRESSED+"'";
		projectClass = "class='"+FitbaConstants.BUTTON_CLASS+"'";
		contentClass = "class='"+FitbaConstants.BUTTON_CLASS+"'";
		seoClass = "class='"+FitbaConstants.BUTTON_CLASS+"'";
		teamClass = "class='"+FitbaConstants.BUTTON_CLASS+"'";
		otherClass = "class='"+FitbaConstants.BUTTON_CLASS+"'";
		processesClass = "class='"+FitbaConstants.BUTTON_CLASS+"'";
		logoutClass = "class='"+FitbaConstants.BUTTON_CLASS+"'";
		adminClass = "class='"+FitbaConstants.BUTTON_CLASS+"'";
		requestedPage = "";
	}
	
	
		
	public String getSeoClass() {
		return seoClass;
	}



	public String getContentClass() {
		return contentClass;
	}


	public String getPressedClass() {
		return pressedClass;
	}

	public String getLoggedOutClass() {
		return loggedOutClass;
	}

	public String getLogoutClass() {
		return logoutClass;
	}

	public void setLogoutClass(String logoutClass) {
		this.logoutClass = logoutClass;
	}

	public String getProcessesClass() {
		return processesClass;
	}



	public void setProcessesClass(String processesClass) {
		this.processesClass = processesClass;
	}



	public String getBaseClass() {
		return baseClass;
	}

	public void setBaseClass(String baseClass) {
		this.baseClass = baseClass;
	}

	public String getRequestedPage() {
		return requestedPage;
	}

	public void setRequestedPage(String req) {
		requestedPage = req;
		if(requestedPage.contains("/matchServlet")) {
			adminClass = "class='"+FitbaConstants.BUTTON_CLASS_PRESSED+"'";
			homeClass = "class='"+FitbaConstants.BUTTON_CLASS+"'";
		}
		else if(requestedPage.contains("/teams")) {
			teamClass = "class='"+FitbaConstants.BUTTON_CLASS_PRESSED+"'";
			homeClass = "class='"+FitbaConstants.BUTTON_CLASS+"'";
		}
		else if(requestedPage.contains("/logout")) {
			logoutClass = "class='"+FitbaConstants.BUTTON_CLASS_PRESSED+"'";
			homeClass = "class='"+FitbaConstants.BUTTON_CLASS+"'";
		}
	}

	public String getHomeClass() {
		return homeClass;
	}
	public void setHomeClass(String homeClass) {
		this.homeClass = homeClass;
	}
	public String getProjectClass() {
		return projectClass;
	}
	public void setProjectClass(String projectClass) {
		this.projectClass = projectClass;
	}
	public String getTeamClass() {
		return teamClass;
	}
	public void setTeamClass(String teamClass) {
		this.teamClass = teamClass;
	}
	public String getOtherClass() {
		return otherClass;
	}
	public void setOtherClass(String otherClass) {
		this.otherClass = otherClass;
	}

	public String getAdminClass() {
		return adminClass;
	}

	public void setAdminClass(String adminClass) {
		this.adminClass = adminClass;
	}	
	
}
