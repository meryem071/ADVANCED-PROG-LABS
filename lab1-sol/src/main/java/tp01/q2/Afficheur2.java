package tp01.q2;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

import tp01.q1.IntrospectionHelper;

/**
 * SOLUTION — generic displayer that prefers the @Label annotation.
 * Same loop as Afficheur, but if a getter carries @Label, the label value is
 * displayed instead of the Java property name.
 */
public class Afficheur2 {
    /**
     * Displays any object by introspection, preferring @Label values.
     *
     * @param o the object to display
     * @return sorted "label-or-property : value" pairs joined with " ; "
     */
    public String afficher(Object o) {
        try {
            Class<? extends Object> clazz = o.getClass();
            ArrayList<String> listeAAfficher = new ArrayList<>();
            for (Method m : clazz.getMethods()) {
                if (m.getName().startsWith("get") && m.getParameterCount() == 0
                        && !m.getName().equals("getClass")) {
                    String displayName;
                    if (m.isAnnotationPresent(Label.class)) {
                        displayName = m.getAnnotation(Label.class).value();
                    } else {
                        displayName = IntrospectionHelper.extraireNomDePropriete("get", m.getName());
                    }
                    listeAAfficher.add(displayName + " : " + m.invoke(o));
                }
            }
            Collections.sort(listeAAfficher);
            return String.join(" ; ", listeAAfficher);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
