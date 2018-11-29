package com.kolonitsky.api.teamcity;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class TeamCityApiUrl {
	public static String BUILDS = "/app/rest/builds?locator=buildType%3A%28id%3A${project}%29%2Crevision%3A%28version%3A${revision}%29";

}
