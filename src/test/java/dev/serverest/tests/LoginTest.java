package dev.serverest.tests;

import dev.serverest.api.applicationAPI.LoginAPI;
import dev.serverest.api.applicationAPI.UsuariosAPI;
import dev.serverest.pojo.Login;
import dev.serverest.pojo.Usuario;
import dev.serverest.pojo.Usuarios;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static dev.serverest.api.applicationAPI.LoginAPI.loginBuilder;
import static dev.serverest.api.applicationAPI.UsuariosAPI.assertNameThroughUserList;
import static dev.serverest.api.applicationAPI.UsuariosAPI.usuariosBuilder;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginTest {
    @Test
    public void UsuariosPost() {
        Usuario requestUsuarios =
                usuariosBuilder("Rafael Macabu", "raffe@qa.com.br", "raff", "true");

        Response response = UsuariosAPI.post(requestUsuarios);
        Assert.assertEquals(response.statusCode(), 201);
    }

    @Test
    public void UsuariosGet() {
        Response response = UsuariosAPI.get();
        Usuarios responseUsuarios = response.as(Usuarios.class);
        assertNameThroughUserList("Rafael Macabu", responseUsuarios);
    }

    @Test
    public void UsuariosDelete(){
        String userId = "";
        Response response = UsuariosAPI.delete(userId);
        Usuario responseUsuarios = response.as(Usuario.class);
        Assert.assertEquals(responseUsuarios.getMessage(),"Registro excluído com sucesso");
    }

    @Test
    public void UsuariosPut(){
        String userId = "9JRpwLureDpKp309";
        Usuario requestUsuarios =
                usuariosBuilder("Rafael Macabu", "raffe@qa.com.br", "raff", "true");
        Response response = UsuariosAPI.put(requestUsuarios,userId);
        Usuario responseUsuarios = response.as(Usuario.class);
        Assert.assertEquals(responseUsuarios.getMessage(),"Registro alterado com sucesso");
    }
    @Test
    public void Login() {
        Login requestLogin = loginBuilder("raffe@qa.com.br", "raff");

        Response response = LoginAPI.post(requestLogin);
        Assert.assertEquals(response.statusCode(), 200);

        Login responseLogin = response.as(Login.class);
        Assert.assertEquals(responseLogin.getMessage(), "Login realizado com sucesso");
    }
}
