package TPE.algoritmos;

import TPE.grafo.Arco;
import TPE.grafo.Grafo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Backtracking {
    public ArrayList<Arco<Integer>> currSolution;
    private Grafo<Integer> graph;
    private int currDist;
    private double totalTime;
    private long startTime;

    public Backtracking(Grafo<Integer> graph){
        this.graph = graph;
        this.currDist = Integer.MAX_VALUE;
    }

    public ArrayList<Arco<Integer>> backtracking(){
        refreshTime();

        currDist = Integer.MAX_VALUE;
        ArrayList<Arco<Integer>> partialSolution = new ArrayList<>();
        Iterator<Integer> vertexes = graph.obtenerVertices();
        HashSet<Integer> notVisited = new HashSet<>();

        while (vertexes.hasNext()){
            int v = vertexes.next();
            notVisited.add(v);
        }

        vertexes = graph.obtenerVertices();

        while (vertexes.hasNext()){
            Integer v = vertexes.next();
            notVisited.remove(v);
            backtracking(partialSolution, notVisited, 0, v, null);
            notVisited.add(v);
        }

        this.setTotalTime(startTime, System.nanoTime());

        for (Arco<Integer> integerArco : currSolution)
            System.out.print(integerArco);
        System.out.println("\nTiempo: " + totalTime+"s");
        System.out.println("Distancia: " + currDist + " kilometros");
        return currSolution;
    }

    public void backtracking(ArrayList<Arco<Integer>> partialSolution, HashSet<Integer> notVisited, int partialDist, Integer currVertex, Integer prevVertex){
        notVisited.remove(currVertex);

        if (notVisited.isEmpty() && partialDist < this.currDist){
            currDist = partialDist;
            currSolution = new ArrayList<>(partialSolution);
            return;
        }

        if (prevVertex != null)
            backtracking(partialSolution, new HashSet<>(notVisited), partialDist, prevVertex, null);

        for (Integer nv : notVisited){
            Arco<Integer> edge = graph.obtenerArco(currVertex, nv);
            if (edge != null) {
                if (partialDist + edge.getEtiqueta() < this.currDist) {
                    partialSolution.add(edge);
                    backtracking(partialSolution, new HashSet<>(notVisited), partialDist + edge.getEtiqueta(), nv, currVertex);
                    partialSolution.remove(edge);
                }
            }
        }
    }

    private void refreshTime(){
        this.startTime = System.nanoTime();
        this.totalTime = 0;
    }

    private void setTotalTime(long startTime, long endTime){
        this.totalTime = (double) (endTime - startTime) / 1_000_000;
    }
}
