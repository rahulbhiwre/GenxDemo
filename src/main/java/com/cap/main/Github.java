package com.cap.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONException;

public class Github {

	public static void main(String[] args) throws IOException, URISyntaxException, JSONException {
		System.out.println("Hello Actions");
		postGit();
	}

	public static void postGit() throws IOException, URISyntaxException, JSONException {
		HttpURLConnection httpClient = null;
		String githubUrl = "https://api.github.com/repos/rahulbhiwre/QuizRestApi/branches";
		httpClient = (HttpURLConnection) new URL(githubUrl).openConnection();

		String userName = "rahulbhiwre";
		String password = "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";
		String auth = userName + ":" + password;
		byte[] encodedAuth = org.apache.commons.codec.binary.Base64
				.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
		String authHeader = "Basic " + new String(encodedAuth);

		httpClient.setRequestProperty("Authorization", authHeader);
		httpClient.setDoOutput(true);
		int responseCode = httpClient.getResponseCode();
		System.out.println(responseCode);

		try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = in.readLine()) != null) {
				response.append(line);
			}
			System.out.println(response.toString());

		} catch (Exception e) {
			System.out.println("in catch");
			System.out.println(e.getMessage());
		}

	}
}