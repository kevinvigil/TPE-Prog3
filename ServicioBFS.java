package TPE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ServicioBFS {
    private Grafo<?> grafo;
	
	public ServicioBFS(Grafo<?> grafo) {
		this.grafo = grafo;
	}
	
	public List<Integer> bfsForest() {
		List<Integer> ans = new ArrayList<>();
        Iterator<Integer> g = grafo.obtenerVertices();
        while (g.hasNext()) {
            Integer curr = g.next();
            if (!ans.contains(curr)) {
                BFS(curr, ans);
            }
        }
        return ans;
	}  

    private void BFS(Integer vert, List<Integer> ans) {
        List<Integer> next = new LinkedList<>();
        ans.add(vert);
        next.add(vert);
        while (!next.isEmpty()) {
            Iterator<Integer> arcos = grafo.obtenerAdyacentes(next.remove(0));
            while (arcos.hasNext()) {
                Integer curr = arcos.next();
                if (!ans.contains(curr)) {
                    ans.add(curr);
                    next.add(curr);
                }
            }
        }
    }
}