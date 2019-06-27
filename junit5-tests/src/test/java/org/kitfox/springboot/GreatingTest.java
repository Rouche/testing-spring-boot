package org.kitfox.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple Greating.
 */
@Slf4j
public class GreatingTest {

    private Greating greating;

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
}
