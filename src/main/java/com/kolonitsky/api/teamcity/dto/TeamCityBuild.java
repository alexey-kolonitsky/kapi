package com.kolonitsky.api.teamcity.dto;

/**
 * Created by akalanitski on 15.11.2018.
 */
public class TeamCityBuild {
	public long id;
	public String buildTypeId;
	public String number;
	public String status;
	public String state;
	public String branch;
	public boolean defaultBranch;
	public String href;
	public String webUrl;
}
