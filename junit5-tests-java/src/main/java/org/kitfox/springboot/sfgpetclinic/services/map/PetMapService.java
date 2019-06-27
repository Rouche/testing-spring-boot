package org.kitfox.springboot.sfgpetclinic.services.map;

import java.util.Set;

import org.kitfox.springboot.sfgpetclinic.model.Pet;
import org.kitfox.springboot.sfgpetclinic.services.PetService;


public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
