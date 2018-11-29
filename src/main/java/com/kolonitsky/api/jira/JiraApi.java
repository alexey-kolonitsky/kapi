package com.kolonitsky.api.jira;

import com.kolonitsky.api.atlassian.AtlassianApi;
import com.kolonitsky.api.jira.dto.JiraIssue;
import com.kolonitsky.api.jira.dto.JiraIssueType;
import com.kolonitsky.utils.KString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class JiraApi extends AtlassianApi {

	//---------------------------------
	// isSubTack
	//---------------------------------

	public static final String ISSUE_TYPE_SUBTASK = "Sub-task";

	public static boolean isSubTask(JiraIssue issue) {
		if (issue != null) {
			JiraIssueType issueType = issue.fields.issuetype;
			return issueType != null && ISSUE_TYPE_SUBTASK.equals(issueType.name);
		}
		return false;
	}

	//---------------------------------
	// ISSUE_KEY
	//---------------------------------

	public static final String ISSUE_KEY = "[a-z][a-z0-9]+-[0-9]+";
	public static final Pattern ISSUE_KEY_PATTERN = Pattern.compile(ISSUE_KEY, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

	public static Matcher matchIssueKey(String text) {
		return ISSUE_KEY_PATTERN.matcher(text);
	}

	//---------------------------------
	// Constructor
	//---------------------------------

	public JiraApi(String serverHost)  {
		super(serverHost);
	}

	/**
	 *
	 * @param issueKey
	 * @return JiraIssue
	 */
	public JiraIssue getIssue(String issueKey) {
		String url = KString.replaceProp(JiraApiUrl.ISSUE, "issueKey", issueKey);
		JiraIssue issue = get(url, JiraIssue.class);
		return issue;
	}

}
