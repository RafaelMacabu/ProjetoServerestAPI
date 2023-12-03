package dev.serverest.api.applicationAPI;

import dev.serverest.api.RestResource;
import dev.serverest.api.TokenManager;
import dev.serverest.pojo.*;
import dev.serverest.services.ProdutosService;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static dev.serverest.api.Route.CARRINHOS;
import static dev.serverest.utils.LogUtils.logInfo;

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

    public static void delete() {
        if (bearerToken != null){
            RestResource.delete(CARRINHOS + "/cancelar-compra", bearerToken.get());
        }
    }

    public static Carrinho carrinhoBuilder(){
        return Carrinho.builder().
                produtos(produtosCarrinhoBuilder()).
                build();
    }

    public static List<ProdutosCarrinho> produtosCarrinhoBuilder(){
        List<ProdutosCarrinho> list = new ArrayList<>();
        for (String produto: ProdutosService.getIdProduto()) {
            list.add(ProdutosCarrinho.builder().
                    idProduto(produto).
                    quantidade(1).
                    build());
        }
        return list;
    }

    public static void logResponseList(Carrinhos responseAsClass) {
        logInfo("========== RESPONSE BODY ==========");
        logInfo("Quantidade: " + responseAsClass.getQuantidade());
        logInfo("Carrinhos: " + responseAsClass.getCarrinhos().get(0));
        logInfo("===================================");
    }

    public static void logResponse(Carrinho responseAsClass) {
        logInfo("========== RESPONSE BODY ==========");
        logInfo("Mensagem: " + responseAsClass.getMessage());
        if (responseAsClass.getIdCarrinho() != null) {
            logInfo("Id do carrinho: " + responseAsClass.getIdCarrinho());
        }
        logInfo("===================================");
    }

}
