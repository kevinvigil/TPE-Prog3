package TPE.servicios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import TPE.grafo.Arco;
import TPE.grafo.Grafo;

public class ServicioCaminos<T> {

    private Grafo<?> graph;
	private int origen;
	private int destino;
	private int lim;
	private List<List<Integer>> ans;
	
	public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
		this.graph = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
		ans = new ArrayList<List<Integer>>();
	}

	public List<List<Integer>> caminos() {
		List<Integer> li = new ArrayList<>();
		List<Arco> ar = new ArrayList<>();
		li.add(origen);
		caminosValidos(origen, lim, li, ar);
		return this.ans;
	}

	private void caminosValidos(int ori, int limite, List<Integer> li, List<Arco> ar){
		if (ori == destino) {
			this.ans.add(new ArrayList<>(li));
		} else {
			if (limite > ar.size()) {
				Iterator<Integer> curr = graph.obtenerAdyacentes(ori);
				while (curr.hasNext()) {
					Integer value = curr.next();
					Arco arco = graph.obtenerArco(ori, value);
					if (!ar.contains(arco)) {
						ar.add(arco);
						li.add(value);
						caminosValidos(value, limite, li, ar);
						li.remove(value);
						ar.remove(arco);
					}
					
				}
			}
		}
	}
}
