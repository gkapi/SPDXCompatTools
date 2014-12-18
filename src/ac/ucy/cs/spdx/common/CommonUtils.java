package ac.ucy.cs.spdx.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author gkapi
 *
 */

public class CommonUtils {

	private static final String VIOLATION_CHECK_FILE = "violationCheckFile";
	
	public static String loadViolationCheckFileFromProperties(String propertiesFile) {
		Properties prop = new Properties();
		try {
			// load a properties file
			prop.load(new FileInputStream(propertiesFile));
			return prop.getProperty(VIOLATION_CHECK_FILE);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
