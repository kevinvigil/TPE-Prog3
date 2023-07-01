package TPE.algoritmos;

import java.util.ArrayList;
import java.util.Iterator;

import TPE.grafo.Arco;
import TPE.grafo.Grafo;

public class BackArcos<T> {
    public ArrayList<Arco<T>> solucion;
    private Grafo<T> grafo;


    public BackArcos(Grafo<T> grafo) {
        this.grafo = grafo;
        this.solucion = new ArrayList<>();
    }

    public void BackT(){
        BackT(1, grafo.cantidadVertices());
    }

    public void BackT(int actual, int destino) {
        solucion.clear();
        long startTime = System.nanoTime();

        ArrayList<Arco<T>> c = new ArrayList<>();
        BackT(c, actual, destino);

        for (Arco<T> s : solucion) {
            System.out.print(s.getVerticeOrigen() + "-" + s.getVerticeDestino() + " , ");
        }

        System.out.println(suma(solucion) + " kms");

        long endTime = System.nanoTime();
        System.out.println("Elapsed time: " + (double) (endTime - startTime) / 1_000_000+"s");
    }

    private void BackT(ArrayList<Arco<T>> curr, int actual, int destino) {
        System.out.println(curr);
        if (actual == destino) {
            if (solucion.isEmpty())
                solucion = new ArrayList<>(curr);
            else if (suma(curr) < suma(solucion))
                solucion.addAll(curr);
        } else {
            Iterator<Integer> aux = grafo.obtenerAdyacentes(actual);

            while (aux.hasNext()) {
                Integer siguiente = aux.next();
                if (!this.contains(curr, siguiente)) {
                    Arco<T> arco = grafo.obtenerArco(actual, siguiente);
                    if ((suma(curr) + ((Integer) arco.getEtiqueta())) > suma(solucion) || solucion.isEmpty()) {
                        curr.add(arco);
                        BackT(curr, siguiente, destino);
                        curr.remove(arco);
                    }
                }
            }
        }
    }


    private boolean contains(ArrayList<Arco<T>> curr, Integer value) {
        for (Arco<T> actual : curr) {
            if (actual.getVerticeDestino() == value || actual.getVerticeOrigen() == value) {
                return true;
            }
        }
        return false;
    }

    private int suma(ArrayList<Arco<T>> a) {
        Iterator<Arco<T>> aux = a.iterator();
        int suma = 0;
        while (aux.hasNext()) {
            Arco<T> curr = aux.next();
            suma += (Integer) curr.getEtiqueta();
        }
        return suma;
    }
}