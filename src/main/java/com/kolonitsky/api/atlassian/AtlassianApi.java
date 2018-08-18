package com.kolonitsky.api.atlassian;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.playtika.kolonitsky.KString;
import com.playtika.kolonitsky.kttp.KTTP;
import com.playtika.kolonitsky.kttp.KTTPRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by akalanitski on 29.06.2018.
 */
public class AtlassianApi {

	protected Gson gson;
	protected String protocol = "https://";
	protected String host = "https://stash.playtika.com";
	protected Map<String, String> urlParameters;

	public AtlassianApi(String host) {
		this.host = host;
		gson = new GsonBuilder().create();
		urlParameters = new HashMap<>();
	}

	public void addUrlParameter(String key, String value) {
		urlParameters.put(key, value);
	}

	public <T> T get(String url, Class<T> classOfT) {
		T result = null;
		url = protocol + host + url;
		url = KString.replaceMap(url, urlParameters);
		KTTPRequest request = KTTP.get(url);
		String responseData = request.response.data;
		if (responseData != null) {
			result = gson.fromJson(responseData, classOfT);
		}
		return result;
	}


	public <T> T post(String url, String requestData, Class<T> classOfT) {
		T result = null;
		url = protocol + host + url;
		KTTPRequest request = KTTP.post(KString.replaceMap(url, urlParameters), requestData);
		String responseData = request.response.data;
		if (responseData != null && classOfT != null) {
			result = gson.fromJson(responseData, classOfT);
		}
		return result;
	}
}
