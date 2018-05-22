package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.CardLayout;
import java.awt.Desktop;
import javax.swing.Box;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

@SuppressWarnings("serial")
public class LibrosGUI extends JFrame {
	
	private JMenuItem menuArPreferencias;
	private JMenuItem menuArSalir;
	private JMenuItem mntmGestLibros;
	private JMenuItem mntmLibrosPdf;
	private JMenuItem mntmGestCds;
	private JMenuItem mntmCDPdf;
	private JMenuItem mntmGestRevistas;
	private JMenuItem menuAyuGithub;
	private JMenuItem menuAyuDocu;
	private JMenuItem mntmRevistasPdf;
	private JMenuItem menuAyuLicencia;
	private JMenuBar menuBar;
	private JMenu mnMenu;
	private JMenu mnMateriales;
	private JMenu mnLibros;
	private JMenu mnCD;
	private JMenu mnRevistas;
	private JMenu mnNewMenu;
	private static JButton btnAtras;
	private static CardLayout cl;
	private JPanel panelVacio;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_1;
	private static JPanel contentPane;
	private JMenu mnUsuariosYPrestamos;
	private JMenu mnUsuarios;
	private JMenuItem mntmGestionarUsuarios;
	private JMenuItem mntmUsuariosPdf;
	private JMenu mnPrestamos;
	private JMenuItem mntmGestionarPrestamos;
	private JMenuItem mntmPrestamosPdf;
	
	

	/**
	 * Create the frame.
	 */
	public LibrosGUI(Connection conn) {
		
		try {
			
			String lf = Configuracion.leerValor("interfaz.estilo");
			
			switch (lf) {
			case "Metal":
				
				lf = UIManager.getCrossPlatformLookAndFeelClassName();
				break;
				
			case "Motif":
				
				lf = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
				break;
				
			case "System":
				
				lf = UIManager.getSystemLookAndFeelClassName();
				break;

			default:
				break;
			}
			
			UIManager.setLookAndFeel(lf);
			
			
			
			UIManager.getDefaults().put("Button.showMnemonics", Boolean.TRUE);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(LibrosGUI.class.getResource("/resources/JavatecaIcon1.png")));
		setTitle("Javateca 0.1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnMenu = new JMenu("Archivo");
		mnMenu.setMnemonic('A');
		menuBar.add(mnMenu);
		
		menuArPreferencias = new JMenuItem("Preferencias");
	
		menuArPreferencias.setIcon(new ImageIcon(LibrosGUI.class.getResource("/resources/config.png")));
		mnMenu.add(menuArPreferencias);
		
		menuArSalir = new JMenuItem("Salir");

		menuArSalir.setIcon(new ImageIcon(LibrosGUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		mnMenu.add(menuArSalir);
		
		mnMateriales = new JMenu("Materiales");
		mnMateriales.setMnemonic('M');
		menuBar.add(mnMateriales);
		
		mnLibros = new JMenu("Libros");
		mnMateriales.add(mnLibros);
		
		mntmGestLibros = new JMenuItem("Gestionar libros");
		mntmGestLibros.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));

		mnLibros.add(mntmGestLibros);
		
		mntmLibrosPdf = new JMenuItem("Informe (PDF)");

		mntmLibrosPdf.setIcon(new ImageIcon(LibrosGUI.class.getResource("/resources/pdf16.png")));
		mnLibros.add(mntmLibrosPdf);
		
		mnCD = new JMenu("CD-ROMs");
		mnMateriales.add(mnCD);
		
		mntmGestCds = new JMenuItem("Gestionar CDs");

		mntmGestCds.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mnCD.add(mntmGestCds);
		
		mntmCDPdf = new JMenuItem("Informe (PDF)");

		mntmCDPdf.setIcon(new ImageIcon(LibrosGUI.class.getResource("/resources/pdf16.png")));
		mnCD.add(mntmCDPdf);
		
		mnRevistas = new JMenu("Revistas");
		mnMateriales.add(mnRevistas);
		
		mntmGestRevistas = new JMenuItem("Gestionar revistas");

		mntmGestRevistas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mnRevistas.add(mntmGestRevistas);
		
		mntmRevistasPdf = new JMenuItem("Informe (PDF)");
		mntmRevistasPdf.setIcon(new ImageIcon(LibrosGUI.class.getResource("/resources/pdf16.png")));
		mnRevistas.add(mntmRevistasPdf);
		
		mnUsuariosYPrestamos = new JMenu("Usuarios y prestamos");
		mnUsuariosYPrestamos.setMnemonic('U');
		menuBar.add(mnUsuariosYPrestamos);
		
		mnUsuarios = new JMenu("Usuarios");
		mnUsuariosYPrestamos.add(mnUsuarios);
		
		mntmGestionarUsuarios = new JMenuItem("Gestionar usuarios");

		mntmGestionarUsuarios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));
		mnUsuarios.add(mntmGestionarUsuarios);
		
		mntmUsuariosPdf = new JMenuItem("Informe (PDF)");
		mntmUsuariosPdf.setIcon(new ImageIcon(LibrosGUI.class.getResource("/resources/pdf16.png")));
		mnUsuarios.add(mntmUsuariosPdf);
		
		mnPrestamos = new JMenu("Prestamos");
		mnUsuariosYPrestamos.add(mnPrestamos);
		
		mntmGestionarPrestamos = new JMenuItem("Gestionar prestamos");
		mntmGestionarPrestamos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		mnPrestamos.add(mntmGestionarPrestamos);
		
		mntmPrestamosPdf = new JMenuItem("Informe (PDF)");
		mntmPrestamosPdf.setIcon(new ImageIcon(LibrosGUI.class.getResource("/resources/pdf16.png")));
		mnPrestamos.add(mntmPrestamosPdf);
		
