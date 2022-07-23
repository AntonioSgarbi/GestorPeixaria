package tech.antoniosgarbi.gestorpeixaria.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.model.Person;
import tech.antoniosgarbi.gestorpeixaria.model.enums.LegalRecordType;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class PersonDTO {
    protected Long id;
    protected String name;
    protected String document;
    protected LegalRecordType legalRecordType;
    protected Set<String> phones;
    protected String email;

    protected PersonDTO(Person model) {
        this.id = model.getId();
        this.document = model.getDocument();
        this.legalRecordType = model.getLegalRecordType();
        this.phones = model.getPhones();
        this.email = model.getEmail();
    }

}