package dev.serverest.services;

import dev.serverest.api.TokenManager;
import dev.serverest.api.applicationAPI.CarrinhoAPI;
import dev.serverest.api.applicationAPI.ProdutosAPI;
import dev.serverest.api.applicationAPI.UsuariosAPI;
import dev.serverest.pojo.*;
import lombok.Getter;

import static dev.serverest.api.applicationAPI.ProdutosAPI.generateRandomProduct;
import static dev.serverest.api.applicationAPI.CarrinhoAPI.logResponseList;
import static dev.serverest.api.applicationAPI.UsuariosAPI.generateRandomUser;
import static dev.serverest.services.Assertions.assertEquals;
import static dev.serverest.services.ProdutosService.requestProduto;
import static dev.serverest.utils.LogUtils.logInfo;

public class CarrinhoService extends BaseService {
    @Getter
    private static ThreadLocal<Carrinho> responseAsClass = new ThreadLocal<>();
    private static ThreadLocal<Carrinhos> responseAsClassList = new ThreadLocal<>();


    public CarrinhoService action(){
        return this;
    }

    public CarrinhoService cadastrarProduto() {
        if (TokenManager.getBearerToken() == null){
            UsuariosAPI.postBearer(generateRandomUser());
        }

        requestProduto.set(generateRandomProduct());
        response.set(ProdutosAPI.post(requestProduto.get()));
        ProdutosService.setResponseAsClass(response.get().as(Produto.class));

        ProdutosService.getIdProduto().add(ProdutosService.getResponseAsClass().getId());

        ProdutosAPI.logRequest(requestProduto.get());
        return this;
    }

    public void cadastrarCarrinho(){
        response.set(CarrinhoAPI.post(CarrinhoAPI.carrinhoBuilder()));
        responseAsClass.set(response.get().as(Carrinho.class));

        CarrinhoAPI.logResponse(responseAsClass.get());
    }

    public void acharCarrinhos() {
        response.set(CarrinhoAPI.get());
        responseAsClassList.set(response.get().as(Carrinhos.class));
        logResponseList(responseAsClassList.get());
    }

    public void acharCarrinhoPorID() {
        response.set(CarrinhoAPI.get("_id", responseAsClass.get().getIdCarrinho()));
        responseAsClassList.set(response.get().as(Carrinhos.class));
        logResponseList(responseAsClassList.get());
    }

    public void assertQuantidade(Integer quantidade) {
        assertEquals(responseAsClassList.get().getQuantidade(), quantidade);
        logInfo("Quantidade de carrinhos encontrados: " + responseAsClassList.get().getQuantidade());
    }

    public static Carrinho getResponseAsClass() {
        return responseAsClass.get();
    }
}
