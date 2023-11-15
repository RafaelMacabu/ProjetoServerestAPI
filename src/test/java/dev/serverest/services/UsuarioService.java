package dev.serverest.services;

import dev.serverest.api.applicationAPI.LoginAPI;
import dev.serverest.api.applicationAPI.UsuariosAPI;
import dev.serverest.pojo.Login;
import dev.serverest.pojo.Usuario;
import dev.serverest.pojo.Usuarios;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

import static dev.serverest.api.applicationAPI.UsuariosAPI.*;
import static dev.serverest.utils.LogUtils.logInfo;


public class UsuarioService extends Assertions {
    @Getter
    private static ThreadLocal<Login> requestLogin = new ThreadLocal<>();
    @Getter
    private static ThreadLocal<Usuario> responseAsClass = new ThreadLocal<>();
    @Getter
    private static ThreadLocal<Usuarios> responseAsClassList = new ThreadLocal<>();
    @Getter
    @Setter
    private static ThreadLocal<Usuario> requestUsuario = new ThreadLocal<>();
    @Getter
    @Setter
    private static ThreadLocal<Response> response = new ThreadLocal<>();

    public UsuarioService action() {
        return this;
    }

    public UsuarioService gerarUsuario() {
        requestUsuario.set(generateRandomUser());
        logRequest(requestUsuario.get());
        return this;
    }

    public UsuarioService gerarUsuario(String tipo) {
        requestUsuario.set(generateRandomUser(tipo.equals("admin")));
        logRequest(requestUsuario.get());
        return this;
    }

    public void cadastrarUsuario() {
        response.set(UsuariosAPI.post(requestUsuario.get()));
        responseAsClass.set(response.get().as(Usuario.class));
        logResponse(responseAsClass.get());
    }

    public void editarUsuario(){
        requestUsuario.set(generateRandomUser());
        response.set(UsuariosAPI.put(requestUsuario.get(),responseAsClass.get().getId()));
        responseAsClass.set(response.get().as(Usuario.class));
        logRequest(requestUsuario.get());
        logResponse(responseAsClass.get());
    }

    public void acharUsuarios(){
        response.set(UsuariosAPI.get());
        responseAsClassList.set(response.get().as(Usuarios.class));
        logResponseList(responseAsClassList.get());
    }

    public void acharUsuarioPorID(){
        response.set(UsuariosAPI.get("_id",responseAsClass.get().getId()));
        responseAsClassList.set(response.get().as(Usuarios.class));
        logResponseList(responseAsClassList.get());
    }

    public void realizarLogin(){
        requestLogin.set(LoginAPI.loginBuilder(requestUsuario.get().getEmail(),requestUsuario.get().getPassword()));
        response.set(LoginAPI.post(requestLogin.get()));
    }

    public void assertStatus(Integer status){
        assertEquals(response.get().getStatusCode(),status);
        logInfo("Status: " + response.get().statusCode());
    }

    public void assertQuantidade(Integer quantidade){
        assertEquals(responseAsClassList.get().getQuantidade(),quantidade);
        logInfo("Quantidade de usuarios encontrados: " + responseAsClassList.get().getQuantidade());
    }
}