		mnNewMenu = new JMenu("Ayuda");
		mnNewMenu.setMnemonic('y');
		menuBar.add(mnNewMenu);
		
		menuAyuGithub = new JMenuItem("GitHub");

		menuAyuGithub.setIcon(new ImageIcon(LibrosGUI.class.getResource("/resources/gitHub1.png")));
		mnNewMenu.add(menuAyuGithub);
		
		menuAyuDocu = new JMenuItem("Documentaci\u00F3n");
		menuAyuDocu.setIcon(new ImageIcon(LibrosGUI.class.getResource("/resources/help.png")));
		mnNewMenu.add(menuAyuDocu);
		
		menuAyuLicencia = new JMenuItem("Licencia");
		mnNewMenu.add(menuAyuLicencia);
		
		btnAtras = new JButton("Atras");
		btnAtras.setMnemonic('s');

		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(btnAtras);
		btnAtras.setVisible(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		cl = new CardLayout();
		contentPane.setLayout(cl);
		
		panelVacio = new JPanel();
		contentPane.add(panelVacio, "menuVacio");
		SpringLayout sl_panelVacio = new SpringLayout();
		panelVacio.setLayout(sl_panelVacio);
		
		lblNewLabel_2 = new JLabel("Javateca 0.1");
		lblNewLabel_2.setBackground(Color.WHITE);
		sl_panelVacio.putConstraint(SpringLayout.WEST, lblNewLabel_2, 28, SpringLayout.WEST, panelVacio);
		sl_panelVacio.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, -10, SpringLayout.SOUTH, panelVacio);
		lblNewLabel_2.setFont(new Font("Sitka Banner", Font.PLAIN, 29));
		panelVacio.add(lblNewLabel_2);
		
		lblNewLabel_1 = new JLabel("");
		sl_panelVacio.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, 0, SpringLayout.SOUTH, panelVacio);
		sl_panelVacio.putConstraint(SpringLayout.EAST, lblNewLabel_1, 0, SpringLayout.EAST, panelVacio);
		lblNewLabel_1.setIcon(new ImageIcon(LibrosGUI.class.getResource("/resources/fondoLibros.png")));
		panelVacio.add(lblNewLabel_1);
		
		contentPane.add(new GestionLibros().gestion(conn),"gestLibros");
		contentPane.add(new GestionCd().gestion(conn),"gestCd");
		contentPane.add(new GestionRevistas().gestion(conn),"gestRev");
		contentPane.add(new GestionUsuarios().gestion(conn),"gestUsu");
		contentPane.add(new GestionPrestamos().gestion(conn,"%"),"gestPres");
		
		menuAyuGithub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
			        Desktop.getDesktop().browse(new URL("https://github.com/jcgadea/Javateca").toURI());
			    } catch (Exception ex) {
			        ex.printStackTrace();
			    }
				
			}
		});
		
		mntmGestLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cl.show(contentPane, "gestLibros");
				setTitle("Javateca - Gestión de libros");
				btnAtras.setVisible(true);
				
			}
		});
		
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cl.show(contentPane, "menuVacio");
				setTitle("Javateca 0.1");
				btnAtras.setVisible(false);
			}
		});
		
		mntmGestCds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cl.show(contentPane, "gestCd");
				setTitle("Javateca - Gestión de CD-ROMs");
				btnAtras.setVisible(true);
				
			}
		});
		
		mntmGestRevistas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cl.show(contentPane, "gestRev");
				setTitle("Javateca - Gestión de revistas");
				btnAtras.setVisible(true);
				
			}
		});
		
		mntmGestionarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cl.show(contentPane, "gestUsu");
				setTitle("Javateca - Gestión de usuarios");
				btnAtras.setVisible(true);
				
			}
		});
		
		mntmGestionarPrestamos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				cl.show(contentPane, "gestPres");
				setTitle("Javateca - Gestión de préstamos");
				btnAtras.setVisible(true);
				
			}
		});
		
		menuArSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		menuArSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		
		mntmLibrosPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CrearPDF.generar(conn, "JAVATECA_LIBROS", " libros en la base de datos:");
				
			}
		});
		
		mntmCDPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CrearPDF.generar(conn, "JAVATECA_CDROMS", " CD-ROMs en la base de datos:");
				
			}
		});
		
		mntmRevistasPdf.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				CrearPDF.generar(conn, "JAVATECA_REVISTAS", " revistas en la base de datos:");
				
			}
		});
		
		mntmUsuariosPdf.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				CrearPDF.generar(conn, "JAVATECA_USUARIOS", " usuarios en la base de datos:");
				
			}
		});
		
		mntmPrestamosPdf.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				CrearPDF.generar(conn, "JAVATECA_PRESTAMOS", " prestamos en la base de datos:");
				
			}
		});
		
		menuArPreferencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new MenuConfig();
				
			}
		});
		

		
		setLocationRelativeTo(null);
	}
	
	public static void lanzaArticulos(String codRevista) {
		// TODO Cambiar metodo de conexion a BD
		contentPane.add(new GestionArticulos().gestion(ConBD.conectar("SERVER2012", "1521", "BIBLIOTECA", "BIBLIOTECA"),codRevista),"gestArt");
		cl.show(contentPane, "gestArt");
		btnAtras.setVisible(true);
		
	}
	
	public static void lanzaPrestamos(String codUsuario) {
		// TODO Cambiar metodo de conexion a BD
		contentPane.add(new GestionPrestamos().gestion(ConBD.conectar("SERVER2012", "1521", "BIBLIOTECA", "BIBLIOTECA"),codUsuario),"gestPres");
		cl.show(contentPane, "gestPres");
		btnAtras.setVisible(true);
		
	}
}
