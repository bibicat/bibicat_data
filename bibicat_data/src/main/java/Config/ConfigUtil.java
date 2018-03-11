package Config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {

	private static final Properties prop = new Properties();
	
	private static final String profilepath = ConfigUtil.class.getResource("/").getPath()+"config.properties";
//	private static final String profilepath = "/home/config.properties";
	static{
		System.out.println(profilepath);
		try {
			prop.load(new FileInputStream(profilepath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}; 
	}
	
	public static String getValue(String key){
		return prop.getProperty(key);
	}
}
