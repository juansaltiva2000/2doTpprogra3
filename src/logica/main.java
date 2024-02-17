package logica;

import Controlador.Controlador;
import Interfaz.Mapa;
import Interfaz.Vista;

public class main {
    public static void main(String[] args) { 
    	Mapa mapa = new Mapa();
        Vista view = new Vista();        
        Controlador ctrlPrueba = new Controlador(view,mapa);  			
		ctrlPrueba.iniciar();		
		view.frame.setVisible(true);		
    }
    
}

