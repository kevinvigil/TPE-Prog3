package TPE.algoritmos;

import java.util.ArrayList;
import java.util.Iterator;

import TPE.grafo.Arco;
import TPE.grafo.Grafo;

public class GreedyArcos {

    private Grafo g;
    private double totalTime;
    private long startTime;


    public GreedyArcos(Grafo grafo) {
        this.g = grafo;
    }

    public void Greedy() {
        this.refreshTime();

        Iterator<Integer> vertice = g.obtenerVertices();
        ArrayList<Integer> padre = dijkstra(vertice.next());
        ArrayList<Arco<Integer>> solucion = new ArrayList<>();
        int currDist = 0;
        
        while (!full(padre)) {
            padre = dijkstra(vertice.next());
        }

        for (int i = 0; i < padre.size(); i++) {
            Integer curr = padre.get(i);
            if (curr != null) {
                Arco<Integer> arco = g.obtenerArco(padre.get(i), i);
                solucion.add(arco);
                currDist += (Integer) arco.getEtiqueta();
            }
        }

        this.setTotalTime(startTime, System.nanoTime());

        System.out.println(solucion);
        System.out.println("Tiempo: " + totalTime+"ms");
        System.out.println("Distancia: " + currDist + " kilometros");
    }

    private boolean full(ArrayList<Integer> padre) {
        int cont = 0;
        for (Integer i : padre) 
            if (i == null) 
                cont++;
        return cont<3;
    }

    public ArrayList<Integer> dijkstra(int origen) {
        ArrayList<Integer> vertices = new ArrayList<>();
        ArrayList<Integer> distancia = new ArrayList<>();
        ArrayList<Integer> padre = new ArrayList<>();
        ArrayList<Integer> considerado = new ArrayList<>();
        Iterator<Integer> verti = g.obtenerVertices();

        distancia.add(Integer.MAX_VALUE);
        padre.add(null);

        while (verti.hasNext()) {
            int curr = verti.next();
            distancia.add(Integer.MAX_VALUE);
            padre.add(null);
            vertices.add(curr);
        }
        distancia.set(origen, 0);
        int contador = vertices.size();

        while (contador > considerado.size()) {
            vertices.removeAll(considerado);
            int u = getArcoMenor(distancia, vertices);
            considerado.add(u);
            vertices.removeAll(considerado);

            for (Integer v : vertices) {
                Arco<Integer> curr = g.obtenerArco(u, v);

                if (curr != null) {
                    Arco<Integer> aux = curr;
                    int value = distancia.get(u) + aux.getEtiqueta();

                    if (value < distancia.get(v)) {
                        distancia.set(v, value);
                        padre.set(v, u);
                    }
                }

            }
        }
        return padre;
    }

    private Integer getArcoMenor(ArrayList<Integer> distancia, ArrayList<Integer> valido) {
        Integer vertice = null;
        for (int aux : valido)
            if (vertice == null || distancia.get(aux) < distancia.get(vertice))
                vertice = aux;

        return vertice;
    }

    private void refreshTime(){
        this.startTime = System.nanoTime();
        this.totalTime = 0;
    }

    private void setTotalTime(long startTime, long endTime){
        this.totalTime = (double) (endTime - startTime) / 1_000_000;
    }
}