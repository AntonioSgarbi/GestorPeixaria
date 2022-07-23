package tech.antoniosgarbi.gestorpeixaria.dto.specification;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class CollaboratorSpecBody implements Serializable {
    @Serial
    private static final long serialVersionUID = 6847245512087316883L;

    private String nome;
    private String documento;
    private String pessoaTipo;

}
