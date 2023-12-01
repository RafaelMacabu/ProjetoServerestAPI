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
import static dev.serverest.services.Assertions.assertEquals;
import static dev.serverest.utils.LogUtils.logInfo;


public class UsuarioService extends BaseService {
    public static ThreadLocal<Usuario> responseAsClass = new ThreadLocal<>();
    protected static ThreadLocal<Usuario> requestUsuario = new ThreadLocal<>();
    protected static ThreadLocal<String> idUsuario = new ThreadLocal<>();
    private static ThreadLocal<Login> requestLogin = new ThreadLocal<>();
    private static ThreadLocal<Usuarios> responseAsClassList = new ThreadLocal<>();


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
        idUsuario.set(responseAsClass.get().getId());
        logResponse(responseAsClass.get());
    }

    public void editarUsuario() {
        requestUsuario.set(generateRandomUser());
        response.set(UsuariosAPI.put(requestUsuario.get(), responseAsClass.get().getId()));
        responseAsClass.set(response.get().as(Usuario.class));
        logRequest(requestUsuario.get());
        logResponse(responseAsClass.get());
    }

    public void acharUsuarios() {
        response.set(UsuariosAPI.get());
        responseAsClassList.set(response.get().as(Usuarios.class));
        logResponseList(responseAsClassList.get());
    }

    public void acharUsuarioPorID() {
        response.set(UsuariosAPI.get("_id", responseAsClass.get().getId()));
        responseAsClassList.set(response.get().as(Usuarios.class));
        logResponseList(responseAsClassList.get());
    }

    public void realizarLogin() {
        requestLogin.set(LoginAPI.loginBuilder(requestUsuario.get().getEmail(), requestUsuario.get().getPassword()));
        response.set(LoginAPI.post(requestLogin.get()));
    }

    public void assertQuantidade(Integer quantidade) {
        assertEquals(responseAsClassList.get().getQuantidade(), quantidade);
        logInfo("Quantidade de usuarios encontrados: " + responseAsClassList.get().getQuantidade());
    }

    public static Usuario getResponseAsClass() {
        return responseAsClass.get();
    }

    public static Usuario getRequestUsuario() {
        return requestUsuario.get();
    }

    public static void setRequestUsuario(Usuario request) {
        requestUsuario.set(request);
    }

    public static String getIdUsuario() {
        return idUsuario.get();
    }

    public static void setIdUsuario(String id) {
        idUsuario.set(id);
    }
}
