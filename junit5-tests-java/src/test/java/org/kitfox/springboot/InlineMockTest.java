package org.kitfox.springboot;

import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * @author Jean-Francois Larouche (rouche) on 12/4/2019
 */
public class InlineMockTest {

    @Test
    void testInlineMock() {
        Map map = mock(Map.class);

        assertEquals(0, map.size());
    }
}
