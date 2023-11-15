package dev.serverest.services;

import dev.serverest.api.applicationAPI.LoginAPI;
import dev.serverest.api.applicationAPI.UsuariosAPI;
import dev.serverest.pojo.Login;
import dev.serverest.pojo.Usuario;
import dev.serverest.pojo.Usuarios;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

import static dev.serverest.api.applicationAPI.UsuariosAPI.generateRandomUser;
import static dev.serverest.utils.LogUtils.logInfo;


public class UsuarioService {
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
        return this;
    }

    public UsuarioService gerarUsuario(String tipo) {
        requestUsuario.set(generateRandomUser(tipo.equals("admin")));
        return this;
    }

    public void cadastrarUsuario() {
        response.set(UsuariosAPI.post(requestUsuario.get()));
        responseAsClass.set(response.get().as(Usuario.class));
    }

    public void editarUsuario(){
        requestUsuario.set(generateRandomUser());
        response.set(UsuariosAPI.put(requestUsuario.get(),responseAsClass.get().getId()));
    }

    public void acharUsuarios(){
        response.set(UsuariosAPI.get());
        responseAsClassList.set(response.get().as(Usuarios.class));
    }

    public void acharUsuarioPorID(){
        response.set(UsuariosAPI.get("_id",responseAsClass.get().getId()));
        responseAsClassList.set(response.get().as(Usuarios.class));
    }

    public void realizarLogin(){
        requestLogin.set(LoginAPI.loginBuilder(requestUsuario.get().getEmail(),requestUsuario.get().getPassword()));
        response.set(LoginAPI.post(requestLogin.get()));
    }

}
