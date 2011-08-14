package org.minioa.core;

import javax.faces.context.FacesContext;

public class FunctionLib {
	public static String baseDir, separator;

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
			baseDir = FacesContext.getCurrentInstance().getExternalContext()
					.getInitParameter("baseDir")
					+ getSeparator();
		return baseDir;
	}
}
