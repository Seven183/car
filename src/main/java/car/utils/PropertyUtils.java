package car.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Slf4j
public class PropertyUtils {

	public static final Properties contextProperties = new Properties();

	public static void init(String[] env){

		String conf;
        if (env.length > 0){
			conf = "application-" + env[0] + ".properties";
			log.info("配置文件加载完毕，环境：" + env[0]);
		} else {
			conf = "application.properties";
		}

		try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(conf)){
			assert in != null;
			InputStreamReader inputStreamReader = new InputStreamReader(in, StandardCharsets.UTF_8);
			contextProperties.load(inputStreamReader);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	public static String getStrValue(String key) {
		return contextProperties.getProperty(key);
	}

	public static int getIntValue(String key) {
		String strValue = getStrValue(key);
		return Integer.parseInt(strValue);
	}
}