package com.kolonitsky.api.jira;

import com.kolonitsky.api.atlassian.AtlassianApi;
import com.playtika.kolonitsky.KString;

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
