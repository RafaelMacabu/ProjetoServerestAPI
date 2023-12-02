package dev.serverest.stepdef;

import dev.serverest.services.CarrinhoService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class CarrinhoStepDef {
    private CarrinhoService service = new CarrinhoService();

    @Dado("que quero adicionar um produto a um carrinho")
    public void queQueroAdicionarUmProdutoAUmCarrinho() {
        service.action().
                cadastrarProduto();
    }

    @Quando("^eu fizer um (POST|GET) na API de (carrinho|carrinho com o id)$")
    public void euFizerUmPOSTNaAPIDeCarrinho(String metodo,String carrinho) {
        switch (metodo) {
            case "POST":
                service.action().
                        cadastrarCarrinho();
                break;
            case "GET":
                if (carrinho.contains("id")) {
                    service.action().
                            acharCarrinhoPorID();
                } else {
                    service.action().
                            acharCarrinhos();
                }
                break;
            case "PUT":
                break;
            default:
                throw new IllegalArgumentException("Opção não parametrizada");
        }
    }

    @Dado("que eu tenho um carrinho com um produto já cadastrado")
    public void queEuTenhoUmCarrinhoJáCadastrado() {
        service.action().
                cadastrarProduto().
                cadastrarCarrinho();
    }

    @Entao("a quantidade de carrinhos encontrados sera {int}")
    public void aQuantidadeDeCarrinhosEncontradosSera(int arg0) {
    }
}
