package dev.serverest.stepdef;

import dev.serverest.api.applicationAPI.UsuariosAPI;
import dev.serverest.services.UsuarioService;
import dev.serverest.utils.ScenarioUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    @Before(order = 0)
    public void before(Scenario scenario) {
        ScenarioUtils.add(scenario);
    }

    @After(order = 9999)
    public void after() {
        ScenarioUtils.remove();
    }

    @After("@CriandoUsuario")
    public void deletarUsuario(){
        if (UsuarioService.getResponseAsClass().get() != null) {
            UsuariosAPI.delete(UsuarioService.getResponseAsClass().get().getId());
        }
    }
}
