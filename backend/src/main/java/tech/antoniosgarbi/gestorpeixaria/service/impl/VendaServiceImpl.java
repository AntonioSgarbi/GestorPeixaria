package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.antoniosgarbi.gestorpeixaria.dto.model.VendaDTO;
import tech.antoniosgarbi.gestorpeixaria.repository.VendaRepository;
import tech.antoniosgarbi.gestorpeixaria.service.contract.VendaService;

@Service
public class VendaServiceImpl implements VendaService {
    private VendaRepository vendaRepository;

    @Override
    public Long registrarVenda(VendaDTO vendaDTO) {
        return null;
    }

    @Override
    public Page<VendaDTO> encontrarTodos(Pageable pageable) {
        return null;
    }

    @Override
    public VendaDTO encontrarCadastro(Long id) {
        return null;
    }

    @Override
    public void apagarCadastro(Long id) {

    }
}
