package org.kitfox.springboot.sfgpetclinic.model;

import java.io.InputStream;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Jean-Francois Larouche (rouche) on 12/4/2019
 *
 * Used to convert a JSON file name to its corresponding Java type parameter in JUnit 5.
 *
 * Usage:
 *
 * <pre>
 *     // One parameter, repeated test
 *      &#64;ParameterizedTest(name = "{displayName} => [{index}] {arguments}")
 *     &#64;ValueSource(strings = {"/myrepetition1.json", "/myrepetition2.json"})
 *     void oneParameterTest(&#64;ConvertWith(JsonFileConverter.class) MyObject val) {
 *         ...
 *     }
 *
 *     // Two parameters
 *     &#64;ParameterizedTest(name = "{displayName} => [{index}] {arguments}")
 *     &#64;CsvSource({"/myparameter1.json, /myparamegter2.json"})
 *     void twoParametersTest(&#64;ConvertWith(JsonFileConverter.class) MyObject val, &#64;ConvertWith(JsonFileConverter.class) MyObject val2) {
 *         ...
 *     }
 * </pre>
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
