package com.kolonitsky.api.conf;

import com.kolonitsky.api.RestApi;
import com.kolonitsky.api.conf.dto.ConfluencePage;
import com.kolonitsky.api.conf.dto.ConfluenceSearchResult;
import com.kolonitsky.utils.KString;

import java.io.UnsupportedEncodingException;
import java.net.*;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class ConfluenceApi extends RestApi {
	public static final String ERROR_CONNECTION_LOST = "ERROR: Artifactory unavailable. Recheck connection with artifactory servert and retry.";

	public static String getJiraMacros(String issueKey) {
		if (issueKey == null) {
			return "";
		}
		return "<ac:structured-macro ac:macro-id='45a839ef-c165-4766-b71e-2c54553849c2' ac:name='jira' ac:schema-version='1'>\n"
				+ "<ac:parameter ac:name='server'>Playtika JIRA</ac:parameter>\n"
				+ "<ac:parameter ac:name='columns'>key,summary,type,created,updated,due,assignee,reporter,priority,status,resolution</ac:parameter>\n"
				+ "<ac:parameter ac:name='serverId'>87a6f6c8-93ce-3187-ab06-f8aa69cac2b6</ac:parameter>\n"
				+ "<ac:parameter ac:name='key'>" +  issueKey + "</ac:parameter>\n"
				+ "</ac:structured-macro>";
	}

	public static String getUserNameMacros(String userName) {
		return "<ac:link><ac:plain-text-link-body><![CDATA[" + userName + "]]></ac:plain-text-link-body></ac:link>";
	}

	public ConfluenceApi(String host) {
		super(host);
	}

	private String encode(String source) {
		try {
			return URLEncoder.encode(source, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			return source;
		}
	}

	public ConfluencePage getPage(String space, String title) {
		String url = KString.replaceProp(ConfluenceApiUrl.SEARCH, "space", space);
		url = KString.replaceProp(url, "title", encode(title));
		ConfluenceSearchResult result = get(url, ConfluenceSearchResult.class);
		if (result != null && result.results != null && result.size > 0)
			return result.results[0];
		return null;
	}

	public void updatePage(ConfluencePage page, String title, String space, String content) {
		String url = KString.replaceProp(ConfluenceApiUrl.CONTENT, "id", page.id);
		String pageContent = encodePageContent(content);
		String pageVersion = String.valueOf(page.version.number +1);
		String data = "{\"id\":" + page.id + ",\"type\":\"page\", \"title\":\"" + title + "\",\"space\":{\"key\":\"" + space + "\"},\"body\":{\"storage\":{\"value\":\"" + pageContent + "\",\"representation\":\"storage\"}},\"version\":{\"number\":" + pageVersion + "}}";
		System.out.println(">>> " + data);
		put(url, data, null);
	}

	public void createPage(String space, String title, String ancestorId, String content) {
		String ancestorObject = (ancestorId == null || ancestorId.isEmpty()) ? "" : " \"ancestors\": [{\"id\": " + ancestorId + "}],";
		String pageContent = encodePageContent(content);
		String bodyObject = " \"body\": {\"storage\": {\"value\":\"" + pageContent + "\",\"representation\":\"storage\"}}";
		String data = "{\"type\":\"page\", \"title\":\"" + title + "\", \"space\":{\"key\":\"" + space + "\"}," + ancestorObject + bodyObject + "}";
		post(ConfluenceApiUrl.CREATE, data, null);
	}

	private String encodePageContent(String content) {
		String pageContent = content.replaceAll("\'", "\\'");
		pageContent = pageContent.replaceAll("\n", "");
		pageContent = pageContent.replaceAll("\t", "");
		pageContent = pageContent.replaceAll("\r", "");
		pageContent = pageContent.replaceAll("\"", "\\\"");
		pageContent = pageContent.replaceAll("\'", "\\\'");
		return pageContent;
	}

}
