package org.minioa.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jboss.seam.ui.HibernateEntityLoader;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;

public class MySession {
	
	public Lang lang;

	public Lang getLang() {
		if (lang == null)
			lang = (Lang) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("Lang");
		return lang;
	}
	
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
		if (null == templateName || "".equals(templateName))
			templateName = "default";
		return templateName;
	}

	private String searchKeyWords;

	public void setSearchKeyWords(String data) {
		searchKeyWords = data;
	}

	public String getSearchKeyWords() {
		if (null == searchKeyWords || "".equals(searchKeyWords)){
			if(null!=getLang())
				searchKeyWords = getLang().getProp().get(this.getL()).get("searchkeywords");
		}
		return searchKeyWords;
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
	
	private Map<String, Map<Integer, Boolean>> tempMap;

	public void setTempMap(Map<String, Map<Integer, Boolean>> data) {
		tempMap = data;
	}

	public Map<String, Map<Integer, Boolean>> getTempMap() {
		if (tempMap == null)
			tempMap = new HashMap<String,  Map<Integer, Boolean>>();
		return tempMap;
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
		if (isLogin == null || !isLogin.equals("true"))
			isLogin = "<script language=\"javascript\">window.location.href = 'login.jsf';</script>";
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

	public String depaName;

	public void setDepaName(String data) {
		depaName = data;
	}

	public String getDepaName() {
		return depaName;
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
	
	private Session session;

	private Session getSession() {
		if (session == null)
			session = new HibernateEntityLoader().getSession();
		if (!session.isOpen())
			session = session.getSessionFactory().openSession();
		return session;
	}
	
	private int i,level;
	private StringBuffer sb;
	private String menuText;
	public String getMenuText() {
		if(menuText==null){
			try
			{
				i = 0;
				level = 0;
				sb = new StringBuffer();
				sb.append("<div class=\"menu\">\r\n");
				sb.append("<ul>\r\n");
				addNodes(0);
				sb.append("</ul>\r\n");
				sb.append("</div>\r\n");
				menuText = sb.toString();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return menuText;
	}
	public void addNodes(int parentId) {
		try {
			level ++;
			boolean hasChildren = false;
			String className ="";
			Query query = getSession().getNamedQuery("core.topmenu.getchildren");
			query.setParameter("parentId", parentId);
			Iterator it = query.list().iterator();
			int id;
			String name, url, target;
			while (it.hasNext()) {
				Object obj[] = (Object[]) it.next();
				id = 0;
				name = url = target = "";
				if (obj[0] != null)
					id = Integer.valueOf(String.valueOf(obj[0]));
				if (obj[1] != null)
					name = String.valueOf(obj[1]);
				if (obj[2] != null)
					url = String.valueOf(obj[2]);
				if (obj[3] != null)
					target = String.valueOf(obj[3]);
				hasChildren = hasChild(id);
				if(i==0){
					className ="class=\"current\"";
					i++;
				}
				else
					className = "";
				
				if(!hasChildren){
					sb.append("<li><a "+ className +" href=\""+url+"\" target=\""+ target +"\">"+ name +"</a></li>\r\n");
				}
				else{
					if(level == 2)
						className = "class=\"sub1\"";
					if(level == 3)
						className = "class=\"sub2\"";
					sb.append("<li><a "+ className +" href=\""+url+"\" target=\""+ target +"\">"+ name +"<!--[if IE 7]><!--></a><!--<![endif]-->\r\n");
					sb.append("<!--[if lte IE 6]><table><tr><td><![endif]-->\r\n");
					sb.append("<ul>\r\n");
					className = "";
					addNodes(id);
					sb.append("</ul>\r\n");
					sb.append("<!--[if lte IE 6]></td></tr></table></a><![endif]-->\r\n");
					sb.append("</li>\r\n");
				}
			}
			level --;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public boolean hasChild(int parentId) {
		try {
			Query query = getSession().getNamedQuery("core.topmenu.haschildren");
			query.setParameter("parentId", parentId);
			if ("0".equals(String.valueOf(query.list().get(0))))
				return false;
			else
				return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
