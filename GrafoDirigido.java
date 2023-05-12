package TPE;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class GrafoDirigido<T> implements Grafo<T>{
    private HashMap<Integer, HashMap<Integer, Arco<T>>> vertices;
    
    public static void main(String[] args) {
        GrafoDirigido j = new GrafoDirigido();

        j.agregarVertice(1);
        j.agregarVertice(2);
        j.agregarVertice(3);
        j.agregarVertice(4);

        j.agregarArco(2, 4, 80);
        j.agregarArco(2, 1, 50);
        Iterator u = j.obtenerVertices();
        while (u.hasNext()) {
            System.out.println(u.next());
        }
    }

    public GrafoDirigido(){
        vertices = new HashMap<Integer, HashMap<Integer, Arco<T>>>();
    }

    @Override
    public void agregarVertice(int verticeId) {
        if (!contieneVertice(verticeId)) {
            HashMap<Integer, Arco<T>> j = new HashMap<Integer, Arco<T>>();
            vertices.put(verticeId, j);
        }
    }

    @Override
    public void borrarVertice(int verticeId) {
        HashMap<Integer, Arco<T>> entry = getVerticeConArcos(verticeId);
        if (entry != null) {
            for (Map.Entry<Integer, Arco<T>> value : entry.entrySet()) {
                    if (value.getKey().equals(verticeId)) {
                        borrarArco(value.getValue().getVerticeOrigen(), verticeId);
                    }
                }
            vertices.remove(verticeId);
        }           
    }

    private HashMap<Integer, Arco<T>> getVerticeConArcos(int verticeIdO){
        HashMap<Integer, Arco<T>> aux = null;
        for (Map.Entry<Integer, HashMap<Integer, Arco<T>>> entry : vertices.entrySet()){
            if (entry.getKey().equals(verticeIdO)) {
                aux = entry.getValue();
            }
            if (aux != null)
                break;
        }
        return aux;
    }

    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if (!existeArco(verticeId1, verticeId2)) {
            Arco<T> aux = new Arco<T>(verticeId1, verticeId2, etiqueta);
            HashMap<Integer, Arco<T>> entry = getVerticeConArcos(verticeId1);
            entry.put(verticeId2, aux);
        }
    }

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        HashMap<Integer, Arco<T>> entry = getVerticeConArcos(verticeId1);
        if (entry != null) {
            for (Map.Entry<Integer, Arco<T>> value : entry.entrySet()) {
                if (value.getKey().equals(verticeId2)) {
                    value.getValue().setVerticeDestino(-1);
                    value.getValue().setVerticeOrigen(-1);
                    entry.remove(value.getKey());
                }
            }
        }   
    }

    @Override
    public boolean contieneVertice(int verticeId) {
        return getVerticeConArcos(verticeId) != null;
    }

    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        return obtenerArco(verticeId1, verticeId2) != null;
    }

    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        HashMap<Integer, Arco<T>> entry = getVerticeConArcos(verticeId1);
        Arco<T> aux = null;
        if (entry != null) {
            for (Map.Entry<Integer, Arco<T>> value : entry.entrySet()){
                if (value.getKey().equals(verticeId2)) {
                    aux = value.getValue();
                }
            }
        }
        return aux;
    }

    @Override
    public int cantidadVertices() {
        return vertices.size();
    }

    @Override
    public int cantidadArcos() {
        int sum = 0;
        for (Map.Entry<Integer, HashMap<Integer, Arco<T>>> entry : vertices.entrySet()){
            sum += entry.getValue().size();
        } 
        return sum;
    }

    @Override
    public Iterator obtenerVertices() {
        return vertices.values().iterator();
    }

    @Override
    public Iterator obtenerAdyacentes(int verticeId) {
        return getVerticeConArcos(verticeId).values().iterator();
    }

    @Override
    public Iterator obtenerArcos() {
        LinkedList<Arco<T>> aux = new LinkedList<>();
        for (Map.Entry<Integer, HashMap<Integer, Arco<T>>> entry : vertices.entrySet()){
            for (Map.Entry<Integer, Arco<T>> value : entry.getValue().entrySet()){
                aux.add(value.getValue());
            }
        }
        return aux.iterator();
    }

    @Override
    public Iterator obtenerArcos(int verticeId) {
        return getVerticeConArcos(verticeId).values().iterator();
    }
}
