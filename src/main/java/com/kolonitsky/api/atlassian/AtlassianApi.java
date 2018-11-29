package com.kolonitsky.api.atlassian;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kolonitsky.utils.KString;
import com.kolonitsky.kttp.KTTP;
import com.kolonitsky.kttp.KTTPRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class AtlassianApi {

	protected Gson gson;
	protected String protocol = "https://";
	protected String host = "stash.playtika.com";
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
		KTTPRequest request = KTTP.get(getURL(url));
		String responseData = request.response.data;
		if (responseData != null) {
			result = gson.fromJson(responseData, classOfT);
		}
		return result;
	}

	public String getURL(String url) {
		url = protocol + host + url;
		url = KString.replaceMap(url, urlParameters);
		return url;
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
