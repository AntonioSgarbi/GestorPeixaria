package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.*;
import tech.antoniosgarbi.gestorpeixaria.dto.model.CollaboratorDTO;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Collaborator extends Person {

    @OneToMany
    private Set<Sale> sales;

    private Double wage;

    public Collaborator(CollaboratorDTO dto) {
        super(
                dto.getId(),
                dto.getDocument(),
                dto.getName(),
                dto.getLegalRecordType(),
                dto.getPhones(),
                dto.getEmail(),
                false
        );
    }
}