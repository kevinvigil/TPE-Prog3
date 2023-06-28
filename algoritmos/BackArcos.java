package TPE.algoritmos;

import java.util.ArrayList;
import java.util.Iterator;

import TPE.grafo.Arco;
import TPE.grafo.Grafo;

public class BackArcos {
    private ArrayList<Arco> solucion;
    private Grafo<?> grafo;

    
    public BackArcos(Grafo gra) {
        this.grafo = gra;
        this.solucion = new ArrayList<>();
    }

    public void BackT(int actual, int destino){
        ArrayList<Arco> c = new ArrayList<>();
        BackT(c, actual, destino);
        Iterator<Arco> aux = solucion.iterator();
        while (aux.hasNext()) {
            Arco s = aux.next();
            System.out.println(s.getVerticeOrigen()+ " -> "+ s.getVerticeDestino());
        }
    }

	private void BackT(ArrayList<Arco> curr, int actual, int destino){
		if (actual == destino) {
            if (solucion.isEmpty()) 
                solucion = new ArrayList<>(curr);
            else if (suma(curr)<suma(solucion))
				solucion = new ArrayList<>(curr);
		}else{
			Iterator<Integer> aux = grafo.obtenerAdyacentes(actual);
			while (aux.hasNext()) {
				Integer siguiente = aux.next();
                if (!contains(curr, siguiente)) {
                    Arco arco = grafo.obtenerArco(actual, siguiente);
                    if ((suma(curr)+((Integer)arco.getEtiqueta())) < suma(solucion) || solucion.isEmpty()) {
                        curr.add(arco);
                        BackT(curr, siguiente, destino);
                        curr.remove(arco);
                    }
                }
			}
		}
	}

	private boolean contains(ArrayList<Arco> curr, Integer value) {
        Iterator<Arco> aux = curr.iterator();
        while (aux.hasNext()) {
            Arco actual = aux.next();
            if (actual.getVerticeDestino() == value || actual.getVerticeOrigen() == value) {
                return true;
            }
        }
        return false;
    }

    private int suma(ArrayList<Arco> a) {
		Iterator<Arco> aux = a.iterator();
		int suma = 0;
		while (aux.hasNext()) {
			Arco curr = aux.next();
			suma += (Integer) curr.getEtiqueta();
		}
		return suma;
	}
}