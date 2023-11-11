package dev.serverest.stepdef;

import dev.serverest.api.applicationAPI.UsuariosAPI;
import dev.serverest.services.UsuarioService;
import io.cucumber.java.After;

public class Hooks {
    @After("@CriandoUsuario")
    public void deletarUsuario(){
        if (UsuarioService.getResponseAsClass() != null) {
            UsuariosAPI.delete(UsuarioService.getResponseAsClass().getId());
        }
    }
}
