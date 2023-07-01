package TPE.algoritmos;

import TPE.grafo.Arco;
import TPE.grafo.GrafoDirigido;

import java.util.ArrayList;
import java.util.Iterator;

public class Pekoe {
    private final GrafoDirigido<Integer> graph;
    private int distancia = -1;

    public Pekoe(GrafoDirigido<Integer> graph) {
        this.graph = graph;
    }

    public int getDistancia() {
        if (distancia == -1)
            greedy();
        return distancia;
    }

    private void updateDistancia(ArrayList<Integer> dist) {
        for (int i = 1; i < dist.size(); i++)
            this.distancia += dist.get(i);
    }

    private void resetDistancia() {
        this.distancia = 0;
    }

    public void greedy() {
        long startTime = System.nanoTime();

        Iterator<Integer> vertexes = graph.obtenerVertices();
        int origin = vertexes.next();
        int destino = graph.cantidadVertices();
        ArrayList<Arco<Integer>> parent = greedy(graph, origin);
        System.out.println(parent);
        System.out.println("Kilómetros: " + distancia);

        long endTime = System.nanoTime();
        System.out.println("Elapsed time: " + (double) (endTime - startTime) / 1_000_000+"s");
    }

    private ArrayList<Arco<Integer>> greedy(GrafoDirigido<Integer> graph, int origin) {
        resetDistancia();

        int cantVertices = graph.cantidadVertices();
        ArrayList<Integer> dist = new ArrayList<>();
        ArrayList<Arco<Integer>> parent = new ArrayList<>();

        for (int i = 0; i <= cantVertices; i++) {
            dist.add(Integer.MAX_VALUE);
            parent.add(null);
        }

        dist.set(origin, 0);
        ArrayList<Integer> checked = new ArrayList<>();
        int count = 0;

        while (checked.size() < cantVertices) {
            int u = minDist(dist, checked);
            checked.add(u);

            for (int v = 0; v < cantVertices; v++) {
                if (graph.obtenerArco(u, v) != null) {
                    if (!checked.contains(v) && (dist.get(u) + graph.obtenerArco(u, v).getEtiqueta()) < dist.get(v)) {
                        dist.set(v, dist.get(u) + graph.obtenerArco(u, v).getEtiqueta());
                        parent.set(v, graph.obtenerArco(u, v));
                    }
                }
            }
            Iterator<Integer> adyU = graph.obtenerAdyacentes(u);

            while (adyU.hasNext()) {
                int v = adyU.next();
                int distUV = graph.obtenerArco(u, v).getEtiqueta();

                if (((dist.get(u) + graph.obtenerArco(u, v).getEtiqueta()) < dist.get(v)) && !checked.contains(v) && dist.get(u) != Integer.MAX_VALUE) {
                    dist.set(v, (distUV));
                    parent.set(v, graph.obtenerArco(u, v));
                }
            }
            count++;
        }

        updateDistancia(dist);
        return parent;
    }

    private int minDist(ArrayList<Integer> dist, ArrayList<Integer> checked) {
        int min = Integer.MAX_VALUE, min_index = -1;

        Iterator<Integer> vertexes = graph.obtenerVertices();
        ArrayList<Integer> aux = new ArrayList<>();
        while (vertexes.hasNext()) {
            aux.add(vertexes.next());
        }
        aux.removeAll(checked);

        for (Integer i : aux) {
            if (dist.get(i) <= min) {
                min = dist.get(i);
                min_index = i;
            }
        }

        return min_index;
    }

//    int minDistance(ArrayList<Integer> dist, Boolean[] sptSet) {
//        int min = Integer.MAX_VALUE, min_index = -1;
//
//        for (int v = 0; v < V; v++)
//            if (sptSet[v] == false && dist[v] <= min) {
//                min = dist.get()z;
//                min_index = v;
//            }
//
//        return min_index;
//    }
}
