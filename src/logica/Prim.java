package logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Prim {
    public List<Localidad> localidades;
    public List<Arista> conexiones;
  //  public List<Arista> conexionesCopia;
    private double costoPorKilometro;
    private double porcentajeAumento;
    private double costoFijoProvincias;
    private double costoTotal;
     public List<Arista> conexionesCopia = new ArrayList<>();

    public Prim(List<Localidad> localidades, double costoPorKilometro, double porcentajeAumento, double costoFijoProvincias) {
        this.localidades = localidades;
        this.costoPorKilometro = costoPorKilometro;
        this.porcentajeAumento = porcentajeAumento;
        this.costoFijoProvincias = costoFijoProvincias;
       
    }

    public List<Arista> solve() {
        List<Arista> conexiones = new ArrayList<>();
        List<Localidad> arbolGeneradorMinimo = new ArrayList<>();
        Set<Localidad> visitados = new HashSet<>();
        
        
        System.out.println(localidades.toString());
        
        Localidad origen = localidades.get(0);
        visitados.add(origen);

        while (visitados.size() < localidades.size()) {
            double distanciaMinima = Double.MAX_VALUE;
            Arista conexionMinima = null;

            for (Localidad localidadVisitada : visitados) {
                for (Localidad localidadNoVisitada : localidades) {
                    if (!visitados.contains(localidadNoVisitada)) {
                        double distancia = calcularDistancia(localidadVisitada, localidadNoVisitada);
                        double costo = calcularCosto(distancia, localidadVisitada, localidadNoVisitada);
                        Arista conexion = new Arista(localidadVisitada, localidadNoVisitada, distancia, costo);
                        if (costo < distanciaMinima) {
                            distanciaMinima = costo;
                            conexionMinima = conexion;
                        }
                    }
                }
            }

            conexiones.add(conexionMinima);
            arbolGeneradorMinimo.add(conexionMinima.getOrigen());
            visitados.add(conexionMinima.getDestino());
            for (Arista a : conexiones) {
            	
            conexionesCopia.add(a);
            }        }

        costoTotal = conexiones.stream().mapToDouble(Arista::getCosto).sum();
        return conexiones;
    }

     double calcularDistancia(Localidad localidadA, Localidad localidadB) {
        // Fórmula para calcular la distancia entre dos puntos geográficos (puede variar según tus necesidades)
        double radioTierra = 6371; // en kilómetros
        double latitudA = Math.toRadians(localidadA.getLatitud());
        double latitudB = Math.toRadians(localidadB.getLatitud());
        double diferenciaLatitudes = Math.toRadians(localidadB.getLatitud() - localidadA.getLatitud());
        double diferenciaLongitudes = Math.toRadians(localidadB.getLongitud() - localidadA.getLongitud());

        double a = Math.sin(diferenciaLatitudes / 2) * Math.sin(diferenciaLatitudes / 2) +
                Math.cos(latitudA) * Math.cos(latitudB) *
                Math.sin(diferenciaLongitudes / 2) * Math.sin(diferenciaLongitudes / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return radioTierra * c;
    }

    double calcularCosto(double distancia, Localidad localidadA, Localidad localidadB) {
        double costo = costoPorKilometro * distancia;
        if (distancia > 300) {
            costo += costo * (porcentajeAumento / 100);
        }
        if (!localidadA.getProvincia().equals(localidadB.getProvincia())) {
            costo += costoFijoProvincias;
        }
        return costo;
    }

    public double getCostoTotal() {
        return costoTotal;
    }
    
    public void agregarLocalidad(Localidad loc) {
    	localidades.add(loc);
    }
    
}


