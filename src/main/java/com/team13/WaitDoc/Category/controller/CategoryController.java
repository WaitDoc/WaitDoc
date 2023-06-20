package com.team13.WaitDoc.Category.controller;

import com.team13.WaitDoc.base.config.AppConfig;
import com.team13.WaitDoc.base.util.Ut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hospital/category")
public class CategoryController {
    @GetMapping("/all")
    @ResponseBody
    public String showHospital() throws IOException, InterruptedException {

        String u = Ut.ApiUrl.builder()
                .page(1)
                .rows(10)
                .region("서울특별시")
                .addr("서대문구")
                .name("삼성")
                .build();
        System.out.println(u);
        URL url = new URL(u);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url.toString()))
                .header("Content-type", "application/xml")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int responseCode = response.statusCode();
        System.out.println(response.body());
        return response.body();
    }

}
