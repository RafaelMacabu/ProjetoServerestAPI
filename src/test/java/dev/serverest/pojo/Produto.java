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
public class Produto {
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("preco")
    private double preco;
    @JsonProperty("descricao")
    private String descricao;
    @JsonProperty("quantidade")
    private long quantidade;
    @JsonProperty("_id")
    private String id;
    @JsonProperty("message")
    private String message;
    @JsonProperty("imagem")
    private String imagem;
}
