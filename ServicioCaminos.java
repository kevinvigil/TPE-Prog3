package TPE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		caminosValidos(origen, lim - 1, aux, ans);
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
					List<Integer> aux = new ArrayList<>(li);

					if (!li.contains(value)) {
						caminosValidos(value, limite - 1, aux, ans);
					}
				}
			}
		}
	}
}
