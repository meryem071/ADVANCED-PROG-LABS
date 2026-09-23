package tp01.q2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestAfficheurV2 {
    @Test
    public void afficherPersonneTest() {
        Personne2 p = new Personne2("Alfred", 1999);
        String expected = "annee de naissance : 1999 ; nom : Alfred";
        String actual = new Afficheur2().afficher(p);
        assertEquals(expected, actual);        
    }
}
