package org.kitfox.springboot.sfgpetclinic.services.springdatajpa;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kitfox.springboot.sfgpetclinic.model.Speciality;
import org.kitfox.springboot.sfgpetclinic.repositories.SpecialtyRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock(lenient = true)
    private SpecialtyRepository specialtyRepository;

    @InjectMocks
    private SpecialitySDJpaService specialitySDJpaService;

    @Test
    void deleteByIdAtLeast() {
        // Given

        // when
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        // Then
        then(specialtyRepository).should(atLeastOnce()).deleteById(1L);
        //verify(specialtyRepository, atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByIdAtMost() {
        // Given

        // when
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        //Then
        then(specialtyRepository).should(atMost(5)).deleteById(1L);
        //verify(specialtyRepository, atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByTimes() {
        // Given - none

        // When
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(anyLong());

        // Then
        then(specialtyRepository).should(times(2)).deleteById(anyLong());
        //verify(specialtyRepository, times(2)).deleteById(anyLong());
    }


    @Test
    void deleteByObject() {
        // Given
        Speciality speciality = new Speciality();

        // When
        specialitySDJpaService.delete(speciality);

        // Then
        then(specialtyRepository).should(atLeastOnce()).delete(any(Speciality.class));
        //verify(specialtyRepository).delete(any(Speciality.class));
    }

    @Test
    void deleteByIdNever() {
        // when
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        //Then
        then(specialtyRepository).should(times(2)).deleteById(1L);
        then(specialtyRepository).should(never()).deleteById(5L);

    }

    @Test
    void delete() {
        // when
        specialitySDJpaService.delete(new Speciality());

        // then
        then(specialtyRepository).should().delete(any());
    }

    @Test
    void findById() {
        //Given
        Speciality speciality = new Speciality();

        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));

        //When
        Speciality found = specialitySDJpaService.findById(1L);

        //Then
        assertThat(found).isNotNull();

        then(specialtyRepository).should(times(1)).findById(1L);
        //then(specialtyRepository).should().findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteThrow() {
        doThrow(new RuntimeException("boom")).when(specialtyRepository).delete(any(Speciality.class));

        assertThrows(RuntimeException.class, () -> specialitySDJpaService.delete(new Speciality()));

        verify(specialtyRepository).delete(any(Speciality.class));
    }

    @Test
    void findThrowBDD() {
        //Given
        given(specialtyRepository.findById(1L)).willThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> specialitySDJpaService.findById(1L));

        then(specialtyRepository).should().findById(1L);
    }

    @Test
    void deleteThrowBDD() {
        //Given
        willThrow(new RuntimeException("boom")).given(specialtyRepository).delete(any(Speciality.class));

        assertThrows(RuntimeException.class, () -> specialitySDJpaService.delete(new Speciality()));

        then(specialtyRepository).should().delete(any(Speciality.class));
    }

    @Test
    void saveLambda() {
        //Given
        final String MATCH = "MATCH";
        Speciality speciality = new Speciality();
        speciality.setDescription(MATCH);

        Speciality savedSpeciality = new Speciality();
        savedSpeciality.setId(1L);

        given(specialtyRepository.save(
                argThat(arg -> arg.getDescription().equals(MATCH))
        )).willReturn(savedSpeciality);

        //When
        Speciality result = specialitySDJpaService.save(speciality);

        //Then
        assertThat(result.getId()).isEqualTo(1L);

    }

    @Test
    void saveLambdaNotMatch() {
        //Given
        final String MATCH = "MATCH";
        Speciality speciality = new Speciality();
        speciality.setDescription("NOT");

        Speciality savedSpeciality = new Speciality();
        savedSpeciality.setId(1L);

        given(specialtyRepository.save(
                argThat(arg -> arg.getDescription().equals(MATCH))
        )).willReturn(savedSpeciality);

        //When
        Speciality result = specialitySDJpaService.save(speciality);

        //Then
        assertNull(result);
    }

//    @Test
//    void findById() {
//        Speciality speciality = new Speciality();
//        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));
//
//        Speciality found = specialitySDJpaService.findById(1L);
//
//        assertThat(found).isNotNull();
//
//        verify(specialtyRepository).findById(1L);
//    }
}