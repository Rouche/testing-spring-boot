package org.kitfox.springboot.sfgpetclinic.controllers;

import org.kitfox.springboot.sfgpetclinic.fauxspring.Model;
import org.kitfox.springboot.sfgpetclinic.services.VetService;

public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    public String listVets(Model model) {

        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }
}
