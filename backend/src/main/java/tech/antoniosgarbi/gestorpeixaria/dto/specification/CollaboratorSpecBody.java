package tech.antoniosgarbi.gestorpeixaria.dto.specification;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.model.enums.LegalRecordType;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class CollaboratorSpecBody implements Serializable {
    @Serial
    private static final long serialVersionUID = 6847245512087316883L;

    private String name;
    private String document;
    private LegalRecordType legalRecordType;

}
