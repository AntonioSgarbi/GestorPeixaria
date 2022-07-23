package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.antoniosgarbi.gestorpeixaria.dto.model.CustomerDTO;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends Person {

    @OneToMany
    private Set<Sale> purchases;

    public Customer(CustomerDTO customerDTO) {
        super(
                customerDTO.getId(),
                customerDTO.getDocument(),
                customerDTO.getName(),
                customerDTO.getLegalRecordType(),
                customerDTO.getPhones(),
                customerDTO.getEmail(),
                false
        );
    }
}