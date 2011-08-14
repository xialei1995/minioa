package org.minioa.core;

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
	
	private String language;

	public void setLanguage(String data) {
		language = data;
	}

	public String getLanguage() {
		language = "zh-cn";
		return language;
	}
}
