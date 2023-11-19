package dev.serverest.services;

import io.restassured.response.Response;

import static dev.serverest.services.Assertions.assertEquals;
import static dev.serverest.utils.LogUtils.logInfo;

public class BaseService {
    protected static ThreadLocal<Response> response = new ThreadLocal<>();

    public BaseService action(){
        return this;
    }

    public void assertStatus(Integer status){
        assertEquals(response.get().getStatusCode(),status);
        logInfo("Status: " + response.get().statusCode());
    }
}
