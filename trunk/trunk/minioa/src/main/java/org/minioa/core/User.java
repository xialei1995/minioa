package org.minioa.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jboss.seam.ui.*;
import org.jivesoftware.util.Blowfish;

public class User {

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

	private String init;

	public String getInit() {
		selectRecordById();
		return init;
	}

	private String initProfile;

	public String getInitProfile() {
		selectUserProfile();
		return initProfile;
	}

	private Map<String, String> prop;

	public void setProp(Map<String, String> data) {
		prop = data;
	}

	public Map<String, String> getProp() {
		if (prop == null)
			prop = new HashMap<String, String>();
		return prop;
	}

	private List<User> recordsList;

	public List<User> getRecordsList() {
		if (recordsList == null)
			buildRecordsList();
		return recordsList;
	}

	public User() {
	}

	public User(int i) {
	}

	public User(Map<String, String> data) {
		setProp(data);
	}

	public List<Integer> dsList;

	public List<Integer> getDsList() {
		if (dsList == null) {
			getMySession();
			dsList = new ArrayList<Integer>();

			String key = "", key2 = "";
			if (mySession.getTempStr() != null) {
				if (mySession.getTempStr().get("User.key") != null)
					key = mySession.getTempStr().get("User.key").toString();
				if (mySession.getTempStr().get("User.key2") != null)
					key2 = mySession.getTempStr().get("User.key2").toString();
			}

			String sql = getSession().getNamedQuery("core.user.records.count").getQueryString();
			String where = " where 1=1";
			if (!key.equals(""))
				where += " and ta.userName like :key";
			if (!key2.equals(""))
				where += " and ta.displayName like :key2";
			Query query = getSession().createSQLQuery(sql + where);
			if (!key.equals(""))
				query.setParameter("key", "%" + key + "%");
			if (!key2.equals(""))
				query.setParameter("key2", "%" + key2 + "%");

			int i = 0;
			int dc = Integer.valueOf(String.valueOf(query.list().get(0)));
			while (i < dc) {
				dsList.add(i);
				i++;
			}
			mySession.setRowCount(dsList.size());
		}
		return dsList;
	}

