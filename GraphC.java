import java.util.LinkedList;

public class GraphC {
    public int nbSommets;
    public Sommet[] sommets;
    
    GraphC(){}

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

    void ShortestPathsFromSource(int source, int[] distances, int[] parents) {  
        // Initialization des tableaux
        for (int i = 0; i < this.nbSommets; i++) {
            if (source != i) distances[i] = Integer.MAX_VALUE;
            else distances[i] = 0;

            parents[i] = -1;            
        }

        // On retient les sommets visités
        boolean[] visited = new boolean[this.nbSommets];
        for (int i = 0; i < this.nbSommets; i++) {
            visited[i] = false;
        }

        // 
        for (int i = 0; i < this.nbSommets; i++) {
            int curReachableSommet = -1;
            int minDistance = Integer.MAX_VALUE;
            // Trouve le sommets avec la moidre distance
            for (int v = 0; v < this.nbSommets; v++) {
                if (!visited[v] && distances[v] < minDistance) {
                    curReachableSommet = v;
                    minDistance = distances[v];
                }
            }

            if (curReachableSommet == -1) break; // On quite la boucle si on peut atteindre aucun sommet

            visited[curReachableSommet] = true;
            // On regarde les sommets atteignables depuis le sommets qu'on a trouvé
            Sommet s = this.sommets[curReachableSommet];
            for (int j = 0; j < s.nbSuccessors; j++) {
                int v = s.Successors[j];
                int cost = s.PathCosts[j];

                // Si on trouve un parcours plus court pour atteindre le sommet V depuis le sommet actuel,
                // on garde cette nouvelle distance et on met à jour le parent
                if (!visited[v] 
                    && (distances[curReachableSommet] + cost) < distances[v])
                {
                    distances[v] = distances[curReachableSommet] + cost;
                    parents[v] = curReachableSommet;
                }
            }

        }
    }

    GraphC[] PCC1toAll(Sommet s) {
        int[] parents = new int[this.nbSommets];
        int[] distances = new int[this.nbSommets];
        this.ShortestPathsFromSource(s.name, distances, parents); // trouve les parcours les plus courts et les parents
        // Array des graphes à retourner
        GraphC[] graphs = new GraphC[this.nbSommets];

        for (int v = 0; v < this.nbSommets; v++) {
            // Reconstruire le chemin entre la source et le sommets V
            // On utilise une linked car l'insertion au début de la liste se fait en temps constant
            LinkedList<Integer> chemin = new LinkedList<>();
            int cur = v;

            // On quite quand on trouve -1 car la source n'a pas de parent
            while (cur != -1) {
                chemin.addFirst(cur);
                cur = parents[cur];
            }

            // recnstruire le graphe
            GraphC g = new GraphC();
            g.nbSommets = chemin.size();
            g.sommets = new Sommet[g.nbSommets];

            for (int i = 0; i < chemin.size(); i++) {
                int sommetActuel = chemin.get(i);

                if (i < chemin.size() - 1) { // tant qu'on est pas arrivé à la fin du chemin
                    int[] succ = { chemin.get(i + 1) };
                    int[] cost = { this.findCostFromAtoB(chemin.get(i), chemin.get(i+1)) }; 
                    g.sommets[i] = new Sommet(sommetActuel, 1, succ, cost);
                } else {
                    g.sommets[i] = new Sommet(sommetActuel, 0, new int[0], new int[0]);
                }
            }

            graphs[v] = g;
        }

        return graphs;
    }

    public GraphC[][] PCCAlltoAll() {
        GraphC [][] res = new GraphC[nbSommets][];
        for (int i = 0; i < nbSommets; i++) {
            res[i] = PCC1toAll(sommets[i]);
        }
        return res;
    }

    public int findCostFromAtoB(int a, int b) {
        int i;
        for(i = 0; this.sommets[a].Successors[i] != b; i++){}
        return this.sommets[a].PathCosts[i];
    }   
}