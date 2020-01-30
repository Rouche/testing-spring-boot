package org.kitfox.springboot.sfgpetclinic.services.springdatajpa;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kitfox.springboot.sfgpetclinic.model.Visit;
import org.kitfox.springboot.sfgpetclinic.repositories.VisitRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test VisitSDJpaService")
class VisitSDJpaServiceTest {

    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    private VisitSDJpaService visitSDJpaService;

    @Test
    @DisplayName("Method find all working")
    void findAll() {
        //Given
        List<Visit> visits = List.of(
                Visit.builder().description("Visit1").build(),
                Visit.builder().description("Visit2").build()
        );
        //When
        when(visitRepository.findAll()).thenReturn(visits);
        Set<Visit> result = visitSDJpaService.findAll();

        //Then
        verify(visitRepository).findAll();
        assertThat(result).hasSize(2);
    }

    @Test
    void findById() {
        //Given
        Visit visit = Visit.builder().description("Visit1").build();

        //When
        when(visitRepository.findById(1L)).thenReturn(Optional.of(visit));
        Visit result = visitSDJpaService.findById(1L);

        //Then
        verify(visitRepository).findById(1L);
        assertNotNull(result);
        assertEquals("Visit1", result.getDescription());
    }

    @Test
    void save() {
        //Given
        Visit visit = Visit.builder().description("Visit1").build();
        Visit visitSaved = Visit.builder().description("Visit1").build();
        visitSaved.setId(1L);

        //When
        when(visitRepository.save(visit)).thenReturn(visit);
        Visit result = visitSDJpaService.save(visit);

        //Then
        verify(visitRepository).save(visit);
        assertNotNull(result);
        assertEquals(visit, result);
    }

    @Test
    void testDeleteByObject() {
        //Given
        Visit visit = Visit.builder().description("Visit1").build();

        //When
        visitSDJpaService.delete(visit);

        //Then
        verify(visitRepository).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        //Given
        //When
        visitSDJpaService.deleteById(1L);
        visitSDJpaService.deleteById(1L);
        //Then
        verify(visitRepository, atLeastOnce()).deleteById(1L);
    }
}