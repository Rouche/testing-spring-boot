package org.kitfox.springboot.sfgpetclinic.fauxspring;

import java.text.ParseException;
import java.util.Locale;

import org.kitfox.springboot.sfgpetclinic.model.PetType;


public interface Formatter<T> {

    String print(PetType petType, Locale locale);

    PetType parse(String text, Locale locale) throws ParseException;
}
