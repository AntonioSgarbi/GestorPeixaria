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

    private final CustomerSpecBody specBodyCliente;

    public CustomerSpecification(CustomerSpecBody specBody) {
        this.specBodyCliente = specBody;
    }
    @Override
    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new LinkedList<>();

        if (specBodyCliente.getNome() != null) {
            predicates.add(
                    builder.like(
                            builder.upper(root.get("nome")),
                            String.format("%%%s%%", specBodyCliente.getNome().toUpperCase())
                    ));
        }
        if (specBodyCliente.getDocumento() != null) {
            predicates.add(
                    builder.like(
                            builder.upper(root.get("documento")),
                            String.format("%%%s%%", specBodyCliente.getDocumento().toUpperCase())
                    ));
        }
        if (specBodyCliente.getPessoaTipo() != null) {
            predicates.add(
                    builder.equal(
                            root.get("pessoaTipo"),
                            specBodyCliente.getPessoaTipo()
                    )
            );
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
