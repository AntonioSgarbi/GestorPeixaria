package tech.antoniosgarbi.gestorpeixaria.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.CollaboratorSpecBody;
import tech.antoniosgarbi.gestorpeixaria.model.Collaborator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serial;
import java.util.LinkedList;
import java.util.List;

public class CollaboratorSpecification implements Specification<Collaborator> {

    @Serial
    private static final long serialVersionUID = 8308450286467231359L;
    private final CollaboratorSpecBody specBody;

    public CollaboratorSpecification(CollaboratorSpecBody specBody) {
        this.specBody = specBody;
    }

    @Override
    public Predicate toPredicate(@NonNull Root<Collaborator> root,
                                 @NonNull CriteriaQuery<?> criteriaQuery,
                                 @NonNull CriteriaBuilder builder) {
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
