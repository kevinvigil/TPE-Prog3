package TPE.algoritmos;

import java.util.ArrayList;
import java.util.Iterator;

import TPE.grafo.Arco;
import TPE.grafo.Grafo;

public class BackArcos<T> {
    private ArrayList<Arco<T>> solucion;
    private Grafo<?> grafo;

    
    public BackArcos(Grafo<?> gra) {
        this.grafo = gra;
        this.solucion = new ArrayList<>();
    }

    public void BackT(int actual, int destino){
        solucion.clear();
        ArrayList<Arco<T>> c = new ArrayList<>();
        BackT(c, actual, destino);
        Iterator<Arco<T>> aux = solucion.iterator();
        while (aux.hasNext()) {
            Arco<T> s = aux.next();
            System.out.print(s.getVerticeOrigen()+ "-"+ s.getVerticeDestino()+" , ");
        }
        System.out.println();
        System.out.println(suma(solucion)+" kms");
        System.out.println("Metrica BackTracking");
    }

	private void BackT(ArrayList<Arco<T>> curr, int actual, int destino){
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

	private boolean contains(ArrayList<Arco<T>> curr, Integer value) {
        Iterator<Arco<T>> aux = curr.iterator();
        while (aux.hasNext()) {
            Arco<T> actual = aux.next();
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
			suma += (Integer)curr.getEtiqueta();
		}
		return suma;
	}
}