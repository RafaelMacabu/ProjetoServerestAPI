package dev.serverest.api;

import io.restassured.response.Response;

import static dev.serverest.api.SpecBuilder.getReqSpec;
import static dev.serverest.api.SpecBuilder.getRespSpec;
import static io.restassured.RestAssured.given;

public class RestResource {
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

    public static Response put(String path, Object request) {
        return given(getReqSpec()).
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
}
