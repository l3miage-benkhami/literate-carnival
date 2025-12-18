public class Sommet {
    public int nbSuccessors = 0;
    public int[] Successors;
    public int[] PathCosts;

    Sommet(int nbSuccessors, int[] Successors, int[] PathCosts) {
        this.nbSuccessors = nbSuccessors;
        this.Successors = Successors.clone();
        this.PathCosts = PathCosts.clone();
    }

    void Afficher() {
        System.out.printf("%d", this.nbSuccessors);
        for (int i = 0; i < this.nbSuccessors; i++) {
            System.out.printf(" %d  %d ", this.Successors[i], this.PathCosts[i]);
        }
        System.out.println();
    }
}