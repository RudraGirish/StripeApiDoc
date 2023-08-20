package frameworklibrary;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class Setup {
	
	
	public void setPropertyFile() throws FileNotFoundException, IOException {
		
		String basePath = System.getProperty("user.dir");
		String propFilePath = basePath + "\\TestData\\Prop.txt";
		
		Properties prop = new Properties();
		prop.load(new FileReader(propFilePath));
		
		Set<Object> keySet = prop.keySet();
		
		for(Object obj : keySet) {
			String key = obj.toString();		
			String value = prop.getProperty(key);
			System.setProperty(key, value);
		}
	}
	
}
