

public class GraphC {
    public int nbSommets;
    public int head = 0; // <- can be changed, needed later if the graph is a path
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

            this.sommets[i] = new Sommet(i, curNbSuccessors, curSuccessors, curCosts);
        }

        f.close();
    }

    public void Afficher() {
        System.out.println(this.nbSommets);
        for (int i = 0; i < this.nbSommets; i++) {
            this.sommets[i].Afficher();
        }
    }

    void PCC1toAll(int source, int[] distances, int[] parents) {
        // Set up distances
        distances = new int[this.nbSommets];
        parents = new int[this.nbSommets];

        for (int i = 0; i < this.nbSommets; i++) {
            if (source != i) distances[i] = Integer.MAX_VALUE;
            else distances[i] = 0;

            parents[i] = -1;            
        }

        // Visited vertices
        boolean[] visited = new boolean[this.nbSommets];
        for (int i = 0; i < this.nbSommets; i++) {
            visited[i] = false;
        }

        // Find vertex with min cost
        for (int i = 0; i < this.nbSommets; i++) {
            int curReachableSommet = -1;

            for (int v = 0; v < this.nbSommets; v++) {
                if (!visited[v] && distances[v] < Integer.MAX_VALUE) {
                    curReachableSommet = v;
                }
            }

            if (curReachableSommet == -1) break; // Soit on a terminé soit on ne peut rien atteindre

            visited[curReachableSommet] = true;
            // On regarde les sommets atteignables depuis le sommets qu'on a trouvé
            Sommet s = this.sommets[curReachableSommet];
            for (int j = 0; j < s.nbSuccessors; j++) {
                int v = s.Successors[j];
                int cost = s.PathCosts[j];

                if (!visited[v] 
                    && (distances[curReachableSommet] + cost) < distances[v])
                {
                    distances[v] = distances[curReachableSommet] + cost;
                    parents[v] = curReachableSommet;
                }
            }

        }
    }

}