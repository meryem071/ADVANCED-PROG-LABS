package fip1;

import java.util.List;

/**
 * TODO (Ex1): turn this into a generic class {@code MaListe<T>} backed by an
 * internal Object array (cast back to T when reading).
 *
 * Required API (exact signatures are part of the exercise):
 * - {@code ajouter} : append an element at the end
 * - {@code taille} : current size
 * - {@code element} : element at index i
 * - {@code toString} : space-separated elements, e.g. "un deux"
 * - {@code ajouterListe} : append all elements of another list (Q2-Q3)
 * - {@code ajouterDansListe} : append all elements of this list into another list (Q4)
 */
public class MaListe<T> {

    private int taille;
    private Object[] l;

    public MaListe(){
        this.l = new Object[10];
        this.taille = 0;
    }

    public int getTaille() {
        return taille;
    }

    @SuppressWarnings("unchecked")
    public T element(int i) {
        if (i < 0 || i >= taille) {
            throw new IndexOutOfBoundsException("Index: " + i + ", taille: " + taille);
        }
        return (T) l[i];
    }

    public void ajouter(T valeur){
       if (taille == l.length) {
            l = java.util.Arrays.copyOf(l, l.length * 2);
        }
        l[taille] = valeur;
        taille++;
    }
}
