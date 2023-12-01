package dev.serverest.api;

import dev.serverest.api.applicationAPI.LoginAPI;
import dev.serverest.api.applicationAPI.UsuariosAPI;
import dev.serverest.pojo.Login;
import dev.serverest.pojo.Usuario;
import dev.serverest.services.BaseService;
import dev.serverest.services.UsuarioService;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

import static dev.serverest.api.applicationAPI.UsuariosAPI.usuariosBuilder;
import static dev.serverest.services.UsuarioService.responseAsClass;

public class TokenManager extends BaseService {
    protected static ThreadLocal<String> bearerToken = new ThreadLocal<>();


    public synchronized static String getToken(Usuario requestUsuario){
        try{
                Response response = renewToken(requestUsuario);
                bearerToken.set(response.path("authorization"));
        }catch (Exception e){
            throw new RuntimeException("Falha ao gerar Bearer Token");
        }
        return bearerToken.get();
    }

    private static Response renewToken(Usuario requestUsuario){
        Response response = UsuariosAPI.post(requestUsuario);
        responseAsClass.set(response.as(Usuario.class));
        UsuarioService.setIdUsuario(responseAsClass.get().getId());
        Login requestLogin = LoginAPI.loginBuilder(requestUsuario.getEmail(),requestUsuario.getPassword());
        Response responseLogin = LoginAPI.post(requestLogin);
        return responseLogin;
    }

    public static String getBearerToken() {
        return bearerToken.get();
    }

    public static void setBearerToken(String bearer) {
        bearerToken.set(bearer);
    }
}
