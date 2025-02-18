package com.literatura.challenge_litura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumeApi {

    public static String getData(String url) throws IOException, InterruptedException {

        HttpClient client =HttpClient.newHttpClient();
        HttpRequest request =HttpRequest.newBuilder()
                                        .uri(URI.create(url))
                                        .build();

        HttpResponse<String> response = null;

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
