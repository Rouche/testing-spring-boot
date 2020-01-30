package org.kitfox.springboot.sfgpetclinic.model;

import java.time.LocalDate;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class Visit extends BaseEntity {

    private LocalDate date;
    private String description;
    private Pet pet;

    public Visit() {
        super(null);
    }

    public Visit(Long id) {
        super(id);
    }
}
