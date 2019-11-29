package org.kitfox.springboot.sfgpetclinic.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.kitfox.springboot.sfgpetclinic.ModelTests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OwnerTest implements ModelTests {

    @Test
    void dependantAssertions() {
        Owner owner = new Owner(1L, "Joe", "Shmoe");
        owner.setCity("Vancouver");
        owner.setTelephone("5551231212");

        assertAll("Properties Test",
                () -> assertAll("Person Properties",
                        () -> assertEquals("Joe", owner.getFirstName(), "First Name Did Not Match"),
                        () -> assertEquals("Shmoe", owner.getLastName())),
                () -> assertAll("Owner Properties",
                        () -> assertEquals("Vancouver", owner.getCity(), "City Did Not Match"),
                        () -> assertEquals("5551231212", owner.getTelephone())
                ));

        assertThat(owner.getCity(), is("Vancouver"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"70:", "My", "Parameterized", "Test"})
    void testValueSource(String val) {
        System.out.println(val);
    }
}