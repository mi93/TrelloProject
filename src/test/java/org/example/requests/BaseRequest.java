package org.example.requests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.example.properties.TrelloProperties;

public class BaseRequest {

    private static RequestSpecBuilder requestBuilder;

    public static RequestSpecification requestSpec() {
        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.addQueryParam("key", TrelloProperties.getKey());
        requestBuilder.addQueryParam("token", TrelloProperties.getToken());

        return requestBuilder.build();
    }

    public static RequestSpecification requestSpecWithLogs() {
        requestSpec();
        requestBuilder.addFilter(new RequestLoggingFilter());
        requestBuilder.addFilter(new ResponseLoggingFilter());

        return requestBuilder.build();
    }
}
