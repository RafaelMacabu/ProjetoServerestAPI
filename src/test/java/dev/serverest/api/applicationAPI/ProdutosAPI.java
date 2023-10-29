package dev.serverest.api.applicationAPI;

import dev.serverest.api.RestResource;
import dev.serverest.pojo.Produto;
import dev.serverest.pojo.Produtos;
import dev.serverest.pojo.Usuario;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.stream.Collectors;

import static dev.serverest.api.Route.PRODUTOS;
import static dev.serverest.api.TokenManager.getToken;

public class ProdutosAPI {
    public static Response get(){
        return RestResource.get(PRODUTOS);
    }

    public static Response post(Produto requestProduto,String bearer){
        return RestResource.post(PRODUTOS,bearer,requestProduto);
    }

    public static Response post(Produto requestProduto, Usuario requestUsuario){
        return RestResource.post(PRODUTOS,getToken(requestUsuario),requestProduto);
    }

    public static Produto produtoBuilder(String nome,double preco,String descricao,int quantidade){
        return Produto.builder().
                nome(nome).
                preco(preco).
                descricao(descricao).
                quantidade(quantidade).
                build();
    }

    public static void assertProductNameThroughList(String produtoNome, Produtos responseProdutos){
        try{
            Produto produto = responseProdutos.getProdutos().
                    stream().
                    filter(e -> e.getNome().equals(produtoNome)).
                    collect(Collectors.toList())
                    .get(0);
            System.out.println(produto.getNome());
            System.out.println(produto.getDescricao());
            Assert.assertEquals(produto.getNome(), produtoNome);
        }catch (IndexOutOfBoundsException exception){
            System.out.println("Produto não encontrado");
            Assert.fail();
        }
    }
}
