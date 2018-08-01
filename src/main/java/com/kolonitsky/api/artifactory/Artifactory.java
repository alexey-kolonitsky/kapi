package com.kolonitsky.api.artifactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by akalanitski on 15.11.2017.
 */
public class Artifactory {
	public static final String ERROR_CONNECTION_LOST = "ERROR: Artifactory unavailable. Recheck connection with artifactory servert and retry.";

	private String _host;
	private String _path;

	public Artifactory(String host, String path) {
		_host = host;
		_path = path;
	}

	public ArrayList<String> search(String name) {
		String url = _host + "/" + _path + "/" + "//api/search/artifact?name=" + name;
		try {
			JSONObject jsonResponse = loadRemoteJSON(url);
			return parseSearchResult(jsonResponse);
		} catch (IOException exception) {
			return null;
		}
	}

	private ArrayList<String> parseSearchResult(JSONObject source) {
		if (source == null) {
			return null;
		}
		JSONArray jsonResults = source.getJSONArray("results");
		if (jsonResults == null || jsonResults.length() == 0) {
			return null;
		}
		ArrayList<String> result = new ArrayList<>();
		JSONObject resultObject = jsonResults.getJSONObject(0);
		String uri = resultObject.getString("url");
		result.add(uri);
		return result;
	}

	public boolean downloadArtifactTo(String artifactId, String destinationPath) throws IOException {
		ArrayList<String> results = search(artifactId);
		if (results.size() > 0) {
			JSONObject jsonArtifactInfo = loadRemoteJSON(results.get(0));
			String downloadURI = jsonArtifactInfo.getString("downloadUri");
			try {
				URL url = new URL(downloadURI);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				unpackStreamTo(connection.getInputStream(), destinationPath);
			}
			catch (IOException exception) {
				return false;
			}
		} else {
			System.out.println("ERROR: Artifact " + artifactId + " not available in repository");
		}
		return false;
	}

	private void unpackStreamTo(InputStream inputStream, String dest) {
		try {
			BufferedInputStream buf = new BufferedInputStream(inputStream);
			buf.mark(Integer.MAX_VALUE);
			byte[] buffer = new byte[1024];
			buf.reset();
			ZipInputStream zis = new ZipInputStream(buf);
			ZipEntry zipEntry = zis.getNextEntry();
			while(zipEntry != null) {
				String fileName = zipEntry.getName();
				File newFile = new File(dest + File.separator + fileName);
//                System.out.println("Unzipping to "+newFile.getAbsolutePath());
				new File(newFile.getParent()).mkdirs();
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				//close this ZipEntry
				zis.closeEntry();
				zipEntry = zis.getNextEntry();
			}
			zis.closeEntry();
			zis.close();
			buf.close();
		} catch (IOException exception) {
			System.out.println(exception);
		}
	}

	private static JSONObject loadRemoteJSON(String urlString) throws IOException {
		URL url;
		HttpURLConnection connection;
		try {
			url = new URL(urlString);
			connection = (HttpURLConnection)url.openConnection();
		} catch (MalformedURLException mex) {
			System.out.println(mex);
			return null;
		}

		connection.setDoOutput(true);
		int responseCode = -1;
		try {
			responseCode = connection.getResponseCode();
		} catch (ConnectException connectionException) {
			System.err.println(ERROR_CONNECTION_LOST);
			return null;
		}

		switch (responseCode){
			case HttpURLConnection.HTTP_OK:
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				String responseString = response.toString();
				return new JSONObject(responseString);
			default:
				return null;
		}
	}
}
