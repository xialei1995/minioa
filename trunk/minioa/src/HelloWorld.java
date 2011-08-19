import java.io.File;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.minioa.core.*;

public class HelloWorld {

	private String message;

	public void setMessage(String data) {
		message = data;
	}

	public String getMessage() {
		return message;
	}

	private int times;

	public void setTimes(int data) {
		times = data;
	}

	public int getTimes() {
		return times;
	}

	public HelloWorld() {
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

	public void setNewMessage() {
		times++;
		this.setMessage("You click me " + times + " times!");
		/*
		 * System.out.println(this.getMessage()); String dir =
		 * FunctionLib.getBaseDir(); System.out.println("dir:" + dir); File file
		 * = new File(dir); System.out.println(file.exists());
		 * System.out.println(System.getProperty("os.name"));
		 */
		Map params = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String msgType = (String) params.get("msgType");
		String msg = getLang().getProp().get(getMySession().getL()).get("success");
		getMySession().setMsg(msg, Integer.valueOf(msgType));
	}
}