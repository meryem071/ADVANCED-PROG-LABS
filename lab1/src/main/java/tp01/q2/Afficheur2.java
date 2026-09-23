package tp01.q2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

import tp01.q1.IntrospectionHelper;

/**
 * Generic displayer: displays any object from its methods.
 * (Instead of printing, we return a string — much easier to test.)
 * Unlike Afficheur, uses the @Label attached to a getter when present.
 */
public class Afficheur2 {
    /**
     * Displays any object by introspection, preferring @Label values.
     *
     * @param o the object to display
     * @return sorted "label-or-property : value" pairs joined with " ; "
     */
    public String afficher(Object o) {
        // TODO (Ex2): copy Afficheur.afficher, but if the getter carries
        // @Label, display the label value instead of the property name.
        try {
            Class<? extends Object> clazz = o.getClass();
            ArrayList<String> listeAAfficher = new ArrayList<>();
            for (Method m : clazz.getMethods()) {
                if (m.getName().startsWith("get") && m.getParameterCount() == 0 
                && ! m.getName().equals("getClass")) {
                    String nomPropriete = IntrospectionHelper.extraireNomDePropriete("get", m.getName());

                    if(m.isAnnotationPresent(Label.class)){
                        nomPropriete = m.getAnnotation(Label.class).value();
                    }

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
