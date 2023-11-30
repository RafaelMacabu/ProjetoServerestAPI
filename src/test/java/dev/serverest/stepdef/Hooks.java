package dev.serverest.stepdef;

import dev.serverest.api.TokenManager;
import dev.serverest.api.applicationAPI.CarrinhoAPI;
import dev.serverest.api.applicationAPI.ProdutosAPI;
import dev.serverest.api.applicationAPI.UsuariosAPI;
import dev.serverest.services.BaseService;
import dev.serverest.services.CarrinhoService;
import dev.serverest.services.ProdutosService;
import dev.serverest.services.UsuarioService;
import dev.serverest.utils.ScenarioUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static dev.serverest.api.applicationAPI.UsuariosAPI.generateRandomUser;

public class Hooks {
    @Before(order = 0)
    public void before(Scenario scenario) {
        ScenarioUtils.add(scenario);
    }

    @After(order = 9999)
    public void after() {
        ScenarioUtils.remove();
    }

    @After(value = "@CriandoCarrinho",order = 3)
    public void deletarCarrinho(){
        if (CarrinhoService.getResponseAsClass().get() != null) {
            CarrinhoAPI.delete();
        }
    }

    @After(value = "@CriandoProduto",order = 2)
    public void deletarProduto(){
        if (ProdutosService.getResponseAsClass().get() != null) {
            for (String produto: ProdutosService.getIdProduto().get()) {
                ProdutosAPI.delete(produto);
            }
        }
    }

    @After(value = "@CriandoUsuario",order = 1)
    public void deletarUsuario(){
        if (UsuarioService.getResponseAsClass().get() != null) {
            UsuariosAPI.delete(UsuarioService.getIdUsuario().get());
        }
    }

    @After(order = 0)
    public void setBearerTokenToNull(){
        if (TokenManager.getBearerToken().get() != null) {
            TokenManager.getBearerToken().set(null);
        }
    }
}
