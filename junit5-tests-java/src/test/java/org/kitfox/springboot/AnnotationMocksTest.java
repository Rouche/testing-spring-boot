package org.kitfox.springboot;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author Jean-Francois Larouche (rouche) on 12/4/2019
 */
public class AnnotationMocksTest {

    @Mock
    Map<String, Object> map;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void name() {
        map.put("key", "value");
    }
}
