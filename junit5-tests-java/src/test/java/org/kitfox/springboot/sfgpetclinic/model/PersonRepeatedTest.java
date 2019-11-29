package org.kitfox.springboot.sfgpetclinic.model;

import org.junit.jupiter.api.*;
import org.kitfox.springboot.sfgpetclinic.DisplayInfoRepeatedTests;
import org.kitfox.springboot.sfgpetclinic.ModelTests;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag("repeated")
class PersonRepeatedTest implements ModelTests, DisplayInfoRepeatedTests {

    @RepeatedTest(value = 10, name = RepeatedTest.DISPLAY_NAME_PLACEHOLDER + " " + RepeatedTest.CURRENT_REPETITION_PLACEHOLDER + " of " + RepeatedTest.TOTAL_REPETITIONS_PLACEHOLDER)
    @DisplayName("My Repeated Test!")
    void myRepeatTest() {
        // blabla
    }

    @RepeatedTest(5)
    void myRepeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        log.info("{} : {}", testInfo.getDisplayName(), repetitionInfo.getCurrentRepetition());
    }

    /**
     * 68: New Repeated test with display name placeholder
     */
    @RepeatedTest(value = 5, name = "{displayName} => {currentRepetition} / {totalRepetitions}")
    @DisplayName("68: Repeated Test Assignment")
    void myRepeatedTestAssignment() {

    }
}