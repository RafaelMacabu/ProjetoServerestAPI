package dev.serverest.api.applicationAPI;

import dev.serverest.api.RestResource;
import dev.serverest.api.TokenManager;
import dev.serverest.pojo.Produto;
import dev.serverest.pojo.Produtos;
import dev.serverest.pojo.Usuario;
import dev.serverest.pojo.Usuarios;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;
import java.util.stream.Collectors;

import static dev.serverest.api.Route.PRODUTOS;
import static dev.serverest.api.TokenManager.getToken;
import static dev.serverest.utils.FakerUtils.*;
import static dev.serverest.utils.LogUtils.logInfo;

public class ProdutosAPI{

    public static Response get() {
        return RestResource.get(PRODUTOS);
    }

    public static Response get(String usuarioKey, String usuarioValue) {
        Map<String, String> paramMap = Map.of(usuarioKey, usuarioValue);

        return RestResource.get(PRODUTOS, paramMap);
    }

    public static Response post(Produto requestProduto, String bearer) {
        return RestResource.post(PRODUTOS, bearer, requestProduto);
    }

    public static Response post(Produto requestProduto) {
        return RestResource.post(PRODUTOS,TokenManager.getBearerToken(), requestProduto);
    }

    public static Response post(Produto requestProduto, Usuario requestUsuario) {
        TokenManager.setBearerToken(getToken(requestUsuario));
        return RestResource.post(PRODUTOS, TokenManager.getBearerToken(), requestProduto);
    }

    public static void delete(String productId) {
        if (TokenManager.getBearerToken() != null){
            RestResource.delete(PRODUTOS + "/" + productId, TokenManager.getBearerToken());
        }
    }

    public static Response put(Produto requestProduto, String productId) {
        return RestResource.put(PRODUTOS + "/" + productId, requestProduto,TokenManager.getBearerToken());
    }

    public static Produto produtoBuilder(String nome, double preco, String descricao, int quantidade) {
        return Produto.builder().
                nome(nome).
                preco(preco).
                descricao(descricao).
                quantidade(quantidade).
                build();
    }

    public static Produto generateRandomProduct() {
        return Produto.builder().
                nome(generateProductName()).
                preco(Double.parseDouble(generatePrice())).
                descricao("Placa de video").
                quantidade(Long.parseLong(generateQuantity())).
                build();
    }

    public static void logRequest(Produto request) {
        logInfo("========== REQUEST BODY ==========");
        logInfo("Nome: " + request.getNome());
        logInfo("Preço: " + request.getPreco());
        logInfo("Descrição: " + request.getDescricao());
        logInfo("Quantidade: " + request.getQuantidade());
        logInfo("==================================");
    }

    public static void logResponse(Produto responseAsClass) {
        logInfo("========== RESPONSE BODY ==========");
        logInfo("Mensagem: " + responseAsClass.getMessage());
        if (responseAsClass.getId() != null) {
            logInfo("Id: " + responseAsClass.getId());
        }
        logInfo("===================================");
    }

    public static void logResponseList(Produtos responseAsClass) {
        logInfo("========== RESPONSE BODY ==========");
        logInfo("Quantidade: " + responseAsClass.getQuantidade());
        logInfo("Produtos: " + responseAsClass.getProdutos());
        logInfo("===================================");
    }

    public static void assertProductNameThroughList(String produtoNome, Produtos responseProdutos) {
        try {
            Produto produto = responseProdutos.getProdutos().
                    stream().
                    filter(e -> e.getNome().equals(produtoNome)).
                    collect(Collectors.toList())
                    .get(0);
            System.out.println(produto.getNome());
            System.out.println(produto.getDescricao());
            Assert.assertEquals(produto.getNome(), produtoNome);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Produto não encontrado");
            Assert.fail();
        }
    }
}
