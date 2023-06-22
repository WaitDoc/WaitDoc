package com.team13.WaitDoc.base.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.team13.WaitDoc.base.config.AppConfig;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ApiUt {
    @AllArgsConstructor
    public static class  ApiUrl {
        StringBuilder ub;

        public static ApiUrl builder() throws UnsupportedEncodingException{
            String serviceKey = AppConfig.getServiceKey();

            StringBuilder sb = new StringBuilder(AppConfig.getApiUrl()).append("?" + URLEncoder.encode("ServiceKey","UTF-8")
                    + "=" + URLEncoder.encode(serviceKey,"UTF-8")); //Service Key

            return new ApiUrl(sb);
        }

        public ApiUrl page(int page)throws UnsupportedEncodingException{
            ub.append("&" + URLEncoder.encode("pageNo","UTF-8")
                    + "=" + URLEncoder.encode(String.valueOf(page), "UTF-8")); //페이지 번호
            return this;
        }

        public ApiUrl rows(int rows)throws UnsupportedEncodingException{
            ub.append("&" + URLEncoder.encode("numOfRows","UTF-8")
                    + "=" + URLEncoder.encode(String.valueOf(rows), "UTF-8")); //페이지 번호
            return this;
        }

        public ApiUrl region(String region) throws UnsupportedEncodingException{
            ub.append("&" + URLEncoder.encode("Q0","UTF-8")
                    + "=" + URLEncoder.encode(region, "UTF-8"));
            return this;
        }
        public ApiUrl addr(String addr) throws UnsupportedEncodingException{
            ub.append("&" + URLEncoder.encode("Q1","UTF-8")
                    + "=" + URLEncoder.encode(addr, "UTF-8"));
            return this;
        }
        public ApiUrl name(String name) throws UnsupportedEncodingException{
            ub.append("&" + URLEncoder.encode("QN","UTF-8")
                    + "=" + URLEncoder.encode(name, "UTF-8"));
            return this;
        }
        public ApiUrl department(String department) throws UnsupportedEncodingException{
            ub.append("&" + URLEncoder.encode("QD","UTF-8")
                    + "=" + URLEncoder.encode(department, "UTF-8"));
            return this;
        }
        public ApiUrl classify(String classify) throws UnsupportedEncodingException{
            ub.append("&" + URLEncoder.encode("QZ","UTF-8")
                    + "=" + URLEncoder.encode(classify, "UTF-8"));
            return this;
        }

        public String build(){
            return this.ub.toString();
        }


    }
    public static class ApiResponse{
        public static String getResult(String s) throws IOException, InterruptedException {
            URL url = new URL(s);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url.toString()))
                    .header("Content-type", "application/xml")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            return response.body();
        }
    }
    public static class ApiXml{
        public static List<HospitalXml.Item> getItems(String xmlStr){
            ObjectMapper xmlMapper = new XmlMapper();
            HospitalXml.Response response = null;
            try {
                response = xmlMapper.readValue(xmlStr, HospitalXml.Response.class);
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return response.getBody().getItems();
        }

    }


}
