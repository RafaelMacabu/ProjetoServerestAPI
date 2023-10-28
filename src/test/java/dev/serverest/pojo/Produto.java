package dev.serverest.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.nio.file.Path;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@Jacksonized
public class Produto {
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("preco")
    private double preco;
    @JsonProperty("descricao")
    private String descricao;
    @JsonProperty("quantidade")
    private long quantidade;
    @JsonProperty("message")
    private String message;
    @JsonProperty("_id")
    private String id;
    @JsonProperty("imagem")
    private String imagem;
}
