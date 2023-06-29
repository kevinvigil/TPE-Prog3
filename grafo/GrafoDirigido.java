package TPE.grafo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class GrafoDirigido<T> implements Grafo<T>{
    private HashMap<Integer, HashMap<Integer, Arco<T>>> vertices;

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
        if (contieneVertice(verticeId2) && !existeArco(verticeId1, verticeId2)) {
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
