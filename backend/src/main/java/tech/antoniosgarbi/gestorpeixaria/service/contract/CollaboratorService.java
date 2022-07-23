package tech.antoniosgarbi.gestorpeixaria.service.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.antoniosgarbi.gestorpeixaria.dto.model.CollaboratorDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.CollaboratorSpecBody;

public interface CollaboratorService {
    long register(CollaboratorDTO collaboratorDTO);
    CollaboratorDTO update(CollaboratorDTO collaboratorDTO);
    CollaboratorDTO findById(long id);
    Page<CollaboratorDTO> findAll(Pageable pageable);
    Page<CollaboratorDTO> findAll(CollaboratorSpecBody specBody, Pageable pageable);
    void delete(long id);

}
