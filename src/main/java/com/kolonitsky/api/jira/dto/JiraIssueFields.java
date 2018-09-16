package com.kolonitsky.api.jira.dto;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class JiraIssueFields {
	public JiraIssueResolution resolution;
	public String lastViewed;
	public JiraUser assignee;
	public JiraIssueType issuetype;
	public JiraIssueStatus status;
	public JiraUser creator;
	public JiraUser reporter;
	public String summary;
}
