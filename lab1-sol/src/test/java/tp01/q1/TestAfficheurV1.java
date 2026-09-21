package tp01.q1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestAfficheurV1 {
    
    @Test
    public void afficherPersonneTest() {
        Personne p = new Personne("Alfred", 1999);
        String expected = "anneeNaissance : 1999 ; nom : Alfred";
        String actual = new Afficheur().afficher(p);
        assertEquals(expected, actual);        
    }
}
