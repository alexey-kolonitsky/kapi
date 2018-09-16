package com.kolonitsky.api.stash.dto;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class StashPullRequestTasks {
	public int size;
	public int limit;
	public boolean isLastPage;
	public StashTask[] values;
}
