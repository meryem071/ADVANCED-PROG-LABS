package tp01.q2;


public class Personne2 {
    private String nom;
    private int anneeNaissance;

    
    public Personne2() {
    }

    public Personne2(String nom, int anneeNaissance) {
        this.nom = nom;
        this.anneeNaissance = anneeNaissance;
    }

    public String getNom() {
        return this.nom;
    }

    @Label("annee de naissance")
    public int getAnneeNaissance() {
        return this.anneeNaissance;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAnneeNaissance(int anneeNaissance) {
        this.anneeNaissance = anneeNaissance;
    }



}
