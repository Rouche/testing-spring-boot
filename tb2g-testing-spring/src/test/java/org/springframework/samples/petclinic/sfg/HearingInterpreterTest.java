package org.springframework.samples.petclinic.sfg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("base-test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BaseConfig.class, LaurelConfig.class})
@Slf4j
public class HearingInterpreterTest {

    @Autowired
    private HearingInterpreter hearingInterpreter;

// Replaced by @RunWith(SpringRunner.class)
//    @Before
//    public void setUp() throws Exception {
//        hearingInterpreter = new HearingInterpreter(new LaurelWordProducer());
//    }

    @Test
    public void whatIHear() {
        String word = hearingInterpreter.whatIHeard();

        assertEquals("Laurel", word);
    }
}