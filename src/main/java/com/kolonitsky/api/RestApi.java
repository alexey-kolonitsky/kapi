package com.kolonitsky.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kolonitsky.api.conf.dto.ConfluenceError;
import com.kolonitsky.utils.KString;
import com.kolonitsky.kttp.KTTP;
import com.kolonitsky.kttp.KTTPRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Rest Api is a place for serialization/deserialization application object from/to
 * JSON strings. This class wrap usage of KTTP and Gson to send requests and
 * process response.
 *
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class RestApi {

	protected Gson gson;
	protected String protocol = "https://";
	protected String host = "stash.playtika.com";
	protected Map<String, String> urlParameters;

	// --------------------------------
	// Errors
	// --------------------------------

	private ArrayList<String> _errors;
	public boolean hasErrors() { return _errors != null && _errors.size() > 0; }
	public ArrayList<String> getErrors() { return _errors; }

	// --------------------------------
	// Constructor
	// --------------------------------

	public RestApi(String host) {
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
		if (request.hasError()) {
			handleError(request.errors, request.response.error);
		} else if (responseData != null) {
			result = gson.fromJson(responseData, classOfT);
		}
		return result;
	}

	public <T> T post(String url, String requestData, Class<T> classOfT) {
		KTTPRequest request = KTTP.post(getURL(url), requestData);
		String responseData = request.response.data;
		T result = null;
		if (request.hasError()) {
			handleError(request.errors, request.response.error);
		} else if (responseData != null && classOfT != null) {
			result = gson.fromJson(responseData, classOfT);
		}
		return result;
	}

	public <T> T put(String url, String data, Class<T> classOfT) {
		KTTPRequest request = KTTP.put(getURL(url), data);
		String responseData = request.response.data;
		T result = null;

		if (request.hasError()) {
			handleError(request.errors, request.response.error);
		} else if (responseData != null && classOfT != null) {
			result = gson.fromJson(responseData, classOfT);
		}
		return result;
	}

	/**
	 * Get url with protocol, host name and all registered parameters.
	 * @param url
	 * @return
	 */
	public String getURL(String url) {
		url = protocol + host + url;
		url = KString.replaceMap(url, urlParameters);
		return url;
	}

	/**
	 * Override to pars server response to correct object.
	 *
	 * @param errors
	 * @param errorData
	 */
	protected void handleError(ArrayList<String> errors, String errorData) {
		_errors = errors;
		if (errorData != null) {
			ConfluenceError confluenceError = gson.fromJson(errorData, ConfluenceError.class);
			if (confluenceError != null) {
				if (_errors == null)
					_errors = new ArrayList<>();
				_errors.add(0, confluenceError.message);
			}
		}
	}
}
