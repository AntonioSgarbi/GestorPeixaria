package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.antoniosgarbi.gestorpeixaria.dto.model.VendaDTO;
import tech.antoniosgarbi.gestorpeixaria.repository.VendaRepository;
import tech.antoniosgarbi.gestorpeixaria.service.contract.ItemCompraService;
import tech.antoniosgarbi.gestorpeixaria.service.contract.VendaService;

@Service
public class VendaServiceImpl implements VendaService {
    private final VendaRepository vendaRepository;
    private ItemCompraService itemCompraService;

    public VendaServiceImpl(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    @Override
    public Long registrarVenda(VendaDTO vendaDTO) {
        return null;
    }

    @Override
    public Page<VendaDTO> encontrarTodos(Pageable pageable) {
        return vendaRepository.findAll(pageable).map(VendaDTO::new);
    }

    @Override
    public VendaDTO encontrarCadastro(Long id) {
        return null;
    }

    @Override
    public void apagarCadastro(Long id) {
        vendaRepository.deleteById(id);
    }
}
