package org.kitfox.springboot;

/**
 * Hello world!
 */
public class Greating {

    public static final String HELLO = "HELLO";
    public static final String WORLD = "WORLD";

    public String helloWorld() {
        return HELLO + " " + WORLD;
    }

    public String helloWorld(String name) {
        return HELLO + " " + name;
    }

}
