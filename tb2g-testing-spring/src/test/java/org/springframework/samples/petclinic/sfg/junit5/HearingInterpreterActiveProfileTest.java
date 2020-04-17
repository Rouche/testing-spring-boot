package org.springframework.samples.petclinic.sfg.junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.samples.petclinic.sfg.HearingInterpreter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ROUCHE_DOCS: Component scan also scans the test directory with config. So it brings multiple beans created with @Configuration.
 * @see org.springframework.samples.petclinic.sfg.BaseConfig
 * Because of that we have to profile everything wich is annoying.
 * IMO, never use package scan in a test... simple. use classes=""
 */
@ActiveProfiles("yanny")
@SpringJUnitConfig(classes = {HearingInterpreterActiveProfileTest.TestConfig.class})
public class HearingInterpreterActiveProfileTest {

    @Profile("yanny")
    @Configuration
    @ComponentScan("org.springframework.samples.petclinic.sfg")
    static class TestConfig {
    }

    @Autowired
    private HearingInterpreter hearingInterpreter;

    @Test
    void whatIHeard() {
        String word = hearingInterpreter.whatIHeard();

        assertEquals("Yanny", word);
    }
}
