package org.kitfox.springboot.sfgpetclinic.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
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

    @RepeatedTest(value = 10, name = RepeatedTest.DISPLAY_NAME_PLACEHOLDER + " " + RepeatedTest.CURRENT_REPETITION_PLACEHOLDER + " of " + RepeatedTest.TOTAL_REPETITIONS_PLACEHOLDER)
    @DisplayName("My Repeated Test!")
    @Test
    void myRepeatTest() {
        // blabla
    }
}