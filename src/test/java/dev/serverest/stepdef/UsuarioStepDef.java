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

import static dev.serverest.utils.FakerUtils.*;

public class UsuarioStepDef {
    private Usuario requestUsuario;
    private Response response;

    @Dado("que quero criar um usuario normal")
    public void que_Quero_Criar_Um_Usuario_Normal() {
        String nome = generateName();
        requestUsuario = Usuario.builder().
                nome(nome).
                password(generatePassword()).
                email(generateEmail(nome.replace(" ",""))).
                administrador("false").
                build();
    }

    @Dado("que quero criar um usuario admin")
    public void que_Quero_Criar_Um_Usuario_Admin() {
        String nome = generateName();
        requestUsuario = Usuario.builder().
                nome(nome).
                password(generatePassword()).
                email(generateEmail(nome.replace(" ",""))).
                administrador("true").
                build();
    }

    @Quando("eu fizer um POST na API de cadastro")
    public void eu_fizer_um_POST_na_API_de_cadastro() {
       response = UsuariosAPI.post(requestUsuario);
    }

    @Entao("o status code será {int}")
    public void o_status_code_será(int status) {
        Usuario usuario = response.as(Usuario.class);

        Assert.assertEquals(response.statusCode(),status);

       UsuariosAPI.delete(usuario.getId());
    }
}
