package com.team13.WaitDoc.base.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
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

public class ApiUt {
    @AllArgsConstructor
    public static class Url {
        StringBuilder ub;

        public static String getByRequestDTO(CategoryRequestDTO requestDTO) throws UnsupportedEncodingException {
            return  Url.builder()
                    .page(1)
                    .rows(40)
                    .name(requestDTO.getName())
                    .region(requestDTO.getRegion())
                    .addr(requestDTO.getAddr())
                    .classify(requestDTO.getClassify())
                    .department(requestDTO.getDepartment())
                    .build();
        }
        public static Url builder() throws UnsupportedEncodingException{
            String serviceKey = AppConfig.getServiceKey();

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
        public static String getBody(String s) throws IOException, InterruptedException {
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

        public static List<HospitalResponseDTO> getResponseDTOs(String url) throws IOException, InterruptedException {
            List<HospitalXml.Item> items = getItems(ApiUt.Response.getBody(url));

            ObjectMapper mapper = new ObjectMapper();
            return mapper.convertValue(items, new TypeReference<List<HospitalResponseDTO>>() {});
        }
    }


}
