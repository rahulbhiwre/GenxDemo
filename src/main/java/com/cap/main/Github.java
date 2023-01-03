package com.cap.main;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Github {

	public static void main(String[] args) throws IOException, URISyntaxException, JSONException {
		System.out.println("Hello Actions");
		postGit();
	}

	public static void postGit() throws IOException, URISyntaxException, JSONException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://api.github.com/repos/{owner}/{repo}/branches");
		URI uri = new URIBuilder(httpGet.getURI()).addParameter("owner", "rahulbhiwre").addParameter("repo", "GenxDemo")
//				.addParameter("repo", "GenxDemo")
//				.addParameter("repo", "GenxDemo")
//				.addParameter("repo", "GenxDemo")
				.build();
		httpGet.setURI(uri);
		httpGet.addHeader("accept", "application/vnd.github+json");
		httpGet.addHeader("Authorization", "Bearer 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");
		CloseableHttpResponse response = client.execute(httpGet);
		client.close();

		String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response Json from GET API-->" + responseJson);

	}

}
