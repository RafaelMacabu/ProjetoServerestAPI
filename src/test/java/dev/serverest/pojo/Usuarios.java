package dev.serverest.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@Jacksonized
public class Usuarios {

    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("administrador")
    private String administrador;
    @JsonProperty("_id")
    private String id;
    @JsonProperty("message")
    private String message;
    @JsonProperty("quantidade")
    private int quantidade;
    @JsonProperty("usuarios")
    private List<Usuarios> usuarios;


}
