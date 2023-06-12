package tech.antoniosgarbi.gestorpeixaria.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.ExpirationLotSpecBody;
import tech.antoniosgarbi.gestorpeixaria.model.Collaborator;
import tech.antoniosgarbi.gestorpeixaria.model.ExpirationLot;
import tech.antoniosgarbi.gestorpeixaria.model.Product;
import tech.antoniosgarbi.gestorpeixaria.model.Supplier;

import javax.persistence.criteria.*;
import java.io.Serial;
import java.util.LinkedList;
import java.util.List;

public class ExpirationLotSpecification implements Specification<ExpirationLot> {

    @Serial
    private static final long serialVersionUID = -2044845799454591750L;
    private final ExpirationLotSpecBody specBody;

    public ExpirationLotSpecification(ExpirationLotSpecBody specBody) {
        this.specBody = specBody;
    }

    @Override
    public Predicate toPredicate(@NonNull Root<ExpirationLot> root,
                                 @NonNull CriteriaQuery<?> criteriaQuery,
                                 @NonNull CriteriaBuilder builder) {

        List<Predicate> predicates = new LinkedList<>();

        if(this.specBody.getArrivalDateStart() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("arrivalDate"), this.specBody.getArrivalDateStart()));
        }

        if(this.specBody.getArrivalDateEnd() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("arrivalDate"), this.specBody.getArrivalDateEnd()));
        }

        if (this.specBody.getExpirationDateStart() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("expirationDate"), this.specBody.getExpirationDateStart()));
        }

        if(this.specBody.getExpirationDateEnd() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("expirationDate"), this.specBody.getExpirationDateEnd()));
        }

        if (this.specBody.getArrivalQuantityMin() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("arrivalQuantity"), this.specBody.getArrivalQuantityMin()));
        }

        if(this.specBody.getArrivalQuantityMax() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("arrivalQuantity"), this.specBody.getArrivalQuantityMax()));
        }

        if (this.specBody.getAvailableQuantityMin() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("availableQuantity"), this.specBody.getAvailableQuantityMin()));
        }

        if(this.specBody.getAvailableQuantityMax() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("availableQuantity"), this.specBody.getAvailableQuantityMax()));
        }

        if (this.specBody.getOptionalPriceMin() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("optionalPrice"), this.specBody.getOptionalPriceMin()));
        }

        if(this.specBody.getOptionalPriceMax() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("optionalPrice"), this.specBody.getOptionalPriceMax()));
        }

        if(this.specBody.getRegisteredMomentStart() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("registeredMoment"), this.specBody.getRegisteredMomentStart()));
        }

        if(this.specBody.getRegisteredMomentEnd() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("registeredMoment"), this.specBody.getRegisteredMomentEnd()));
        }

        if(this.specBody.getArrivalRegistered() != null) {
            predicates.add(builder.equal(root.get("arrivalRegistered"), this.specBody.getArrivalRegistered()));
        }

        if(this.specBody.getProducts() != null && !this.specBody.getProducts().isEmpty()) {
            Path<Product> productPath = root.<Product>get("product");
            predicates.add(productPath.in(this.specBody.getProducts().stream().map(Product::new).toList()));
        }

        if(this.specBody.getSuppliers() != null && !this.specBody.getSuppliers().isEmpty()) {
            Path<Supplier> supplierPath = root.<Supplier>get("supplier");
            predicates.add(supplierPath.in(this.specBody.getSuppliers().stream().map(Supplier::new).toList()));
        }

        if(this.specBody.getReceivedBy() != null && !this.specBody.getReceivedBy().isEmpty()) {
            Path<Collaborator> collaboratorPath = root.<Collaborator>get("collaborator");
            predicates.add(collaboratorPath.in(this.specBody.getReceivedBy().stream().map(Collaborator::new).toList()));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
