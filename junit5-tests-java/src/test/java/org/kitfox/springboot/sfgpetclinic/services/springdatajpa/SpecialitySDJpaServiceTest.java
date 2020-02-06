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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
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