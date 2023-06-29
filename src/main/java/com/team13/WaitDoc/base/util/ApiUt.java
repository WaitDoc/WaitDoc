package com.team13.WaitDoc.base.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.team13.WaitDoc.category.DTO.CategoryRequestDTO;
import com.team13.WaitDoc.category.DTO.HospitalResponseDTO;
import com.team13.WaitDoc.base.config.AppConfig;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Random;

public class ApiUt {
    @AllArgsConstructor
    public static class Url {
        StringBuilder ub;

        public static String getByRequestDTO(CategoryRequestDTO requestDTO) throws UnsupportedEncodingException {
            return  Url.builder()
                    .page(requestDTO.getPage())
                    .rows(requestDTO.getRows())
                    .name(requestDTO.getName())
                    .region(requestDTO.getRegion())
                    .addr(requestDTO.getAddr())
                    .classify(requestDTO.getClassify())
                    .department(requestDTO.getDepartment())
                    .build();
        }

        public static Url builder() throws UnsupportedEncodingException{
            List<String> serviceKeys = List.of(
                    AppConfig.getServiceKey_1(),
                    AppConfig.getServiceKey_2(),
                    AppConfig.getServiceKey_3(),
                    AppConfig.getServiceKey_4()
            );
            String serviceKey = serviceKeys.get(new Random().nextInt(4));

            StringBuilder sb = new StringBuilder(AppConfig.getApiUrl()).append("?" + URLEncoder.encode("ServiceKey","UTF-8")
                    + "=" + URLEncoder.encode(serviceKey,"UTF-8")); //Service Key

            return new Url(sb);
        }

        public Url page(int page)throws UnsupportedEncodingException{
            ub.append("&" + URLEncoder.encode("pageNo","UTF-8")
                    + "=" + URLEncoder.encode(String.valueOf(page), "UTF-8")); //페이지 번호
            return this;
        }

        public Url rows(int rows)throws UnsupportedEncodingException{
            ub.append("&" + URLEncoder.encode("numOfRows","UTF-8")
                    + "=" + URLEncoder.encode(String.valueOf(rows), "UTF-8")); //페이지 번호
            return this;
        }

        public Url region(String region) throws UnsupportedEncodingException{
            if(region != null && !region.isBlank())
                ub.append("&" + URLEncoder.encode("Q0","UTF-8")
                        + "=" + URLEncoder.encode(region, "UTF-8"));
            return this;
        }
        public Url addr(String addr) throws UnsupportedEncodingException{
            if(addr != null && !addr.isBlank())
                ub.append("&" + URLEncoder.encode("Q1","UTF-8")
                        + "=" + URLEncoder.encode(addr, "UTF-8"));
            return this;
        }
        public Url name(String name) throws UnsupportedEncodingException{
            if(name != null && !name.isBlank())
                ub.append("&" + URLEncoder.encode("QN","UTF-8")
                        + "=" + URLEncoder.encode(name, "UTF-8"));
            return this;
        }
        public Url department(String department) throws UnsupportedEncodingException{
                if(department != null && !department.isBlank())
                    ub.append("&" + URLEncoder.encode("QD","UTF-8")
                        + "=" + URLEncoder.encode(department, "UTF-8"));
            return this;
        }
        public Url classify(String classify) throws UnsupportedEncodingException{
            if(classify != null && !classify.isBlank())
                ub.append("&" + URLEncoder.encode("QZ","UTF-8")
                        + "=" + URLEncoder.encode(classify, "UTF-8"));
            return this;
        }

        public String build(){
            return this.ub.toString();
        }


    }
    public static class Response {
        private static String responseToAPI(String urlStr) throws IOException, InterruptedException {
            URL url = new URL(urlStr);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url.toString()))
                    .header("Content-type", "application/xml")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            return response.body();
        }
        private static HospitalXml.Body getBody(CategoryRequestDTO requestDTO) throws IOException, InterruptedException {
            String url = Url.getByRequestDTO(requestDTO);
            ObjectMapper xmlMapper = new XmlMapper();
            HospitalXml.Response response = xmlMapper.readValue(responseToAPI(url), HospitalXml.Response.class);

            return response.getBody();

        }

        private static boolean isFail(HospitalXml.Body body) {
            return body == null;
        }

        public static List<HospitalXml.Item> getItems(CategoryRequestDTO requestDTO) throws IOException, InterruptedException {
            HospitalXml.Body body = getBody(requestDTO);

            while(isFail(body))
                body = getBody(requestDTO);

            return body.getItems();
        }
        public static List<HospitalResponseDTO> getResponseDTOs(CategoryRequestDTO requestDTO) throws IOException, InterruptedException {
            List<HospitalXml.Item> items = getItems(requestDTO);
            return new ObjectMapper().convertValue(items, new TypeReference<List<HospitalResponseDTO>>() {});
        }
    }


}
