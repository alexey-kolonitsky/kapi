package com.playtika.jira;

import com.google.gson.GsonBuilder;
import com.playtika.atlassian.AtlassianApi;
import com.playtika.kolonitsky.KString;
import com.playtika.kolonitsky.kttp.KTTP;
import com.playtika.stash.StashPullRequest;

/**
 * Created by akalanitski on 29.06.2018.
 */
public class JiraApi extends AtlassianApi {

	public JiraApi(String serverHost)  {
		super(serverHost);
	}


	/**
	 *
	 * @param issueKey
	 * @return JiraIssue
	 */
	public JiraIssue getIssue(String issueKey) {
		String url = KString.replaceProp(JiraUrl.ISSUE, "issueKey", issueKey);
		JiraIssue issue = get(url, JiraIssue.class);
		return issue;
	}
}
