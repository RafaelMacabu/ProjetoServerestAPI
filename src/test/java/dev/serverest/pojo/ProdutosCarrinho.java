package dev.serverest.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter
@Setter
@Builder
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProdutosCarrinho {
    @JsonProperty("idProduto")
    private String idProduto;
    @JsonProperty("quantidade")
    private int quantidade;
    @JsonProperty("precoUnitario")
    private String precoUnitario;
}
