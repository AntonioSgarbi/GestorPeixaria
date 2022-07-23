package tech.antoniosgarbi.gestorpeixaria.dto.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.model.Customer;

@Data
@NoArgsConstructor
public class CustomerDTO extends PersonDTO {

    public CustomerDTO(Customer model) {
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
