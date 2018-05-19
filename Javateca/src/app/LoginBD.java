package app;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import otros.PruebaBD;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JCheckBox;

public class LoginBD extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField rutaField;
	private JTextField puertoField;
	private JTextField usuarioField;
	private JPasswordField passField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginBD frame = new LoginBD();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public void login() {
		
		try {
			/*
			Usar "getText" en una contrase�a es mala practica, ya que funciona con Strings. Una String es inmutable, y aunque la intentes cambiar
			despues de usarla, siempre quedara grabada en la pool de Strings hasta que el recolector de basura la elimine.
			En ese lapso de tiempo, hay programas maliciosos que pueden capturarla.
			Pero implementar una contrase�a mas segura es una tarea compleja y por motivo de falta de tiempo, usare getText por el momento.
			 */
			@SuppressWarnings("deprecation")
			Connection conn = ConBD.conectar(rutaField.getText(), puertoField.getText(), usuarioField.getText(), passField.getText());

			PruebaBD.chorrada(conn);
			
			dispose();
			
			try {
				Splash splash = new Splash();
				splash.setVisible(true);
				splash.setBackground(new Color(0,0,0,0));
				
				TimeUnit.SECONDS.sleep(2);
				
				splash.dispose();
				
				LibrosGUI app = new LibrosGUI();
				app.setVisible(true);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
		} catch (NullPointerException e) {
			System.out.println("Error de conexion: "+e.getMessage());
		}
		
	}
	
	public LoginBD() {
		
		setResizable(false);
		setTitle("Libreria Java - Conexi\u00F3n BD");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginBD.class.getResource("/resources/JavatecaIcon1.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(LoginBD.class.getResource("/resources/OracleDB.png")));
		label.setBounds(85, 0, 223, 220);
		contentPane.add(label);
		
		JLabel lblOracleLogin = new JLabel("Oracle Login");
		lblOracleLogin.setFont(new Font("Source Sans Pro", Font.BOLD, 23));
		lblOracleLogin.setBounds(133, 218, 128, 30);
		contentPane.add(lblOracleLogin);
		
		rutaField = new JTextField();
		rutaField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				login();
				
			}
		});
		rutaField.setText("www.navunov.es");
		rutaField.setToolTipText("La ruta hacia el servidor Oracle. Ejemplo: \"localhost\", \"192.168.1.00\"");
		rutaField.setBounds(128, 268, 200, 20);
		contentPane.add(rutaField);
		rutaField.setColumns(10);
		
		JLabel lblRuta = new JLabel("Ruta");
		lblRuta.setFont(new Font("Source Sans Pro", Font.BOLD, 14));
		lblRuta.setBounds(89, 270, 29, 14);
		contentPane.add(lblRuta);
		
		puertoField = new JTextField();
		puertoField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				login();
				
			}
		});
		puertoField.setText("1521");
		puertoField.setToolTipText("El puerto de conexion de la BD. Ejemplo: \"1521\"");
		puertoField.setColumns(10);
		puertoField.setBounds(128, 299, 200, 20);
		contentPane.add(puertoField);
		
		usuarioField = new JTextField();
		usuarioField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				login();
				
			}
		});
		usuarioField.setText("BIBLIOTECA");
		usuarioField.setToolTipText("El nombre de usuario de la base de datos.");
		usuarioField.setColumns(10);
		usuarioField.setBounds(128, 330, 200, 20);
		contentPane.add(usuarioField);
		
		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setFont(new Font("Source Sans Pro", Font.BOLD, 14));
		lblPuerto.setBounds(76, 302, 42, 14);
		contentPane.add(lblPuerto);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Source Sans Pro", Font.BOLD, 14));
		lblUsuario.setBounds(70, 333, 48, 14);
		contentPane.add(lblUsuario);
		
		passField = new JPasswordField();
		passField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				login();
				
			}
		});
		passField.setToolTipText("La contrase\u00F1a del usuario en la base de datos");
		passField.setColumns(10);
		passField.setBounds(128, 361, 200, 20);
		contentPane.add(passField);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Source Sans Pro", Font.BOLD, 14));
		lblContrasea.setBounds(48, 364, 70, 14);
		contentPane.add(lblContrasea);
		
		JButton btnNewButton = new JButton("Conectar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				login();
				
			}
		});
		btnNewButton.setFont(new Font("Source Sans Pro", Font.BOLD, 17));
		btnNewButton.setBounds(136, 424, 121, 23);
		contentPane.add(btnNewButton);
		
		JCheckBox chkRecordar = new JCheckBox("Recordar datos");
		chkRecordar.setFont(new Font("Arial", Font.PLAIN, 11));
		chkRecordar.setToolTipText("Cuando esta marcado, guardara los datos de conexi\u00F3n (menos la contrase\u00F1a) para el proximo inicio");
		chkRecordar.setBounds(128, 388, 101, 23);
		contentPane.add(chkRecordar);
		this.setLocationRelativeTo(null);
	}
}