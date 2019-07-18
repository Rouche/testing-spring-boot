package org.kitfox.springboot.sfgpetclinic.controllers;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;
import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


@Slf4j
class IndexControllerTest {

    IndexController indexController;

    @BeforeEach
    void setUp() {
        indexController = new IndexController();
    }

    @DisplayName("Test proper view name returnde for index page")
    @Test
    void index() {
        assertEquals("index", indexController.index());
        assertEquals("index", indexController.index(), "Wrong View Returned");

        assertThat(indexController.index()).isEqualTo("index");

    }

    @Test
    @DisplayName("Test Exception different annotation order")
    void oupsHandler() {
        assertThrows(ValueNotFoundException.class, () -> indexController.oupsHandler());
//        assertTrue("notimplemented".equals(indexController.oupsHandler()), () -> "This is some expensive" +
//                " message to build" + " during test");
    }

    @Disabled("Demo of timeout")
    @Test
    void testTimeout() {
        assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(2000);

            log.error("Im here");
        });
    }

    @Disabled("Demo of timeout Preemptively")
    @Test
    void testTimeoutPreemptively() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            Thread.sleep(2000);

            log.error("Im here Preemptively");
        });
    }

    @Test
    void testAssumptionTrue() {
        assumeTrue("ZOMG".equals(System.getenv("ZOMG_RUNTIME")));
    }

    @Test
    void testAssumptionTrueIsTrue() {
        assumeTrue("ZOMG".equals("ZOMG"));
    }

    /**
     * https://junit.org/junit5/docs/current/user-guide/#writing-tests-conditional-execution
     */
    @Test
    @EnabledOnOs(OS.MAC)
    void testMeOnMacOS() {
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testMeOnMacWindows() {
    }

    @Test
    @EnabledOnJre(JRE.JAVA_12)
    void testMeOnMacJava11() {
    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void testMeOnMacJava8() {
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "USERNAME", matches = "zomfg")
    void testMeIfUserZomg() {
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "USERNAME", matches = "jealar2")
    void testMeIfUserLarj() {
    }
}