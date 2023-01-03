package com.cap.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.json.JSONException;
import org.json.simple.parser.JSONParser;

public class Github {

	public static void main(String[] args) throws IOException, URISyntaxException, JSONException, InterruptedException {
		System.out.println("Hello Actions");
//		getRequestGitHubApi();
		postRequestGitHubApi();
	}

	private static void postRequestGitHubApi() throws MalformedURLException, IOException, InterruptedException {

		HttpClient client = HttpClient.newHttpClient();

		String url = "https://api.github.com/repos/rahulbhiwre/QuizRestApi/branches/Dev01/rename";

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
				.POST(HttpRequest.BodyPublishers.ofString("{\"new_name\":\"hello\"}"))
				.header("Authorization",
						"Basic " + Base64.getEncoder()
								.encodeToString(("rahulbhiwre:TOKEN_HERE").getBytes()))
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		System.out.println("StatusCode : " + response.statusCode());
		System.out.println(response.body());

	}

	public static void getRequestGitHubApi() throws IOException, URISyntaxException, JSONException {
		HttpURLConnection httpClient = null;
		String githubUrl = "https://api.github.com/repos/rahulbhiwre/QuizRestApi/branches";
		httpClient = (HttpURLConnection) new URL(githubUrl).openConnection();

		String userName = "rahulbhiwre";
		String password = "TOKEN_HERE";
		String auth = userName + ":" + password;
		byte[] encodedAuth = org.apache.commons.codec.binary.Base64
				.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
		String authHeader = "Basic " + new String(encodedAuth);
		httpClient.setRequestMethod("GET");
		httpClient.setRequestProperty("Authorization", authHeader);
		httpClient.setDoOutput(true);
		int responseCode = httpClient.getResponseCode();
		System.out.println("responseCode : " + responseCode);

		try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = in.readLine()) != null) {
				response.append(line);
			}
			System.out.println(response.toString());

			JSONParser parser1 = new JSONParser();
			Object json = parser1.parse(response.toString());

			System.out.println(json);

		} catch (Exception e) {
			System.out.println("in catch");
			System.out.println(e.getMessage());
		}

	}
}