package com.kolonitsky.api.teamcity;

import com.kolonitsky.api.atlassian.AtlassianApi;
import com.kolonitsky.api.stash.StashApiUrl;
import com.kolonitsky.api.stash.dto.StashPullRequests;
import com.kolonitsky.api.teamcity.dto.TeamCityBuild;
import com.kolonitsky.api.teamcity.dto.TeamCityBuilds;
import com.kolonitsky.kttp.KTTP;
import com.kolonitsky.utils.KString;
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
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class TeamCityApi extends AtlassianApi {

	/**
	 *
	 * @param host https://teamcity.corp
	 */
	public TeamCityApi(String protocol, String host) {
		super(host);
		this.protocol = protocol;
	}

	public TeamCityBuild[] builds(String project, String revision) {
		String url = KString.replaceProp(TeamCityApiUrl.BUILDS, "project", project);
		url = KString.replaceProp(url, "revision", revision);
		TeamCityBuilds result = get(url, TeamCityBuilds.class);
		return result != null ? result.build : null;
	}
}
