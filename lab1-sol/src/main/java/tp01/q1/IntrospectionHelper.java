package tp01.q1;


public class IntrospectionHelper {
    // Technical trick: IntrospectionHelper is only meant to be used through
    // its static methods, so we prevent creating instances of it.
    private IntrospectionHelper() {}

    /**
     * Extracts a property name from a getter or setter name.
     *
     * Example: called with "get" and "getNom", returns "nom".
     * @param prefixe the conventional prefix ("get", "set" or "is") at the
     *                start of the method name.
     * @param nomMethode the method name.
     * @return the property name handled by the method. If the method name is
     *         exactly the prefix, returns "".
     * @throws IllegalArgumentException if the prefix does not match
     */
    public static String extraireNomDePropriete(String prefixe, String nomMethode) {
        if (! nomMethode.startsWith(prefixe))
            throw new IllegalArgumentException("couple "+prefixe+ " et "+ nomMethode + " incorrect");
        String nom = nomMethode.substring(prefixe.length());
        if (nom.length() > 0) {
            return Character.toLowerCase(nom.charAt(0)) + nom.substring(1);
        } else {
            return "";
        }
    }


    /**
     * Builds the accessor method name for a property.
     * @param prefixe the prefix (typically "get", "set" or "is")
     * @param propriete the property name, normally starting with a lowercase letter, e.g. "prenom"
     * @return the method name, e.g. "getPrenom"
     */
    public static String construireNomMethode(String prefixe, String propriete) {
        if (propriete.length() > 0) {
            return prefixe + Character.toUpperCase(propriete.charAt(0)) + propriete.substring(1);
        } else {
            return prefixe;
        }
    }
}
