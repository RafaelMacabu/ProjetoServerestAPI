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

    @JsonProperty("quantidade")
    private int quantidade;
    @JsonProperty("usuarios")
    private List<Usuario> usuarios;


}
