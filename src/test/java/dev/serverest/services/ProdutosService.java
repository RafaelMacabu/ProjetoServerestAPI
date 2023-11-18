package dev.serverest.services;

import dev.serverest.api.applicationAPI.ProdutosAPI;
import dev.serverest.pojo.*;
import io.restassured.response.Response;

import static dev.serverest.api.applicationAPI.ProdutosAPI.generateRandomProduct;
import static dev.serverest.api.applicationAPI.UsuariosAPI.generateRandomUser;
import static dev.serverest.utils.LogUtils.logInfo;

public class ProdutosService extends Assertions{
    private static ThreadLocal<Produto> requestProduto = new ThreadLocal<>();
    private static ThreadLocal<Response> response = new ThreadLocal<>();
    private static ThreadLocal<Produto> responseAsClass = new ThreadLocal<>();
    private static ThreadLocal<Produtos> responseAsClassList = new ThreadLocal<>();

    public ProdutosService action(){
        return this;
    }

    public ProdutosService criarProduto(){
        requestProduto.set(generateRandomProduct());
        return this;
    }

    public void cadastrarProduto(){
        response.set(ProdutosAPI.post(requestProduto.get(),generateRandomUser()));
    }

    public void assertStatus(Integer status){
        assertEquals(response.get().getStatusCode(),status);
        logInfo("Status: " + response.get().statusCode());
    }
}
