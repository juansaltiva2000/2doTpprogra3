package logica;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;





public class TestPrim {
	 private List<Localidad> localidades;
	    private double costoPorKilometro;
	    private double porcentajeAumento;
	    private double costoFijoProvincias;


	 @Before
	    public void setUp() {
	        localidades = new ArrayList<>();
	        // Agrega las localidades de Argentina necesarias para las pruebas
	        localidades.add(new Localidad("Buenos Aires","Buenos Aires", -34.6037, -58.3816));
	        localidades.add(new Localidad("Córdoba","Córdoba", -31.4167, -64.1833));
	        localidades.add(new Localidad("Rosario","Rosario" ,-32.9468, -60.6393));

	        costoPorKilometro = 0.5;
	        porcentajeAumento = 10;
	        costoFijoProvincias = 100;
	    }

	    @Test
	    public void testSolve() {
	        Prim prim = new Prim(localidades, costoPorKilometro, porcentajeAumento, costoFijoProvincias);
	        List<Arista> conexiones = prim.solve();

	        Assert.assertEquals(2, conexiones.size());

	        // Verificar las conexiones individuales
	        Arista conexion1 = conexiones.get(0);
	       Assert.assertEquals("Buenos Aires", conexion1.getOrigen().getNombre());
	       Assert.assertEquals("Rosario", conexion1.getDestino().getNombre());

	       Arista conexion2 = conexiones.get(1);
	       Assert.assertEquals("Rosario", conexion2.getOrigen().getNombre());
	       Assert.assertEquals("Córdoba", conexion2.getDestino().getNombre());
	    }

	    @Test
	    public void testCalcularDistancia() {
	        Localidad buenosAires = new Localidad("Buenos Aires","Buenos Aires", -34.6037, -58.3816);
	        Localidad cordoba = new Localidad("Córdoba","Córdoba", -31.4167, -64.1833);

	        Prim prim = new Prim(localidades, costoPorKilometro, porcentajeAumento, costoFijoProvincias);
	        double distancia = prim.calcularDistancia(buenosAires, cordoba);

	        Assert.assertEquals(636.748, distancia, 0.001);
	    }

	    @Test
	    public void testCalcularCosto() {
	        Localidad buenosAires = new Localidad("Buenos Aires","Buenos Aires", -34.6037, -58.3816);
	        Localidad cordoba = new Localidad("Córdoba","Córdoba", -31.4167, -64.1833);
	        double distancia = 100; // Valor de ejemplo

	        Prim prim = new Prim(localidades, costoPorKilometro, porcentajeAumento, costoFijoProvincias);
	        double costo = prim.calcularCosto(distancia, buenosAires, cordoba);

	        Assert.assertEquals(50.0, costo, 0.001);
	    }

}
