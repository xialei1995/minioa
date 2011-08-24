package org.minioa.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jboss.seam.ui.*;

public class User {

	public Lang lang;

	public Lang getLang() {
		if (lang == null)
			lang = (Lang) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("Lang");
		return lang;
	}

	public MySession mySession;

	public MySession getMySession() {
		if (mySession == null)
			mySession = (MySession) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("MySession");
		return mySession;
	}

	private Session session;

	private Session getSession() {
		if (session == null)
			session = new HibernateEntityLoader().getSession();
		if (!session.isOpen())
			session = session.getSessionFactory().openSession();
		return session;
	}

	private String init;

	public String getInit() {
		selectRecordById();
		return init;
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

			String key = "";
			if (mySession.getTempStr() != null) {
				if (mySession.getTempStr().get("User.key") != null)
					key = mySession.getTempStr().get("User.key").toString();
			}

			String sql = getSession().getNamedQuery("core.user.records.count").getQueryString();
			String where = " where 1=1";
			if (!key.equals(""))
				where += " and (ta.username like :key)";
			Query query = getSession().createSQLQuery(sql + where);
			if (!key.equals(""))
				query.setParameter("key", "%" + key + "%");

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
			Map params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
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

			String key = "";
			if (mySession.getTempStr() != null) {
				if (mySession.getTempStr().get("User.key") != null)
					key = mySession.getTempStr().get("User.key").toString();
			}

			String sql = getSession().getNamedQuery("core.user.records").getQueryString();
			String where = " where 1=1";
			String other = " order by ta.userName desc limit :limit offset :offset";

			if (!key.equals(""))
				where += " and (n.title like :key or n.content like :key)";
			Query query = getSession().createSQLQuery(sql + where + other);
			query.setParameter("limit", mySession.getPageSize());
			query.setParameter("offset", (Integer.valueOf(mySession.getScrollerPage()) - 1) * mySession.getPageSize());

			if (!key.equals(""))
				query.setParameter("key", "%" + key + "%");

			Iterator it = query.list().iterator();
			Map<String, String> p;
			while (it.hasNext()) {
				Object obj[] = (Object[]) it.next();
				p = new HashMap<String, String>();
				p.put("id", FunctionLib.getString(obj[0]));
				p.put("depaId", FunctionLib.getString(obj[6]));
				p.put("superiorId", FunctionLib.getDateString(obj[7]));
				p.put("managerId", FunctionLib.getString(obj[8]));
				p.put("userName", FunctionLib.getString(obj[9]));
				p.put("email", FunctionLib.getString(obj[10]));
				p.put("phone", FunctionLib.getString(obj[11]));
				p.put("mobilePhone", FunctionLib.getString(obj[12]));
				p.put("gender", FunctionLib.getString(obj[13]));
				p.put("displayName", FunctionLib.getString(obj[14]));
				p.put("isLock", FunctionLib.getString(obj[15]));
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
			Map params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String id = (String) params.get("id");
			if (id == null || !FunctionLib.isNum(id))
				return;
			Query query = getSession().getNamedQuery("core.user.getrecordbyid");
			query.setParameter("id", id);
			Iterator it = query.list().iterator();
			while (it.hasNext()) {
				Object obj[] = (Object[]) it.next();
				prop = new HashMap<String, String>();
				prop.put("id", FunctionLib.getString(obj[0]));
				prop.put("depaId", FunctionLib.getString(obj[6]));
				prop.put("superiorId", FunctionLib.getDateString(obj[7]));
				prop.put("managerId", FunctionLib.getString(obj[8]));
				prop.put("userName", FunctionLib.getString(obj[9]));
				prop.put("email", FunctionLib.getString(obj[10]));
				prop.put("phone", FunctionLib.getString(obj[11]));
				prop.put("mobilePhone", FunctionLib.getString(obj[12]));
				prop.put("gender", FunctionLib.getString(obj[13]));
				prop.put("displayName", FunctionLib.getString(obj[14]));
				prop.put("isLock", FunctionLib.getString(obj[15]));
			}
			it = null;
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void newRecord() {
		try {
			if(!FunctionLib.isEmail(prop.get("email"))){
				String msg = getLang().getProp().get(getMySession().getL()).get("emailcheckerror");
				getMySession().setMsg(msg, Integer.valueOf(0));
				return;
			}
			if("".equals(prop.get("userName"))){
				String msg = getLang().getProp().get(getMySession().getL()).get("noempty");
				getMySession().setMsg(msg, Integer.valueOf(0));
				return;
			}
			if(isUserExists(prop.get("userName"))){
				String msg = getLang().getProp().get(getMySession().getL()).get("usernamehasbeanexists");
				getMySession().setMsg(msg, Integer.valueOf(0));
				return;
			}
			
			Query query = getSession().getNamedQuery("core.user.newrecord");
			query.setParameter("cId",0);
			query.setParameter("depaId", prop.get("depaId"));
			query.setParameter("superiorId", prop.get("superiorId"));
			query.setParameter("managerId", prop.get("managerId"));
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
	
	public boolean isUserExists(String userName){
		try
		{	
			if(Integer.valueOf(FunctionLib.exeSql(getSession(), "core.user.isuserexistbyname", "userName", userName, "float")) == 0)
				return false;
		}catch(Exception ex){ex.printStackTrace();}
		return true;
	}
	public boolean isUserExists(String userName,String id){
		try
		{	
			if(Integer.valueOf(FunctionLib.exeSql(getSession(), "core.user.isuserexistbyname.byid", "userName", userName,"id",id, "float")) == 0)
				return false;
		}catch(Exception ex){ex.printStackTrace();}
		return true;
	}
	
	public void updateRecordById() {
		try {
			Map params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String id = (String) params.get("id");
			if(!FunctionLib.isNum(id))
				return ;

			if(!FunctionLib.isEmail(prop.get("email"))){
				String msg = getLang().getProp().get(getMySession().getL()).get("emailcheckerror");
				getMySession().setMsg(msg, Integer.valueOf(0));
				return;
			}
			if("".equals(prop.get("userName"))){
				String msg = getLang().getProp().get(getMySession().getL()).get("noempty");
				getMySession().setMsg(msg, Integer.valueOf(0));
				return;
			}
			if(isUserExists(prop.get("userName"),id)){
				String msg = getLang().getProp().get(getMySession().getL()).get("usernamehasbeanexists");
				getMySession().setMsg(msg, Integer.valueOf(0));
				return;
			}

			Query query = getSession().getNamedQuery("core.user.updaterecordbyid");
			query.setParameter("mId", 0);
			query.setParameter("depaId", prop.get("depaId"));
			query.setParameter("superiorId", prop.get("superiorId"));
			query.setParameter("managerId", prop.get("managerId"));
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
			Map params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			getMySession().getTempStr().put("User.id", (String) params.get("id"));
		} catch (Exception ex) {
			String msg = getLang().getProp().get(getMySession().getL()).get("faield");
			getMySession().setMsg(msg, Integer.valueOf(2));
			ex.printStackTrace();
		}
	}

}
