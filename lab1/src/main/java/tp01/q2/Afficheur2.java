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
        throw new RuntimeException("TODO: implement me!");
    }

}
