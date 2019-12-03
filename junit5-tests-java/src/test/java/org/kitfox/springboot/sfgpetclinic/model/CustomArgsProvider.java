package org.kitfox.springboot.sfgpetclinic.model;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

/**
 * @author Jean-Francois Larouche (rouche) on 12/3/2019
 */
public class CustomArgsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(Arguments.of("CU", 1, 2), Arguments.of("ST", 2, 3), Arguments.of("OM", 4, 5));
    }
}
