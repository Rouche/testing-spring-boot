package org.kitfox.springboot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple Greating.
 */
public class GreatingTest {

    @Test
    void helloWorld() {
        Greating greating = new Greating();

        String result = greating.helloWorld();

        assertEquals("HELLO WORLD", result);
    }

    @Test
    void helloWorld1() {
        Greating greating = new Greating();

        String result = greating.helloWorld("ME");

        assertEquals("HELLO ME", result);
    }
}
