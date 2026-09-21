package tp01.q1;

/**
 * Exceptions thrown by the introspection system.
 */
public class IntrospectionException extends RuntimeException { 

    private static final long serialVersionUID = -8657931718320714494L;

    public IntrospectionException() {
    }

    public IntrospectionException(String message) {
        super(message);
    }   

    public IntrospectionException(Exception e) {
        super(e);
    }   

}
