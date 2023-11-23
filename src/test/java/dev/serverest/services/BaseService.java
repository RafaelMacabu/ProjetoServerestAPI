package dev.serverest.services;

import dev.serverest.pojo.Produto;
import dev.serverest.pojo.Usuario;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

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
