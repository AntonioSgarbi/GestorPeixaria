package tech.antoniosgarbi.gestorpeixaria.dto.specification;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.model.enums.LegalRecordType;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class SupplierSpecBody implements Serializable {
    @Serial
    private static final long serialVersionUID = -1279122557236063452L;

    private String name;
    private String document;
    private LegalRecordType legalRecordType;
}
