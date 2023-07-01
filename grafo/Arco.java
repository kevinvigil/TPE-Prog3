package TPE.grafo;

public class Arco<T> {
    private int verticeOrigen;
	private int verticeDestino;
	private T etiqueta;

	public Arco(int verticeOrigen, int verticeDestino, T etiqueta) {
		this.verticeOrigen = verticeOrigen;
		this.verticeDestino = verticeDestino;
		this.etiqueta = etiqueta;
	}
	
	public int getVerticeOrigen() {
		return verticeOrigen;
	}

    public void setVerticeOrigen(int verticeOrigen) {
        this.verticeOrigen = verticeOrigen;
    }
	
	public int getVerticeDestino() {
		return verticeDestino;
	}

    public void setVerticeDestino(int verticeDestino) {
        this.verticeDestino = verticeDestino;
    }

	public T getEtiqueta() {
		return etiqueta;
	}

    public void setEtiqueta(T etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public String toString() {
        return "vertice Origen: "+verticeOrigen + "| vertice Destino: "+ verticeDestino;
    }

	public boolean equals(Object o){
		Arco<T> value = (Arco)o;
		if (this.verticeOrigen == value.getVerticeOrigen() && this.getVerticeDestino() == value.getVerticeDestino()) {
			return true;
		}
		return false;
	}
}
