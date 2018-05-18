package app;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

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
	public LoginBD() {
		setResizable(false);
		setTitle("Libreria Java - Conexi\u00F3n BD");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginBD.class.getResource("/resources/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(LoginBD.class.getResource("/resources/OracleDB.png")));
		label.setBounds(80, 0, 223, 220);
		contentPane.add(label);
		
		JLabel lblOracleLogin = new JLabel("Oracle Login");
		lblOracleLogin.setFont(new Font("Source Sans Pro", Font.BOLD, 23));
		lblOracleLogin.setBounds(128, 218, 128, 30);
		contentPane.add(lblOracleLogin);
		
		rutaField = new JTextField();
		rutaField.setText("SERVER2012");
		rutaField.setToolTipText("La ruta hacia el servidor Oracle. Ejemplo: \"localhost\", \"192.168.1.00\"");
		rutaField.setBounds(128, 268, 200, 20);
		contentPane.add(rutaField);
		rutaField.setColumns(10);
		
		JLabel lblRuta = new JLabel("Ruta");
		lblRuta.setFont(new Font("Source Sans Pro", Font.BOLD, 14));
		lblRuta.setBounds(89, 270, 29, 14);
		contentPane.add(lblRuta);
		
		puertoField = new JTextField();
		puertoField.setText("1521");
		puertoField.setToolTipText("El puerto de conexion de la BD. Ejemplo: \"1521\"");
		puertoField.setColumns(10);
		puertoField.setBounds(128, 299, 200, 20);
		contentPane.add(puertoField);
		
		usuarioField = new JTextField();
		usuarioField.setText("LIBRERIA");
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
				
				try {
					Connection conn = ConBD.conectar(rutaField.getText(), puertoField.getText(), usuarioField.getText(), passField.getText());

					PruebaBD.chorrada(conn);
					
					dispose();
				
				} catch (NullPointerException e) {
					System.out.println("Error de conexion: "+e.getMessage());
				}
				
			}
		});
		btnNewButton.setFont(new Font("Source Sans Pro", Font.BOLD, 17));
		btnNewButton.setBounds(131, 410, 121, 23);
		contentPane.add(btnNewButton);
		this.setLocationRelativeTo(null);
	}
}