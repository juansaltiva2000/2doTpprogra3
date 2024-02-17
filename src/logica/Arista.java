package logica;

public class Arista {
	private  Localidad origen;
	private  Localidad destino;
	private  double peso;
	private  double costo;

	public Arista(Localidad origen, Localidad destino,double peso, double costo) {
		this.origen = origen;
		this.destino = destino;
		this.peso = peso;
		this.costo = costo;
	}

	// Resto de la implementación de la clase Arista

	public Localidad getOrigen() {
		return origen;
	}

	public Localidad getDestino() {
		return destino;
	}

	public double getCosto() {
		return costo;
	}

	public double getPeso() {
		return peso;
	}

	@Override
	public String toString() {
		return toStrin().toString();
	}
	public StringBuilder toStrin() {
		StringBuilder sb = new StringBuilder(); 
		sb.append(origen);
		sb.append("\n");
		sb.append(destino);
		sb.append("\n");
		sb.append(peso);
		sb.append("\n");
		//sb.append();
		return sb;
		//return "Arista [origen=" + origen + ", destino=" + destino + ", peso=" + peso + "]";
	}



}