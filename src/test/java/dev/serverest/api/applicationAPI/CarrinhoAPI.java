package dev.serverest.api.applicationAPI;

import dev.serverest.api.RestResource;
import dev.serverest.api.TokenManager;
import dev.serverest.pojo.Carrinho;
import dev.serverest.pojo.Produto;
import dev.serverest.pojo.ProdutosCarrinho;
import dev.serverest.pojo.Usuario;
import dev.serverest.services.ProdutosService;
import io.restassured.response.Response;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static dev.serverest.api.Route.CARRINHOS;

public class CarrinhoAPI extends TokenManager {

    public static Response get() {
        return RestResource.get(CARRINHOS);
    }

    public static Response get(String usuarioKey, String usuarioValue) {
        Map<String, String> paramMap = Map.of(usuarioKey, usuarioValue);

        return RestResource.get(CARRINHOS, paramMap);
    }

    public static Response post(Carrinho requestProduto) {
        return RestResource.post(CARRINHOS, bearerToken.get(), requestProduto);
    }

    public static Carrinho carrinhoBuilder(){
        return Carrinho.builder().
                produtos(produtosCarrinhoBuilder()).
                build();
    }

    public static List<ProdutosCarrinho> produtosCarrinhoBuilder(){
        List<ProdutosCarrinho> list = new ArrayList<>();
        list.add(ProdutosCarrinho.builder().
                idProduto(ProdutosService.getIdProduto().get()).
                quantidade(1).
                build());
        /*list.add(ProdutosCarrinho.builder().
                idProduto(ProdutosService.getIdProduto().get()).
                quantidade(1).
                build());*/
        return list;
    }

}
