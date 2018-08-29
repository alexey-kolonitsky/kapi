package com.kolonitsky.api.stash;

/**
 * Created by akalanitski on 22.05.2018.
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
