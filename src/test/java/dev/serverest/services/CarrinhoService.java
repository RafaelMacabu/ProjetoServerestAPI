package dev.serverest.services;

import dev.serverest.api.TokenManager;
import dev.serverest.api.applicationAPI.CarrinhoAPI;
import dev.serverest.api.applicationAPI.ProdutosAPI;
import dev.serverest.api.applicationAPI.UsuariosAPI;
import dev.serverest.pojo.*;
import lombok.Getter;

import java.util.ArrayList;

import static dev.serverest.api.applicationAPI.ProdutosAPI.generateRandomProduct;
import static dev.serverest.api.applicationAPI.UsuariosAPI.generateRandomUser;
import static dev.serverest.api.applicationAPI.UsuariosAPI.logResponse;
import static dev.serverest.services.ProdutosService.idProduto;
import static dev.serverest.services.ProdutosService.requestProduto;
import static dev.serverest.services.UsuarioService.idUsuario;
import static dev.serverest.services.UsuarioService.requestUsuario;

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

        ProdutosAPI.logResponse(ProdutosService.getResponseAsClass());
        return this;
    }

    public void cadastrarCarrinho(){
        response.set(CarrinhoAPI.post(CarrinhoAPI.carrinhoBuilder()));
        responseAsClass.set(response.get().as(Carrinho.class));
    }

    public static Carrinho getResponseAsClass() {
        return responseAsClass.get();
    }
}
