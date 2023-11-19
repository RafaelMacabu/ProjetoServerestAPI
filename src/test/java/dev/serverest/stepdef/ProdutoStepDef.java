package dev.serverest.stepdef;

import dev.serverest.services.ProdutosService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class ProdutoStepDef {
    private ProdutosService service = new ProdutosService();

    @Dado("que quero criar um produto")
    public void que_quero_criar_um_produto() {
        service.action()
                .criarProduto();
    }
    @Quando("^eu fizer um (POST|GET|PUT) na API de (produtos|produtos com o id)$")
    public void eu_fizer_um_post_na_api_de_produtos(String metodo,String produtos) {
        switch (metodo){
            case "POST":
                service.action().
                        cadastrarProduto();
                break;
            case "GET":
                if (produtos.contains("id")){
                    service.action().
                            acharUsuarioPorID();
                }else {
                    service.action().
                            acharUsuarios();
                }
                break;
            case "PUT":
                break;
            default:
                throw new IllegalArgumentException("Opção não parametrizada");
        }
    }

    @Dado("que tenho um produto ja criado")
    public void queTenhoUmProdutoJaCriado() {
        service.action().
                criarProduto().
                cadastrarProduto();
    }

    @Entao("a quantidade de produtos encontrados sera {int}")
    public void aQuantidadeDeProdutosEncontradosSera(int quantidade) {
        service.action().
                assertQuantidade(quantidade);
    }
}
