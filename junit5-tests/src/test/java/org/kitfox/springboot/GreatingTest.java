package org.kitfox.springboot;

import org.junit.jupiter.api.*;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple Greating.
 */
@Slf4j
public class GreatingTest {

    private Greating greating;

    @BeforeAll
    public static void beforeAll() {
        log.debug("Before Class");
    }

    @BeforeEach
    void setUp() {

        log.debug("In Before Each...");

        greating = new Greating();
    }

    @Test
    void helloWorld() {

        String result = greating.helloWorld();

        assertEquals("HELLO WORLD", result);
    }

    @Test
    void helloWorld1() {

        String result = greating.helloWorld("ME");

        assertEquals("HELLO ME", result);
    }

    @AfterEach
    void afterEach() {
        log.debug("In After Each");
    }

    @AfterAll
    public static void afterAll() {
        log.debug("In After All");
    }
}
