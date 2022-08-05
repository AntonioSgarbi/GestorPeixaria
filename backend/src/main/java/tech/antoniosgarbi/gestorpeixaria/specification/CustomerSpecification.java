package tech.antoniosgarbi.gestorpeixaria.specification;

import org.springframework.data.jpa.domain.Specification;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.CustomerSpecBody;
import tech.antoniosgarbi.gestorpeixaria.model.Customer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

public class CustomerSpecification implements Specification<Customer> {

    private final CustomerSpecBody specBody;

    public CustomerSpecification(CustomerSpecBody specBody) {
        this.specBody = specBody;
    }
    @Override
    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new LinkedList<>();

        if (specBody.getName() != null) {
            predicates.add(
                    builder.like(
                            builder.upper(root.get("name")),
                            String.format("%%%s%%", specBody.getName().toUpperCase())
                    ));
        }
        if (specBody.getDocument() != null) {
            predicates.add(
                    builder.like(
                            builder.upper(root.get("document")),
                            String.format("%%%s%%", specBody.getDocument().toUpperCase())
                    ));
        }
        if (specBody.getLegalRecordType() != null) {
            predicates.add(
                    builder.equal(
                            root.get("legalRecordType"),
                            specBody.getLegalRecordType()
                    )
            );
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
