package org.kitfox.springboot.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertTrue("notimplemented".equals(indexController.oupsHandler()), () -> "This is some expensive" +
                " message to build" + " during test");
    }
}