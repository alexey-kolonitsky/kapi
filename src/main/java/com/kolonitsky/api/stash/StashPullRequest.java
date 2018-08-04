package com.kolonitsky.api.stash;

/**
 * Created by akalanitski on 07.04.2018.
 */
public class StashPullRequest {
	public int id;
	public int version;
	public String title;
	public String description;
	public String state;
	public boolean open;
	public boolean closed;
	public long createdDate;
	public long updatedDate;
	public StashBranch fromRef;
	public StashBranch toRef;
	public boolean locked;
	public StashAuthor author;
	public StashReviewer[] reviewers;
	public StashReviewer[] participants;
	public StashProperties properties;
}
