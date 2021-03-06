package com.kolonitsky.api.stash;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class StashApiUrl {
	// pull-request
	public static String PULL_REQUEST = "/rest/api/1.0/projects/${projectKey}/repos/${projectSlug}/pull-requests/${pullRequestId}";
	public static String PULL_REQUEST_APPROVE = "/rest/api/1.0/projects/${projectKey}/repos/${projectSlug}/pull-requests/${pullRequestId}/approve";
	public static String PULL_REQUESTS = "/rest/api/1.0/projects/${projectKey}/repos/${projectSlug}/pull-requests?state=${state}&limit=128";
	public static String ADD_PULL_REQUEST = "/rest/api/1.0/projects/${projectKey}/repos/${projectSlug}/pull-requests";
	public static String PULL_REQUEST_COMMITS = "/rest/api/1.0/projects/${projectKey}/repos/${projectSlug}/pull-requests/${pullRequestId}/commits";
	public static String PULL_REQUEST_TASKS = "/rest/api/1.0/projects/${projectKey}/repos/${projectSlug}/pull-requests/${pullRequestId}/tasks";
	///
	public static String BROWSE_FILE = "/projects/${projectKey}/repos/${projectSlug}/browse/${filePath}";
	public static String BROWSE_PULL_REQUEST = "/projects/${projectKey}/repos/${projectSlug}/pull-requests/${pullRequestId}";
	public static String BROWSE_COMMIT = "/projects/${projectKey}/repos/${projectSlug}/commits/${commitId}";
	public static String USER_AVATAR = "/users/${username}/avatar.png?s=32";
	public static String USER_PRIFILE = "/users/${username}";
	// Comments
	public static String PULL_REQUEST_COMMENTS = "/rest/api/1.0/projects/${projectKey}/repos/${projectSlug}/pull-requests/${pullRequestId}/comments";
	// Tasks
	public static String TASKS = "/rest/api/1.0/tasks";

}
