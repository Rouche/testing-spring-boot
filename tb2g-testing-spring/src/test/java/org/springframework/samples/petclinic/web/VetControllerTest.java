package org.springframework.samples.petclinic.web;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.ui.ModelMap;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @InjectMocks
    private VetController vetController;

    @Mock
    private ClinicService clinicService;

    @Mock
    private ModelMap model;

    private List<Vet> vets;

    @BeforeEach
    void setUp() {
        Vet vet = new Vet();
        vet.setId(1);
        vets = Arrays.asList(vet);

        given(clinicService.findVets()).willReturn(vets);
    }

    @Test
    void showVetList() {
        // When
        String view = vetController.showVetList(model);

        // Then
        then(clinicService).should(times(1)).findVets();
        then(model).should(atLeastOnce()).put(anyString(), any(Object.class));
        assertThat(view).isEqualTo("vets/vetList");
    }

    @Test
    void showResourcesVetList() {
        // When
        Vets vets = vetController.showResourcesVetList();

        // Then
        then(clinicService).should(atLeastOnce()).findVets();
        assertThat(vets.getVetList()).isNotNull();
        assertThat(vets.getVetList()).hasSize(1);
    }
}