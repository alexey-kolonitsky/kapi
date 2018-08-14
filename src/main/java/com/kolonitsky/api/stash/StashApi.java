package com.kolonitsky.api.stash;

import com.kolonitsky.api.atlassian.AtlassianApi;
import com.playtika.kolonitsky.KString;

/**
 * Created by akalanitski on 02.04.2018.
 */
public class StashApi extends AtlassianApi {

	public StashApi(String projectId, String repositroyId, String serverHost)  {
		super(serverHost);
		addUrlParameter("projectKey", projectId);
		addUrlParameter("projectSlug", repositroyId);
	}

	/**
	 *
	 * @param pullRequestId
	 * @return StashPullRequest object if pull-request with id pullRequestId has
	 * been found otherwise return null
	 */
	public StashPullRequest getPullRequest(String pullRequestId) {
		String url = KString.replaceProp(StashApiUrl.PULL_REQUEST, "pullRequestId", pullRequestId);
		return get(url, StashPullRequest.class);
	}

	/**
	 *
	 * @param status merged / open
	 * @throws Exception
	 */
	public StashPullRequest[] getPullRequests(String status) throws Exception {
		String url = KString.replaceProp(StashApiUrl.PULL_REQUESTS, "state", status);
		StashPullRequests result = get(url, StashPullRequests.class);
		return result.values;
	}

	public void approvePullRequest(String pullRequestId) {
		String url = KString.replaceProp(StashApiUrl.PULL_REQUEST_APPROVE, "pullRequestId", pullRequestId);
		post(url, null, null);
	}

	public StashComment addComment(String pullRequestId, String comment) {
		String requestData = "{\"text\": \"" + comment + "\"}";
		String url = KString.replaceProp(StashApiUrl.PULL_REQUEST_COMMENTS, "pullRequestId", pullRequestId);
		return post(url, requestData, StashComment.class);
	}

	public StashTask addTaskToComment(int anchorId, String text) {
		String postData = "{\"anchor\": {\"id\": " + anchorId + ",\"type\": \"COMMENT\"},\"text\": \"" + text + "\"}";
		return post(StashApiUrl.TASKS, postData, StashTask.class);
	}

	public StashTask[] getPullRequestTasks(String pullRequestId) {
		String url = KString.replaceProp(StashApiUrl.PULL_REQUEST_TASKS, "pullRequestId", pullRequestId);
		StashPullRequestTasks tasks = get(url, StashPullRequestTasks.class);
		return tasks.values;
	}

	public StashCommits getChanges(String pullRequestId) {
		String url = KString.replaceProp(StashApiUrl.PULL_REQUEST_COMMITS, "pullRequestId", pullRequestId);
		return get(url, StashCommits.class);
	}
}
