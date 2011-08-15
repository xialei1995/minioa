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

public class Org {
	/**
	 * orgName 单位名称 orgDesc 单位描述
	 */

	private int ID_, CID_, MID_;
	private String CDATE, MDATE;
	private java.util.Date CDATE_, MDATE_;
	private String UUID_, init;
	public static SimpleDateFormat dtf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

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

	@NotEmpty(message = "单位名称必须填写")
	@Length(min = 2, max = 12, message = "最少2个字符，最大12个字符")
	private String orgName;
	@NotEmpty(message = "单位描述也必须填写")
	private String orgDesc;

	public void setOrgName(String data) {
		orgName = data;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgDesc(String data) {
		orgDesc = data;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	private List<Org> recordsList;

	public List<Org> getRecordsList() {
		if (recordsList == null)
			buildRecordsList();
		return recordsList;
	}

	public Lang lang;

	public Lang getLang() {
		if (lang == null)
			lang = (Lang) FacesContext.getCurrentInstance()
					.getExternalContext().getApplicationMap().get("Lang");
		return lang;
	}

	public MySession mySession;

	public MySession getMySession() {
		if (mySession == null)
			mySession = (MySession) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap().get("MySession");
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

	public Org() {
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
	public Org(int id, int cId, String cDate, int mId, String mDate,
			String uuid, String name, String desc) {
		setID_(id);
		setCID_(cId);
		setCDATE(cDate);
		setMID_(mId);
		setMDATE(mDate);
		setUUID_(uuid);
		setOrgName(name);
		setOrgDesc(desc);
	}

	/**
	 * 初始化变量
	 */
	public void mReset() {
		ID_ = CID_ = MID_ = 0;
		CDATE = MDATE = UUID_ = "";
		CDATE_ = MDATE_ = null;
		orgName = orgDesc = "";
	}

	/**
	 * 将全部记录加入列表
	 */
	public void buildRecordsList() {
		try {
			getMySession();
			recordsList = new ArrayList<Org>();
			Query query = getSession().getNamedQuery("core.org.records");
			Iterator it = query.list().iterator();
			int id, cId, mId;
			String cDate, mDate, uuid;
			java.util.Date cDate_, mDate_;
			String name, desc;
			while (it.hasNext()) {
				Object obj[] = (Object[]) it.next();
				id = cId = mId = 0;
				cDate = mDate = uuid = "";
				cDate_ = mDate_ = null;
				name = desc = "";
				// 读取obj数据时，一定要确保obj不能为null
				if (obj[0] != null)
					id = Integer.valueOf(String.valueOf(obj[0]));
				if (obj[1] != null)
					cId = Integer.valueOf(String.valueOf(obj[1]));
				if (obj[2] != null) {
					java.sql.Timestamp t = (java.sql.Timestamp) obj[2];
					cDate_ = new java.util.Date(t.getTime());
					cDate = dtf.format(cDate_);
				}
				if (obj[3] != null)
					mId = Integer.valueOf(String.valueOf(obj[3]));
				if (obj[4] != null) {
					java.sql.Timestamp t = (java.sql.Timestamp) obj[4];
					mDate_ = new java.util.Date(t.getTime());
					mDate = dtf.format(mDate_);
				}
				if (obj[5] != null)
					uuid = String.valueOf(obj[5]);
				if (obj[6] != null)
					name = String.valueOf(obj[6]);
				if (obj[7] != null)
					desc = String.valueOf(obj[7]);

				recordsList.add(new Org(id, cId, cDate, mId, mDate, uuid, name,
						desc));
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
			Map params = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			String id = (String) params.get("id");
			Query query = getSession().getNamedQuery("core.org.getrecordbyid");
			query.setParameter("id", id);
			Iterator it = query.list().iterator();
			while (it.hasNext()) {
				this.mReset();
				Object obj[] = (Object[]) it.next();
				if (obj[0] != null)
					ID_ = Integer.valueOf(String.valueOf(obj[0]));
				if (obj[1] != null)
					CID_ = Integer.valueOf(String.valueOf(obj[0]));
				if (obj[2] != null) {
					java.sql.Timestamp t = (java.sql.Timestamp) obj[2];
					CDATE_ = new java.util.Date(t.getTime());
					CDATE = dtf.format(CDATE_);
				}
				if (obj[3] != null)
					MID_ = Integer.valueOf(String.valueOf(obj[0]));
				if (obj[4] != null) {
					java.sql.Timestamp t = (java.sql.Timestamp) obj[4];
					MDATE_ = new java.util.Date(t.getTime());
					MDATE = dtf.format(MDATE_);
				}
				if (obj[5] != null)
					UUID_ = String.valueOf(obj[5]);
				if (obj[6] != null)
					orgName = String.valueOf(obj[6]);
				if (obj[7] != null)
					orgDesc = String.valueOf(obj[7]);
			}
			it = null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 新增一条记录，注意这里没有使用到insert语句，这是hibernate的特点
	 */
	public void mNewRecord() {
		try {
			Org bean = new Org();
			bean.setOrgName(orgName);
			bean.setOrgDesc(orgDesc);
			bean.setCID_(0);
			bean.setCDATE_(new java.util.Date());
			getSession().save(bean);
			bean = null;
			getMySession().setMsg(
					getLang().getProp().getProperty(
							getMySession().getLanguage() + ".success")
							.toString(), Integer.valueOf(1));
		} catch (Exception ex) {
			getMySession().setMsg(
					getLang().getProp().getProperty(
							getMySession().getLanguage() + ".faield")
							.toString(), Integer.valueOf(2));
			ex.printStackTrace();
		}
	}

	/**
	 * 更新一条记录
	 */
	public void mUpdateRecord() {
		try {
			Map params = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			String id = (String) params.get("id");
			Query query = getSession().getNamedQuery(
					"core.org.updaterecordbyid");
			query.setParameter("mId", 0);
			query.setParameter("orgName", orgName);
			query.setParameter("orgDesc", orgDesc);
			query.setParameter("id", id);
			query.executeUpdate();
			query = null;
			getMySession().setMsg(
					getLang().getProp().getProperty(
							getMySession().getLanguage() + ".success")
							.toString(), Integer.valueOf(1));
		} catch (Exception ex) {
			getMySession().setMsg(
					getLang().getProp().getProperty(
							getMySession().getLanguage() + ".faield")
							.toString(), Integer.valueOf(2));
			ex.printStackTrace();
		}
	}

	/**
	 * 删除一条记录
	 */
	public void mDeleteRecordById() {
		try {
			Map params = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			String id = (String) params.get("id");
			Query query = getSession().getNamedQuery(
					"core.org.deleterecordbyid");
			query.setParameter("id", id);
			query.executeUpdate();
			query = null;
			getMySession().setMsg(
					getLang().getProp().getProperty(
							getMySession().getLanguage() + ".success")
							.toString(), Integer.valueOf(1));
		} catch (Exception ex) {
			getMySession().setMsg(
					getLang().getProp().getProperty(
							getMySession().getLanguage() + ".faield")
							.toString(), Integer.valueOf(2));
			ex.printStackTrace();
		}
	}
}
