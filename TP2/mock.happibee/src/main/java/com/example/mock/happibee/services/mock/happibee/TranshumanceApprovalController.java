package com.example.mock.happibee.services.mock.happibee;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class TranshumanceApprovalController {

    @Value("${destination.application.url}")
    private String destinationURL;

    @PostMapping("/transhumanceApproval/{transhumanceId}")
    public boolean handlePostRequest(@PathVariable String transhumanceId) throws IOException, InterruptedException {
        Random random = new Random();
        boolean approvalResult = random.nextBoolean();



        String URL = destinationURL + "api/receiveTranshumanceApproval";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .POST(HttpRequest.BodyPublishers.ofString(transhumanceId+":"+approvalResult))
                .build();
        client.send(request,
                HttpResponse.BodyHandlers.ofString());


        return approvalResult;
    }
}
