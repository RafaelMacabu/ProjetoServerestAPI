package dev.serverest.stepdef;

import dev.serverest.api.applicationAPI.ProdutosAPI;
import dev.serverest.api.applicationAPI.UsuariosAPI;
import dev.serverest.services.BaseService;
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

    @After(value = "@CriandoProduto",order = 2)
    public void deletarProduto(){
        if (ProdutosService.getResponseAsClass().get() != null) {
            ProdutosAPI.delete(ProdutosService.getIdProduto().get());
        }
    }

    @After(value = "@CriandoUsuario",order = 1)
    public void deletarUsuario(){
        if (UsuarioService.getResponseAsClass().get() != null) {
            UsuariosAPI.delete(UsuarioService.getIdUsuario().get());
        }
    }
}
