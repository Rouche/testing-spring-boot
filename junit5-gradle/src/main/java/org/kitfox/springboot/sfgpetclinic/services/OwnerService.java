package org.kitfox.springboot.sfgpetclinic.services;

import java.util.List;

import org.kitfox.springboot.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
