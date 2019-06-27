package org.kitfox.springboot.sfgpetclinic.controllers;

import javax.validation.Valid;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.kitfox.springboot.sfgpetclinic.fauxspring.BindingResult;
import org.kitfox.springboot.sfgpetclinic.fauxspring.Model;
import org.kitfox.springboot.sfgpetclinic.fauxspring.ModelMap;
import org.kitfox.springboot.sfgpetclinic.fauxspring.WebDataBinder;
import org.kitfox.springboot.sfgpetclinic.model.Owner;
import org.kitfox.springboot.sfgpetclinic.model.Pet;
import org.kitfox.springboot.sfgpetclinic.model.PetType;
import org.kitfox.springboot.sfgpetclinic.services.OwnerService;
import org.kitfox.springboot.sfgpetclinic.services.PetService;
import org.kitfox.springboot.sfgpetclinic.services.PetTypeService;

public class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    public Owner findOwner(Long ownerId) {
        return ownerService.findById(ownerId);
    }

    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    public String initCreationForm(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, ModelMap model) {
        if (StringUtils.length(pet.getName()) > 0 && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.getPets().add(pet);
        if (result.hasErrors()) {
            model.put("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            petService.save(pet);

            return "redirect:/owners/" + owner.getId();
        }
    }

    public String initUpdateForm(Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, Model model) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            owner.getPets().add(pet);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }
}
