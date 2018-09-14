package com.kolonitsky.api.testrail;

import com.kolonitsky.api.atlassian.AtlassianApi;
import com.kolonitsky.utils.KString;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class TestrailApi extends AtlassianApi {

	public TestrailApi(String host) {
		super(host);
	}

	/**
	 *
	 * @return JiraIssue
	 */
	public TestrailCases[] getCases(String projectId, String testSuite) {
		String url = KString.replaceProp(TestrailUrl.GET_CASES, "project_id", projectId);
		url = KString.replaceProp(url, "suite_id", testSuite);
		TestrailCases[] cases = get(url, TestrailCases[].class);
		return cases;
	}

}
