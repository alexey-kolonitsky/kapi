package com.kolonitsky.api.artifactory;

import com.kolonitsky.kttp.KTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class ArtifactoryApiUrl {

	public static final String SEARCH = "/${path}/api/search/artifact?name=${name}";
}
