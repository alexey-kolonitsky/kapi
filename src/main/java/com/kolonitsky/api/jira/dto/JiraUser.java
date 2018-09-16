package com.kolonitsky.api.jira.dto;

import java.util.Map;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class JiraUser {
	public String self;
	public String name;
	public String key;
	public String emailAddress;
	public String displayName;
	public boolean active;
	public String timeZone;
	public Map<String, String> avatarUrls;
}
