package org.minioa.core;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

public class MySession {
	// 消息
	public String msg;
	public int msgType;

	public void setMsg(String data, int type) {
		msg = data;
		switch (type) {
		case 0:
			msgScript = "<script language=\"javascript\">if(document.getElementById('mpForMsg').component != undefined) {document.getElementById('mpForMsgContentDiv').style.backgroundImage='url(images/warning.png)';document.getElementById('mpForMsg').component.show();}</script>";
			break;
		case 1:
			msgScript = "<script language=\"javascript\">if(document.getElementById('mpForMsg').component != undefined) {document.getElementById('mpForMsgContentDiv').style.backgroundImage='url(images/valid.png)';document.getElementById('mpForMsg').component.show();}</script>";
			break;
		default:
			msgScript = "<script language=\"javascript\">if(document.getElementById('mpForMsg').component != undefined) {document.getElementById('mpForMsgContentDiv').style.backgroundImage='url(images/error.png)';document.getElementById('mpForMsg').component.show();}</script>";
		}
	}

	public String getMsg() {
		if ("".equals(msg))
			msgScript = "";
		return msg;
	}

	// 显示消息的脚本
	public String msgScript;

	public String getMsgScript() {
		msg = "";
		return msgScript;
	}

	private String templateName;

	public void setTemplateName(String data) {
		templateName = data;
	}

	public String getTemplateName() {
		return templateName;
	}

	/**
	 * User Language
	 */
	private String l;

	public void setL(String data) {
		l = data;
	}

	public String getL() {
		l = "zh-cn";
		return l;
	}

	private Map<String, String> tempStr;

	public void setTempStr(Map<String, String> data) {
		tempStr = data;
	}

	public Map<String, String> getTempStr() {
		if (tempStr == null)
			tempStr = new HashMap<String, String>();
		return tempStr;
	}

	private Map<String, Integer> tempInt;

	public void setTempInt(Map<String, Integer> data) {
		tempInt = data;
	}

	public Map<String, Integer> getTempInt() {
		if (tempInt == null)
			tempInt = new HashMap<String, Integer>();
		return tempInt;
	}

	public String tab;

	public void setTab(String data) {
		tab = data;
	}

	public String getTab() {
		try {
			Map params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String tab = (String) params.get("tab");
			if (tab != null && tab.substring(0, 3).equals("tab"))
				return tab;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "tab1";
	}

	public String isLogin;

	public void setIsLogin(String data) {
		isLogin = data;
	}

	public String getIsLogin() {
		if (isLogin == null || !isLogin.equals("true")) {
			try {
				isLogin = "<script language=\"javascript\">window.parent.location.href = '../../templates/" + getTemplateName() + "/Login.jsf';</script>";
			} catch (Exception ex) {
				;
			}
		}
		return isLogin;
	}

	public int userId;

	public void setUserId(int data) {
		userId = data;
	}

	public int getUserId() {
		return userId;
	}

	public String userName;

	public void setUserName(String data) {
		userName = data;
	}

	public String getUserName() {
		return userName;
	}

	public String email;

	public void setEmail(String data) {
		email = data;
	}

	public String getEmail() {
		return email;
	}

	public String displayName;

	public void setDisplayName(String data) {
		displayName = data;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String roleNames;

	public void setRoleNames(String data) {
		roleNames = data;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public String superior;

	public void setSuperior(String data) {
		superior = data;
	}

	public String getSuperior() {
		return superior;
	}

	public String manager;

	public void setManager(String data) {
		manager = data;
	}

	public String getManager() {
		return manager;
	}

	public String ip;

	public void setIp(String data) {
		ip = data;
	}

	public String getIp() {
		return ip;
	}

	public int pageSize;

	public void setPageSize(int data) {
		pageSize = data;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int rowCount;

	public void setRowCount(int data) {
		rowCount = data;
	}

	public int getRowCount() {
		return rowCount;
	}

	private int pageCount;

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int data) {
		this.pageCount = data;
	}

	private int scrollerPage = 1;

	public int getScrollerPage() {
		return scrollerPage;
	}

	public void setScrollerPage(int data) {
		this.scrollerPage = data;
	}

	private String pageInit;

	public String getPageInit() {

		if (scrollerPage != 1)
			scrollerPage = 1;
		return null;
	}
}
