package org.minioa.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;
import org.jboss.seam.ui.*;


public class Ballot {
	/**
	 * 作者：daiqianjie 网址：www.minioa.net 创建日期：2011-11-05
	 */

	private int ID_, CID_, MID_;
	private String CDATE, MDATE;
	private java.util.Date CDATE_, MDATE_;
	private String UUID_, init;
	public static SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void setID_(int data) {
		ID_ = data;
	}

	public int getID_() {
		return ID_;
	}

	public void setCID_(int data) {
		CID_ = data;
	}

	public int getCID_() {
		return CID_;
	}

	public void setMID_(int data) {
		MID_ = data;
	}

	public int getMID_() {
		return MID_;
	}

	public void setCDATE(String data) {
		CDATE = data;
	}

	public String getCDATE() {
		return CDATE;
	}

	public void setMDATE(String data) {
		MDATE = data;
	}

	public String getMDATE() {
		return MDATE;
	}

	public void setCDATE_(java.util.Date data) {
		CDATE_ = data;
	}

	public java.util.Date getCDATE_() {
		return CDATE_;
	}

	public void setMDATE_(java.util.Date data) {
		MDATE_ = data;
	}

	public java.util.Date getMDATE_() {
		return MDATE_;
	}

	public void setUUID_(String data) {
		UUID_ = data;
	}

	public String getUUID_() {
		return UUID_;
	}

	public String getInit() {
		selectRecordById();
		return init;
	}

	@NotEmpty
	@Length(min = 2, max = 64)
	private String title;

	public void setTitle(String data) {
		title = data;
	}

	public String getTitle() {
		return title;
	}

	private int status;
	public void setStatus(Integer data) {
		status = data;
	}

	public Integer getStatus() {
		return status;
	}

	private List<Ballot> recordsList;

	public List<Ballot> getRecordsList() {
		if (recordsList == null)
			buildRecordsList();
		return recordsList;
	}

	public Lang lang;

	public Lang getLang() {
		if (lang == null)
			lang = (Lang) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("Lang");
		if(lang == null)
			FunctionLib.redirect(FunctionLib.getWebAppName());
		return lang;
	}

	public MySession mySession;

	public MySession getMySession() {
		if (mySession == null)
			mySession = (MySession) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("MySession");
		if(mySession == null)
			FunctionLib.redirect(FunctionLib.getWebAppName());
		return mySession;
	}

	private Session session;

	private Session getSession() {
		if (session == null)
			session = new HibernateEntityLoader().getSession();
		return session;
	}

	public Ballot() {
	}

	public Ballot(int i) {
		setID_(i);
	}

	public Ballot(String t, int s) {
		setTitle(t);
		setStatus(s);
	}

	/**
	 * 构造函数，用于创建recordsList
	 * 
	 * @param id
	 * @param cId
	 * @param cDate
	 * @param mId
	 * @param mDate
	 * @param uuid
	 * @param name
	 * @param desc
	 */
	public Ballot(int id, int cId, String cDate, int mId, String mDate, String uuid, String t, int s) {
		setID_(id);
		setCID_(cId);
		setCDATE(cDate);
		setMID_(mId);
		setMDATE(mDate);
		setUUID_(uuid);
		setTitle(t);
		setStatus(s);
	}

	/**
	 * 初始化变量
	 */
	public void reset() {
		ID_ = CID_ = MID_ = 0;
		CDATE = MDATE = UUID_ = "";
		CDATE_ = MDATE_ = null;
		title = "";
		status = 0;
	}

