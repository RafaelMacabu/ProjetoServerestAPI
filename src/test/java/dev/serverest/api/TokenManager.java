package dev.serverest.api;

import dev.serverest.api.applicationAPI.LoginAPI;
import dev.serverest.api.applicationAPI.UsuariosAPI;
import dev.serverest.pojo.Login;
import dev.serverest.pojo.Usuario;
import io.restassured.response.Response;

import static dev.serverest.api.applicationAPI.UsuariosAPI.usuariosBuilder;
import static dev.serverest.services.UsuarioService.responseAsClass;

public class TokenManager {
    static String bearer_token;
    public synchronized static String getToken(Usuario requestUsuario){
        try{
                Response response = renewToken(requestUsuario);
                bearer_token = response.path("authorization");
        }catch (Exception e){
            throw new RuntimeException("Falha ao gerar Bearer Token");
        }
        return bearer_token;
    }

    private static Response renewToken(Usuario requestUsuario){
        Response response = UsuariosAPI.post(requestUsuario);
        responseAsClass.set(response.as(Usuario.class));
        Login requestLogin = LoginAPI.loginBuilder(requestUsuario.getEmail(),requestUsuario.getPassword());
        Response responseLogin = LoginAPI.post(requestLogin);
        return responseLogin;
    }
}
