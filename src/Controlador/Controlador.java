package Controlador;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import Interfaz.Mapa;
import Interfaz.TextPrompt;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import logica.*;
import Interfaz.Vista;

public class Controlador implements ActionListener, KeyListener, MouseListener {
	private Mapa map;
	private Vista view;
	private Prim p;
	boolean menuUp = true;
	//JMapViewer mapa;

	public Controlador(Vista view, Mapa map) {
		this.view = view;
		this.map  = map;
		//mapa = new JMapViewer();
		map.txtRes.append(null);
		
		this.view.btnComenzar.addActionListener(this);
		this.view.btnComenzar.addMouseListener(this);
		this.view.btnAgregar.addMouseListener(this);
		this.view.btnCalcular.addMouseListener(this);
		this.view.btnAgregar.addActionListener(this);
		this.view.btnCalcular.addActionListener(this);
		List<Localidad> listaLocalidades = new ArrayList<>();
		this.p = new Prim(listaLocalidades, 0.5, 10, 100);
		
	}

	@SuppressWarnings("unused") // Placeholders Warning
	public void iniciar() {
		map.frame.setVisible(false);
		view.frame.setTitle("Conectando Localidades");
		view.frame.setLocationRelativeTo(null);
		TextPrompt placeHolderProvincia = new TextPrompt("Inserte una Provincia", view.txtProvincia);
		TextPrompt placeHolderCiudad = new TextPrompt("Inserte una Ciudad", view.txtCiudad);
		TextPrompt placeHolderLatitud = new TextPrompt("Inserte la Latitud", view.txtLatitud);
		TextPrompt placeHolderLongitud = new TextPrompt("Inserte la Longitud", view.txtLongitud);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Inicio
		while (menuUp) {
			seteoMenu();
			if (e.getSource() == this.view.btnComenzar) {
				seteoPantallaPrincipal();
			}
		}

		// Pantalla Principal Agregar Ciudades
		if (e.getSource() == this.view.btnAgregar) {
		    String ciudad = view.txtCiudad.getText();
		    String provincia = view.txtProvincia.getText();
		    String latitudText = view.txtLatitud.getText();
		    String longitudText = view.txtLongitud.getText();

		    if (ciudad.isEmpty() || provincia.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Los campos de ciudad y provincia no pueden estar vacíos", "Error", JOptionPane.ERROR_MESSAGE);
		        throw new IllegalArgumentException("campos vacios");
		    } else if (latitudText.isEmpty() || longitudText.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Los campos de latitud y longitud no pueden estar vacíos", "Error", JOptionPane.ERROR_MESSAGE);
		        throw new IllegalArgumentException("coordenadas vacias");
		    } else {
		        try {
		            double latitud = Double.parseDouble(latitudText);
		            double longitud = Double.parseDouble(longitudText);
		            Localidad localidad = new Localidad(ciudad, provincia, latitud, longitud);
		            p.agregarLocalidad(localidad);
		            System.out.println("localidad agregada:" + p.localidades.toString());
		            seteoPantallaLimpia();
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Los valores de latitud y longitud deben ser números", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		}

		// Pantalla Principal Calcular Total
		if (e.getSource() == this.view.btnCalcular) {
		    if (p.localidades.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Debe agregar al menos una ciudad antes de calcular", "Error", JOptionPane.ERROR_MESSAGE);
		    } else {
		        p.solve();
		        cargarEnMapa();
		        seteoPantalla2();
		      
		        map.txtRes.append(p.conexionesCopia.toString());
		        System.out.println(p.solve());
		        cargarPoligonos();
		        p.conexionesCopia.toString();
		    }
		}
	}

	private void cargarPoligonos() {
		
		for (Arista a : p.conexionesCopia) {
			map.dibujar1Poligono(a.getOrigen().getLatitud(),a.getOrigen().getLongitud(),a.getDestino().getLatitud(),a.getDestino().getLongitud());
		}
	}

	private void cargarEnMapa() {
		for (Localidad l : p.localidades) {
			map.cargarPuntosMapa(l.getNombre(), l.getLatitud(), l.getLongitud());
		}
	
		
	}

	// Seteo de pantalla
	private void seteoPantallaLimpia() {
		view.txtCiudad.setText(null);
		view.txtProvincia.setText(null);
		view.txtLatitud.setText(null);
		view.txtLongitud.setText(null);
	}

	private void seteoMenu() {
		view.fondo.setIcon(new ImageIcon(Vista.class.getResource("/Recursos/FondoTp2.jpg")));
		view.btnComenzar.setVisible(true);
		view.btnComenzar.setBorder(null);
	}

	private void seteoPantallaPrincipal() {
		menuUp = false;
		view.fondo.setIcon(new ImageIcon(Vista.class.getResource("/Recursos/FONDO.jpg")));
		view.btnComenzar.setVisible(false);
		view.btnAgregar.setVisible(true);
		view.btnCalcular.setVisible(true);
		view.txtCiudad.setVisible(true);
		view.txtProvincia.setVisible(true);
		view.txtLatitud.setVisible(true);
		view.txtLongitud.setVisible(true);
	}

	private void seteoPantalla2() {
		map.frame.setVisible(true);
		view.frame.setVisible(false);
		view.fondo.setIcon(new ImageIcon(Vista.class.getResource("/Recursos/PRUEBAAAAAA.jpg")));
		view.btnComenzar.setVisible(false);
		view.btnAgregar.setVisible(false);
		view.btnCalcular.setVisible(false);
		view.txtCiudad.setVisible(false);
		view.txtProvincia.setVisible(false);
		view.txtLatitud.setVisible(false);
		view.txtLongitud.setVisible(false);
	}

	// Sobrescritura de los metodos no implementados de las interfaces

	@Override
	public void mouseEntered(MouseEvent e) {
		view.btnComenzar.setCursor((new Cursor(Cursor.HAND_CURSOR)));
		view.btnAgregar.setCursor((new Cursor(Cursor.HAND_CURSOR)));
		view.btnCalcular.setCursor((new Cursor(Cursor.HAND_CURSOR)));

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
