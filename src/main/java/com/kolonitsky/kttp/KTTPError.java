package com.kolonitsky.kttp;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class KTTPError {
	public static String INVALID_URL = "KTTP: Invalid URL: '%s'";
	public static String CONNECTION = "KTTP: Cant open connection: '%s'";
	public static String METHOD = "KTTP: Cant set HttpRequestMethod %s for URL '%s'";
	public static String RESPONSE = "KTTP: Cna't get response '%s'";
	public static String RESPONSE_CODE = "KTTP: %d '%s'";


	public static String InvalidUrl(String url) {
		return String.format(INVALID_URL, url);
	}

	public static String Connection(String url) {
		return String.format(CONNECTION, url);
	}

	public static String Method(String method, String url) {
		return String.format(METHOD, method, url);
	}

	public static String Response(String url) {
		return String.format(RESPONSE, url);
	}

	public static String ResponseCode(int code, String url) {
		return String.format(RESPONSE_CODE, code, url);
	}
}
