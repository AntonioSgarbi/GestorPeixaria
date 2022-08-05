package tech.antoniosgarbi.gestorpeixaria.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import tech.antoniosgarbi.gestorpeixaria.Builder;
import tech.antoniosgarbi.gestorpeixaria.dto.model.CollaboratorDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.CollaboratorSpecBody;
import tech.antoniosgarbi.gestorpeixaria.exception.PersonException;
import tech.antoniosgarbi.gestorpeixaria.service.contract.CollaboratorService;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CollaboratorControllerTest {
    @Mock
    private CollaboratorService collaboratorService;
    @InjectMocks
    private CollaboratorController underTest;

    @Test
    @DisplayName("Should return 200<id> when register success")
    void register0() {
        when(collaboratorService.register(any(CollaboratorDTO.class))).thenReturn(1L);
        ResponseEntity<Long> response = underTest.register(new CollaboratorDTO());
        assertEquals(1L, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Throws PersonException when register fails")
    void register1() {
        PersonException expected = new PersonException("PersonException");
        when(collaboratorService.register(any(CollaboratorDTO.class))).thenThrow(expected);
        var result = assertThrows(PersonException.class, () -> underTest.register(new CollaboratorDTO()));
        assertEquals(expected.getMessage(), result.getMessage());

    }

    @Test
    @DisplayName("Should return 202<void> when update success")
    void update0() {
        when(collaboratorService.update(any(CollaboratorDTO.class))).thenReturn(new CollaboratorDTO());
        ResponseEntity<Void> response = underTest.update(new CollaboratorDTO());
        assertNull(response.getBody());
        assertEquals(202, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Throws PersonException when update fails")
    void update1() {
        PersonException expected = new PersonException("PersonException");
        when(collaboratorService.update(any(CollaboratorDTO.class))).thenThrow(expected);
        var result = assertThrows(PersonException.class, () -> underTest.update(new CollaboratorDTO()));
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    @DisplayName("Should return 200 CollaboratorDTO when findById success")
    void findById0() {
        CollaboratorDTO expected = Builder.collaboratorDTO1();
        when(collaboratorService.findById(any(Long.class))).thenReturn(expected);
        ResponseEntity<CollaboratorDTO> response = underTest.findById(1L);
        assertEquals(expected, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Throws PersonException when findById fails")
    void findById1() {
        PersonException expected = new PersonException("PersonException");
        when(collaboratorService.findById(any(Long.class))).thenThrow(expected);
        var result = assertThrows(PersonException.class, () -> underTest.findById(1L));
        assertEquals(expected.getMessage(), result.getMessage());
    }

    @Test
    @DisplayName("Should return 200 Page<CollaboratorDTO> when findAll receives Pageable")
    void findAll0() {
        CollaboratorDTO expected0 = Builder.collaboratorDTO1();
        CollaboratorDTO expected1 = CollaboratorDTO.builder().wage(2000.0).build();

        Page<CollaboratorDTO> expected = new PageImpl<CollaboratorDTO>(List.of(expected0, expected1));
        when(collaboratorService.findAll(any(Pageable.class))).thenReturn(expected);
        Pageable pageable = Pageable.unpaged();
        ResponseEntity<Page<CollaboratorDTO>> response = underTest.findAll(pageable);
        assertEquals(expected, response.getBody());
        assertEquals(2, Objects.requireNonNull(response.getBody()).getTotalElements());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Should return 200 Page<CollaboratorDTO> empty when findAll receives Pageable")
    void findAll1() {
        Page<CollaboratorDTO> expected = new PageImpl<>(List.of());
        when(collaboratorService.findAll(any(Pageable.class))).thenReturn(expected);
        Pageable pageable = Pageable.unpaged();
        ResponseEntity<Page<CollaboratorDTO>> response = underTest.findAll(pageable);
        assertEquals(expected, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Should return 200 Page<CollaboratorDTO> when findAll receives CollaboratorSpecBody and Pageable")
    void testFindAll2() {
        List<CollaboratorDTO> collaboratorList = List.of(Builder.collaboratorDTO1(), Builder.collaboratorDTO1(), Builder.collaboratorDTO1());
        Page<CollaboratorDTO> collaboratorPage = new PageImpl<>(collaboratorList);
        when(collaboratorService.findAll(any(CollaboratorSpecBody.class), any(Pageable.class))).thenReturn(collaboratorPage);

        ResponseEntity<Page<CollaboratorDTO>> response = underTest.findAll(new CollaboratorSpecBody(), Pageable.unpaged());

        assertNotNull(response.getBody().getContent());
        assertEquals(3, response.getBody().getContent().size());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(collaboratorPage.getTotalElements(), response.getBody().getTotalElements());
        assertEquals(collaboratorPage.getContent().get(0).getId(), response.getBody().getContent().get(0).getId());
    }

    @Test
    @DisplayName("Should return 204 null when delete receives id")
    void delete0() {
        ResponseEntity<Void> response = underTest.delete(1L);
        verify(collaboratorService).delete(any(Long.class));
        assertEquals(204, response.getStatusCodeValue());
    }

}