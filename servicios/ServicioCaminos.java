package TPE.servicios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import TPE.grafo.Grafo;

public class ServicioCaminos {

    private Grafo<?> grafo;
	private int origen;
	private int destino;
	private int lim;
	
	public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
	}

	public List<List<Integer>> caminos() {
		List<List<Integer>> ans = new ArrayList<>();
		List<Integer> aux = new ArrayList<>();
		caminosValidos(origen, lim, aux, ans);
		return ans;
	}

	private void caminosValidos(int ori, int limite, List<Integer> li, List<List<Integer>> ans){
		li.add(ori);
		if (ori == destino) {
			ans.add(li);

		} else {
			if (limite > 0) {
				Iterator<Integer> curr = grafo.obtenerAdyacentes(ori);

				while (curr.hasNext()) {
					Integer value = curr.next();
					int ind = li.indexOf(value);
					
					if (ind == -1 || li.indexOf(ind - 1) != ori) {
						List<Integer> aux = new ArrayList<>(li);
						caminosValidos(value, limite - 1, aux, ans);
					}
				}
			}
		}
	}
}
