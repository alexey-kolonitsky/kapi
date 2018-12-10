package com.kolonitsky.kttp;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Wrapper around the basic Java request/response communication.
 *
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class KTTP {

	public static String USER_AGENT = "KHTTP";
	public static String CONTENT_TYPE_JSON = "application/json";
	public static String AUTHORIZATION = "YWthbGFuaXRza2k6ZTdHMWpmclFSSQ==";

	/**
	 * Send PUT-request
	 *
	 * @param url
	 * @param data
	 * @return
	 */
	public static KTTPRequest put(String url, String data) {
		KTTPRequest request = createKTTPRequest(url, "PUT", CONTENT_TYPE_JSON, AUTHORIZATION, CONTENT_TYPE_JSON);
		request.connect();

		if (data != null) {
			byte[] putData = data.getBytes( StandardCharsets.UTF_8 );
			request.connection.setDoOutput( true );
			request.connection.setRequestProperty("charset", "utf-8");
			request.connection.setRequestProperty("Content-Length", Integer.toString(putData.length));
			request.connection.setUseCaches( false );
			try(DataOutputStream wr = new DataOutputStream(request.connection.getOutputStream())) {
				wr.write( putData );
			} catch (Exception ex) {
				request.addError(ex.getMessage());
			}
		}
		request.handleResponse();
		return request;
	}

	/**
	 * Send POST-request
	 * @param url
	 * @param data
	 * @return
	 */
	public static KTTPRequest post(String url, String data) {
		KTTPRequest request = createKTTPRequest(url, "POST", CONTENT_TYPE_JSON, AUTHORIZATION, CONTENT_TYPE_JSON);
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
				request.addError(ex.getMessage());
			}
		}
		request.handleResponse();
		return request;
	}

	/**
	 * Send GET request
	 * @param url
	 * @return
	 */
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

	public static KTTPRequest download(String url, String path, KTTPHandler handler) {
		KTTPRequest request = new KTTPRequest();
		request.url = url;
		request.method = "GET";
		request.headers = new HashMap<>();
		request.headers.put("User-Agent", USER_AGENT);
		request.headers.put("Authorization", "Basic " + AUTHORIZATION);
		request.handler = handler;
		request.connect();
		request.unpackStreamTo(path);
		return request;
	}

	/**
	 * Create KTTPRequest method with a typical headers
	 *
	 * @param url
	 * @param method
	 * @param contentType
	 * @param auth
	 * @param accept
	 * @return
	 */
	private static KTTPRequest createKTTPRequest(String url, String method, String contentType, String auth, String accept) {
		KTTPRequest request = new KTTPRequest();
		request.url = url;
		request.method = method;
		request.headers = new HashMap<>();
		request.headers.put("User-Agent", USER_AGENT);
		request.headers.put("Content-Type", contentType);
		request.headers.put("Authorization", "Basic " + auth);
		request.headers.put("Accept", accept);
		request.headers.put("Pragma", "no-cache");
		return request;
	}

}
