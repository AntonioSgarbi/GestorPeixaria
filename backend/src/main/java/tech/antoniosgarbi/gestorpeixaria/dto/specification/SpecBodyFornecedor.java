package tech.antoniosgarbi.gestorpeixaria.dto.specification;

import lombok.Data;
import tech.antoniosgarbi.gestorpeixaria.model.enums.PessoaTipo;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SpecBodyFornecedor implements Serializable {
    @Serial
    private static final long serialVersionUID = -1279122557236063452L;

    private String nome;
    private String documento;
    private PessoaTipo pessoaTipo;
}