	public void buildRecordsList() {
		try {

			getDsList();
			recordsList = new ArrayList<User>();
			Map<?, ?> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			if ("false".equals((String) params.get("reload"))) {
				int size = 0;
				while (size < mySession.getPageSize()) {
					recordsList.add(new User(size));
					size++;
				}
				return;
			}
			if ("true".equals((String) params.get("resetPageNo")))
				mySession.setScrollerPage(1);

			String key = "", key2 = "";
			if (mySession.getTempStr() != null) {
				if (mySession.getTempStr().get("User.key") != null)
					key = mySession.getTempStr().get("User.key").toString();
				if (mySession.getTempStr().get("User.key2") != null)
					key2 = mySession.getTempStr().get("User.key2").toString();
			}

			String sql = getSession().getNamedQuery("core.user.records").getQueryString();
			String where = " where 1=1";
			String other = " order by ta.userName desc limit :limit offset :offset";

			if (!key.equals(""))
				where += " and ta.userName like :key";
			if (!key2.equals(""))
				where += " and ta.displayName like :key2";
			Query query = getSession().createSQLQuery(sql + where + other);
			query.setParameter("limit", mySession.getPageSize());
			query.setParameter("offset", (Integer.valueOf(mySession.getScrollerPage()) - 1) * mySession.getPageSize());

			if (!key.equals(""))
				query.setParameter("key", "%" + key + "%");
			if (!key2.equals(""))
				query.setParameter("key2", "%" + key2 + "%");

			Iterator<?> it = query.list().iterator();
			Map<String, String> p;
			while (it.hasNext()) {
				Object obj[] = (Object[]) it.next();
				p = new HashMap<String, String>();
				p.put("id", FunctionLib.getString(obj[0]));
				p.put("depaId", FunctionLib.getString(obj[6]));
				p.put("jobId", FunctionLib.getString(obj[7]));
				p.put("jobId2", FunctionLib.getString(obj[8]));
				p.put("userName", FunctionLib.getString(obj[9]));
				p.put("email", FunctionLib.getString(obj[10]));
				p.put("phone", FunctionLib.getString(obj[11]));
				p.put("mobilePhone", FunctionLib.getString(obj[12]));
				p.put("gender", FunctionLib.getString(obj[13]));
				p.put("displayName", FunctionLib.getString(obj[14]));
				p.put("isLock", FunctionLib.getString(obj[15]));
				p.put("depaName", FunctionLib.getString(obj[16]));
				recordsList.add(new User(p));
			}
			it = null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 读取一条记录
	 */
	public void selectRecordById() {
		try {
			Map<?, ?> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String id = (String) params.get("id");
			if (id == null || !FunctionLib.isNum(id))
				id = String.valueOf(getMySession().getUserId());
			Query query = getSession().getNamedQuery("core.user.getrecordbyid");
			query.setParameter("id", id);
			Iterator<?> it = query.list().iterator();
			while (it.hasNext()) {
				Object obj[] = (Object[]) it.next();
				prop = new HashMap<String, String>();
				prop.put("id", FunctionLib.getString(obj[0]));
				prop.put("depaId", FunctionLib.getString(obj[6]));
				prop.put("jobId", FunctionLib.getString(obj[7]));
				prop.put("jobId2", FunctionLib.getString(obj[8]));
				prop.put("userName", FunctionLib.getString(obj[9]));
				prop.put("email", FunctionLib.getString(obj[10]));
				prop.put("phone", FunctionLib.getString(obj[11]));
				prop.put("mobilePhone", FunctionLib.getString(obj[12]));
				prop.put("gender", FunctionLib.getString(obj[13]));
				prop.put("displayName", FunctionLib.getString(obj[14]));
				prop.put("isLock", FunctionLib.getString(obj[15]));

				getMySession().getTempInt().put("Department.id", FunctionLib.getInt(obj[6]));
				getMySession().getTempStr().put("Department.depaName", FunctionLib.getString(obj[16]));
				getMySession().getTempInt().put("Job.id", FunctionLib.getInt(obj[7]));
				getMySession().getTempStr().put("Job.jobName", FunctionLib.getString(obj[17]));
				getMySession().getTempInt().put("Job.id2", FunctionLib.getInt(obj[8]));
				getMySession().getTempStr().put("Job.jobName2", FunctionLib.getString(obj[18]));
			}
			it = null;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void selectUserProfile() {
		try {
			Query query = getSession().getNamedQuery("core.user.getrecordbyid");
			query.setParameter("id", getMySession().getUserId());
			Iterator<?> it = query.list().iterator();
			while (it.hasNext()) {
				Object obj[] = (Object[]) it.next();
				prop = new HashMap<String, String>();
				prop.put("id", FunctionLib.getString(obj[0]));
				prop.put("depaId", FunctionLib.getString(obj[6]));
				prop.put("jobId", FunctionLib.getString(obj[7]));
				prop.put("jobId2", FunctionLib.getString(obj[8]));
				prop.put("userName", FunctionLib.getString(obj[9]));
				prop.put("email", FunctionLib.getString(obj[10]));
				prop.put("phone", FunctionLib.getString(obj[11]));
				prop.put("mobilePhone", FunctionLib.getString(obj[12]));
				prop.put("gender", FunctionLib.getString(obj[13]));
				prop.put("displayName", FunctionLib.getString(obj[14]));
				prop.put("isLock", FunctionLib.getString(obj[15]));
				prop.put("password", FunctionLib.getString(obj[19]));

				String passwordKey = FunctionLib.getWebParameter("passwordKey");
				String userName = prop.get("userName");
				String password = prop.get("password");
				String url = FunctionLib.getWebAppName();
				if ("".equals(url))
					url = "http://" + FunctionLib.getRequestHeaderMap("host") + "/autologin.jsf?url=";
				else
					url = "http://" + FunctionLib.getRequestHeaderMap("host") + "/" + url + "/autologin.jsf?url=";
				prop.put("url", url + new Blowfish(passwordKey).encryptString(userName + ";" + password + ";" + FunctionLib.getIp()));

				getMySession().getTempInt().put("Department.id", FunctionLib.getInt(obj[6]));
				getMySession().getTempStr().put("Department.depaName", FunctionLib.getString(obj[16]));
				getMySession().getTempInt().put("Job.id", FunctionLib.getInt(obj[7]));
				getMySession().getTempStr().put("Job.jobName", FunctionLib.getString(obj[17]));
				getMySession().getTempInt().put("Job.id2", FunctionLib.getInt(obj[8]));
				getMySession().getTempStr().put("Job.jobName2", FunctionLib.getString(obj[18]));
			}
			it = null;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void newRecord() {
		try {
			getMySession();
			if (!FunctionLib.isEmail(prop.get("email"))) {
				String msg = getLang().getProp().get(getMySession().getL()).get("emailcheckerror");
				getMySession().setMsg(msg, Integer.valueOf(0));
				return;
			}
			if ("".equals(prop.get("userName"))) {
				String msg = getLang().getProp().get(getMySession().getL()).get("noempty");
				getMySession().setMsg(msg, Integer.valueOf(0));
				return;
			}
			if (isUserExists(prop.get("userName"))) {
				String msg = getLang().getProp().get(getMySession().getL()).get("usernamehasbeanexists");
				getMySession().setMsg(msg, Integer.valueOf(0));
				return;
			}
			if (null == mySession.getTempInt().get("Department.id") || null == mySession.getTempInt().get("Job.id")) {
				return;
			}
			Query query = getSession().getNamedQuery("core.user.newrecord");
			query.setParameter("cId", 0);
			query.setParameter("depaId", mySession.getTempInt().get("Department.id"));
			query.setParameter("jobId", mySession.getTempInt().get("Job.id"));
			query.setParameter("jobId2", mySession.getTempInt().get("Job.id2"));
			query.setParameter("userName", prop.get("userName"));
			query.setParameter("email", prop.get("email"));
			query.setParameter("phone", prop.get("phone"));
			query.setParameter("mobilePhone", prop.get("mobilePhone"));
			query.setParameter("displayName", prop.get("displayName"));
			query.setParameter("password", prop.get("password"));
			query.setParameter("gender", prop.get("gender"));
			query.setParameter("isLock", prop.get("isLock"));

			query.executeUpdate();

			String msg = getLang().getProp().get(getMySession().getL()).get("success");
			getMySession().setMsg(msg, Integer.valueOf(1));
		} catch (Exception ex) {
			String msg = getLang().getProp().get(getMySession().getL()).get("faield");
			getMySession().setMsg(msg, Integer.valueOf(2));
			ex.printStackTrace();
		}
	}

	public boolean isUserExists(String userName) {
		try {
			if (Integer.valueOf(FunctionLib.exeSql(getSession(), "core.user.isrecordexistbyname", "userName", userName, "float")) == 0)
				return false;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

	public boolean isUserExists(String userName, String id) {
		try {
			if (Integer.valueOf(FunctionLib.exeSql(getSession(), "core.user.isrecordexistbyname.byid", "userName", userName, "id", id, "float")) == 0)
				return false;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

	public void updateRecordById() {
		try {
			getMySession();
			Map<?, ?> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String id = (String) params.get("id");
			if (!FunctionLib.isNum(id))
				return;

			if (!FunctionLib.isEmail(prop.get("email"))) {
				String msg = getLang().getProp().get(getMySession().getL()).get("emailcheckerror");
				getMySession().setMsg(msg, Integer.valueOf(0));
				return;
			}
			if ("".equals(prop.get("userName"))) {
				String msg = getLang().getProp().get(getMySession().getL()).get("noempty");
				getMySession().setMsg(msg, Integer.valueOf(0));
				return;
			}
			if (isUserExists(prop.get("userName"), id)) {
				String msg = getLang().getProp().get(getMySession().getL()).get("usernamehasbeanexists");
				getMySession().setMsg(msg, Integer.valueOf(0));
				return;
			}
			if (null == mySession.getTempInt().get("Department.id") || null == mySession.getTempInt().get("Job.id")) {
				return;
			}
			Query query = getSession().getNamedQuery("core.user.updaterecordbyid");
			query.setParameter("mId", 0);
			query.setParameter("depaId", mySession.getTempInt().get("Department.id"));
			query.setParameter("jobId", mySession.getTempInt().get("Job.id"));
			query.setParameter("jobId2", mySession.getTempInt().get("Job.id2"));
			query.setParameter("userName", prop.get("userName"));
			query.setParameter("email", prop.get("email"));
			query.setParameter("phone", prop.get("phone"));
			query.setParameter("mobilePhone", prop.get("mobilePhone"));
			query.setParameter("displayName", prop.get("displayName"));
			query.setParameter("gender", prop.get("gender"));
			query.setParameter("isLock", prop.get("isLock"));
			query.setParameter("id", id);
			query.executeUpdate();
			query = null;

			String msg = getLang().getProp().get(getMySession().getL()).get("success");
			getMySession().setMsg(msg, Integer.valueOf(1));

		} catch (Exception ex) {
			String msg = getLang().getProp().get(getMySession().getL()).get("faield");
			getMySession().setMsg(msg, Integer.valueOf(2));
			ex.printStackTrace();
		}
	}

	public void updateProfile() {
		try {
			getMySession();
			if (!FunctionLib.isEmail(prop.get("email"))) {
				String msg = getLang().getProp().get(getMySession().getL()).get("emailcheckerror");
				getMySession().setMsg(msg, Integer.valueOf(0));
				return;
			}
			if (null == mySession.getTempInt().get("Department.id") || null == mySession.getTempInt().get("Job.id"))
				return;
			Query query = getSession().getNamedQuery("core.user.updateprofile");
			query.setParameter("mId", 0);
			query.setParameter("depaId", mySession.getTempInt().get("Department.id"));
			query.setParameter("jobId", mySession.getTempInt().get("Job.id"));
			query.setParameter("jobId2", mySession.getTempInt().get("Job.id2"));
			query.setParameter("email", prop.get("email"));
			query.setParameter("phone", prop.get("phone"));
			query.setParameter("mobilePhone", prop.get("mobilePhone"));
			query.setParameter("displayName", prop.get("displayName"));
			query.setParameter("gender", prop.get("gender"));
			query.setParameter("id", mySession.getUserId());
			query.executeUpdate();
			query = null;

			String msg = getLang().getProp().get(getMySession().getL()).get("success");
			getMySession().setMsg(msg, Integer.valueOf(1));
		} catch (Exception ex) {
			String msg = getLang().getProp().get(getMySession().getL()).get("faield");
			getMySession().setMsg(msg, Integer.valueOf(2));
			ex.printStackTrace();
		}
	}

	public void updatePassword() {
		try {
			String oldpassword = prop.get("password");
			String newpassword = prop.get("password2");
			String confirmpassword = prop.get("password3");
			Query query = getSession().getNamedQuery("core.user.getuserbypassword");
			query.setParameter("id", getMySession().getUserId());
			query.setParameter("password", oldpassword);
			if ("1".equals(String.valueOf(query.list().get(0)))) {
				if (!newpassword.equals("") && newpassword.equals(confirmpassword)) {
					query = getSession().getNamedQuery("core.user.updatepassword");
					query.setParameter("password", newpassword);
					query.setParameter("id", getMySession().getUserId());
					query.setParameter("mId", getMySession().getUserId());
					query.executeUpdate();
					query = null;
					String msg = getLang().getProp().get(getMySession().getL()).get("yourpasswordhasbeenupdate");
					getMySession().setMsg(msg, Integer.valueOf(1));
				} else {
					String msg = getLang().getProp().get(getMySession().getL()).get("confirmpasswordnotequalsnewpassword");
					getMySession().setMsg(msg, Integer.valueOf(2));
				}
			} else {
				String msg = getLang().getProp().get(getMySession().getL()).get("oldpasswordincorrect");
				getMySession().setMsg(msg, Integer.valueOf(2));
			}
		} catch (Exception ex) {
			String msg = getLang().getProp().get(getMySession().getL()).get("faield");
			getMySession().setMsg(msg, Integer.valueOf(2));
			ex.printStackTrace();
		}
	}

	/**
	 * 删除一条记录
	 */
	public void deleteRecordById() {
		try {
			String id = getMySession().getTempStr().get("User.id");
			Query query = getSession().getNamedQuery("core.user.deleterecordbyid");
			query.setParameter("id", id);
			query.executeUpdate();
			query = null;
			String msg = getLang().getProp().get(getMySession().getL()).get("success");
			getMySession().setMsg(msg, Integer.valueOf(1));
		} catch (Exception ex) {
			String msg = getLang().getProp().get(getMySession().getL()).get("faield");
			getMySession().setMsg(msg, Integer.valueOf(2));
			ex.printStackTrace();
		}
	}

	public void showDialog() {
		try {
			Map<?, ?> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			getMySession().getTempStr().put("User.id", (String) params.get("id"));
		} catch (Exception ex) {
			String msg = getLang().getProp().get(getMySession().getL()).get("faield");
			getMySession().setMsg(msg, Integer.valueOf(2));
			ex.printStackTrace();
		}
	}

	public void login() {
		try {
			String name = prop.get("userName");
			String pwd = prop.get("password");

			if ("".equals(name) || "".equals(pwd)) {
				String msg = getLang().getProp().get(getMySession().getL()).get("usernameorpasswordnoempty");
				getMySession().setMsg(msg, Integer.valueOf(2));
				return;
			}
			Query query = getSession().getNamedQuery("core.user.login");
			query.setParameter("userName", name);
			query.setParameter("password", pwd);
			if ("1".equals(String.valueOf(query.list().get(0)))) {
				query = getSession().getNamedQuery("core.user.getrecordbyusername");
				query.setParameter("userName", name);
				Iterator<?> it = query.list().iterator();
				while (it.hasNext()) {
					Object obj[] = (Object[]) it.next();
					if ("1".equals(FunctionLib.getString(obj[14]))) {
						String msg = getLang().getProp().get(getMySession().getL()).get("userhasbeenlocked");
						getMySession().setMsg(msg, Integer.valueOf(2));
						return;
					}
					getMySession().setUserId(FunctionLib.getInt(obj[0]));
					getMySession().setDepaName(FunctionLib.getString(obj[16]));
					getMySession().setEmail(FunctionLib.getString(obj[9]));
					getMySession().setDisplayName(FunctionLib.getString(obj[13]));
					getMySession().buildOpList(getSession());
					getMySession().buildTopMenu();
					getMySession().buildLeftMenu();
				}
				it = null;
				getMySession().setUserName(name);
				getMySession().setIsLogin("true");
				// FunctionLib.redirect(getMySession().getTemplateName(),"index.jsf");
				FacesContext context = FacesContext.getCurrentInstance();
				HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
				response.sendRedirect(getMySession().getFormUrl());
				System.out.println(name + " login at time " + FunctionLib.dtf.format(new java.util.Date()));
			} else {
				System.out.println(name + " attempt to login at time " + FunctionLib.dtf.format(new java.util.Date()));
				String msg = getLang().getProp().get(getMySession().getL()).get("usernameorpasswordincorrect");
				getMySession().setMsg(msg, Integer.valueOf(2));
			}
		} catch (Exception ex) {
			String msg = getLang().getProp().get(getMySession().getL()).get("faield");
			getMySession().setMsg(msg, Integer.valueOf(2));
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private String autoLogin;

	public String getAutoLogin() {
		try {
			String url;
			if (!"true".equals(getMySession().getIsLogin())) {
				Map<?, ?> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
				url = (String) params.get("url");
				String passwordKey = FunctionLib.getWebParameter("passwordKey");
				url = new Blowfish(passwordKey).decryptString(url);
				String[] arr = url.split(";");
				if (arr.length == 3) {
					String name = arr[0];
					String pwd = arr[1];
					String ip = FunctionLib.getIp();
					if (ip.equals(arr[2])) {
						Query query = getSession().getNamedQuery("core.user.autologin");
						query.setParameter("userName", name);
						query.setParameter("password", pwd);
						if ("1".equals(String.valueOf(query.list().get(0)))) {
							query = getSession().getNamedQuery("core.user.getrecordbyusername");
							query.setParameter("userName", name);
							Iterator<?> it = query.list().iterator();
							while (it.hasNext()) {
								Object obj[] = (Object[]) it.next();
								if ("1".equals(FunctionLib.getString(obj[14])))
									return "";
								getMySession().setUserId(FunctionLib.getInt(obj[0]));
								getMySession().setDepaName(FunctionLib.getString(obj[16]));
								getMySession().setEmail(FunctionLib.getString(obj[9]));
								getMySession().setDisplayName(FunctionLib.getString(obj[13]));
								getMySession().buildOpList(getSession());
								getMySession().buildTopMenu();
								getMySession().buildLeftMenu();
							}
							it = null;
							getMySession().setUserName(name);
							getMySession().setIsLogin("true");
							System.out.println(name + " auto login at time " + FunctionLib.dtf.format(new java.util.Date()) + ", ip is " + ip);
						} else
							System.out.println(name + " attempt to auto login at time " + FunctionLib.dtf.format(new java.util.Date()) + ", ip is " + ip);
					} else
						System.out.println(name + " auto login at time " + FunctionLib.dtf.format(new java.util.Date()) + ", ip is incorrect");
				}
			}
			url = FunctionLib.getWebAppName();
			if ("".equals(url))
				url = "http://" + FunctionLib.getRequestHeaderMap("host") + "/";
			else
				url = "http://" + FunctionLib.getRequestHeaderMap("host") + "/" + url;

			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			response.sendRedirect(url);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	public void logout() {
		try {
			getMySession().setHasOp(null);
			getMySession().setTopMenu(null);
			getMySession().setLeftMenu(null);
			FunctionLib.redirect(getMySession().getTemplateName(), "index.jsf");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("MySession");
		} catch (Exception ex) {
			String msg = getLang().getProp().get(getMySession().getL()).get("faield");
			getMySession().setMsg(msg, Integer.valueOf(2));
			ex.printStackTrace();
		}
	}

	public int getUserIdByUserName(Session s, String userName) {
		try {
			Query query = s.getNamedQuery("core.user.getid.byusername");
			query.setParameter("userName", userName);
			return FunctionLib.getInt(query.list().get(0));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	public String getUserInfoByUserName(Session s, String userName, String fieldName) {
		try {
			Query query = s.getNamedQuery("core.user.get" + fieldName + ".byusername");
			query.setParameter("userName", userName);
			return FunctionLib.getString(query.list().get(0));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}
}
