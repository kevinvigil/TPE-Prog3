package TPE.algoritmos;

import java.util.ArrayList;
import java.util.Iterator;

import TPE.grafo.Arco;
import TPE.grafo.Grafo;

public class GreedyArcos {

    private Grafo g;

    public GreedyArcos(Grafo grafo){
        this.g = grafo;
    }

    public void Greedy(int origen, int destino) {
        ArrayList<Integer> padre = dijkstra(origen, destino);
        ArrayList<Arco> solucion = new ArrayList<>();
        while (origen != destino) {
            Arco ar = g.obtenerArco(padre.get(destino), destino);
            solucion.add(ar);
            destino = padre.get(destino);
        }
        int curr = 0;
        while (curr<solucion.size()/2) {
            Arco aux = solucion.get(curr);
            solucion.set(curr, solucion.get(solucion.size()-1-curr));
            solucion.set(solucion.size()-1-curr, aux);
            curr++;
        }
        int suma = 0;
        for (Arco arco : solucion) {
            System.out.print(arco.getVerticeOrigen()+"-"+ arco.getVerticeDestino()+" , ");
            suma += (Integer)arco.getEtiqueta();
        }
        System.out.println();
        System.out.println(suma+" kms");
        System.out.println("Metrica Greedy");
    }

    private ArrayList<Integer> dijkstra(int origen, int destino) {
        ArrayList<Integer> vertices = new ArrayList<>();
        ArrayList<Integer> distancia = new ArrayList<>();
        ArrayList<Integer> padre = new ArrayList<>();
        Iterator<Integer> verti = g.obtenerVertices();
        cargar(distancia, g.cantidadVertices()+1, Integer.MAX_VALUE);
        cargar(padre, g.cantidadVertices()+1, -1);
        while (verti.hasNext()) {
            Integer curr = verti.next();
            vertices.add(curr);
            padre.set(curr, null);
        }
        distancia.set(origen, 0);
        ArrayList<Integer> valido = new ArrayList<>(vertices); 
        ArrayList<Integer> considerado = new ArrayList<>();
        Integer u = origen;
        while (vertices.size() > considerado.size()) { 
            valido.removeAll(considerado);  
            u = getArcoMenor(distancia, valido);
            considerado.add(u);
            valido.removeAll(considerado);
            for (Integer v : valido) {
                if (g.obtenerArco(u, v) != null) {
                    Arco<Integer> aux = g.obtenerArco(u, v);
                    if (distancia.get(u) + aux.getEtiqueta() < distancia.get(v)) {
                        distancia.set(v, distancia.get(u) + aux.getEtiqueta());
                        padre.set(v, u);
                    }
                }
                
            }
        }
        return padre;
    }

    private void cargar(ArrayList<Integer> arr, int i, int value) {
        for (int j = 0; j < i; j++) {
            arr.add(value);
        }
    }

    private Integer getArcoMenor(ArrayList<Integer> distancia, ArrayList<Integer> valido) {
        Integer vertice = null;
        for (int i = 0; i < valido.size(); i++) {
            int aux = valido.get(i);
            int val = distancia.get(aux);
            if (vertice == null || val<distancia.get(vertice)){
                vertice = aux;
            }
        }
        return vertice;
    }
}
