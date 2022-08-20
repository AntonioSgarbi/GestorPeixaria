package tech.antoniosgarbi.gestorpeixaria.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.ProductSpecBody;
import tech.antoniosgarbi.gestorpeixaria.model.Product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serial;
import java.util.LinkedList;
import java.util.List;

public class ProductSpecification implements Specification<Product> {

    @Serial
    private static final long serialVersionUID = -935421897406832275L;
    private final ProductSpecBody productSpecBody;

    public ProductSpecification(ProductSpecBody productSpecBody) {
        this.productSpecBody = productSpecBody;
    }

    @Override
    public Predicate toPredicate(@NonNull Root<Product> root,
                                 @NonNull CriteriaQuery<?> criteriaQuery,
                                 @NonNull CriteriaBuilder builder) {
        List<Predicate> predicates = new LinkedList<>();

        if(this.productSpecBody.getName() != null) {
            predicates.add(
                    builder.like(
                            builder.upper(root.get("name")),
                            String.format("%%%s%%", this.productSpecBody.getName().toUpperCase()))
            );
        }
        if(this.productSpecBody.getQuantityType() != null) {
            predicates.add(
                    builder.equal(
                            root.get("quantityType"),
                            this.productSpecBody.getQuantityType())
            );
        }
        if(this.productSpecBody.getSupplier() != null) {
            predicates.add(
                    builder.equal(
                            root.get("supplier"),
                            this.productSpecBody.getSupplier())
            );
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
