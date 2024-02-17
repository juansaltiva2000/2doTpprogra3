package Interfaz;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import javax.swing.JTextField;

public class Vista {

	public JFrame frame;
	public JMapViewer mapa;
	public JLabel fondo;
	public JButton btnComenzar;
	public JButton btnCalcular;
	public JButton btnAgregar;
	public JTextField txtCiudad;
	public JTextField latitudInput;
	public JTextField longitudInput;
	public JTextField txtLatitud;
	public JTextField txtLongitud;
	public JTextField txtProvincia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista window = new Vista();
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
	public Vista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Conectando Localidades");
		frame.getContentPane().setLayout(null);
		
				btnCalcular = new JButton("");
				btnCalcular.setBorder(null);
				btnCalcular.setBounds(432, 362, 162, 54);
				frame.getContentPane().add(btnCalcular);
				btnCalcular.setIcon(new ImageIcon(Vista.class.getResource("/Recursos/BotonCalcular.jpg")));
				btnCalcular.setVisible(false);

		// *SECTORBOTONES*
		btnComenzar = new JButton("");
		btnComenzar.setIcon(new ImageIcon(Vista.class.getResource("/Recursos/BotonComenzar.jpg")));
		btnComenzar.setBounds(474, 361, 217, 84);
		btnComenzar.setBorder(null);
		frame.getContentPane().add(btnComenzar);

		btnAgregar = new JButton("");
		btnAgregar.setBounds(241, 364, 162, 53);
		frame.getContentPane().add(btnAgregar);
		btnAgregar.setBorder(null);
		btnAgregar.setIcon(new ImageIcon(Vista.class.getResource("/Recursos/BotonAgregar.jpg")));
		btnAgregar.setVisible(false);

		//SECTOR TXT:
		txtProvincia = new JTextField();
		txtProvincia.setColumns(10);
		txtProvincia.setBorder(null);
		txtProvincia.setBackground(new Color(110, 223, 195));
		txtProvincia.setBounds(318, 119, 186, 24);
		frame.getContentPane().add(txtProvincia);
		txtProvincia.setVisible(false);

		txtCiudad = new JTextField();
		txtCiudad.setBounds(318, 175, 186, 24);
		frame.getContentPane().add(txtCiudad);
		txtCiudad.setColumns(10);
		txtCiudad.setBorder(null);
		txtCiudad.setBackground(new Color(110, 223, 195));
		txtCiudad.setVisible(false);

		txtLatitud = new JTextField();
		txtLatitud.setColumns(10);
		txtLatitud.setBorder(null);
		txtLatitud.setBackground(new Color(110, 223, 195));
		txtLatitud.setBounds(318, 235, 186, 24);
		frame.getContentPane().add(txtLatitud);
		txtLatitud.setVisible(false);

		txtLongitud = new JTextField();
		txtLongitud.setForeground(Color.DARK_GRAY);
		txtLongitud.setColumns(10);
		txtLongitud.setBorder(null);
		txtLongitud.setBackground(new Color(110, 223, 195));
		txtLongitud.setBounds(318, 295, 186, 24);
		frame.getContentPane().add(txtLongitud);
		txtLongitud.setVisible(false);

		fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(Vista.class.getResource("/Recursos/FondoTp2.jpg")));
		fondo.setBounds(0, 0, 784, 561);
		frame.getContentPane().add(fondo);

	}
}
