package tech.antoniosgarbi.gestorpeixaria.dto.specification;

import lombok.Data;
import tech.antoniosgarbi.gestorpeixaria.model.enums.PessoaTipo;

@Data
public class SpecBodyFornecedor {
    private String nome;
    private String documento;
    private PessoaTipo pessoaTipo;
}
