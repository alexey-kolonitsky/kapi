package com.kolonitsky.api.stash;

import com.kolonitsky.api.RestApi;
import com.kolonitsky.api.stash.dto.*;
import com.kolonitsky.utils.KString;

import java.util.HashMap;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class StashApi extends RestApi {

	public StashApi(String projectId, String repositroyId, String serverHost)  {
		super(serverHost);
		addUrlParameter("projectKey", projectId);
		addUrlParameter("projectSlug", repositroyId);
	}

	public String getFileLink(String path) {
		String url = getURL(StashApiUrl.BROWSE_FILE);
		return KString.replaceProp(url, "filePath", path);
	}

	public String getCommitLink(String commitId) {
		String url = getURL(StashApiUrl.BROWSE_COMMIT);
		return KString.replaceProp(url, "commitId", commitId);
	}

	public String userProfileLink(String username) {
		String url = getURL(StashApiUrl.USER_PRIFILE);
		return KString.replaceProp(url, "username", username);
	}

	public String userProfilePicture(String username) {
		String url = getURL(StashApiUrl.USER_AVATAR);
		return KString.replaceProp(url, "username", username);
	}

	/**
	 * Create new pull request
	 *
	 * @param title
	 * @param description
	 * @param fromBranch
	 * @param toBranch
	 * @return
	 */
	public StashPullRequest addPullRequest(String title, String description, String fromBranch, String toBranch) {
		String url = StashApiUrl.ADD_PULL_REQUEST;
		HashMap<String, Object> o = new HashMap<>();
		String repoObject = KString.replaceMap("{'slug': '${projectSlug}', 'name': null, 'project': {'key': '${projectKey}'}}", urlParameters);
		String requestData = "{" +
			"'title': '${title}', " +
			"'description': '${description}'," +
			"'state': 'OPEN', " +
			"'open': true, " +
			"'closed': false, " +
			"'fromRef': {'id': '${fromBranch}', 'repository': " + repoObject + "," +
			"'toRef': {'id': '${toBranch}', 'repository':" + repoObject +
		"}";
		//TODO: Replace sigle qotes to double, escape title and description
		return post(url, requestData, StashPullRequest.class);
	}

	/**
	 * Retrieve pull-request by id
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
	 * Update exists pull-request title and description.
	 *
	 * @param pullRequestId
	 * @param title
	 * @param details
	 * @return
	 */
	public StashPullRequest updatePullRequest(String pullRequestId, String title, String details) {
		String url = KString.replaceProp(StashApiUrl.PULL_REQUEST, "pullRequestId", pullRequestId);
		String requestData = "{\"id\": " + pullRequestId + ", \"version\": 2, \"title\": \"" + title + "\", \"description\": \"" + details + "\"}";
		return put(url, requestData, StashPullRequest.class);
	}

	/**
	 *
	 * @param status merged / open
	 * @throws Exception
	 */
	public StashPullRequest[] getPullRequests(String status) throws Exception {
		String url = KString.replaceProp(StashApiUrl.PULL_REQUESTS, "state", status);
		StashPullRequests result = get(url, StashPullRequests.class);
		return result != null ? result.values : null;
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
		return tasks != null ? tasks.values : null;
	}

	public StashCommits getChanges(String pullRequestId) {
		String url = KString.replaceProp(StashApiUrl.PULL_REQUEST_COMMITS, "pullRequestId", pullRequestId);
		return get(url, StashCommits.class);
	}
}
