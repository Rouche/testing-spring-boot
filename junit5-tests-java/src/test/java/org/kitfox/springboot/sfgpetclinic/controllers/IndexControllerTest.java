package org.kitfox.springboot.sfgpetclinic.controllers;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;

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
}