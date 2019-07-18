package org.kitfox.springboot.sfgpetclinic.fauxspring;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jean-Francois Larouche (jealar2) on 2019-07-18
 */
public class ModelMapImpl implements Model {

    Map<String, Object> map = new HashMap<>();

    @Override
    public void addAttribute(String key, Object o) {
        map.put(key, o);
    }

    @Override
    public void addAttribute(Object o) {
        map.put(o.toString(), o);
    }

    @Override
    public Object getAttribute(String key) {
        return map.get(key);
    }
}
