package org.minioa.core;

import java.io.*;
import java.util.Properties;

public class Lang {
	public static Properties prop;
	public Properties getProp() {
		if (prop == null) {
			try {
				String filename = FunctionLib.getBaseDir()
						+ "language.properties";
				File f = new File(filename);
				if (f.exists()) {
					InputStream is = new FileInputStream(filename);
					prop = new Properties();
					prop.load(is);
					is.close();
				} else
					System.out.print("The file named " + filename + " not found!");
				f = null;
			} catch (Exception ex) {
				System.out.print(ex.getMessage());
			}
		}
		return prop;
	}
	public void setProp(Properties data) {
		prop = data;
	}
}