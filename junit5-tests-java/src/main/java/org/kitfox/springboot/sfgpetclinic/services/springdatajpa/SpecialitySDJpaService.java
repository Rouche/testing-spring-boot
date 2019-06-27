package org.kitfox.springboot.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.kitfox.springboot.sfgpetclinic.model.Speciality;
import org.kitfox.springboot.sfgpetclinic.repositories.SpecialtyRepository;
import org.kitfox.springboot.sfgpetclinic.services.SpecialtyService;


public class SpecialitySDJpaService implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialitySDJpaService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialtyRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public Speciality findById(Long aLong) {
        return specialtyRepository.findById(aLong).orElse(null);
    }

    @Override
    public Speciality save(Speciality object) {
        return specialtyRepository.save(object);
    }

    @Override
    public void delete(Speciality object) {
        specialtyRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        specialtyRepository.deleteById(aLong);
    }
}
