package org.kitfox.springboot.sfgpetclinic.controllers;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kitfox.springboot.sfgpetclinic.fauxspring.Model;
import org.kitfox.springboot.sfgpetclinic.fauxspring.ModelMapImpl;
import org.kitfox.springboot.sfgpetclinic.model.Vet;
import org.kitfox.springboot.sfgpetclinic.services.SpecialtyService;
import org.kitfox.springboot.sfgpetclinic.services.VetService;
import org.kitfox.springboot.sfgpetclinic.services.map.SpecialityMapService;
import org.kitfox.springboot.sfgpetclinic.services.map.VetMapService;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VetControllerTest {

    VetService vetService;
    SpecialtyService specialtyService;

    VetController vetController;

    @BeforeEach
    void setUp() {
        specialtyService = new SpecialityMapService();
        vetService = new VetMapService(specialtyService);

        vetController = new VetController(vetService);
    }

    @Test
    void listVets() {
        //Given
        Model model = new ModelMapImpl();

        // When
        String result = vetController.listVets(model);

        //Then
        assertEquals("vets/index", result);
    }

    @Test
    void listVetsTwo() {

        //Given
        ModelMapImpl model = new ModelMapImpl();
        Vet vet = new Vet(1l, "Joe", "Shmoe", null);
        vetService.save(vet);
        vet = new Vet(2l, "Barb", "Wire", null);
        vetService.save(vet);

        // When
        String result = vetController.listVets(model);

        //Then
        assertEquals(2, ((Set<Vet>) model.getAttribute("vets")).size());
    }
}