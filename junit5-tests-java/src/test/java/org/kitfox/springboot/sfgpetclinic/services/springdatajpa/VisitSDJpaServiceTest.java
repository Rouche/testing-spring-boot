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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test VisitSDJpaService")
class VisitSDJpaServiceTest {

    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    private VisitSDJpaService visitSDJpaService;

    @DisplayName("Method find all working")
    @Test
    void findAll() {
        //Given
        List<Visit> visits = List.of(
                Visit.builder().description("Visit1").build(),
                Visit.builder().description("Visit2").build()
        );
        given(visitRepository.findAll()).willReturn(visits);

        //When
        //when(visitRepository.findAll()).thenReturn(visits);
        Set<Visit> result = visitSDJpaService.findAll();

        //Then
        //verify(visitRepository).findAll();
        then(visitRepository).should().findAll();
        assertThat(result).hasSize(2);
    }

    @Test
    void findById() {
        //Given
        Visit visit = Visit.builder().description("Visit1").build();
        given(visitRepository.findById(1L)).willReturn(Optional.of(visit));

        //When
        Visit result = visitSDJpaService.findById(1L);

        //Then
        then(visitRepository).should().findById(1L);
        assertNotNull(result);
        assertEquals("Visit1", result.getDescription());
    }

    @Test
    void save() {
        //Given
        Visit visit = Visit.builder().description("Visit1").build();
        given(visitRepository.save(visit)).willReturn(visit);

        //When
        Visit result = visitSDJpaService.save(visit);

        //Then
        then(visitRepository).should().save(visit);
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
        then(visitRepository).should().delete(any(Visit.class));
        //verify(visitRepository).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        //Given - none
        //When
        visitSDJpaService.deleteById(1L);
        visitSDJpaService.deleteById(1L);
        //Then
        then(visitRepository).should(atLeastOnce()).deleteById(1L);
        //verify(visitRepository, atLeastOnce()).deleteById(1L);
    }
}