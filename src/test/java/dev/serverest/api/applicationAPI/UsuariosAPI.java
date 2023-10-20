package dev.serverest.api.applicationAPI;

import dev.serverest.api.RestResource;
import dev.serverest.pojo.Usuarios;
import io.restassured.response.Response;


import static dev.serverest.api.Route.USUARIOS;

public class UsuariosAPI {

    public static Response get(){
        return RestResource.get(USUARIOS);
    }

    public static Response post(Usuarios requestLogin){
        return RestResource.post(USUARIOS,requestLogin);

    }

    public static Usuarios usuariosBuilder(String nome,String email,String password,String administrador){
        return Usuarios.builder().
                nome(nome).
                email(email).
                password(password).
                administrador(administrador).
                build();

    }
}
