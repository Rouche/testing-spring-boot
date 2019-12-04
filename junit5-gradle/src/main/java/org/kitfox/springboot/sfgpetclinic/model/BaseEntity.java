package org.kitfox.springboot.sfgpetclinic.model;

import java.io.Serializable;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class BaseEntity implements Serializable {

    private Long id;

    public boolean isNew() {
        return this.id == null;
    }
}
