package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SupplierDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.SupplierSpecBody;
import tech.antoniosgarbi.gestorpeixaria.exception.PersonException;
import tech.antoniosgarbi.gestorpeixaria.model.Supplier;
import tech.antoniosgarbi.gestorpeixaria.repository.SupplierRepository;
import tech.antoniosgarbi.gestorpeixaria.service.Util;
import tech.antoniosgarbi.gestorpeixaria.service.contract.SupplierService;
import tech.antoniosgarbi.gestorpeixaria.specification.SupplierSpecification;

import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public long register(SupplierDTO supplierDTO) {
        Optional<Supplier> optional = this.supplierRepository.findByDocument(supplierDTO.getDocument());
        if (optional.isPresent()) throw new PersonException("O documento informado já possui cadastro no sistema!");
        Supplier model = new Supplier(supplierDTO);
        model = this.supplierRepository.save(model);
        return model.getId();
    }

    @Override
    public SupplierDTO update(SupplierDTO supplierDTO) {
        Supplier supplier = this.findModel(supplierDTO.getId());
        Util.myCopyProperties(supplierDTO, supplier);
        this.supplierRepository.save(supplier);
        return supplierDTO;
    }

    @Override
    public SupplierDTO findById(long id) {
        return new SupplierDTO(this.findModel(id));
    }

    @Override
    public Page<SupplierDTO> findAll(Pageable pageable) {
        return this.supplierRepository.findAll(pageable).map(SupplierDTO::new);
    }

    @Override
    public Page<SupplierDTO> findAll(SupplierSpecBody specBody, Pageable pageable) {
        Specification<Supplier> specification = new SupplierSpecification(specBody);
        return this.supplierRepository.findAll(specification, pageable).map(SupplierDTO::new);
    }

    @Override
    public void delete(long id) {
        SupplierDTO dto = this.findById(id);
        Supplier model = new Supplier(dto);
        model.setExcluded(true);
        this.supplierRepository.save(model);
    }

    private Supplier findModel(long id) {
        Optional<Supplier> optional = this.supplierRepository.findById(id);
        if (optional.isEmpty()) throw new PersonException("Cadastro não encontrado");
        return optional.get();
    }

}