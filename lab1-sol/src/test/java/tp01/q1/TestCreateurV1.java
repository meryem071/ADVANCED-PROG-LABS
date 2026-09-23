package tp01.q1;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TestCreateurV1 {
    
    @Test
    public void testCreerVide() {
        Createur<Adresse> createur = new Createur<>(Adresse.class);
        Adresse a = createur.creer();
        assertNotNull(a);
    }

    @Test
    public void testsetProprieteTexte() {
        Createur<Adresse> createur = new Createur<>(Adresse.class);
        Adresse a = createur.creer();
        createur.setProprieteTexte(a, "rue", "r1");
        createur.setProprieteTexte(a, "ville", "v1");
        assertEquals("r1", a.getRue());
        assertEquals("v1", a.getVille());
    }


    @Test
    public void testSetPropriete() {
        Createur<Personne> createur = new Createur<>(Personne.class);
        Personne p = createur.creer();
        createur.setPropriete(p, "nom", "n1", String.class);
        createur.setPropriete(p, "anneeNaissance", 1995, Integer.TYPE);
        assertEquals("n1", p.getNom());
        assertEquals(1995, p.getAnneeNaissance());
    }


}
