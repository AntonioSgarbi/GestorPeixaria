package tech.antoniosgarbi.gestorpeixaria.service.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SupplierDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.SupplierSpecBody;

public interface SupplierService {
    long register(SupplierDTO supplierDTO);
    SupplierDTO update(SupplierDTO supplierDTO);
    SupplierDTO findById(long id);
    Page<SupplierDTO> findAll(Pageable pageable);
    Page<SupplierDTO> findAll(SupplierSpecBody specBody, Pageable pageable);
    void delete(long id);


}
