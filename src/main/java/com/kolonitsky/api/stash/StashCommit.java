package com.kolonitsky.api.stash;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class StashCommit {
	public String id;
	public String displayId;
	public StashUser author;
	public long authorTimestamp;
	public StashUser committer;
	public long committerTimestamp;
	public String message;
	public StashBranch parent;
}
