package com.kolonitsky.utils;

import java.util.Map;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class KString {
	public static boolean isNotEmpty(String str) {
		return str != null && str.length() > 0;
	}
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	public static String replaceMap(String input, Map<String, String> placeholders) {
		if (placeholders == null || placeholders.size() == 0 || input == null || input.isEmpty())
			return input;

		for (String key : placeholders.keySet()) {
			String pkey = "${" + key + "}";
			String pvalue = placeholders.get(key);
			input = input.replace(pkey, pvalue);
		}

		return input;
	}
	public static String replaceProp(String input, String propertyKey, String propertyValue) {
		return input.replace("${" + propertyKey + "}", propertyValue);
	}
}
