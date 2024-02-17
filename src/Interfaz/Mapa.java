package Interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Mapa
{

	public JFrame frame;
	private JPanel panelMapa;
	private JMapViewer _mapa;
	private ArrayList<Coordinate> _lasCoordenadas;
	private ArrayList<Coordinate> Coord;
	private MapPolygonImpl _poligono;
	public JTextArea txtRes;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					Mapa window = new Mapa();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Mapa() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panelMapa = new JPanel();
		panelMapa.setBounds(10, 104, 437, 446);
		frame.getContentPane().add(panelMapa);
		
		_mapa = new JMapViewer();
		Coordinate argentina = new Coordinate(-34.603722, -58.381592);
		_mapa.setDisplayPosition(argentina, 4);
		
				
		panelMapa.add(_mapa);
		
		txtRes = new JTextArea();
		
		txtRes.setAlignmentX(JTextField.CENTER);
		txtRes.setAlignmentY(JTextField.CENTER);
		txtRes.setFont(new Font("Amatic SC", Font.BOLD, 30));
		txtRes.setText("test");
		txtRes.setEditable(false);
		txtRes.setBounds(446, 112, 328, 393);
		frame.getContentPane().add(txtRes);

		detectarCoordenadas();
		dibujarPoligono();
		eliminarPoligono();		
	}
	
	private void detectarCoordenadas() 
	{
		_lasCoordenadas = new ArrayList<Coordinate>();
				
		_mapa.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
			if (e.getButton() == MouseEvent.BUTTON1)
			{
				Coordinate markeradd = (Coordinate)
				_mapa.getPosition(e.getPoint());
				_lasCoordenadas.add(markeradd);
				String nombre = JOptionPane.showInputDialog("Nombre: ");
				_mapa.addMapMarker(new MapMarkerDot(nombre, markeradd));
			}}
		});
	}
	
	public void cargarPuntosMapa(String nombre, double latitud, double longitud) 
	{
				Coordinate markeradd = new Coordinate(latitud, longitud);	
				_mapa.addMapMarker(new MapMarkerDot(nombre, markeradd));
				
	}	
	
	// esto esta mal tiene que trabajar con una lista de coordenadas y ahi recorrer e imprimir (tenemos que pasar un ArrayList<Coordinate> elem)
	// adentro de ese tiene que estar convertidas las lat y long en coordinate! (!!)
	public void dibujar1Poligono(double latitud, double longitud,double latitud2, double longitud2) {

				List<Coordinate> coordinates = new ArrayList<>();
				coordinates.add(new Coordinate(latitud,longitud));
				coordinates.add(new Coordinate(latitud2,longitud2));
				coordinates.add(new Coordinate(latitud,longitud));
				MapPolygonImpl line = new MapPolygonImpl(coordinates);
				line.setBackColor(Color.RED);
				line.setBackColor(Color.BLUE);
				_mapa.addMapPolygon(line);		
				
				
			}
		
	

	private void dibujarPoligono() 
	{
	}



	private void eliminarPoligono() 
	{
	}	
}
