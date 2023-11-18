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
    @Quando("^eu fizer um (POST|GET|PUT) na API de produtos$")
    public void eu_fizer_um_post_na_api_de_produtos(String metodo) {
        service.action().
                cadastrarProduto();
    }

    @Entao("o status code da API de produtos sera {int}")
    public void oStatusCodeDaAPIDeProdutosSera(int status) {
        service.action().
                assertStatus(status);
    }
}
