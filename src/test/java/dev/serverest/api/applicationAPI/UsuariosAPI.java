package dev.serverest.api.applicationAPI;

import dev.serverest.api.RestResource;
import dev.serverest.pojo.Usuario;
import dev.serverest.pojo.Usuarios;
import io.restassured.response.Response;
import org.testng.Assert;


import java.util.stream.Collectors;

import static dev.serverest.api.Route.USUARIOS;

public class UsuariosAPI {
    public static Response get() {
        return RestResource.get(USUARIOS);
    }

    public static Response post(Usuario requestUsuario) {
        return RestResource.post(USUARIOS, requestUsuario);
    }

    public static Response delete(String userId){
        return RestResource.delete(USUARIOS + "/" + userId);
    }

    public static Response put(Usuario requestUsuario,String userId){
        return RestResource.put(USUARIOS + "/" + userId,requestUsuario);
    }

    public static Usuario usuariosBuilder(String nome, String email, String password, String administrador) {
        return Usuario.builder().
                nome(nome).
                email(email).
                password(password).
                administrador(administrador).
                build();
    }

    public static void assertNameThroughUserList(String usuarioNome, Usuarios responseUsuarios) {
        //String name= "";
        /*for (Usuarios usuarios: responseUsuarios.getUsuarios()) {
            if (usuarios.getNome().equals(nome)){
                name = usuarios.getNome();
            }
        }*/
        try{
            Usuario usuario = responseUsuarios.getUsuarios().
                    stream().
                    filter(e -> e.getNome().equals(usuarioNome)).
                    collect(Collectors.toList())
                    .get(0);
            System.out.println(usuario.getNome());
            Assert.assertEquals(usuario.getNome(), usuarioNome);
        }catch (IndexOutOfBoundsException exception){
            System.out.println("Usuário não encontrado");
            Assert.fail();
        }
    }
}
