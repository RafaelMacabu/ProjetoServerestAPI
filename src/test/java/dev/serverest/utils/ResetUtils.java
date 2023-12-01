package dev.serverest.utils;

import dev.serverest.api.TokenManager;
import dev.serverest.api.applicationAPI.CarrinhoAPI;
import dev.serverest.api.applicationAPI.ProdutosAPI;
import dev.serverest.api.applicationAPI.UsuariosAPI;
import dev.serverest.services.CarrinhoService;
import dev.serverest.services.ProdutosService;
import dev.serverest.services.UsuarioService;

import java.util.ArrayList;

public class ResetUtils {
    public static void reset(){
        if (CarrinhoService.getResponseAsClass() != null) {
            CarrinhoAPI.delete();
        }

        if (ProdutosService.getResponseAsClass() != null) {
            for (String produto: ProdutosService.getIdProduto()) {
                ProdutosAPI.delete(produto);
            }
        }

        if (UsuarioService.getResponseAsClass() != null) {
            UsuariosAPI.delete(UsuarioService.getIdUsuario());
        }

        if (ProdutosService.getResponseAsClass() != null) {
            ProdutosService.getIdProduto().clear();
        }

        if (TokenManager.getBearerToken() != null) {
            TokenManager.setBearerToken(null);
        }
    }
}
