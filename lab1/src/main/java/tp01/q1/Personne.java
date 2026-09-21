package tp01.q1;


public class Personne {
    private String nom;
    private int anneeNaissance;

    public Personne() {
    }

    public Personne(String nom, int anneeNaissance) {
        this.nom = nom;
        this.anneeNaissance = anneeNaissance;
    }

    public String getNom() {
        return this.nom;
    }

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
