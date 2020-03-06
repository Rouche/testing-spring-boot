package org.springframework.samples.petclinic.sfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jean-Francois Larouche (rouche) on 2020-03-06
 */
@Configuration
public class LaurelConfig {
    @Bean
    public WordProducer laurelWordProducer() {
        return new LaurelWordProducer();
    }
}
