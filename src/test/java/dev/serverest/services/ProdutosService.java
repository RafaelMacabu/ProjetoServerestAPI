package dev.serverest.services;

import dev.serverest.api.TokenManager;
import dev.serverest.api.applicationAPI.ProdutosAPI;
import dev.serverest.api.applicationAPI.UsuariosAPI;
import dev.serverest.pojo.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static dev.serverest.api.applicationAPI.ProdutosAPI.*;
import static dev.serverest.api.applicationAPI.UsuariosAPI.generateRandomUser;
import static dev.serverest.api.applicationAPI.ProdutosAPI.logRequest;
import static dev.serverest.api.applicationAPI.ProdutosAPI.logResponse;
import static dev.serverest.services.Assertions.assertEquals;
import static dev.serverest.utils.LogUtils.logInfo;

public class ProdutosService extends BaseService {
    protected static ThreadLocal<Produto> requestProduto = new ThreadLocal<>();
    protected static ThreadLocal<List<String>> idProduto = new ThreadLocal<>();
    private static ThreadLocal<Produto> responseAsClass = new ThreadLocal<>();
    private static ThreadLocal<Produtos> responseAsClassList = new ThreadLocal<>();

    public ProdutosService action() {
        return this;
    }

    public ProdutosService criarProduto() {
        requestProduto.set(generateRandomProduct());
        logRequest(requestProduto.get());
        return this;
    }

    public ProdutosService cadastrarProduto() {
        if (TokenManager.getBearerToken() == null) {
            UsuariosAPI.postBearer(generateRandomUser());
        }

        response.set(ProdutosAPI.post(requestProduto.get()));
        responseAsClass.set(response.get().as(Produto.class));

        ProdutosService.getIdProduto().add(responseAsClass.get().getId());

        logResponse(responseAsClass.get());
        return this;
    }

    public void acharUsuarios() {
        response.set(ProdutosAPI.get());
        responseAsClassList.set(response.get().as(Produtos.class));
        logResponseList(responseAsClassList.get());
    }

    public void acharUsuarioPorID() {
        response.set(ProdutosAPI.get("_id", responseAsClass.get().getId()));
        responseAsClassList.set(response.get().as(Produtos.class));
        logResponseList(responseAsClassList.get());
    }

    public void editarProduto() {
        requestProduto.set(generateRandomProduct());
        response.set(ProdutosAPI.put(requestProduto.get(), responseAsClass.get().getId()));
        responseAsClass.set(response.get().as(Produto.class));
        logRequest(requestProduto.get());
        logResponse(responseAsClass.get());
    }

    public void assertQuantidade(Integer quantidade) {
        assertEquals(responseAsClassList.get().getQuantidade(), quantidade);
        logInfo("Quantidade de produtos encontrados: " + responseAsClassList.get().getQuantidade());
    }

    public static List<String> getIdProduto() {
        if (idProduto.get() == null) {
            idProduto.set(new ArrayList<>());
        }
        return idProduto.get();
    }

    public static Produto getResponseAsClass() {
        return responseAsClass.get();
    }

    public static void setResponseAsClass(Produto response) {
        responseAsClass.set(response);
    }

    public static Produto getRequestProduto() {
        return requestProduto.get();
    }
}
