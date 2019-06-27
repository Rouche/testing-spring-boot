package org.kitfox.springboot.sfgpetclinic.repositories;


import java.util.List;

import org.kitfox.springboot.sfgpetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
