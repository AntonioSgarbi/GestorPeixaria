package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.antoniosgarbi.gestorpeixaria.dto.model.CollaboratorDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.CollaboratorSpecBody;
import tech.antoniosgarbi.gestorpeixaria.exception.PersonException;
import tech.antoniosgarbi.gestorpeixaria.model.Collaborator;
import tech.antoniosgarbi.gestorpeixaria.repository.CollaboratorRepository;
import tech.antoniosgarbi.gestorpeixaria.service.Util;
import tech.antoniosgarbi.gestorpeixaria.service.contract.CollaboratorService;
import tech.antoniosgarbi.gestorpeixaria.specification.CollaboratorSpecification;

import java.util.Optional;

@Service
public class CollaboratorServiceImpl implements CollaboratorService {
    private final CollaboratorRepository collaboratorRepository;

    public CollaboratorServiceImpl(CollaboratorRepository collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }

    @Override
    public long register(CollaboratorDTO collaboratorDTO) {
        Optional<Collaborator> optional = this.collaboratorRepository.findByDocument(collaboratorDTO.getDocument());
        if (optional.isPresent()) throw new PersonException("O documento informado já possui cadastro no sistema!");
        Collaborator model = new Collaborator(collaboratorDTO);
        model = this.collaboratorRepository.save(model);
        return model.getId();
    }

    @Override
    public CollaboratorDTO update(CollaboratorDTO collaboratorDTO) {
        Collaborator model = this.findModel(collaboratorDTO.getId());
        Util.myCopyProperties(collaboratorDTO, model);
        this.collaboratorRepository.save(model);
        return collaboratorDTO;
    }

    @Override
    public CollaboratorDTO findById(long id) {
        return new CollaboratorDTO(this.findModel(id));
    }

    @Override
    public Page<CollaboratorDTO> findAll(Pageable pageable) {
        return this.collaboratorRepository.findAll(pageable).map(CollaboratorDTO::new);
    }

    @Override
    public Page<CollaboratorDTO> findAll(CollaboratorSpecBody specBody, Pageable pageable) {
        Specification<Collaborator> specification = new CollaboratorSpecification(specBody);
        return this.collaboratorRepository.findAll(specification, pageable).map(CollaboratorDTO::new);
    }

    @Override
    public void delete(long id) {
        CollaboratorDTO dto = this.findById(id);
        Collaborator model = new Collaborator(dto);
        model.setExcluded(true);
        this.collaboratorRepository.save(model);
    }

    public Collaborator findModel(long id) {
        Optional<Collaborator> optional = this.collaboratorRepository.findById(id);
        if (optional.isEmpty()) throw new PersonException("Cadastro não encontrado");
        return optional.get();
    }

}