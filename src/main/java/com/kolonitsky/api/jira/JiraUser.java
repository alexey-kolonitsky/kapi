package com.kolonitsky.api.jira;

import java.util.Map;

/**
 * Created by akalanitski on 29.06.2018.
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
