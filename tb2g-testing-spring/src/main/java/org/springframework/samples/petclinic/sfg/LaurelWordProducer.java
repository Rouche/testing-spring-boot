package org.springframework.samples.petclinic.sfg;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Jean-Francois Larouche (rouche) on 2020-03-06
 */
@Component
//@Primary
@Profile("laurel")
public class LaurelWordProducer implements WordProducer {

    @Override
    public String getWord() {
        return "Laurel";
    }
}
