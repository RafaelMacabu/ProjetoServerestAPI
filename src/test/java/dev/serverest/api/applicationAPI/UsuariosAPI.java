package dev.serverest.api.applicationAPI;

import dev.serverest.api.RestResource;
import dev.serverest.pojo.Usuario;
import dev.serverest.pojo.Usuarios;
import io.restassured.response.Response;
import org.testng.Assert;


import java.util.stream.Collectors;

import static dev.serverest.api.Route.USUARIOS;

public class UsuariosAPI {

    public static Response get(){
        return RestResource.get(USUARIOS);
    }

    public static Response post(Usuario requestLogin){
        return RestResource.post(USUARIOS,requestLogin);

    }

    public static Usuario usuariosBuilder(String nome, String email, String password, String administrador){
        return Usuario.builder().
                nome(nome).
                email(email).
                password(password).
                administrador(administrador).
                build();

    }

    public static void assertNameThroughUserList(String nome, Usuarios responseUsuarios){
        //String name= "";
        /*for (Usuarios usuarios: responseUsuarios.getUsuarios()) {
            if (usuarios.getNome().equals(nome)){
                name = usuarios.getNome();
            }
        }*/
        String name = responseUsuarios.getUsuarios().
                stream().
                filter(e -> e.getNome().equals(nome)).
                collect(Collectors.toList())
                .get(0).getNome();
        Assert.assertEquals(name,nome);

    }
}
