package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import tech.antoniosgarbi.gestorpeixaria.Builder;
import tech.antoniosgarbi.gestorpeixaria.dto.model.CollaboratorDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.CollaboratorSpecBody;
import tech.antoniosgarbi.gestorpeixaria.exception.PersonException;
import tech.antoniosgarbi.gestorpeixaria.model.Collaborator;
import tech.antoniosgarbi.gestorpeixaria.repository.CollaboratorRepository;
import tech.antoniosgarbi.gestorpeixaria.specification.CollaboratorSpecification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CollaboratorServiceTest {
    @Mock
    CollaboratorRepository collaboratorRepository;
    @InjectMocks
    CollaboratorServiceImpl underTest;

    @Test
    @DisplayName("Should return Long when receives CollaboratorDTO with an unregistered document")
    void register0() {
        Collaborator expected = Builder.collaborator1();
        when(collaboratorRepository.findByDocument(anyString())).thenReturn(Optional.empty());
        when(collaboratorRepository.save(any(Collaborator.class))).thenReturn(expected);

        long response = underTest.register(Builder.collaboratorDTO1());

        assertEquals(expected.getId(), response);
    }

    @Test
    @DisplayName("Throws PersonException when receives CollaboratorDTO with document already registered")
    void register1() {
        CollaboratorDTO dto = Builder.collaboratorDTO1();

        when(collaboratorRepository.findByDocument(anyString())).thenReturn(Optional.of(Builder.collaborator1()));

        var exception =
                assertThrows(PersonException.class, () -> underTest.register(dto));

        String expectedMessage = "O documento informado já possui cadastro no sistema!";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Throws PersonException when updating an object that doesn't exist")
    void update0() {
        when(collaboratorRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception =
                assertThrows(PersonException.class, () -> underTest.update(Builder.collaboratorDTO1()));

        String expectedMessage = "Cadastro não encontrado";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Should return CollaboratorDTO when updating valid CollaboratorDTO")
    void update1() {
        Collaborator expected = Builder.collaborator1();
        when(collaboratorRepository.findById(anyLong())).thenReturn(Optional.of(expected));

        String newName = "new name";
        expected.setName(newName);
        when(collaboratorRepository.save(any(Collaborator.class))).thenReturn(expected);

        CollaboratorDTO argument = Builder.collaboratorDTO1();
        argument.setName(newName);
        CollaboratorDTO response = underTest.update(argument);

        assertEquals(newName, response.getName());
    }

    @Test
    @DisplayName("Should return CollaboratorDTO when receiving a valid id")
    void findById0() {
        CollaboratorDTO collaboratorDTO = Builder.collaboratorDTO1();
        when(collaboratorRepository.findById(anyLong())).thenReturn(Optional.of(new Collaborator(collaboratorDTO)));
        CollaboratorDTO response = underTest.findById(1L);

        assertNotNull(response);
        assertEquals(collaboratorDTO.getId(), response.getId());
    }

    @Test
    @DisplayName("Throws PersonException when id doesn't exist")
    void findById1() {
        when(collaboratorRepository.findById(anyLong())).thenReturn(Optional.empty());
        var exception = assertThrows(PersonException.class, () -> underTest.findById(1L));

        String expectedMessage = "Cadastro não encontrado";
        assertEquals(expectedMessage, exception.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("Should return Page<CollaboratorDTO> when receives Pageable")
    void findAll0() {
        List<Collaborator> collaboratorList = List.of(Builder.collaborator1(), Builder.collaborator1(), Builder.collaborator1());
        Page<Collaborator> collaboratorPage = new PageImpl<>(collaboratorList);
        when(collaboratorRepository.findAll(any(Pageable.class))).thenReturn(collaboratorPage);

        Page<CollaboratorDTO> response = underTest.findAll(Pageable.unpaged());

        assertNotNull(response.getContent());
        assertEquals(collaboratorPage.getTotalElements(), response.getTotalElements());
        assertEquals(collaboratorPage.getContent().get(0).getId(), response.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Should return Page<CollaboratorDTO> when receives CollaboratorSpecBody and Pageable")
    void findAll1() {
        List<Collaborator> collaboratorList = List.of(Builder.collaborator1(), Builder.collaborator1(), Builder.collaborator1());
        Page<Collaborator> collaboratorPage = new PageImpl<>(collaboratorList);
        when(collaboratorRepository.findAll(any(CollaboratorSpecification.class), any(Pageable.class))).thenReturn(collaboratorPage);

        Page<CollaboratorDTO> response = underTest.findAll(new CollaboratorSpecBody(), Pageable.unpaged());

        assertNotNull(response.getContent());
        assertEquals(collaboratorPage.getTotalElements(), response.getTotalElements());
        assertEquals(collaboratorPage.getContent().get(0).getId(), response.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Throws PersonException when deleting id that doesn't exist")
    void delete0() {
        when(collaboratorRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception = assertThrows(PersonException.class, () -> underTest.delete(1L));

        String expectedMessage = "Cadastro não encontrado";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Should update Collaborator with excluded true when receives id that exists")
    void delete1() {
        Collaborator collaborator = Builder.collaborator1();
        when(collaboratorRepository.findById(anyLong())).thenReturn(Optional.of(collaborator));

        Assertions.assertThatCode(() -> underTest.delete(1L))
                .doesNotThrowAnyException();
    }

}