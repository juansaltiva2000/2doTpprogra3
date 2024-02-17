package logica;

import java.util.Objects;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Localidad {
	private String nombre;
	private Coordinate cordenada;
	private String provincia;

	public Localidad(String nombre,String provincia,double latitud,double longitud) {
		this.nombre = nombre;
		cordenada = new Coordinate(latitud, longitud);
		this.provincia = provincia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getLatitud() {
		return cordenada.getLat();
	}

	public double getLongitud() {
		return cordenada.getLon();
	}



	public Coordinate getCordenada() {
		return cordenada;
	}

	public void setCordenada(Coordinate cordenada) {
		this.cordenada = cordenada;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Localidad localidad = (Localidad) o;
		return Double.compare(localidad.getLongitud(), getLongitud()) == 0 &&
				Double.compare(localidad.getLatitud(), getLatitud()) == 0 &&
				Objects.equals(nombre, localidad.nombre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, getLongitud(), getLatitud());
	}

	@Override
	public String toString() {
		return nombre;
	}

	
}
