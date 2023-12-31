package dev.serverest.api;

import dev.serverest.config.ApiConfig;
import io.restassured.response.Response;

import java.util.Map;

import static dev.serverest.api.SpecBuilder.getReqSpec;
import static dev.serverest.api.SpecBuilder.getRespSpec;
import static io.restassured.RestAssured.given;

public class RestResource extends ApiConfig {
    public static Response post(String path, Object request) {
        return given(getReqSpec()).
                body(request).
                when().
                post(path).
                then().spec(getRespSpec()).
                extract().response();
    }

    public static Response post(String path, String bearerToken, Object request) {
        return given(getReqSpec()).
                body(request).
                header("Authorization",bearerToken).
                when().
                post(path).
                then().spec(getRespSpec()).
                extract().response();
    }

    public static Response get(String path) {
        return given(getReqSpec()).
                when().
                get(path).
                then().spec(getRespSpec()).
                extract().response();

    }

    public static Response get(String path, Map<String,String> query) {
        return given(getReqSpec()).
                queryParams(query).
                when().
                get(path).
                then().spec(getRespSpec()).
                extract().response();

    }

    public static Response put(String path, Object request) {
        return given(getReqSpec()).
                body(request).
                when().
                put(path).
                then().
                extract().response();
    }

    public static Response put(String path, Object request,String bearerToken) {
        return given(getReqSpec()).
                header("Authorization",bearerToken).
                body(request).
                when().
                put(path).
                then().
                extract().response();
    }

    public static Response delete(String path) {
        return given(getReqSpec()).
                when().
                delete(path).
                then().
                extract().response();
    }

    public static Response delete(String path,String bearerToken) {
        return given(getReqSpec()).
                header("Authorization",bearerToken).
                when().
                delete(path).
                then().
                extract().response();
    }
}
