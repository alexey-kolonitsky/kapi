package com.kolonitsky.kttp;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class KTTPRequest {
	public String url;
	public String method;
	public Map<String, String> headers;
	public String data;
	public HttpURLConnection connection;
	public KTTPResponse response;
	public KTTPHandler handler = null;

	public ArrayList<String> errors = new ArrayList<>();

	public boolean hasError() {
		return errors != null && errors.size() > 0;
	}

	public void connect() {
		URL url = null;
		try {
			url = new URL(this.url);
		} catch (MalformedURLException ex) {
			onError(KTTPError.InvalidUrl(this.url));
			return;
		}

		try {
			connection = (HttpURLConnection) url.openConnection();
		} catch (IOException ex) {
			onError(KTTPError.Connection(this.url));
			return;
		}

		// optional default is GET
		try {
			connection.setRequestMethod(method);
		} catch (ProtocolException ex) {
			onError(KTTPError.Method(method, this.url));
			return;
		}

		if (headers != null && headers.size() > 0) {
			for (String key : headers.keySet()) {
				connection.setRequestProperty(key, headers.get(key));
			}
		}
	}

	public void handleResponse() {
		response = new KTTPResponse();
		try {
			response.code = connection.getResponseCode();
			if (response.code == 200 || response.code == 201) {
				onOutput(method + ": " + response.code + ": " + url);
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer buffer = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					buffer.append(inputLine);
				}
				in.close();
				response.data = buffer.toString();
			} else {
				onError(KTTPError.ResponseCode(response.code, url));
			}
		} catch (IOException ex) {
			onError(KTTPError.Response(url));
		}
	}

	public void unpackStreamTo(String path) {
		try {
			BufferedInputStream buf = new BufferedInputStream(connection.getInputStream());
			buf.mark(Integer.MAX_VALUE);
			byte[] buffer = new byte[1024];
			buf.reset();
			ZipInputStream zis = new ZipInputStream(buf);
			ZipEntry zipEntry = zis.getNextEntry();
			while(zipEntry != null) {
				String fileName = zipEntry.getName();
				File newFile = new File(path + File.separator + fileName);
				onOutput("Unzipping to " + newFile.getAbsolutePath());
				if (fileName.charAt(fileName.length() - 1) == '/') {
					newFile.mkdirs();
				} else {
					newFile.getParentFile().mkdirs();
					newFile.createNewFile();
					FileOutputStream fos = new FileOutputStream(newFile);
					int len;
					while ((len = zis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					fos.close();
				}
				//close this ZipEntry
				zis.closeEntry();
				zipEntry = zis.getNextEntry();
			}
			zis.closeEntry();
			zis.close();
			buf.close();
		} catch (IOException exception) {
			onError(exception.getMessage());
		}
	}

	private void onOutput(String message) {
		if (handler != null) {
			handler.kttpOutput(message);
		}
	}

	private void onError(String message) {
		errors.add(message);
		if (handler != null) {
			handler.kttpError(message);
		}
	}
}
