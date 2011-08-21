package org.minioa.core;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletResponse;
import javax.faces.context.FacesContext;

public class FunctionLib {
	public static String baseDir, separator;
	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getSeparator() {
		if (separator == null) {
			String osName = System.getProperty("os.name");
			if (osName == null)
				osName = "";
			if (osName.toLowerCase().indexOf("win") != -1)
				separator = "\\";
			else
				separator = "/";
		}
		return separator;
	}

	public static String getBaseDir() {
		if (baseDir == null)
			baseDir = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("baseDir") + getSeparator();
		return baseDir;
	}

	public static boolean isNum(String str) {
		if (str == null)
			return false;
		Pattern pattern = Pattern.compile("[0-9]+");
		return pattern.matcher(str).matches();
	}

	public static boolean isFloat(String str) {
		if (str == null)
			return false;
		Pattern pattern = Pattern.compile("[0-9|.]+");
		return pattern.matcher(str).matches();
	}

	public static boolean isEmail(String str) {
		if (str == null)
			return false;
		Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
		return pattern.matcher(str).matches();
	}

	public static String getString(Object o) {
		if (o != null)
			return String.valueOf(o);
		else
			return "";
	}

	public static String getSubString8(String str) {
		if (str.length() > 8)
			return str.substring(0, 8);
		return str;
	}

	public static String getSubString15(String str) {
		if (str.length() > 15)
			return str.substring(0, 15);
		return str;
	}

	public static String getSubString24(String str) {
		if (str.length() > 24)
			return str.substring(0, 24);
		return str;
	}

	public static int getInt(Object o) {
		if (o != null)
			return Integer.valueOf(String.valueOf(o));
		else
			return 0;
	}

	public static float getFloat(Object o) {
		if (o != null)
			return Float.valueOf(String.valueOf(o));
		else
			return 0;
	}

	public static boolean getBoolean(Object o) {
		if (o != null)
			return tinyint2Boolean(String.valueOf(o));
		else
			return false;
	}

	public static java.util.Date getDate(Object o) {
		if (o != null) {
			java.sql.Timestamp t = (java.sql.Timestamp) o;
			return new java.util.Date(t.getTime());
		} else
			return null;
	}

	public static String getDateString(Object o) {
		if (o != null) {
			java.sql.Timestamp t = (java.sql.Timestamp) o;
			return FunctionLib.df.format(new java.util.Date(t.getTime()));
		} else
			return "";
	}

	public static String getDateTimeString(Object o) {
		if (o != null) {
			java.sql.Timestamp t = (java.sql.Timestamp) o;
			return FunctionLib.dtf.format(new java.util.Date(t.getTime()));
		} else
			return "";
	}

	/**
	 * 将1转化成true，其它转化成false，针对MySQL数据库的TINYINT字段类型
	 * 
	 * @param str
	 *            0 or 1
	 * @return true or false
	 */
	public static boolean tinyint2Boolean(String str) {
		if ("1".equals(str))
			return true;
		else
			return false;
	}

	public static String gb23122Unicode(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) <= 256)
				sb.append("\\u00");
			else
				sb.append("\\u");
			sb.append(Integer.toHexString(str.charAt(i)));
		}
		return sb.toString();
	}
	public static void redirect(String page)
	{
		try
		{
			FacesContext context = FacesContext.getCurrentInstance();   
	        HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse(); 
	        //System.out.println(context.getExternalContext().getRequestHeaderMap());
	        response.sendRedirect("http://" + context.getExternalContext().getRequestHeaderMap().get("host") + "/" + page);
		}
		catch(Exception ex){ex.printStackTrace();}
	}
	public static void refresh()
	{
		try
		{
			FacesContext context = FacesContext.getCurrentInstance();   
	        HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse(); 
	        response.sendRedirect(context.getExternalContext().getRequestHeaderMap().get("referer"));
		}
		catch(Exception ex){ex.printStackTrace();}
	}
}
