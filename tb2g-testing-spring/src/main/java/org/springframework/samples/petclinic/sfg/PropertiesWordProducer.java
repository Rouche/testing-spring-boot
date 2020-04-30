package org.springframework.samples.petclinic.sfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Jean-Francois Larouche (resolutech) on 2020-04-29
 */
@Component
@Profile("externalized")
@Primary
public class PropertiesWordProducer implements WordProducer {

    private final String word;

    public PropertiesWordProducer(@Value("${say.word}") String word) {
        this.word = word;
    }

    @Override
    public String getWord() {
        return word;
    }
}
