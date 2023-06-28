package TPE.algoritmos;

import java.util.ArrayList;
import java.util.Iterator;

import TPE.grafo.Arco;
import TPE.grafo.Grafo;

public class GreedyArcos {

    public void dijkstra(Grafo g, int origen, int destino) {
        ArrayList<Integer> vertices = new ArrayList<>();
        ArrayList<Integer> distancia = new ArrayList<>();
        ArrayList<Integer> padre = new ArrayList<>();
        Iterator<Integer> verti = g.obtenerVertices();
        cargar(distancia, g.cantidadVertices()+1, Integer.MAX_VALUE); // La distancia inicial desde el origen al vértice v
        cargar(padre, g.cantidadVertices()+1, -1);
        while (verti.hasNext()) {
            Integer curr = verti.next();
            vertices.add(curr);

                                                    // se establece en infinito
            padre.set(curr, null);          // El nodo anterior en el camino óptimo desde el origen
        }
        distancia.set(origen, 0);           // Distancia desde el origen hasta el origen
        ArrayList<Integer> valido = new ArrayList<>(vertices); 
        ArrayList<Integer> considerado = new ArrayList<>();// considerado será el conjunto de vértices ya considerados
        Integer u = origen;
        while (vertices.size() > considerado.size()) { 
            System.out.println("while");     // loop principal
            // u = vértice en (G.Vértices – S) 
            valido.removeAll(considerado);   
            System.out.println(valido);                       //tal que dist[u] tiene el menor valor
            u = getArcoMenor(distancia, valido);

            considerado.add(u);
            valido.removeAll(considerado);
            
            for (Integer v : valido) {
                System.out.print(u);
                if (g.obtenerArco(u, v) != null) {
                    Arco<Integer> aux = g.obtenerArco(u, v);
                    if (distancia.get(u) + aux.getEtiqueta() < distancia.get(v)) {
                        distancia.set(v, distancia.get(u) + aux.getEtiqueta());
                        padre.set(v, u);
                    }
                }
                
            }
        }

        for (int i = 1; i < distancia.size(); i++) {
            if (padre.get(i)!=null) {
                System.out.print("pos:"+i+"  distancia:"+ distancia.get(i));
            }
        }
    }
    /*
    function Dijkstra(Grafo G, Vértice origen):
        for eachVértice v en G: // Inicialización
            dist[v] = infinito // La distancia inicial desde el origen al vértice v
                            // se establece en infinito
            padre[v] = indefinido // El nodo anterior en el camino óptimo desde el origen
        dist[origen] = 0 // Distancia desde el origen hasta el origen
        S = vacío // S será el conjunto de vértices ya considerados
        while (G.Vértices – S) no es vacío: // loop principal
            u = vértice en (G.Vértices – S) tal que dist[u] tiene el menor valor
            S = S U {u}
            for each v en (G.Vértices – S) que sea adyacente a u:
                if (dist[u] + dist_entre(u, v)) < dist[v])
                    dist[v] = dist[u] + dist_entre(u, v)
                    padre[v] = u
        return padre[ ]
*/

    private void cargar(ArrayList<Integer> arr, int i, int value) {
        for (int j = 0; j < i; j++) {
            arr.add(value);
        }
    }

    private Integer getArcoMenor(ArrayList<Integer> distancia, ArrayList<Integer> valido) {
        Integer vertice = null;
        System.out.println(distancia);
        for (int i = 0; i < valido.size(); i++) {
            int aux = valido.get(i);
            int val = distancia.get(aux);
            if (vertice == null || val<distancia.get(vertice)){
                vertice = aux;
            }
        }
        // for (int j = 0; j < distancia.size(); j++) {
        //     int val = distancia.get(j);
        //     System.out.println(distancia.get(valido.get(2)));
        //     if (vertice == null || val<distancia.get(vertice)) {
        //         vertice = j;
        //     }
        // }
        return vertice;
    }
}
