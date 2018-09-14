package com.kolonitsky.api.stash;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
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
