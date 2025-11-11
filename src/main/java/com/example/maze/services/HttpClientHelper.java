package com.example.maze.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpClientHelper {
    private  static  final String BASE_URL = "http://localhost:3000/api/auth";
    private static  final ObjectMapper mapper = new ObjectMapper();

    public  static String login(String email, String password) throws  Exception {
        String url = BASE_URL + "/login";
        Map<String, String> payload = new HashMap<>();
        payload.put("email", email);
        payload.put("password", password);

        String json = mapper.writeValueAsString(payload);
        HttpPost post = new HttpPost(url);
        post.setEntity(new StringEntity(json, StandardCharsets.UTF_8));
        post.setHeader("Content-Type", "application/json");

        HttpClient client = HttpClients.createDefault();
        ClassicHttpResponse response = (ClassicHttpResponse) client.execute(post);

        int statusCode = response.getCode();
        if (statusCode == 200) {
            Map result = mapper.readValue(response.getEntity().getContent(), Map.class);
            return (String) result.get("access_token");
        } else {
            throw  new RuntimeException("Login failed with status: " + statusCode);
        }

    }

    public static  boolean register(String name, String email, String password, String role) throws  Exception {
        String url = BASE_URL +  "/register";
        Map<String, String> paylaod = new HashMap<>();
        paylaod.put("name", name);
        paylaod.put("email", email);
        paylaod.put("password", password);
        paylaod.put("role", role);

        String json = mapper.writeValueAsString(paylaod);
        HttpPost post = new HttpPost(url);
        post.setEntity(new StringEntity(json, StandardCharsets.UTF_8));
        post.setHeader("Content-Type", "application/json");


        HttpClient client = HttpClients.createDefault();
        ClassicHttpResponse response = (ClassicHttpResponse) client.execute(post);

        int statusCode = response.getCode();
        return statusCode == 201 || statusCode == 200;
    }
}
