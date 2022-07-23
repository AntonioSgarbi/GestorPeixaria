package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SupplierDTO;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Supplier extends Person {
    public Supplier(SupplierDTO dto) {
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