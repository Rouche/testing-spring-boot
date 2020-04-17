package org.springframework.samples.petclinic.sfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Jean-Francois Larouche (rouche) on 2020-03-06
 */
@Profile("base-test")
@Configuration
public class YannyConfig {
    @Bean
    public WordProducer yannyWordProducer() {
        return new YannyWordProducer();
    }
}
