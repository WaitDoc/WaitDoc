package com.team13.WaitDoc.base.util;

import com.team13.WaitDoc.base.config.AppConfig;
import lombok.AllArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Ut {
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

        public String build(){
            return this.ub.toString();
        }
    }


}
