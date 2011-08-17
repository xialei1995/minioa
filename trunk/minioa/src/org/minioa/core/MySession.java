package org.minioa.core;

import java.util.HashMap;
import java.util.Map;

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
	public void setTempStr(Map<String, String> data){
		tempStr = data;
	}
	public Map<String, String> getTempStr(){
		if(tempStr==null)
			tempStr = new HashMap<String, String>();
		return tempStr;
	}
    private Map<String, Integer> tempInt;
	public void setTempInt(Map<String, Integer> data){
		tempInt = data;
	}
	public Map<String, Integer> getTempInt(){
		if(tempInt==null)
			tempInt = new HashMap<String, Integer>();
		return tempInt;
	}
}
