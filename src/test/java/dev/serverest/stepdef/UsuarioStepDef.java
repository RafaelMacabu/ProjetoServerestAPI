package dev.serverest.stepdef;

import dev.serverest.api.applicationAPI.UsuariosAPI;
import dev.serverest.pojo.Usuario;
import dev.serverest.pojo.Usuarios;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;

import static dev.serverest.api.applicationAPI.UsuariosAPI.generateRandomUser;
import static dev.serverest.utils.FakerUtils.*;

public class UsuarioStepDef {
    private Usuario responseAsClass;
    private Usuarios responseAsClassList;
    private Usuario requestUsuario;
    private Response response;

    @Dado("^que quero criar um usuario (normal|admin)$")
    public void que_Quero_Criar_Um_Usuario(String tipo) {
        requestUsuario = generateRandomUser(tipo.equals("admin"));
    }

    @Quando("eu fizer um POST na API de cadastro")
    public void eu_fizer_um_POST_na_API_de_cadastro() {
       response = UsuariosAPI.post(requestUsuario);
        responseAsClass = response.as(Usuario.class);
    }

    @Entao("o status code sera {int}")
    public void o_status_code_sera(Integer status) {
        Assert.assertEquals(response.statusCode(),status);

        UsuariosAPI.delete(responseAsClass.getId());
    }

    @Dado("tenho uma conta ja cadastrada")
    public void tenho_Uma_Conta_Ja_Cadastrada() {
        requestUsuario = generateRandomUser();
        response = UsuariosAPI.post(requestUsuario);
        
    }

    @Quando("eu fizer um PUT na API de cadastro")
    public void eu_Fizer_Um_PUT_Na_API_De_Cadastro() {
        responseAsClass = response.as(Usuario.class);
        requestUsuario = generateRandomUser();
        response = UsuariosAPI.put(requestUsuario,responseAsClass.getId());
    }

    @Quando("eu fizer um GET na API de cadastro com o id")
    public void eu_Fizer_Um_GET_Na_API_De_Cadastro() {
        responseAsClass = response.as(Usuario.class);
        response = UsuariosAPI.get(responseAsClass.getId());
    }

    @Entao("a quantidade de usuarios achados sera {int}")
    public void a_Quantidade_De_Usuarios_Achados_Sera(int quantidade) {
        responseAsClassList = response.as(Usuarios.class);
        Assert.assertEquals(responseAsClassList.getQuantidade(),quantidade);
        UsuariosAPI.delete(responseAsClass.getId());
    }
}
