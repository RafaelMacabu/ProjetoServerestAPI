package dev.serverest.pojo;

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
public class Carrinhos {
    @JsonProperty("quantidade")
    private double quantidade;
    @JsonProperty("carrinhos")
    private List<Carrinho> carrinhos;

}
