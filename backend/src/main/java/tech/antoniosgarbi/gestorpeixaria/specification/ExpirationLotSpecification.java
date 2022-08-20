package tech.antoniosgarbi.gestorpeixaria.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import tech.antoniosgarbi.gestorpeixaria.dto.stock.ExpirationLotSpecBody;
import tech.antoniosgarbi.gestorpeixaria.model.ExpirationLot;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serial;

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
        return null;
    }
}
