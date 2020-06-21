package com.dursun.airways.common.utils;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public final class ResourceFactory {

	private static Map<String, Properties> properties = new HashMap<String, Properties>();
	private static final Logger LOGGER = Logger.getLogger(ResourceFactory.class.getName());
	private static final String PROP_PREFIX = "META-INF/properties/";
	private static final String PROP_SUFFIX = ".properties";

	private ResourceFactory() {
	}

	private static class SingletonHolder {
		private SingletonHolder() {

		}

		private static final ResourceFactory INSTANCE = new ResourceFactory();
	}

	public static ResourceFactory getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public String getPropertyValue(Class<?> clazz, String key) {
		String classSimpleName = clazz.getSimpleName();
		if (!properties.containsKey(classSimpleName)) {
			loadProperty(clazz);
		}
		if (StringUtils.hasText(key) && !CollectionUtils.isEmpty(properties)
				&& properties.containsKey(classSimpleName)) {
			Properties prop = properties.get(classSimpleName);
			if (prop != null) {
				return prop.getProperty(key);
			}
		}
		return null;
	}

	private void loadProperty(Class<?> clazz) {
		String classSimpleName = clazz.getSimpleName();
		if (!properties.containsKey(classSimpleName)) {
			URL resource = Thread.currentThread().getContextClassLoader()
					.getResource(PROP_PREFIX.concat(classSimpleName).concat(PROP_SUFFIX));
			if (resource != null) {
				try {
					Properties prop = new Properties();
					prop.load(new InputStreamReader(resource.openStream(), "UTF-8"));
					properties.put(classSimpleName, prop);
				} catch (Exception e) {
					LOGGER.info(String.format("getProperyValue is getting exception, %s", e.getMessage()));
				}
			}
		}
	}
}
