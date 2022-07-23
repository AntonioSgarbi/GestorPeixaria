package tech.antoniosgarbi.gestorpeixaria.specification;

import org.springframework.data.jpa.domain.Specification;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.CollaboratorSpecBody;
import tech.antoniosgarbi.gestorpeixaria.model.Collaborator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

public class CollaboratorSpecification implements Specification<Collaborator> {

    private final CollaboratorSpecBody specBodyFuncionario;

    public CollaboratorSpecification(CollaboratorSpecBody specBody) {
        this.specBodyFuncionario = specBody;
    }
    @Override
    public Predicate toPredicate(Root<Collaborator> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new LinkedList<>();

        if (specBodyFuncionario.getNome() != null) {
            predicates.add(
                    builder.like(
                            builder.upper(root.get("nome")),
                            String.format("%%%s%%", specBodyFuncionario.getNome().toUpperCase())
                    ));
        }
        if (specBodyFuncionario.getDocumento() != null) {
            predicates.add(
                    builder.like(
                            builder.upper(root.get("documento")),
                            String.format("%%%s%%", specBodyFuncionario.getDocumento().toUpperCase())
                    ));
        }
        if (specBodyFuncionario.getPessoaTipo() != null) {
            predicates.add(
                    builder.equal(
                            root.get("pessoaTipo"),
                            specBodyFuncionario.getPessoaTipo()
                    )
            );
        }
        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
