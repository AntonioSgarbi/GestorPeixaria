package tech.antoniosgarbi.gestorpeixaria.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SaleDTO;
import tech.antoniosgarbi.gestorpeixaria.exception.SaleException;
import tech.antoniosgarbi.gestorpeixaria.model.Sale;
import tech.antoniosgarbi.gestorpeixaria.repository.SaleRepository;
import tech.antoniosgarbi.gestorpeixaria.service.contract.SaleItemService;
import tech.antoniosgarbi.gestorpeixaria.service.contract.SaleService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleItemService saleItemService;

    @Override
    public Long register(SaleDTO saleDTO) {
        this.saleItemService.saveAll(saleDTO.getSaleItems());
        Sale sale = this.saleRepository.save(new Sale(saleDTO));
        return sale.getId();
    }

    @Override
    public Page<SaleDTO> findAll(Pageable pageable) {
        return this.saleRepository.findAll(pageable).map(SaleDTO::new);
    }

    @Override
    public SaleDTO findById(Long id) {
        Optional<Sale> optional = this.saleRepository.findById(id);

        if(optional.isEmpty()) {
            throw new SaleException("Cadastro não encontrado");
        }
        return new SaleDTO(optional.get());
    }

    @Override
    public void delete(Long id) {
        this.saleRepository.deleteById(id);
    }

}
