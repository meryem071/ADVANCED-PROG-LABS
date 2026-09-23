package tp01.q1;

import java.lang.reflect.Method;

/**
 * SOLUTION — generic factory that creates objects and sets fields by
 * introspection, without knowing their class at compile time.
 * This mimics what frameworks like Spring/Hibernate do internally.
 */
public class Createur<T> {

    private Class<T> clazz;

    public Createur(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * Q2: creates an instance using the default (no-arg) constructor.
     * Equivalent to "new T()" which Java does not allow directly.
     */
    public T creer() {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            // Wrap any reflection exception in an IntrospectionException.
            throw new IntrospectionException(e);
        }
    }

    /**
     * Q3: sets a String property by calling the matching setter.
     * Example: setProprieteTexte(a, "rue", "r1") calls a.setRue("r1").
     *
     * @param objet the object to modify
     * @param nomPropriete the property name (normally starts with a lowercase letter)
     * @param valeur the value to assign
     */
    public void setProprieteTexte(T objet, String nomPropriete, String valeur) {
        try {
            String setterName = IntrospectionHelper.construireNomMethode("set", nomPropriete);
            Method setter = clazz.getMethod(setterName, String.class);
            setter.invoke(objet, valeur);
        } catch (Exception e) {
            throw new IntrospectionException(e);
        }
    }

    /**
     * Q4: generic version of Q3 — the caller also passes the property type.
     * Example: setPropriete(p, "anneeNaissance", 1995, Integer.TYPE) calls
     * p.setAnneeNaissance(1995). Note: primitive int -&gt; Integer.TYPE,
     * not Integer.class.
     *
     * @param objet the object to modify
     * @param nomPropriete the property name
     * @param valeur the value to assign
     * @param clazzPropriete the setter parameter type
     */
    public <V> void setPropriete(T objet, String nomPropriete, V valeur, Class<? super V> clazzPropriete) {
        try {
            String setterName = IntrospectionHelper.construireNomMethode("set", nomPropriete);
            Method setter = clazz.getMethod(setterName, clazzPropriete);
            setter.invoke(objet, valeur);
        } catch (Exception e) {
            throw new IntrospectionException(e);
        }
    }
}
