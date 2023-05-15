package TPE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServicioDFS {
    private Grafo<?> grafo;

	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
	}
	
	public List<Integer> dfsForest() {
		List<Integer> ans = new ArrayList<>();
        Iterator<Integer> g = grafo.obtenerVertices();
        while (g.hasNext()) {
            Integer curr = g.next();
            if (!ans.contains(curr)) {
                ans.add(curr);
                DFS(curr, ans);
                
            }
        }
        return ans;
	}

    private void DFS(Integer vert, List<Integer> ans) {
        Iterator<Integer> arcos = grafo.obtenerAdyacentes(vert);
        while (arcos.hasNext()) {
            Integer curr = arcos.next();
            if (!ans.contains(curr)) {
                ans.add(curr);
                DFS(curr, ans);
            }
        }
    }
}
