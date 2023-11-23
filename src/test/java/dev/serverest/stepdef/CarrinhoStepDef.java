package dev.serverest.stepdef;

import dev.serverest.services.CarrinhoService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;

public class CarrinhoStepDef {
    private CarrinhoService service = new CarrinhoService();

    @Dado("que quero adicionar um produto a um carrinho")
    public void queQueroAdicionarUmProdutoAUmCarrinho() {
        service.action().
                cadastroCompleto();
    }

    @Quando("^eu fizer um (POST|GET) na API de (carrinho|carrinho com o id)$")
    public void euFizerUmPOSTNaAPIDeCarrinho(String metodo,String carrinho) {
        service.action().
                cadastrarCarrinho();
    }
}
