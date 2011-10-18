package org.minioa.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jboss.seam.ui.*;
import org.jivesoftware.util.Blowfish;

public class UserSession {

	public Lang lang;

	public Lang getLang() {
		if (lang == null)
			lang = (Lang) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("Lang");
		if (lang == null)
			FunctionLib.redirect(FunctionLib.getWebAppName());
		return lang;
	}

	public MySession mySession;

	public MySession getMySession() {
		if (mySession == null)
			mySession = (MySession) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("MySession");
		if (mySession == null)
			FunctionLib.redirect(FunctionLib.getWebAppName());
		return mySession;
	}

	private Session session;

	private Session getSession() {
		if (session == null)
			session = new HibernateEntityLoader().getSession();
		return session;
	}

	public UserSession() {
	}

	private String online;
	
	public String getOnline(){
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			Query query = getSession().getNamedQuery("core.user.session.update");
			query.setParameter("cId", getMySession().getUserId());
			query.setParameter("sId", request.getSession().getId());
			query.setParameter("cTime", request.getSession().getCreationTime());
			query.setParameter("mTime", request.getSession().getLastAccessedTime());
			query.setParameter("ipAddress", getMySession().getIp());
			online = String.valueOf(query.list().get(0));
			if("-1".equals(online)){
				query = getSession().getNamedQuery("core.user.session.delete");
				query.setParameter("sessionId", request.getSession().getId());
				query.executeUpdate();
				HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
				session.invalidate();
				FunctionLib.redirect(FunctionLib.getWebAppName());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return online;
	}
}
