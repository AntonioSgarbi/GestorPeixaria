package tech.antoniosgarbi.gestorpeixaria.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.antoniosgarbi.gestorpeixaria.model.Supplier;

@Data
@AllArgsConstructor
public class SupplierDTO extends PersonDTO {

    public SupplierDTO(Supplier model) {
        super(
                model.getId(),
                model.getName(),
                model.getDocument(),
                model.getLegalRecordType(),
                model.getPhones(),
                model.getEmail()
        );
    }
}
