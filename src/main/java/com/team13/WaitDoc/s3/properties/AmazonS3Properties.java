package com.team13.WaitDoc.s3.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "custom.amazon-s3")
public class AmazonS3Properties {
    private final String endPoint;
    private final String regionName;
    private final String accessKey;
    private final String secretKey;
    private final String cdnEndPoint;
    private final String bucketName;
}
