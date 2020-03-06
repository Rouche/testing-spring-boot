package org.springframework.samples.petclinic.sfg;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jean-Francois Larouche (rouche) on 2020-03-06
 */
@Slf4j
@Service
public class HearingInterpreter {
    private final WordProducer wordProducer;

    public HearingInterpreter(WordProducer wordProducer) {
        this.wordProducer = wordProducer;
    }

    public String whatIHeard() {
        String word = wordProducer.getWord();

        log.info("I heard {}", word);

        return word;
    }
}
