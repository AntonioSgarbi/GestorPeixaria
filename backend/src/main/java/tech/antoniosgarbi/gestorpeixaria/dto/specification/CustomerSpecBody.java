package tech.antoniosgarbi.gestorpeixaria.dto.specification;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class CustomerSpecBody implements Serializable {
    @Serial
    private static final long serialVersionUID = 1218924684057835267L;

    private String nome;
    private String documento;
    private String pessoaTipo;

}
