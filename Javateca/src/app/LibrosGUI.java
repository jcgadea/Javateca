package app;

import java.awt.EventQueue;
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

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibrosGUI frame = new LibrosGUI();
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
	public LibrosGUI() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			// TODO UIManager.getDefaults().put("Button.showMnemonics", Boolean.TRUE);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(LibrosGUI.class.getResource("/resources/JavatecaIcon1.png")));
		setTitle("Javateca 0.1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Archivo");
		mnMenu.setMnemonic('A');
		menuBar.add(mnMenu);
		
		JMenuItem menuArPreferencias = new JMenuItem("Preferencias");
		menuArPreferencias.setIcon(new ImageIcon(LibrosGUI.class.getResource("/resources/config.png")));
		mnMenu.add(menuArPreferencias);
		
		JMenuItem menuArSalir = new JMenuItem("Salir");

		menuArSalir.setIcon(new ImageIcon(LibrosGUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		mnMenu.add(menuArSalir);
		
		JMenu mnMateriales = new JMenu("Materiales");
		mnMateriales.setMnemonic('M');
		menuBar.add(mnMateriales);
		
		JMenu mnLibros = new JMenu("Libros");
		mnMateriales.add(mnLibros);
		
		JMenuItem mntmGestLibros = new JMenuItem("Gestionar libros");
		mntmGestLibros.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));

		mnLibros.add(mntmGestLibros);
		
		JMenuItem mntmLibrosPdf = new JMenuItem("Informe (PDF)");
		mnLibros.add(mntmLibrosPdf);
		
		JMenu mnCD = new JMenu("CD-ROMs");
		mnMateriales.add(mnCD);
		
		JMenuItem mntmGestCds = new JMenuItem("Gestionar CDs");

		mntmGestCds.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mnCD.add(mntmGestCds);
		
		JMenuItem mntmCDPdf = new JMenuItem("Informe (PDF)");
		mnCD.add(mntmCDPdf);
		
		JMenu mnRevistas = new JMenu("Revistas");
		mnMateriales.add(mnRevistas);
		
		JMenuItem mntmGestRevistas = new JMenuItem("Gestionar revistas");

		mntmGestRevistas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mnRevistas.add(mntmGestRevistas);
		
		JMenuItem mntmRevistasPdf = new JMenuItem("Informe (PDF)");
		mnRevistas.add(mntmRevistasPdf);
		
		JMenu mnNewMenu = new JMenu("Ayuda");
		mnNewMenu.setMnemonic('y');
		menuBar.add(mnNewMenu);
		
		JMenuItem menuAyuGithub = new JMenuItem("GitHub");

		menuAyuGithub.setIcon(new ImageIcon(LibrosGUI.class.getResource("/resources/gitHub1.png")));
		mnNewMenu.add(menuAyuGithub);
		
		JMenuItem menuAyuDocu = new JMenuItem("Documentaci\u00F3n");
		menuAyuDocu.setIcon(new ImageIcon(LibrosGUI.class.getResource("/resources/help.png")));
		mnNewMenu.add(menuAyuDocu);
		
		JMenuItem menuAyuLicencia = new JMenuItem("Licencia");
		mnNewMenu.add(menuAyuLicencia);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setMnemonic('s');

		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(btnAtras);
		btnAtras.setVisible(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		CardLayout cl = new CardLayout();
		contentPane.setLayout(cl);
		
		JPanel panelVacio = new JPanel();
		contentPane.add(panelVacio, "menuVacio");
		SpringLayout sl_panelVacio = new SpringLayout();
		panelVacio.setLayout(sl_panelVacio);
		
		JLabel lblNewLabel_2 = new JLabel("Javateca 0.1");
		lblNewLabel_2.setBackground(Color.WHITE);
		sl_panelVacio.putConstraint(SpringLayout.WEST, lblNewLabel_2, 28, SpringLayout.WEST, panelVacio);
		sl_panelVacio.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, -10, SpringLayout.SOUTH, panelVacio);
		lblNewLabel_2.setFont(new Font("Sitka Banner", Font.PLAIN, 29));
		panelVacio.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		sl_panelVacio.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, 0, SpringLayout.SOUTH, panelVacio);
		sl_panelVacio.putConstraint(SpringLayout.EAST, lblNewLabel_1, 0, SpringLayout.EAST, panelVacio);
		lblNewLabel_1.setIcon(new ImageIcon(LibrosGUI.class.getResource("/resources/fondoLibros.png")));
		panelVacio.add(lblNewLabel_1);
		
		// TODO Cambiar conexiones para que vengan del padre.
		
		contentPane.add(new GestionLibros().gestion(ConBD.conectar("SERVER2012", "1521", "BIBLIOTECA", "BIBLIOTECA")),"gestLibros");
		contentPane.add(new GestionCd().gestion(ConBD.conectar("SERVER2012", "1521", "BIBLIOTECA", "BIBLIOTECA")),"gestCd");
		contentPane.add(new GestionRevistas().gestion(ConBD.conectar("SERVER2012", "1521", "BIBLIOTECA", "BIBLIOTECA")),"gestRev");
		
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
		
		menuArSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		menuArSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		

		
		setLocationRelativeTo(null);
	}
}
