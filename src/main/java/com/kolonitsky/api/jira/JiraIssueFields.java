package com.kolonitsky.api.jira;

/**
 * Created by akalanitski on 29.06.2018.
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
