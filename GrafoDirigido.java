package TPE;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GrafoDirigido<T> implements Grafo<T>{
    private HashMap<Integer, HashMap<Integer, Arco<T>>> vertices;
    
    public static void main(String[] args) {
        // IMPLEMENTACION GRAFO
        // GrafoDirigido j = new GrafoDirigido();

        // j.agregarVertice(4);
        // j.agregarVertice(1);
        // j.agregarVertice(2);
        // j.agregarVertice(3);
        // j.agregarVertice(7);
        // j.agregarVertice(8);
        // j.agregarVertice(10);

        // j.agregarArco(4, 2, 10);
        // j.agregarArco(4, 8, 10);
        // j.agregarArco(2, 1, 10);
        // j.agregarArco(2, 3, 10);
        // j.agregarArco(8, 7, 10);
        // j.agregarArco(8, 10, 10);
        // j.agregarArco(1, 4, 10);
        // j.agregarArco(10, 7, 10);

        // Iterator<Arco<Integer>> ggg = j.obtenerArcos();
        // while (ggg.hasNext()) {
        //     Arco<Integer> k = ggg.next();
        //     System.out.println(k);
        // }

        // DFS && BFS
        // ServicioBFS auxB = new ServicioBFS(j);
        // List<Integer> ansB = auxB.bfsForest();

        // ServicioDFS auxD = new ServicioDFS(j);
        // List<Integer> ansD = auxD.dfsForest();
        // System.out.println("BFS");
        // for (Integer integer : ansB) {
        //     System.out.print(integer + " | ");
        // }

        // System.out.println();
        // System.out.println("DFS");
        // for (Integer integer : ansD) {
        //     System.out.print(integer + " | ");
        // }
        // System.out.println("");

        // CAMINOS
        // System.out.println("CAMINOS");
        // ServicioCaminos auxCaminos = new ServicioCaminos(j, 1, 7, 5);
        // List<List<Integer>> lis = auxCaminos.caminos();
        // for (List<Integer> list : lis) {
        //     for (Integer list2 : list) {
        //         System.out.print(list2 + " | ");
        //     }
        //     System.out.println("");
        // }
    }

    public GrafoDirigido(){
        vertices = new HashMap<Integer, HashMap<Integer, Arco<T>>>();
    }

    /**
    * Complejidad: O(1) debido a que realiza la insercion directa en el HashMap.
    * Debido a la optimizacion que posee HashMap la mayoria de sus metodos terminan siendo O(1).
    */
    @Override
    public void agregarVertice(int verticeId) {
        if (!vertices.containsKey(verticeId)) {
            HashMap<Integer, Arco<T>> j = new HashMap<Integer, Arco<T>>();
            vertices.put(verticeId, j);
        }
    }

    /**
    * Complejidad: O(n) donde n son los vertices debido a que recorre buscando para
    * ver que vertices tienen un arco apuntando al que se desea eliminar.
    */
    @Override
    public void borrarVertice(int verticeId) {
        if (vertices.containsKey(verticeId)) {
            vertices.remove(verticeId);
            for (Map.Entry<Integer, HashMap<Integer, Arco<T>>> entry : vertices.entrySet()){
                entry.getValue().remove(verticeId);
            }
        }        
    }

    /**
    * Complejidad: O(1) debido a que busca directamente el vertice donde insertar.
    * Debido a la optimizacion que posee HashMap la mayoria de sus metodos terminan siendo O(1).
    */
    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if (!existeArco(verticeId1, verticeId2)) {
            Arco<T> aux = new Arco<T>(verticeId1, verticeId2, etiqueta);
            vertices.get(verticeId1).put(verticeId2, aux);
        }
    }

    /**
    * Complejidad: O(1) debido a que busca directamente el vertice  origen y elimina
    * dentro del HashMap de adyacencia key con el vertice destino.
    * Debido a la optimizacion que posee HashMap la mayoria  de sus metodos terminan siendo O(1).
    */
    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        vertices.get(verticeId1).remove(verticeId2);
    }

    /**
    * Complejidad: O(1) debido a que busca directamente si existe el vertice.
    * Debido a la optimizacion que posee HashMap la mayoria  de sus metodos terminan siendo O(1).
    */
    @Override
    public boolean contieneVertice(int verticeId) {
        return vertices.get(verticeId) != null;
    }

    /**
    * Complejidad: O(1) debido a que busca directamente si existe el vertice origen y luego busca dentro del 
    * HashMap de adyacencia la key con el vertice destino  y si el resultado es distinto de "null" es porque existe.
    * Debido a la optimizacion que posee HashMap la mayoria  de sus metodos terminan siendo O(1).
    */
    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        return obtenerArco(verticeId1, verticeId2) != null;
    }

    /**
    * Complejidad: O(1) debido a que busca directamente si existe el vertice origen y luego busca dentro del 
    * HashMap de adyacencia la key con el vertice destino y retorna su valor.
    * Debido a la optimizacion que posee HashMap la mayoria de sus metodos terminan siendo O(1).
    */
    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        return vertices.get(verticeId1).get(verticeId2);
    }

    /**
    * Complejidad: O(1) ya que pide el tamaño del HashMap y este lo retorna.
    * Debido a la optimizacion que posee HashMap la mayoria de sus metodos terminan siendo O(1).
    */
    @Override
    public int cantidadVertices() {
        return vertices.size();
    }

    /**
    * Complejidad: O(n) donde n es la cantidad de vertices debido a que debe sumar la cantidad
    * de arcos que posee cada uno para devolverlo.
    */
    @Override
    public int cantidadArcos() {
        int sum = 0;
        for (Map.Entry<Integer, HashMap<Integer, Arco<T>>> entry : vertices.entrySet()){
            sum += entry.getValue().size();
        } 
        return sum;
    }

    /**
    * Complejidad: O(1) debido a que solo se esta llamando al iterador. Sin embargo, 
    * el funcionamiento interno de este, es O(n)
    */
    @Override
    public Iterator<Integer> obtenerVertices() {
        return vertices.keySet().iterator();
    }
    /*
    * Mismo caso que obtenerVertices() 
    */
    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        return vertices.get(verticeId).keySet().iterator();
    }

    /*
    * Complejidad: O(n²) debido a que hay un bloque de for anidado adentro de otro, 
    * en cada caso habiendo un llamado a memoria 
    */
    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        LinkedList<Arco<T>> aux = new LinkedList<>();
        for (Map.Entry<Integer, HashMap<Integer, Arco<T>>> entry : vertices.entrySet()){
            for (Map.Entry<Integer, Arco<T>> value : entry.getValue().entrySet()){
                aux.add(value.getValue());
            }
        }
        return aux.iterator();
    }

    /* 
    * Mismo caso que obtenerVertices() 
    */
    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        return vertices.get(verticeId).values().iterator();
    }
}
