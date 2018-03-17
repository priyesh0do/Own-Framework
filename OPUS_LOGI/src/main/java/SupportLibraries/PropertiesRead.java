package SupportLibraries;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesRead {

	public void readProperty() {

		Properties prop = new Properties();
		InputStream input = null;
		try {
			System.out.println(System.getProperty("user.dir"));
			input = new FileInputStream(System.getProperty("user.dir")+"\\TestData.properties");
			
			prop.load(input);
			System.out.println(prop.getProperty("database"));
			System.out.println(prop.getProperty("dbuser"));
			System.out.println(prop.getProperty("dbpassword"));

		} catch (IOException ex) {
			ex.printStackTrace();
		} 
		finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Properties readConfigProperty() {

		Properties prop = new Properties();
		InputStream input = null;
		try {
			
			input = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
			
			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} 
		finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
}
