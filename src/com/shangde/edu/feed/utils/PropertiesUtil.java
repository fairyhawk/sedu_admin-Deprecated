package com.shangde.edu.feed.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

/**
 * 属性文件读取工具
 * 
 * @author Basil
 * 
 */
public class PropertiesUtil {

	private static final String DEFAULT_ENCODING = "UTF-8";

	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

	private static PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	/**
	 * 载入多个properties文件, 相同的属性在最后载入的文件中的值将会覆盖之前的载入. 文件路径使用Spring Resource格式,
	 * 文件编码使用UTF-8.
	 * 
	 * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
	 */
	public static Properties loadProperties(String... resourcesPaths) throws IOException {
		Properties props = new Properties();

		for (String location : resourcesPaths) {

			logger.debug("Loading properties file from:" + location);

			InputStream is = null;
			try {
				Resource resource = resourceLoader.getResource(location);
				is = resource.getInputStream();
				propertiesPersister.load(props, new InputStreamReader(is, DEFAULT_ENCODING));
			} catch (IOException ex) {
				logger.info("Could not load properties from classpath:" + location + ": " + ex.getMessage());
			} finally {
				if (is != null) {
					is.close();
				}
			}
		}
		return props;
	}

	/**
	 * 根据key取值
	 * 
	 * @param key
	 *            属性键
	 * @param resourcePaths
	 *            资源文件路径
	 * @return String value
	 * @throws IOException
	 */
	public static String getEntryValue(String key, String... resourcePaths) throws IOException {
		Properties prop = loadProperties(resourcePaths);
		if (prop != null)
			return prop.getProperty(key);
		else
			return null;
	}

	/**
	 * 取 Long值
	 * 
	 * @param key
	 * @param resourcePaths
	 * @return
	 * @throws IOException
	 */
	public static long getLongEntryValue(String key, String... resourcePaths) throws IOException {
		String value = getEntryValue(key, resourcePaths);
		long longValue = 0L;
		if (value != null) {
			try {
				longValue = Long.parseLong(value.trim());
			} catch (NumberFormatException ex) {
				logger.error("getLongEntryValue(String propName,String key) NumberFormatException.", ex);
			}
		}
		return longValue;
	}

	/**
	 * 取 int 值
	 * 
	 * @param propName
	 * @param key
	 * @return
	 */
	public static int getIntEntryValue(String key, String... resourcePaths) throws IOException {
		{
			String value = getEntryValue(key, resourcePaths);
			int intValue = 0;
			if (value != null) {
				try {
					intValue = Integer.parseInt(value.trim());
				} catch (NumberFormatException ex) {
					logger.error("getIntEntryValue(String propName,String key) NumberFormatException.", ex);
				}
			}
			return intValue;
		}
	}

	public static void main(String args[]) {
		while (true) {
			ThreadUtils.sleep(1000L);
			try {
				System.out.println(getEntryValue("email.host", "email.properties"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
