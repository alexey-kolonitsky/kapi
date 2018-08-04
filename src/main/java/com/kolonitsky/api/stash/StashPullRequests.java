package com.kolonitsky.api.stash;

/**
 * Created by akalanitski on 07.04.2018.
 */
public class StashPullRequests {
	public int size;
	public int start;
	public int limit;
	public boolean isLastPage;
	public StashPullRequest[] values;
}
