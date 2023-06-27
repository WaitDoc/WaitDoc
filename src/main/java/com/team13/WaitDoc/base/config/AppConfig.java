package com.team13.WaitDoc.base.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Getter
    private static String apiUrl;

    @Value("${custom.API.url}")
    public void setApiUrl(String apiUrl) {
        AppConfig.apiUrl = apiUrl;
    }



    @Getter
    private static String serviceKey;

    @Value("${custom.API.service_key}")
    public void setServiceKey(String serviceKey) {
        AppConfig.serviceKey = serviceKey;
    }

    @Getter
    private static int rows;

    @Value("${custom.API.rows}")
    public void setRows(int rows) {
        AppConfig.rows = rows;
    }
}


