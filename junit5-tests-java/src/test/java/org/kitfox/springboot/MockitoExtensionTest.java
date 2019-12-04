package org.kitfox.springboot;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Jean-Francois Larouche (rouche) on 12/4/2019
 */
@ExtendWith(MockitoExtension.class)
public class MockitoExtensionTest {

    @Mock
    Map map;

    @Test
    void testExtension() {
        assertEquals(0, map.size());
    }
}
