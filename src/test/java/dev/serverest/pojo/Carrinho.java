package dev.serverest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter
@Setter
@Builder
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Carrinho {
    @JsonProperty("produtos")
    private List<ProdutosCarrinho> produtos;
    @JsonProperty("precoTotal")
    private int precoTotal;
    @JsonProperty("quantidadeTotal")
    private int quantidadeTotal;
    @JsonProperty("idUsuario")
    private String idUsuario;
    @JsonProperty("_id")
    private String idCarrinho;
    @JsonProperty("message")
    private String message;
}
