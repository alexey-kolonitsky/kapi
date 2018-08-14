package com.kolonitsky.api.stash;

/**
 * Created by akalanitski on 29.06.2018.
 */
public class StashApiUrl {
	// pull-request
	public static String PULL_REQUEST = "/rest/api/1.0/projects/{projectKey}/repos/{projectSlug}/pull-requests/{pullRequestId}";
	public static String PULL_REQUEST_APPROVE = "/rest/api/1.0/projects/{projectKey}/repos/{projectSlug}/pull-requests/{pullRequestId}/approve";
	public static String PULL_REQUESTS = "/rest/api/1.0/projects/{projectKey}/repos/{projectSlug}/pull-requests?state={state}&limit=1000";
	public static String PULL_REQUEST_COMMITS = "/rest/api/1.0/projects/{projectKey}/repos/{projectSlug}/pull-requests/{pullRequestId}/commits";
	public static String PULL_REQUEST_TASKS = "/rest/api/1.0/projects/{projectKey}/repos/{projectSlug}/pull-requests/{pullRequestId}/tasks";
	// Comments
	public static String PULL_REQUEST_COMMENTS = "/rest/api/1.0/projects/{projectKey}/repos/{projectSlug}/pull-requests/{pullRequestId}/comments";
	// Tasks
	public static String TASKS = "/rest/api/1.0/tasks";

}
