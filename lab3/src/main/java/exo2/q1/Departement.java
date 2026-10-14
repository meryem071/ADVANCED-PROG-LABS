package exo2.q1;

import java.util.ArrayList;
import java.util.List;

public class Departement {
    private String nom;
    private List<Service> services;

    public Departement(String nom, List<Service> services) {
        this.nom = nom;
        this.services = services;
    }

    public String getNom() {
        return this.nom;
    }

    public List<Service> getServices() {
        return this.services;
    }


}
