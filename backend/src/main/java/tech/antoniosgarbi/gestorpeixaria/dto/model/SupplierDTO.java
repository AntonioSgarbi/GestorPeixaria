package tech.antoniosgarbi.gestorpeixaria.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.antoniosgarbi.gestorpeixaria.model.Supplier;

@Data
@AllArgsConstructor
public class SupplierDTO extends PersonDTO {

    public SupplierDTO(Supplier modelo) {
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
