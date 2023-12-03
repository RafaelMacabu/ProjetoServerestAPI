package dev.serverest.stepdef;

import dev.serverest.services.UsuarioService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class UsuarioStepDef {
    private UsuarioService service = new UsuarioService();

    @Dado("^que quero criar um usuario (normal|admin)$")
    public void que_Quero_Criar_Um_Usuario(String tipo) {
        service.action().
                gerarUsuario(tipo);
    }

    @Quando("^eu fizer um (POST|GET|PUT) na API de (cadastro|cadastro com o id)$")
    public void eu_fizer_um_POST_na_API_de_cadastro(String metodo,String cadastro) {
        switch (metodo){
            case "POST":
                service.action().
                        cadastrarUsuario();
                break;
            case "GET":
                if (cadastro.contains("id")){
                    service.action().
                            acharUsuarioPorID();
                }else {
                    service.action().
                            acharUsuarios();
                }
                break;
            case "PUT":
                service.action().
                        editarUsuario();
                break;
            default:
                throw new IllegalArgumentException("Opção não parametrizada");
        }
    }

    @Dado("que eu tenho uma conta ja cadastrada")
    public void tenho_Uma_Conta_Ja_Cadastrada() {
        service.action().
                gerarUsuario().
                cadastrarUsuario();
    }

    @Entao("a quantidade de usuarios encontrados sera {int}")
    public void a_Quantidade_De_Usuarios_Achados_Sera(int quantidade) {
        service.action().
                assertQuantidade(quantidade);
    }

    @Dado("que eu tenho uma conta nao cadastrada")
    public void queEuTenhoUmaContaNaoCadastrada() {
        service.action().
                gerarUsuario();
    }

    @Quando("eu fizer um POST na API de login")
    public void euFizerUmPOSTNaAPIDeLogin() {
        service.action().
                realizarLogin();
    }
}
