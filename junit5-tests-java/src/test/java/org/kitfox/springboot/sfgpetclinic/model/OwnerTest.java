package org.kitfox.springboot.sfgpetclinic.model;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
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

    @DisplayName("Value source Test")
    @ParameterizedTest(name = "{displayName} => [{index}] {arguments}")
    @ValueSource(strings = {"70:", "My", "Parameterized", "Test"})
    void testValueSource(String val) {
        System.out.println(val);
    }

    @DisplayName("Enum source Test")
    @ParameterizedTest(name = "{displayName} => [{index}] {arguments}")
    @EnumSource(OwnerType.class)
    void enumTest(OwnerType ownerType) {
        System.out.println(ownerType);
    }

    @DisplayName("CSV input Test")
    @ParameterizedTest(name = "{displayName} => [{index}] {arguments}")
    @CsvSource({"FL, 1, 2", "QC, 2, 3", "ON, 4, 5"})
    void csvInputTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " " + val1 + " " + val2);
    }

    @DisplayName("CSV File input Test")
    @ParameterizedTest(name = "{displayName} => [{index}] {arguments}")
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
    void csvFileInputTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " " + val1 + " " + val2);
    }

    @DisplayName("Method provider input Test")
    @ParameterizedTest(name = "{displayName} => [{index}] {arguments}")
    @MethodSource("getArgs")
    void fromMethodTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " " + val1 + " " + val2);
    }
    static Stream<Arguments> getArgs() {
        return Stream.of(Arguments.of("ME", 1, 2), Arguments.of("TH", 2, 3), Arguments.of("OD", 4, 5));
    }

    @DisplayName("Custom provider input Test")
    @ParameterizedTest(name = "{displayName} => [{index}] {arguments}")
    @ArgumentsSource(CustomArgsProvider.class)
    void customProviderTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " " + val1 + " " + val2);
    }
}