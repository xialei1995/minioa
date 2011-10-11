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

public class News {

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

	private String uuid;

	public void setUuid(String data) {
		uuid = data;
	}

	public String getUuid() {
		return uuid;
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

	private Map<String, Integer> propInt;

	public void setPropInt(Map<String, Integer> data) {
		propInt = data;
	}

	public Map<String, Integer> getPropInt() {
		if (propInt == null)
			propInt = new HashMap<String, Integer>();
		return propInt;
	}
	
	private List<News> recordsList;

	public List<News> getRecordsList() {
		if (recordsList == null)
			buildRecordsList();
		return recordsList;
	}

	public News() {
	}

	public News(int i) {
	}

	public News(Map<String, String> data) {
		setProp(data);
	}

	public List<Integer> dsList;

	public List<Integer> getDsList() {
		if (dsList == null) {
			getMySession();
			dsList = new ArrayList<Integer>();

			String key = "";
			if (mySession.getTempStr() != null) {
				if (mySession.getTempStr().get("News.key") != null)
					key = mySession.getTempStr().get("News.key").toString();
			}

			String sql = getSession().getNamedQuery("core.news.records.count").getQueryString();
			String where = " where 1=1";
			if (!key.equals(""))
				where += " and (title like :key or content like :key)";
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
			recordsList = new ArrayList<News>();
			Map<?, ?> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			if ("false".equals((String) params.get("reload"))) {
				int size = 0;
				while (size < mySession.getPageSize()) {
					recordsList.add(new News(size));
					size++;
				}
				return;
			}
			if ("true".equals((String) params.get("resetPageNo")))
				mySession.setScrollerPage(1);

			String key = "";
			if (mySession.getTempStr() != null) {
				if (mySession.getTempStr().get("News.key") != null)
					key = mySession.getTempStr().get("News.key").toString();
			}

			String sql = getSession().getNamedQuery("core.news.records").getQueryString();
			String where = " where 1=1";
			String other = " order by ta.ID_ desc limit :limit offset :offset";

			if (!key.equals(""))
				where += " and (ta.title like :key or ta.content like :key)";
			Query query = getSession().createSQLQuery(sql + where + other);
			query.setParameter("limit", mySession.getPageSize());
			query.setParameter("offset", (Integer.valueOf(mySession.getScrollerPage()) - 1) * mySession.getPageSize());

			if (!key.equals(""))
				query.setParameter("key", "%" + key + "%");

			Iterator<?> it = query.list().iterator();
			Map<String, String> p;
			while (it.hasNext()) {
				Object obj[] = (Object[]) it.next();
				p = new HashMap<String, String>();
				p.put("id", FunctionLib.getString(obj[0]));
				p.put("uuid", FunctionLib.getString(obj[5]));
				p.put("cate", FunctionLib.getString(obj[6]));
				p.put("title", FunctionLib.getString(obj[7]));
				p.put("ispicnews", FunctionLib.getString(obj[8]));
				p.put("status", FunctionLib.getString(obj[9]));
				p.put("times", FunctionLib.getString(obj[10]));
				p.put("catename", FunctionLib.getString(obj[11]));
				recordsList.add(new News(p));
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
			if (FunctionLib.isNum(id)){
				Query query = getSession().getNamedQuery("core.news.getrecordbyid");
				query.setParameter("id", id);
				Iterator<?> it = query.list().iterator();
				while (it.hasNext()) {
					Object obj[] = (Object[]) it.next();
					prop = new HashMap<String, String>();
					propInt = new HashMap<String, Integer>();
					prop.put("id", FunctionLib.getString(obj[0]));
					prop.put("uuid", FunctionLib.getString(obj[5]));
					prop.put("cate", FunctionLib.getString(obj[6]));
					prop.put("title", FunctionLib.getString(obj[7]));
					prop.put("content", FunctionLib.getString(obj[8]));
					propInt.put("ispicnews", FunctionLib.getInt(obj[9]));
					prop.put("status", FunctionLib.getString(obj[10]));
					prop.put("times", FunctionLib.getString(obj[11]));
					uuid = prop.get("uuid");
				}
				it = null;
			}
			if(null == uuid || "".equals(uuid))
				uuid = java.util.UUID.randomUUID().toString();
			if(null == propInt){
				propInt = new HashMap<String, Integer>();
				propInt.put("ispicnews",0);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void newRecord() {
		try {
			getMySession();
			Map<?, ?> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

			Query query = getSession().getNamedQuery("core.news.newrecord");
			query.setParameter("cId", mySession.getUserId());
			query.setParameter("uuid", (String) params.get("uuid"));
			query.setParameter("cate", prop.get("cate"));
			query.setParameter("title", prop.get("title"));
			query.setParameter("content", prop.get("content"));
			query.setParameter("ispicnews", propInt.get("ispicnews"));
			query.executeUpdate();

			String msg = getLang().getProp().get(getMySession().getL()).get("success");
			getMySession().setMsg(msg, Integer.valueOf(1));
		} catch (Exception ex) {
			String msg = getLang().getProp().get(getMySession().getL()).get("faield");
			getMySession().setMsg(msg, Integer.valueOf(2));
			ex.printStackTrace();
		}
	}

	public void updateRecordById() {
		try {
			getMySession();
			Map<?, ?> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String id = (String) params.get("id");
			if (FunctionLib.isNum(id)){
				Query query = getSession().getNamedQuery("core.news.updaterecordbyid");
				query.setParameter("mId", mySession.getUserId());
				query.setParameter("cate", prop.get("cate"));
				query.setParameter("title", prop.get("title"));
				query.setParameter("content", prop.get("content"));
				query.setParameter("ispicnews", propInt.get("ispicnews"));
				query.setParameter("id", id);
				query.executeUpdate();
				query = null;
				String msg = getLang().getProp().get(getMySession().getL()).get("success");
				getMySession().setMsg(msg, Integer.valueOf(1));
			}
		} catch (Exception ex) {
			String msg = getLang().getProp().get(getMySession().getL()).get("faield");
			getMySession().setMsg(msg, Integer.valueOf(2));
			ex.printStackTrace();
		}
	}

	public void updateTimesById() {
		try {
			getMySession();
			Map<?, ?> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String id = (String) params.get("id");
			if (FunctionLib.isNum(id)){
				Query query = getSession().getNamedQuery("core.news.updatetimesbyid");
				query.setParameter("times", prop.get("times"));
				query.setParameter("id", id);
				query.executeUpdate();
				query = null;

				String msg = getLang().getProp().get(getMySession().getL()).get("success");
				getMySession().setMsg(msg, Integer.valueOf(1));
			}
		} catch (Exception ex) {
			String msg = getLang().getProp().get(getMySession().getL()).get("faield");
			getMySession().setMsg(msg, Integer.valueOf(2));
			ex.printStackTrace();
		}
	}

	public void updateStatusById() {
		try {
			getMySession();
			Map<?, ?> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String id = (String) params.get("id");
			if (FunctionLib.isNum(id)){
				Query query = getSession().getNamedQuery("core.news.updatestatusbyid");
				query.setParameter("status_", prop.get("status_"));
				query.setParameter("id", id);
				query.executeUpdate();
				query = null;

				String msg = getLang().getProp().get(getMySession().getL()).get("success");
				getMySession().setMsg(msg, Integer.valueOf(1));
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
			String id = getMySession().getTempStr().get("News.id");
			Query query = getSession().getNamedQuery("core.news.deleterecordbyid");
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
			getMySession().getTempStr().put("News.id", (String) params.get("id"));
		} catch (Exception ex) {
			String msg = getLang().getProp().get(getMySession().getL()).get("faield");
			getMySession().setMsg(msg, Integer.valueOf(2));
			ex.printStackTrace();
		}
	}
}
