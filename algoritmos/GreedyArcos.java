package TPE.algoritmos;

import java.util.ArrayList;
import java.util.Iterator;

import TPE.grafo.Arco;
import TPE.grafo.Grafo;

public class GreedyArcos {

    private Grafo g;

    public GreedyArcos(Grafo grafo) {
        this.g = grafo;
    }

    public void Greedy(int origen, int destino) {
        ArrayList<Integer> padre = dijkstra(origen, destino);
        ArrayList<Arco<Integer>> solucion = new ArrayList<>();
        while (origen != destino) {
            Arco<Integer> ar = g.obtenerArco(padre.get(destino), destino);
            solucion.add(ar);
            destino = padre.get(destino);
        }
        int curr = 0;
        while (curr < solucion.size() / 2) {
            Arco<Integer> aux = solucion.get(curr);
            solucion.set(curr, solucion.get(solucion.size() - 1 - curr));
            solucion.set(solucion.size() - 1 - curr, aux);
            curr++;
        }
        int suma = 0;
        for (Arco<Integer> arco : solucion) {
            System.out.print(arco.getVerticeOrigen() + "-" + arco.getVerticeDestino() + " , ");
            suma += (Integer) arco.getEtiqueta();
        }
        System.out.println();
        System.out.println(suma + " kms");
        System.out.println("Metrica Greedy");
    }

    public ArrayList<Integer> dijkstra(int origen, int destino) {
        ArrayList<Integer> vertices = new ArrayList<>();
        ArrayList<Integer> distancia = new ArrayList<>();
        ArrayList<Integer> padre = new ArrayList<>();
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
        ArrayList<Integer> considerado = new ArrayList<>();
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
        for (int i = 0; i < valido.size(); i++) {
            int aux = valido.get(i);
            if (vertice == null || distancia.get(aux) < distancia.get(vertice)) {
                vertice = aux;
            }
        }
        return vertice;
    }
}