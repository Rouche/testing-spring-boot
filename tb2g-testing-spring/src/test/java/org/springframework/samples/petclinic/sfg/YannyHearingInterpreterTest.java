package org.springframework.samples.petclinic.sfg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("base-test")
@ContextConfiguration(classes = {BaseConfig.class, YannyConfig.class})
public class YannyHearingInterpreterTest {

    @Autowired
    private HearingInterpreter hearingInterpreter;

    @Test
    public void whatIHear() {
        String word = hearingInterpreter.whatIHeard();

        assertEquals("Yanny", word);
    }
}