package tech.antoniosgarbi.gestorpeixaria.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.model.Collaborator;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollaboratorDTO extends PersonDTO {

    private Set<SaleDTO> sales;
    private Double wage;

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
