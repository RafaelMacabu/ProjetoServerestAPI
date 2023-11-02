package dev.serverest.stepdef;

import dev.serverest.api.applicationAPI.UsuariosAPI;
import dev.serverest.pojo.Usuario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;

public class UsuarioStepDef {
    private Usuario requestUsuario;
    private Response response;

    @Dado("que quero criar o usuario")
    public void que_Quero_Criar_O_Usuario(DataTable dataTable) {
        Map<String,String> map = dataTable.asMap(String.class,String.class);
        requestUsuario = Usuario.builder().
                nome(map.get("nome")).
                password(map.get("senha")).
                email(map.get("email")).
                administrador(map.get("admin")).
                build();
    }
    @Quando("eu fizer um POST na API de cadastro")
    public void eu_fizer_um_POST_na_API_de_cadastro() {
       response = UsuariosAPI.post(requestUsuario);
    }
    @Entao("o status code será {int}")
    public void o_status_code_será(int status) {
        Assert.assertEquals(response.statusCode(),status);
    }
}
