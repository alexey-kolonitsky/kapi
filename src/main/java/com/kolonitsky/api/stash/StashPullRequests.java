package com.kolonitsky.api.stash;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class StashPullRequests {
	public int size;
	public int start;
	public int limit;
	public boolean isLastPage;
	public StashPullRequest[] values;
}
