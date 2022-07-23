package tech.antoniosgarbi.gestorpeixaria.specification;

import org.springframework.data.jpa.domain.Specification;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.SupplierSpecBody;
import tech.antoniosgarbi.gestorpeixaria.model.Supplier;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

public class SupplierSpecification implements Specification<Supplier> {

    private final SupplierSpecBody specBodyFornecedor;

    public SupplierSpecification(SupplierSpecBody specBody) {
        this.specBodyFornecedor = specBody;
    }
    @Override
    public Predicate toPredicate(Root<Supplier> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new LinkedList<>();

        if (specBodyFornecedor.getNome() != null) {
            predicates.add(
                    builder.like(
                            builder.upper(root.get("nome")),
                            String.format("%%%s%%", specBodyFornecedor.getNome().toUpperCase())
                    ));
        }
        if (specBodyFornecedor.getDocumento() != null) {
            predicates.add(
                    builder.like(
                            builder.upper(root.get("documento")),
                            String.format("%%%s%%", specBodyFornecedor.getDocumento().toUpperCase())
                    ));
        }
        if (specBodyFornecedor.getPessoaTipo() != null) {
            predicates.add(
                    builder.equal(
                            root.get("pessoaTipo"),
                            specBodyFornecedor.getPessoaTipo()
                    )
            );
        }
        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
