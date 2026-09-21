package tp01.q1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Generic displayer: displays any object from its methods.
 * (Instead of printing, we return a string — much easier to test.)
 */
public class Afficheur {
    /**
     * Displays any object by introspection.
     *
     * @param o the object to display
     * @return sorted "property : value" pairs joined with " ; "
     */
    public String afficher(Object o) {
        try {
            Class<? extends Object> clazz = o.getClass();
            ArrayList<String> listeAAfficher = new ArrayList<>();
            for (Method m : clazz.getMethods()) {
                if (m.getName().startsWith("get") && m.getParameterCount() == 0 
                && ! m.getName().equals("getClass")) {
                    String nomPropriete = IntrospectionHelper.extraireNomDePropriete("get", m.getName());
                    listeAAfficher.add(nomPropriete + " : " + m.invoke(o));
                }
            }
            Collections.sort(listeAAfficher);
            return String.join(" ; ", listeAAfficher);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
