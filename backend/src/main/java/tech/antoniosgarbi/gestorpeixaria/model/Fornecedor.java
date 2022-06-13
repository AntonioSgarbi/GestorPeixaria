package tech.antoniosgarbi.gestorpeixaria.model;

import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.dto.model.FornecedorDTO;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Fornecedor extends Pessoa {
    public Fornecedor(FornecedorDTO dto) {
        super(
                dto.getId(),
                dto.getDocumento(),
                dto.getNome(),
                dto.getPessoaTipo(),
                dto.getTelefones(),
                dto.getEmail(),
                false
        );
    }
}