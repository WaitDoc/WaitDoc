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
    private static String serviceKey_1;

    @Value("${custom.API.service_key_1}")
    public void setServiceKey_1(String serviceKey_1) {
        AppConfig.serviceKey_1 = serviceKey_1;
    }

    @Getter
    private static String serviceKey_2;

    @Value("${custom.API.service_key_2}")
    public void setServiceKey_2(String serviceKey_2) {
        AppConfig.serviceKey_2 = serviceKey_2;
    }

    @Getter
    private static String serviceKey_3;

    @Value("${custom.API.service_key_3}")
    public void setServiceKey_3(String serviceKey_3) {
        AppConfig.serviceKey_3 = serviceKey_3;
    }

    @Getter
    private static int rows;

    @Value("${custom.API.rows}")
    public void setRows(int rows) {
        AppConfig.rows = rows;
    }
}


