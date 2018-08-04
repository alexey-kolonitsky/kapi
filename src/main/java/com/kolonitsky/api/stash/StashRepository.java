package com.kolonitsky.api.stash;

/**
 * Created by akalanitski on 07.05.2018.
 */
public class StashRepository {
	public int id;
	public String slug;
	public String name;
	public String scmId;
	public String state;
	public String statusMessage;
	public boolean forkable;
	public StashProject project;
}
