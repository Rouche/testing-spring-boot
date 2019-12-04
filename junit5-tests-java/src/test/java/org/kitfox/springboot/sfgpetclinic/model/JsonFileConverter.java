package org.kitfox.springboot.sfgpetclinic.model;

import java.io.InputStream;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Jean-Francois Larouche (rouche) on 12/4/2019
 */
public class JsonFileConverter implements ArgumentConverter {

    @Override
    public Object convert(Object o, ParameterContext parameterContext) throws ArgumentConversionException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        try (InputStream stream = this.getClass().getResourceAsStream((String) o)) {
            if(stream == null) {
                throw new RuntimeException("Impossible to read file: [" + o +"]");
            }
            return mapper.readValue(stream, parameterContext.getParameter().getType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
