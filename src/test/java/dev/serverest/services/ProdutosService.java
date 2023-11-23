package dev.serverest.services;

import dev.serverest.api.applicationAPI.ProdutosAPI;
import dev.serverest.api.applicationAPI.UsuariosAPI;
import dev.serverest.pojo.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static dev.serverest.api.applicationAPI.ProdutosAPI.*;
import static dev.serverest.api.applicationAPI.UsuariosAPI.generateRandomUser;
import static dev.serverest.api.applicationAPI.ProdutosAPI.logRequest;
import static dev.serverest.api.applicationAPI.ProdutosAPI.logResponse;
import static dev.serverest.services.Assertions.assertEquals;
import static dev.serverest.utils.LogUtils.logInfo;

public class ProdutosService extends BaseService {
    @Getter
    private static ThreadLocal<Produto> responseAsClass = new ThreadLocal<>();
    private static ThreadLocal<Produtos> responseAsClassList = new ThreadLocal<>();
    @Getter
    @Setter
    protected static ThreadLocal<Produto> requestProduto = new ThreadLocal<>();
    @Getter
    @Setter
    protected static ThreadLocal<String> idProduto = new ThreadLocal<>();

    public ProdutosService action() {
        return this;
    }

    public ProdutosService criarProduto() {
        requestProduto.set(generateRandomProduct());
        logRequest(requestProduto.get());
        return this;
    }

    public void cadastrarProduto() {
        response.set(ProdutosAPI.post(requestProduto.get(), generateRandomUser()));
        responseAsClass.set(response.get().as(Produto.class));
        idProduto.set(responseAsClass.get().getId());
        logResponse(responseAsClass.get());
    }

    public void acharUsuarios(){
        response.set(ProdutosAPI.get());
        responseAsClassList.set(response.get().as(Produtos.class));
        logResponseList(responseAsClassList.get());
    }

    public void acharUsuarioPorID(){
        response.set(ProdutosAPI.get("_id",responseAsClass.get().getId()));
        responseAsClassList.set(response.get().as(Produtos.class));
        logResponseList(responseAsClassList.get());
    }

    public void editarProduto(){
        requestProduto.set(generateRandomProduct());
        response.set(ProdutosAPI.put(requestProduto.get(),responseAsClass.get().getId()));
        responseAsClass.set(response.get().as(Produto.class));
        logRequest(requestProduto.get());
        logResponse(responseAsClass.get());
    }

    public void assertQuantidade(Integer quantidade){
        assertEquals(responseAsClassList.get().getQuantidade(),quantidade);
        logInfo("Quantidade de produtos encontrados: " + responseAsClassList.get().getQuantidade());
    }
}
