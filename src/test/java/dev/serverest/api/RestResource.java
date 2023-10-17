package dev.serverest.api;

import io.restassured.response.Response;

import static dev.serverest.api.SpecBuilder.getReqSpec;
import static dev.serverest.api.SpecBuilder.getRespSpec;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(String path,Object requestPlaylist){
        return given(getReqSpec()).
                body(requestPlaylist).
                when().
                post(path).
                then().spec(getRespSpec()).
                extract().response();
    }

    public static Response post(String path,String bearerToken,Object requestPlaylist){
        return given(getReqSpec()).
                body(requestPlaylist).
                header("Authorization","Bearer " + bearerToken).
                when().
                post(path).
                then().spec(getRespSpec()).
                extract().response();
    }


    public static Response get(String path,String token){
        return given(getReqSpec()).
                when().
                get(path).
                then().spec(getRespSpec()).
                extract().response();

    }

    public static Response put(String path,String token,Object requestPlaylist){
        return given(getReqSpec()).
                body(requestPlaylist).
                when().
                put(path).
                then().
                extract().response();
    }
}
