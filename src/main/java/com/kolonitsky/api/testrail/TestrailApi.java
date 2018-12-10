package com.kolonitsky.api.testrail;

import com.kolonitsky.api.RestApi;
import com.kolonitsky.api.testrail.dto.TestrailCases;
import com.kolonitsky.utils.KString;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class TestrailApi extends RestApi {

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
