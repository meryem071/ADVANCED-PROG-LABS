package exo2.q1;

import java.util.List;

public class Service {
    private String nom;
    private List<Equipe> equipes;


    public Service(String nom, List<Equipe> equipes) {
        this.nom = nom;
        this.equipes = equipes;
    }

    public String getNom() {
        return this.nom;
    }

    public List<Equipe> getEquipes() {
        return this.equipes;
    }

}
