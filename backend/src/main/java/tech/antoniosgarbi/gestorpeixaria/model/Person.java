package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.antoniosgarbi.gestorpeixaria.dto.model.PersonDTO;
import tech.antoniosgarbi.gestorpeixaria.model.enums.LegalRecordType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Person {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String document;
    protected String name;
    protected LegalRecordType legalRecordType;
    @ElementCollection
    protected Set<String> phones;
    protected String email;
    protected Boolean excluded;

    protected Person(PersonDTO dto) {
        this.id = dto.getId();
        this.document = dto.getDocument();
        this.legalRecordType = dto.getLegalRecordType();
        this.phones = dto.getPhones();
        this.email = dto.getEmail();
        this.excluded = false;
    }

}