public class GraphC {
    public int nbSommets;
    public Sommet[] sommets;
    
    GraphC(String filename) {
        InputFile f = new InputFile();
        f.open(filename);
        // Read nb sommets
        this.nbSommets = f.readInt();
        this.sommets = new Sommet[this.nbSommets];

        // Read sommets
        for(int i = 0; !f.eof(); i++) {
            int curNbSuccessors = f.readInt();

            int[] curSuccessors = new int[curNbSuccessors];
            int[] curCosts = new int[curNbSuccessors];

            // Read successors
            for (int j = 0; j < curNbSuccessors; j++) {
                curSuccessors[j] = f.readInt();
                curCosts[j] = f.readInt();
            }

            this.sommets[i] = new Sommet(curNbSuccessors, curSuccessors, curCosts);
        }

        f.close();
    }

    public void Afficher() {
        System.out.println(this.nbSommets);
        for (int i = 0; i < this.nbSommets; i++) {
            this.sommets[i].Afficher();
        }
    }
}