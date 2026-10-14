package exo2.q1;

import java.util.ArrayList;
import java.util.List;

public class Entreprise {
    private String nom;
    private List<Departement> departements;

    public Entreprise(String nom, List<Departement> departements) {
        this.nom = nom;
        this.departements = departements;
    }

    public String getNom() {
        return this.nom;
    }

    public List<Departement> getDepartements() {
        return this.departements;
    }

    public List<Employe> listeDirecteursDepartement() {
        ArrayList<Employe> directeurs = new ArrayList<>();
        for (Departement d : departements) {
            for (Service s : d.getServices()) {
                for (Equipe e : s.getEquipes()) {
                    directeurs.add(e.getDirecteur());
                }
            }
        }
        return directeurs;
    }

}
