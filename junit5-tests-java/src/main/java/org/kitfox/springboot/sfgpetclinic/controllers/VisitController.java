package org.kitfox.springboot.sfgpetclinic.controllers;

import javax.validation.Valid;
import java.util.Map;

import org.kitfox.springboot.sfgpetclinic.fauxspring.BindingResult;
import org.kitfox.springboot.sfgpetclinic.fauxspring.WebDataBinder;
import org.kitfox.springboot.sfgpetclinic.model.Pet;
import org.kitfox.springboot.sfgpetclinic.model.Visit;
import org.kitfox.springboot.sfgpetclinic.services.PetService;
import org.kitfox.springboot.sfgpetclinic.services.VisitService;


public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    public Visit loadPetWithVisit(Long petId, Map<String, Object> model) {
        Pet pet = petService.findById(petId);
        model.put("pet", pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }

    public String initNewVisitForm(Long petId, Map<String, Object> model) {
        return "pets/createOrUpdateVisitForm";
    }

    public String processNewVisitForm(@Valid Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        } else {
            visitService.save(visit);

            return "redirect:/owners/{ownerId}";
        }
    }
}
