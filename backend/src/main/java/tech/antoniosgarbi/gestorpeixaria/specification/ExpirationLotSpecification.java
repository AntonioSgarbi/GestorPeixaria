package tech.antoniosgarbi.gestorpeixaria.specification;

import org.springframework.data.jpa.domain.Specification;
import tech.antoniosgarbi.gestorpeixaria.dto.stock.ExpirationLotSpecBody;
import tech.antoniosgarbi.gestorpeixaria.model.ExpirationLot;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ExpirationLotSpecification implements Specification<ExpirationLot> {

    private final ExpirationLotSpecBody specBody;

    public ExpirationLotSpecification(ExpirationLotSpecBody specBody) {
        this.specBody = specBody;
    }

    @Override
    public Predicate toPredicate(Root<ExpirationLot> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
