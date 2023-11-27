package dev.serverest.services;

import dev.serverest.api.TokenManager;
import dev.serverest.api.applicationAPI.CarrinhoAPI;
import dev.serverest.api.applicationAPI.ProdutosAPI;
import dev.serverest.api.applicationAPI.UsuariosAPI;
import dev.serverest.pojo.*;
import lombok.Getter;

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

    /*public CarrinhoService criarUsuario() {
        requestUsuario.set(generateRandomUser());
        return this;
    }*/

    public CarrinhoService criarProduto() {
        requestProduto.set(generateRandomProduct());
        return this;
    }

    /*public CarrinhoService cadastrarUsuario() {
        response.set(UsuariosAPI.post(requestUsuario.get()));
        UsuarioService.getResponseAsClass().set(response.get().as(Usuario.class));
        idUsuario.set(UsuarioService.getResponseAsClass().get().getId());
        UsuariosAPI.logResponse(UsuarioService.getResponseAsClass().get());
        TokenManager.getToken()
        return this;
    }*/

    public CarrinhoService cadastrarProduto() {
        response.set(ProdutosAPI.post(requestProduto.get(), generateRandomUser()));
        ProdutosService.getResponseAsClass().set(response.get().as(Produto.class));
        idProduto.set(ProdutosService.getResponseAsClass().get().getId());
        ProdutosAPI.logResponse(ProdutosService.getResponseAsClass().get());
        return this;
    }

    public CarrinhoService cadastroCompleto(){
        action().
                criarProduto().
                cadastrarProduto();
        return this;
    }

    public void cadastrarCarrinho(){
        response.set(CarrinhoAPI.post(CarrinhoAPI.carrinhoBuilder()));
        responseAsClass.set(response.get().as(Carrinho.class));
    }
}
