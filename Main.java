package TPE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import TPE.algoritmos.GreedyArcos;
import TPE.algoritmos.Backtracking;
import TPE.grafo.Arco;
import TPE.grafo.Grafo;
import TPE.grafo.GrafoDirigido;

public class Main {

    public static <T> void mostrarGrafo (Grafo<T> grafo) {
		// Recorremos todos los vertices
		Iterator<Integer> it = grafo.obtenerVertices();
		
		while (it.hasNext()) {
			Integer v = (Integer) it.next();
			System.out.println("    " + v);
			// Recorremos todos los adyacentes de cada vertice
			Iterator<Arco<T>> itA = grafo.obtenerArcos(v);
			while (itA.hasNext()) {
				Arco<T> arco = itA.next();
				System.out.println("    " + v + "-> " + arco.getVerticeDestino() + " (" + arco.getEtiqueta() + ")");
			}
		}
	}
	
	public static void cargaAutomatica(Grafo g){
		// TPE\dataTest\DT1.txt
		// TPE\dataTest\DT2.txt
		// TPE\dataTest\DT3.txt
		carga("TPE\\dataTest\\DT3.txt", g);
	}

	public static void cargaConsola(Grafo g){
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Ingrese direccion de enlace");
			String value = entrada.readLine();
			while (value != "./dataTest/DT1.txt" && value != "./dataTest/DT2.txt" && value != "./dataTest/DT1.txt") {
				System.out.println("Respuesta no valida");
				System.out.println("Intente nuevamente");
				value = entrada.readLine();
			}
			carga(value, g);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void carga(String path, Grafo g){
		CSVReader reader = new CSVReader(path);
		reader.read(g);
	}

	public static void main(String[] args) {
		GrafoDirigido<Integer> g = new GrafoDirigido<Integer>();

        // Cargamos un grafo dirigido
		if (args.length>0) {
			String value = "";
			for (String s : args) {
				value+=s;
			}
			carga(value, g);
		}else{
			cargaAutomatica(g);
		}
		
		//Backtracking
		System.out.println("Backtracking:");
		Backtracking b = new Backtracking(g);
		b.backtracking();
	    System.out.println("---------");
		// Greedy
		System.out.println("Greedy: ");
		GreedyArcos greed = new GreedyArcos(g);
		greed.Greedy();
	}
}