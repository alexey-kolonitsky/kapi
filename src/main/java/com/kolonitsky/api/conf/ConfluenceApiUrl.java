package com.kolonitsky.api.conf;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class ConfluenceApiUrl {

	public static final String SEARCH = "/rest/api/content/?spaceKey=${space}&title=${title}&type=page&expand=body.view,version,container";
	public static final String CONTENT = "/rest/api/content/${id}";
	public static final String CREATE = "/rest/api/content/";
}
