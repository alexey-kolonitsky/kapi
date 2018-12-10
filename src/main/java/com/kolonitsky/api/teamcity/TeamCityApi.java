package com.kolonitsky.api.teamcity;

import com.kolonitsky.api.RestApi;
import com.kolonitsky.api.teamcity.dto.TeamCityBuild;
import com.kolonitsky.api.teamcity.dto.TeamCityBuilds;
import com.kolonitsky.utils.KString;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class TeamCityApi extends RestApi {

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
