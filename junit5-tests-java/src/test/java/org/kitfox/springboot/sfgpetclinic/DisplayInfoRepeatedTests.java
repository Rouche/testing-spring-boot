package org.kitfox.springboot.sfgpetclinic;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface DisplayInfoRepeatedTests {

    /**
     * 68: Tesi information
     * @param testInfo test information injected
     * @param reporter reporter injected
     */
    @BeforeEach
    default void beforeEachConsole(TestInfo testInfo, RepetitionInfo repetitionInfo, TestReporter reporter) {
        System.out.println("Class: " + testInfo.getTestClass().get().toString() + " current repetition: [" + repetitionInfo.getCurrentRepetition() + "] reporter: " + reporter.toString());
    }
}
