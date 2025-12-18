public class Executable {
    public static void main(String[] args) {
        // 1. de lire une fichier contenant un graphe orienté et valué et d’initialiser l’objet
        GraphC g = new GraphC("../Graphe02.dat");
        // 2. d’afficher le graphe lu en entrée avec la méthode Afficher();
        g.Afficher();
        // 3. d’afficher tous les plus courts chemins du graphe à partir d’un sommet s choisi
        GraphC[] paths = g.PCC1toAll(g.sommets[0]);
        printG(paths, 0);
        // 4. d’afficher tous les plus courts chemins du graphe à partir de tous les sommets du graphe vers tous les autres.
        GraphC[][] resAll = g.PCCAlltoAll();
        for (int i = 0; i < resAll.length; i++)
        {
            System.out.println("----------");
            printG(resAll[i], i);
            System.out.println("----------");
        }

    }

    public static void printG(GraphC[] paths, int sommet) {
        // Afficher les graphes
        for (int i = 0; i < paths.length; i++) {
            System.out.println("Plus court chemin de " + sommet+ " à " + i + " :");
            paths[i].Afficher();
        }
    }
}