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


public class UsuarioService {
    @Getter
    private static Login requestLogin = new Login();
    @Getter
    private static Usuario responseAsClass = new Usuario();
    @Getter
    private static Usuarios responseAsClassList = new Usuarios();
    @Getter
    private static Usuario requestUsuario = new Usuario();
    @Getter
    @Setter
    private static Response response;

    public UsuarioService action() {
        return this;
    }

    public UsuarioService gerarUsuario() {
        requestUsuario = generateRandomUser();
        return this;
    }

    public UsuarioService gerarUsuario(String tipo) {
        requestUsuario = generateRandomUser(tipo.equals("admin"));
        return this;
    }

    public void cadastrarUsuario() {
        response = UsuariosAPI.post(requestUsuario);
        responseAsClass = response.as(Usuario.class);
    }

    public void editarUsuario(){
        requestUsuario = generateRandomUser();
        response = UsuariosAPI.put(requestUsuario, responseAsClass.getId());
    }

    public void acharUsuarios(){
        response = UsuariosAPI.get();
        responseAsClassList = response.as(Usuarios.class);
    }

    public void acharUsuarioPorID(){
        response = UsuariosAPI.get("_id", responseAsClass.getId());
        responseAsClassList = response.as(Usuarios.class);
    }

    public void realizarLogin(){
        requestLogin = LoginAPI.loginBuilder(requestUsuario.getEmail(), requestUsuario.getPassword());
        response = LoginAPI.post(requestLogin);
    }

    public Usuario setResponseAsClass(Response response) {
        responseAsClass = response.as(Usuario.class);
        return responseAsClass;
    }

    public Usuarios setResponseAsClassList(Response response) {
        responseAsClassList = response.as(Usuarios.class);
        return responseAsClassList;
    }
}
