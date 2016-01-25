package com.tae.letscook.utils;

import java.util.HashMap;
import java.util.Map;

import com.tae.letscook.constants.Constants;

public class UrlParamsUtils {
	
	/**
	 * getUrlParams creates a map with the user query and the tipe of recipe that is looking for.
	 * E.g: query = rice, omelet, etc.
	 * @param query
	 * @return Map<String, String>
	 */
	public static Map<String, String> getUrlParams(String query) {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put(Constants.QUERY, query);
		return paramMap;
	}

}
