package org.minioa.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.hibernate.Query;
import org.jboss.seam.ui.HibernateEntityLoader;

public class Application {

	public List<String> userList;
	public List<SelectItem> userSelectItem;

	public List<String> getUserList() {
		if (userList == null)
			mUserList();
		return userList;
	}

	public List<SelectItem> getUserSelectItem() {
		if (userSelectItem == null)
			mUserList();
		return userSelectItem;
	}

	@SuppressWarnings("unchecked")
	public void mUserList() {
		userList = new ArrayList<String>();
		userSelectItem = new ArrayList<SelectItem>();
		try {
			Query query = new HibernateEntityLoader().getSession().getNamedQuery("core.user.getuserlist");
			Iterator it = query.list().iterator();
			while (it.hasNext()) {
				Object obj[] = (Object[]) it.next();
				userList.add(FunctionLib.getString(obj[1]));
				userSelectItem.add(new SelectItem(FunctionLib.getInt(obj[0]), FunctionLib.getString(obj[2])));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public List<String> roleList;
	public List<SelectItem> roleSelectItem;

	public List<String> getRoleList() {
		if (roleList == null)
			mRoleList();
		return roleList;
	}

	public List<SelectItem> getRoleSelectItem() {
		if (roleSelectItem == null)
			mRoleList();
		return roleSelectItem;
	}

	@SuppressWarnings("unchecked")
	public void mRoleList() {
		roleList = new ArrayList<String>();
		roleSelectItem = new ArrayList<SelectItem>();
		try {
			Query query =new HibernateEntityLoader().getSession().getNamedQuery("core.role.getrolelist");
			Iterator it = query.list().iterator();
			while (it.hasNext()) {
				Object obj[] = (Object[]) it.next();
				roleList.add(FunctionLib.getString(obj[1]));
				roleSelectItem.add(new SelectItem(FunctionLib.getInt(obj[0]), FunctionLib.getString(obj[2])));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
