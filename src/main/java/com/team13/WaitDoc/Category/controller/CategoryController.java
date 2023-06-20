package com.team13.WaitDoc.Category.controller;

import com.team13.WaitDoc.base.config.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
        String serviceKey = AppConfig.getServiceKey();
        StringBuilder urlBuilder = new StringBuilder(AppConfig.getApiUrl()); //URL

        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8")
                        + "=" + URLEncoder.encode(serviceKey,"UTF-8")); //Service Key
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8")
                        + "=" + URLEncoder.encode(String.valueOf(AppConfig.getRows()), "UTF-8")); //한 페이지 결과 수
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8")
                        + "=" + URLEncoder.encode("1", "UTF-8")); //페이지 번호
        URL url = new URL(urlBuilder.toString());

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
