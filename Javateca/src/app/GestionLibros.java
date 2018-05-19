package app;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class GestionLibros {
	
	private JPanel contentPane;
	private JTable tableDatos;
	private JTextField textFieldBusqueda;
	private JTextField isbnTField;
	private JTextField signaturaTField;
	private JTextField editorialTField;
	private JTextField tituloTField;
	private JTextField autorTField;
	private JTextField materiaTField;
	private JButton btnNuevoMod;
	private JButton btnVaciar;
	private JButton btnEliminar;
	private JLabel avisoYaExiste;
	private DefaultTableModel modelo;
	
	private void vaciar() {
		
		   isbnTField.setText("");
		   signaturaTField.setText("");
		   tituloTField.setText("");
		   autorTField.setText("");
		   materiaTField.setText("");
		   editorialTField.setText("");
		   
		   isbnTField.setEditable(true);
		   isbnTField.setBackground(new Color(255,255,255));
		   
		   btnNuevoMod.setText("Nuevo");
		   btnNuevoMod.setToolTipText("Da de alta un nuevo registro con los datos del formulario");
		   
		   avisoYaExiste.setForeground(Color.WHITE);
		   
		   btnEliminar.setEnabled(false);
		
	}
	
	private void vaciar(Connection conn) {
		
		   isbnTField.setText("");
		   signaturaTField.setText("");
		   tituloTField.setText("");
		   autorTField.setText("");
		   materiaTField.setText("");
		   editorialTField.setText("");
		   
		   isbnTField.setEditable(true);
		   isbnTField.setBackground(new Color(255,255,255));
		   
		   btnNuevoMod.setText("Nuevo");
		   btnNuevoMod.setToolTipText("Da de alta un nuevo registro con los datos del formulario");
		   
		   avisoYaExiste.setForeground(Color.WHITE);
		   
		   btnEliminar.setEnabled(false);
		   
		   textFieldBusqueda.setText("");
		   
		   String sql = "select * from libros";
		   
		   modelo = Buscador.buscar(sql, conn);
		   
		   tableDatos.setModel(modelo);
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel gestion(Connection conn) {
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel lblTablaDeDatos = new JLabel("Resultados");
		
		try {
			
			modelo = Buscador.buscar("select * from libros", conn);
				
			JScrollPane scrollPane = new JScrollPane();
			
			tableDatos = new JTable(modelo);
			tableDatos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int fila = tableDatos.rowAtPoint(e.getPoint());
					if ((fila > -1)) {
						
					   //System.out.println(modelo.getValueAt(fila,0));
					   
					}
				}
			});
			
			scrollPane.setViewportView(tableDatos);
			tableDatos.setBackground(SystemColor.controlHighlight);
			
			JComboBox<Object> comboBoxBuscar = new JComboBox<Object>();
			comboBoxBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					vaciar(conn);
					
				}
			});
			comboBoxBuscar.setToolTipText("Selecciona el criterio de b\u00FAsqueda");
			comboBoxBuscar.setModel(new DefaultComboBoxModel<Object>(new String[] {"ISBN", "Titulo", "Materia", "Autor", "Editorial", "Signatura"}));
			
			JLabel lblBuscarPor = new JLabel("Buscar por...");
			
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
			textFieldBusqueda.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("B\u00FAsqueda");
			
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon(GestionLibros.class.getResource("/resources/libro45.png")));
			
			JLabel lblNewLabel_2 = new JLabel("Gesti\u00F3n de libros");
			lblNewLabel_2.setFont(new Font("DejaVu Sans", Font.PLAIN, 25));
			
			JLabel lblDetalles = new JLabel("Detalles");
			lblDetalles.setFont(new Font("DejaVu Sans", Font.PLAIN, 25));
			
			JLabel lblIsbn = new JLabel("ISBN");
			lblIsbn.setHorizontalAlignment(SwingConstants.TRAILING);
			
			isbnTField = new JTextField();
			isbnTField.setColumns(10);
			
			signaturaTField = new JTextField();
			signaturaTField.setColumns(10);
			
			editorialTField = new JTextField();
			editorialTField.setColumns(10);
			
			tituloTField = new JTextField();
			tituloTField.setColumns(10);
			
			autorTField = new JTextField();
			autorTField.setColumns(10);
			
			materiaTField = new JTextField();
			materiaTField.setColumns(10);
			
			JLabel lblSignatura = new JLabel("Signatura");
			lblSignatura.setHorizontalAlignment(SwingConstants.TRAILING);
			
			JLabel lblEditorial = new JLabel("Editorial");
			lblEditorial.setHorizontalAlignment(SwingConstants.TRAILING);
			
			JLabel lblTtulo = new JLabel("T\u00EDtulo");
			lblTtulo.setHorizontalAlignment(SwingConstants.TRAILING);
			
			JLabel lblAutor = new JLabel("Autor");
			lblAutor.setHorizontalAlignment(SwingConstants.TRAILING);
			
			JLabel lblMateria = new JLabel("Materia");
			lblMateria.setHorizontalAlignment(SwingConstants.TRAILING);
			
			btnNuevoMod = new JButton("Nuevo");

			btnNuevoMod.setToolTipText("Da de alta un nuevo registro con los datos del formulario");
			
			btnVaciar = new JButton("Vaciar");
			
			btnVaciar.setToolTipText("Vacia los campos del formulario de detalles");
			
			avisoYaExiste = new JLabel("ISBN ya existe");
			avisoYaExiste.setForeground(Color.WHITE);
			
			btnEliminar = new JButton("Elminar");

			btnEliminar.setToolTipText("Elimina PERMANENTEMENTE de la base de datos el registro seleccionado");
			btnEliminar.setEnabled(false);
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(77)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addGap(7)
						.addComponent(lblNewLabel_2))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(77)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addGap(1)
						.addComponent(textFieldBusqueda, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addGap(10)
						.addComponent(lblBuscarPor)
						.addGap(10)
						.addComponent(comboBoxBuscar, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
						.addGap(79))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(77)
						.addComponent(lblTablaDeDatos, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(77)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
						.addGap(77))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(77)
						.addComponent(lblDetalles, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
						.addGap(453))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(83)
						.addComponent(lblEditorial, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addGap(10)
						.addComponent(editorialTField, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addGap(116)
						.addComponent(lblMateria, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addGap(10)
						.addComponent(materiaTField, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addGap(105))
					.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addGap(376)
						.addComponent(btnVaciar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addGap(10)
						.addComponent(btnNuevoMod, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addGap(10)
						.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addGap(77))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(82)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblSignatura)
								.addGap(10)
								.addComponent(signaturaTField, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
								.addGap(87))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblIsbn, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(isbnTField, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
								.addGap(10)
								.addComponent(avisoYaExiste, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
						.addGap(29)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblTtulo, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(tituloTField, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblAutor, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(autorTField, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)))
						.addGap(105))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel_1)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(10)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
						.addGap(33)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(3)
								.addComponent(lblNewLabel))
							.addComponent(textFieldBusqueda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(3)
								.addComponent(lblBuscarPor))
							.addComponent(comboBoxBuscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(22)
						.addComponent(lblTablaDeDatos)
						.addGap(11)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(16)
								.addComponent(lblDetalles, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(22)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(isbnTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(3)
												.addComponent(avisoYaExiste))
											.addComponent(tituloTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(25)
										.addComponent(lblIsbn))))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(66)
								.addComponent(lblTtulo)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(11)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(3)
										.addComponent(lblSignatura))
									.addComponent(signaturaTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(autorTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(14)
								.addComponent(lblAutor)))
						.addGap(11)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(3)
								.addComponent(lblEditorial))
							.addComponent(editorialTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(3)
								.addComponent(lblMateria))
							.addComponent(materiaTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(24)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(btnVaciar)
							.addComponent(btnNuevoMod)
							.addComponent(btnEliminar))
						.addGap(65))
			);
			contentPane.setLayout(gl_contentPane);
			avisoYaExiste.setForeground(Color.WHITE);
			

			
			tableDatos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int fila = tableDatos.rowAtPoint(e.getPoint());
					if ((fila > -1)) {
						
					   isbnTField.setText((String)(modelo.getValueAt(fila,0)));
					   signaturaTField.setText((String)(modelo.getValueAt(fila,1)));
					   tituloTField.setText((String)(modelo.getValueAt(fila,2)));
					   autorTField.setText((String)(modelo.getValueAt(fila,3)));
					   materiaTField.setText((String)(modelo.getValueAt(fila,4)));
					   editorialTField.setText((String)(modelo.getValueAt(fila,5)));
					   
					   isbnTField.setEditable(false);
					   isbnTField.setBackground(new Color(255,0,0));
					   
					   btnNuevoMod.setText("Modificar");
					   btnNuevoMod.setToolTipText("Modifica el registro seleccionado");
					   
					   btnEliminar.setEnabled(true);
					   
					}
				}
			});
			
			isbnTField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					
					if(Buscador.existeClave("LIBROS", "ISBN", isbnTField.getText(), conn)){
						
						avisoYaExiste.setForeground(Color.RED);
						textFieldBusqueda.setText(isbnTField.getText());
						String sql = "select * from libros where isbn like upper('"+isbnTField.getText()+"')";
						tableDatos.setModel(Buscador.buscar(sql, conn));
						btnNuevoMod.setText("Modificar");
						btnNuevoMod.setToolTipText("Modifica el registro seleccionado");
						btnEliminar.setEnabled(true);
						
					}else {
						
						avisoYaExiste.setForeground(Color.WHITE);
						textFieldBusqueda.setText("");
						String sql = "select * from libros";
						tableDatos.setModel(Buscador.buscar(sql, conn));
						btnNuevoMod.setText("Nuevo");
						btnNuevoMod.setToolTipText("Da de alta un nuevo registro con los datos del formulario");
						btnEliminar.setEnabled(false);
						
					}
					
				}
			});
			
			btnVaciar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					   vaciar(conn);
					
				}
			});
			
			btnNuevoMod.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if(btnNuevoMod.getText().equals("Nuevo")) {
						
						String sql = "INSERT INTO LIBROS(ISBN,SIGNATURA,TITULO,AUTOR,MATERIA,EDITORIAL) VALUES"
								+ "('"+isbnTField.getText().toUpperCase()+"','"
								+ signaturaTField.getText().toUpperCase()+"','"
								+ tituloTField.getText().toUpperCase()+"','"
								+ autorTField.getText().toUpperCase()+"','"
								+ materiaTField.getText().toUpperCase()+"','"
								+ editorialTField.getText().toUpperCase()+"'"
								+ ")";
						
						if(ConBD.ejecutarUpdateSQL(conn, sql)) {
							
							
							
							textFieldBusqueda.setText("");
							sql = "select * from libros";
							modelo = Buscador.buscar(sql, conn);
							tableDatos.setModel(modelo);
							
							Object frame = null;
							JOptionPane.showMessageDialog((Component) frame,
								    "Libro con ISBN '"+isbnTField.getText()+"' creado correctamente.","Creación correcta",JOptionPane.DEFAULT_OPTION);
							vaciar();
							
							
						}else {
							
							Object frame = null;
							JOptionPane.showMessageDialog((Component) frame,
								    "El libro con ISBN '"+isbnTField.getText()+"' no se ha creado correctamente.","Error",JOptionPane.ERROR_MESSAGE);
							
						}
						
					}else {
						
						String sql = "UPDATE LIBROS SET "
								+ "SIGNATURA = '"+signaturaTField.getText().toUpperCase()+"',"
								+ "TITULO = '"+tituloTField.getText().toUpperCase()+"',"
								+ "AUTOR = '"+autorTField.getText().toUpperCase()+"',"
								+ "MATERIA= '"+materiaTField.getText().toUpperCase()+"',"
								+ "EDITORIAL = '"+editorialTField.getText().toUpperCase()+"'"
								+ " WHERE ISBN = '"+isbnTField.getText().toUpperCase()+"'";
						
						System.out.println(sql);
						
						if(ConBD.ejecutarUpdateSQL(conn, sql)) {
							
							Object frame = null;
							
							
							sql = "select * from libros where isbn = '"+isbnTField.getText()+"'";
							modelo = Buscador.buscar(sql, conn);
							tableDatos.setModel(modelo);
							
							JOptionPane.showMessageDialog((Component) frame,
								    "Libro con ISBN '"+isbnTField.getText()+"' modificado correctamente.","Modificación correcta",JOptionPane.DEFAULT_OPTION);
							
							
						}else {
							
							Object frame = null;
							JOptionPane.showMessageDialog((Component) frame,
								    "El libro con ISBN '"+isbnTField.getText()+"' no se ha modificado correctamente.","Error",JOptionPane.ERROR_MESSAGE);
							
						}
						
						
					}
					
				}
			});
			
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					String sql = "DELETE FROM LIBROS WHERE ISBN = '"+isbnTField.getText()+"'";
					
					if(ConBD.ejecutarUpdateSQL(conn, sql)) {
						
						textFieldBusqueda.setText("");
						sql = "select * from libros";
						modelo = Buscador.buscar(sql, conn);
						tableDatos.setModel(modelo);
						
						Object frame = null;
						JOptionPane.showMessageDialog((Component) frame,
							    "Libro con ISBN '"+isbnTField.getText()+"' eliminado correctamente.","Eliminación correcta",JOptionPane.DEFAULT_OPTION);
						
						vaciar();
						
					}else {
						
						Object frame = null;
						JOptionPane.showMessageDialog((Component) frame,
							    "El libro con ISBN '"+isbnTField.getText()+"' no se ha eliminado correctamente.","Error",JOptionPane.ERROR_MESSAGE);
						
					}
					
					
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return contentPane;
		
	}
	
}
