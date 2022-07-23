package tech.antoniosgarbi.gestorpeixaria.dto.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.model.Collaborator;

import java.util.Set;

@Data
@NoArgsConstructor
public class CollaboratorDTO extends PersonDTO {

    private Set<SaleDTO> vendas;

    public CollaboratorDTO(Collaborator modelo) {
        super(
                modelo.getId(),
                modelo.getName(),
                modelo.getDocument(),
                modelo.getLegalRecordType(),
                modelo.getPhones(),
                modelo.getEmail()
        );
    }
}
