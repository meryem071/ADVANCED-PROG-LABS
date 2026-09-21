package tp01.q1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Createur<T> {

    private Class<T> clazz;

    public Createur(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T creer() {
        try {
            // TODO (Q2): create an instance using the default constructor.
            throw new RuntimeException("TODO: implement me!");
        } catch (Exception e) {
            // Wrap any exception in an IntrospectionException.
            throw new IntrospectionException(e);
        }
    }

    /**
     * Sets a field value on the object, given the property name and the value
     * to assign. Calls the matching "setter".
     *
     * @param objet the object to modify
     * @param nomPropriete the property name (normally starts with a lowercase
     *                     letter);
     * @param valeur the value to assign.
     */
    public void setProprieteTexte(T objet, String nomPropriete, String valeur) {
        // TODO (Q3): find the String setter and invoke it.
        throw new RuntimeException("TODO: implement me!");
    }

    /**
     * Sets a field value on the object, given the property name and the value
     * to assign. Calls the matching "setter".
     *
     * @param objet the object to modify
     * @param nomPropriete the property name (normally starts with a lowercase
     *                     letter);
     * @param valeur the value to assign.
     */
    public <V> void setPropriete(T objet, String nomPropriete, V valeur, Class<? super V> clazzPropriete) {
        // TODO (Q4): same as above but with an explicit property type.
        throw new RuntimeException("TODO: implement me!");
    }

}
