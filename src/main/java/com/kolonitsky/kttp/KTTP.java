package com.kolonitsky.kttp;

import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class KTTP {

	public static String USER_AGENT = "KHTTP";
	public static String CONTENT_TYPE_JSON = "application/json";
	public static String AUTHORIZATION = "YWthbGFuaXRza2k6TmFuYVRoZUJlc3QyMQ==";

	public static boolean echo = false;

	public static KTTPRequest post(String url, String data) {
		KTTPRequest request = new KTTPRequest();
		request.url = url;
		request.method = "POST";
		request.headers = new HashMap<>();
		request.headers.put("User-Agent", USER_AGENT);
		request.headers.put("Content-Type", CONTENT_TYPE_JSON);
		request.headers.put("Authorization", "Basic " + AUTHORIZATION);
		request.headers.put("Accept", CONTENT_TYPE_JSON);
		request.headers.put("Pragma", "no-cache");
		request.connect();

		if (data != null) {
			byte[] postData = data.getBytes( StandardCharsets.UTF_8 );
			request.connection.setDoOutput( true );
			request.connection.setRequestProperty("charset", "utf-8");
			request.connection.setRequestProperty("Content-Length", Integer.toString(postData.length));
			request.connection.setUseCaches( false );
			try(DataOutputStream wr = new DataOutputStream(request.connection.getOutputStream())) {
				wr.write( postData );
			} catch (Exception ex) {
//				Konsole.error("POST ERROR");
			}
		}
		request.handleResponse();
		return request;
	}

	// HTTP GET request
	public static KTTPRequest get(String url) {
		KTTPRequest request = new KTTPRequest();
		request.url = url;
		request.method = "GET";
		request.headers = new HashMap<>();
		request.headers.put("User-Agent", USER_AGENT);
		request.headers.put("Content-Type", CONTENT_TYPE_JSON);
		request.headers.put("Authorization", "Basic " + AUTHORIZATION);
		request.headers.put("Accept", CONTENT_TYPE_JSON);
		request.headers.put("Pragma", "no-cache");
		request.connect();
		request.handleResponse();
		return request;
	}


}