	/**
	 * 将全部记录加入列表
	 */
	public void buildRecordsList() {
		try {
			getMySession();
			recordsList = new ArrayList<Ballot>();
			Query query = getSession().getNamedQuery("core.ballot.records");
			Iterator<?> it = query.list().iterator();
			int id, cId, mId;
			String cDate, mDate, uuid;
			String t;
			int s;
			int rowcount = 0;
			while (it.hasNext()) {
				Object obj[] = (Object[]) it.next();
				id = cId = mId = 0;
				cDate = mDate = uuid = "";
				t = "";
				s = 0;
				id = FunctionLib.getInt(obj[0]);
				cId = FunctionLib.getInt(obj[1]);
				cDate = FunctionLib.getDateTimeString(obj[2]);
				mId = FunctionLib.getInt(obj[3]);
				mDate = FunctionLib.getDateTimeString(obj[4]);
				t = FunctionLib.getString(obj[6]);
				s = FunctionLib.getInt(obj[7]);
				recordsList.add(new Ballot(id, cId, cDate, mId, mDate, uuid, t, s));
				rowcount++;
			}
			it = null;
			mySession.getTempInt().put("Ballot.rowcount", rowcount);
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
			Query query = getSession().getNamedQuery("core.ballot.getrecordbyid");
			query.setParameter("id", id);
			Iterator<?> it = query.list().iterator();
			while (it.hasNext()) {
				this.reset();
				Object obj[] = (Object[]) it.next();
				ID_ = FunctionLib.getInt(obj[0]);
				CID_ = FunctionLib.getInt(obj[1]);
				CDATE = FunctionLib.getDateTimeString(obj[2]);
				MID_ = FunctionLib.getInt(obj[3]);
				MDATE = FunctionLib.getDateTimeString(obj[4]);
				title = FunctionLib.getString(obj[6]);
				status = FunctionLib.getInt(obj[7]);
			}
			it = null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 新增一条记录，注意这里没有使用到insert语句，这是hibernate的特点
	 */
	public void newRecord() {
		try {
			Ballot bean = new Ballot();
			bean.setTitle(title);
			bean.setStatus(status);
			bean.setCID_(0);
			bean.setCDATE_(new java.util.Date());
			getSession().save(bean);
			bean = null;

			String msg = getLang().getProp().get(getMySession().getL()).get("success");
			getMySession().setMsg(msg, 1);
		} catch (Exception ex) {
			String msg = getLang().getProp().get(getMySession().getL()).get("faield");
			getMySession().setMsg(msg, 2);
			ex.printStackTrace();
		}
	}

	/**
	 * 更新一条记录
	 */
	public void updateRecordById() {
		try {
			Map<?, ?> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String id = (String) params.get("id");
			Query query = getSession().getNamedQuery("core.ballot.updaterecordbyid");
			query.setParameter("mId", 0);
			query.setParameter("title", title);
			query.setParameter("status", status);
			query.setParameter("id", id);
			query.executeUpdate();
			query = null;

			String msg = getLang().getProp().get(getMySession().getL()).get("success");
			getMySession().setMsg(msg, 1);
		} catch (Exception ex) {
			String msg = getLang().getProp().get(getMySession().getL()).get("faield");
			getMySession().setMsg(msg, 2);
			ex.printStackTrace();
		}
	}

	/**
	 * 删除一条记录
	 */
	public void deleteRecordById() {
		try {
			String id = getMySession().getTempStr().get("Ballot.id");
			Query query = getSession().getNamedQuery("core.ballot.deleterecordbyid");
			query.setParameter("id", id);
			query.executeUpdate();
			query = null;
			String msg = getLang().getProp().get(getMySession().getL()).get("success");
			getMySession().setMsg(msg, 1);
		} catch (Exception ex) {
			String msg = getLang().getProp().get(getMySession().getL()).get("faield");
			getMySession().setMsg(msg, 2);
			ex.printStackTrace();
		}
	}

	public void showDialog() {
		try {
			Map<?, ?> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			getMySession().getTempStr().put("Ballot.id", (String) params.get("id"));
		} catch (Exception ex) {
			String msg = getLang().getProp().get(getMySession().getL()).get("faield");
			getMySession().setMsg(msg, 2);
			ex.printStackTrace();
		}
	}

}
