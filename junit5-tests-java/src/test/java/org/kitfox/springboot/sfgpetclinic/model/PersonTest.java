package org.kitfox.springboot.sfgpetclinic.model;

import org.junit.jupiter.api.Test;
import org.kitfox.springboot.sfgpetclinic.ModelTests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest implements ModelTests {

    @Test
    void groupedAssertions() {
        //given
        Person person = new Person(1L, "Joe", "Buck");

        //then
        assertAll("Test Props Set",
                () -> assertEquals(person.getFirstName(), "Joe"),
                () -> assertEquals(person.getLastName(), "Buck"));
    }

    @Test
    void groupedAssertionsMsgs() {
        //given
        Person person = new Person(1L, "Joe", "Buck");

        //then
        assertAll("Test Props Set Msgs",
                () -> assertEquals(person.getFirstName(), "Joe", "First name failed"),
                () -> assertEquals(person.getLastName(), "Buck"));
    }
}