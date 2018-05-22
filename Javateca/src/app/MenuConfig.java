package app;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MenuConfig extends JFrame {

	private JPanel contentPane;
	private JTextField rutaField;
	private JTextField puertoField;
	private JTextField usuarioField;
	private JCheckBox chkRecordar;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MenuConfig() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 385);
		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Configuraci\u00F3n Javateca 0.1");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 11, 299, 27);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 49, 434, 2);
		contentPane.add(separator);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Metal", "Motif", "System"}));
		comboBox.setBounds(306, 75, 118, 20);
		contentPane.add(comboBox);
		
		JLabel lblEstiloVentanas = new JLabel("Estilo ventanas:");
		lblEstiloVentanas.setBounds(222, 78, 77, 14);
		contentPane.add(lblEstiloVentanas);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 128, 434, 2);
		contentPane.add(separator_1);
		
		JLabel lblInterfaz = new JLabel("Interfaz");
		lblInterfaz.setForeground(Color.DARK_GRAY);
		lblInterfaz.setBounds(10, 56, 46, 14);
		contentPane.add(lblInterfaz);
		
		JLabel lblNewLabel_1 = new JLabel("Credenciales Base de Datos");
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setBounds(10, 141, 133, 14);
		contentPane.add(lblNewLabel_1);
		
		chkRecordar = new JCheckBox("Guardar credenciales");
		chkRecordar.setBackground(Color.LIGHT_GRAY);
		chkRecordar.setBounds(222, 173, 133, 23);
		contentPane.add(chkRecordar);
		
		rutaField = new JTextField();
		rutaField.setToolTipText("La ruta hacia el servidor Oracle. Ejemplo: \"localhost\", \"192.168.1.00\"");
		rutaField.setColumns(10);
		rutaField.setBounds(224, 203, 200, 20);
		contentPane.add(rutaField);
		
		puertoField = new JTextField();
		puertoField.setToolTipText("El puerto de conexion de la BD. Ejemplo: \"1521\"");
		puertoField.setColumns(10);
		puertoField.setBounds(224, 234, 200, 20);
		contentPane.add(puertoField);
		
		usuarioField = new JTextField();
		usuarioField.setToolTipText("El nombre de usuario de la base de datos.");
		usuarioField.setColumns(10);
		usuarioField.setBounds(224, 265, 200, 20);
		contentPane.add(usuarioField);
		
		JLabel lblNewLabel_2 = new JLabel("Ruta");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setBounds(168, 206, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPuerto.setBounds(168, 237, 46, 14);
		contentPane.add(lblPuerto);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsuario.setBounds(168, 268, 46, 14);
		contentPane.add(lblUsuario);
		
		JButton btnAceptar = new JButton("Aceptar");

		btnAceptar.setBounds(335, 312, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnAplicar = new JButton("Aplicar");

		btnAplicar.setBounds(236, 312, 89, 23);
		contentPane.add(btnAplicar);
		
		JLabel lblelCambioRequiere = new JLabel("(El cambio requiere reinicio de la App)");
		lblelCambioRequiere.setHorizontalAlignment(SwingConstants.TRAILING);
		lblelCambioRequiere.setForeground(Color.DARK_GRAY);
		lblelCambioRequiere.setBounds(184, 106, 240, 14);
		contentPane.add(lblelCambioRequiere);
		
		String estado = Configuracion.leerValor("interfaz.estilo");
		
		switch (estado) {
		
		case "Metal":
			
			comboBox.setSelectedIndex(0);
			break;
			
		case "Motif":
			
			comboBox.setSelectedIndex(1);
			break;
			
		case "System":
			
			comboBox.setSelectedIndex(2);
			break;

		default:
			break;
			
		}
		
		if(Configuracion.leerValor("login.recordar").equals("true")) {
			
			chkRecordar.setSelected(true);
			rutaField.setText(Configuracion.leerValor("login.url"));
			puertoField.setText(Configuracion.leerValor("login.puerto"));
			usuarioField.setText(Configuracion.leerValor("login.usuario"));
			
		}
		
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				chequear();
				
			}
		});
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chequear();
				dispose();
				
			}
		});
		
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}
	
	private void chequear() {
		
		if(chkRecordar.isSelected()) {
			
			Configuracion.escribir("login.recordar", "true");
			Configuracion.escribir("login.url", rutaField.getText());
			Configuracion.escribir("login.puerto", puertoField.getText());
			Configuracion.escribir("login.usuario", usuarioField.getText());
			
		}
		
		if(!chkRecordar.isSelected()) {
			
			Configuracion.escribir("login.recordar", "false");
			Configuracion.escribir("login.url", "");
			Configuracion.escribir("login.puerto", "");
			Configuracion.escribir("login.usuario", "");
		}
		
		Configuracion.escribir("interfaz.estilo", comboBox.getSelectedItem().toString());
	}
}
