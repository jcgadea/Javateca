package otros;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import app.Buscador;

import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class PruebaJTable extends JFrame {

	private JPanel contentPane;
	private JTable tableDatos;
	private JTextField textFieldBusqueda;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PruebaJTable frame = new PruebaJTable();
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
	
	public PruebaJTable() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTablaDeDatos = new JLabel("Resultados");
		lblTablaDeDatos.setBounds(67, 62, 77, 14);
		contentPane.add(lblTablaDeDatos);
		
		final DefaultTableModel modelo;

		
		
		try {
			
			Connection conn;
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@server2012:1521:xe","BIBLIOTECA","BIBLIOTECA");
			
			modelo = Buscador.buscar("select * from libros", conn);
				
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(67, 87, 650, 235);
			contentPane.add(scrollPane);
			
			tableDatos = new JTable(modelo);
			
			tableDatos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int fila = tableDatos.rowAtPoint(e.getPoint());
					if ((fila > -1)) {
						
					   System.out.println(modelo.getValueAt(fila,0));
					   
					}
				}
			});
			scrollPane.setViewportView(tableDatos);
			tableDatos.setBackground(SystemColor.controlHighlight);
			
			
			
			JComboBox<Object> comboBoxBuscar = new JComboBox<Object>();
			comboBoxBuscar.setToolTipText("Selecciona el criterio de b\u00FAsqueda");
			comboBoxBuscar.setModel(new DefaultComboBoxModel<Object>(new String[] {"ISBN", "Titulo", "Materia", "Autor", "Editorial", "Signatura"}));
			comboBoxBuscar.setBounds(140, 29, 77, 20);
			contentPane.add(comboBoxBuscar);
			
			JLabel lblBuscarPor = new JLabel("Buscar por...");
			lblBuscarPor.setBounds(67, 32, 63, 14);
			contentPane.add(lblBuscarPor);
			
			textFieldBusqueda = new JTextField();
			textFieldBusqueda.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					
					String sql = "";
					
					if(textFieldBusqueda.getText().equals("")) {
						
						sql = "select * from libros";
						
					}else {
					
						sql = "select * from libros where upper("+comboBoxBuscar.getSelectedItem()+") like upper('"+textFieldBusqueda.getText()+"%')";
						
					}
					
					tableDatos.setModel(Buscador.buscar(sql, conn));
					
					
					
					
				}
			});
			textFieldBusqueda.setBounds(227, 29, 204, 20);
			contentPane.add(textFieldBusqueda);
			textFieldBusqueda.setColumns(10);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
