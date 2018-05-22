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
import javax.swing.LayoutStyle.ComponentPlacement;

public class GestionArticulos {
	
	private JPanel contentPane;
	private JTable tableDatos;
	private JTextField textFieldBusqueda;
	private JTextField codRevistaTField;
	private JTextField tituloTField;
	private JTextField pagsTField;
	private JTextField codArticuloTField;
	private JTextField autorTField;
	private JTextField keywordsTField;
	private JButton btnNuevoMod;
	private JButton btnVaciar;
	private JButton btnEliminar;
	private JLabel avisoYaExiste;
	private DefaultTableModel modelo;
	
	private void vaciar(Connection conn, String codRevista) {
		
		   tituloTField.setText("");
		   codArticuloTField.setText("");
		   autorTField.setText("");
		   keywordsTField.setText("");
		   pagsTField.setText("");
		   
		   codArticuloTField.setEditable(true);
		   codArticuloTField.setBackground(new Color(255,255,255));
		   
		   btnNuevoMod.setText("Nuevo");
		   btnNuevoMod.setToolTipText("Da de alta un nuevo registro con los datos del formulario");
		   
		   avisoYaExiste.setForeground(Color.WHITE);
		   
		   btnEliminar.setEnabled(false);
		   
		   textFieldBusqueda.setText("");
		   
		   String sql = "select * from JAVATECA_ARTICULOS WHERE COD_REVISTA LIKE '"+codRevista+"'";
		   
		   modelo = Buscador.buscar(sql, conn);
		   
		   tableDatos.setModel(modelo);
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel gestion(Connection conn,String codRevista) {
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel lblTablaDeDatos = new JLabel("Resultados");
		
		try {
			
			modelo = Buscador.buscar("select * from JAVATECA_ARTICULOS where COD_REVISTA = '"+codRevista+"'", conn);
				
			JScrollPane scrollPane = new JScrollPane();
			
			tableDatos = new JTable(modelo) {
		        private static final long serialVersionUID = 1L;

		        public boolean isCellEditable(int row, int column) {                
		                return false; 
		                
		        
		        };
		    };
		    
		    tableDatos.getTableHeader().setReorderingAllowed(false);
		    
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
					
					vaciar(conn,codRevista);
					
				}
			});
			comboBoxBuscar.setToolTipText("Selecciona el criterio de b\u00FAsqueda");
			comboBoxBuscar.setModel(new DefaultComboBoxModel<Object>(new String[] {"Cod_Articulo", "Titulo", "Autor", "Num_Pags", "Key_Words"}));
			
			JLabel lblBuscarPor = new JLabel("Buscar por...");
			
			textFieldBusqueda = new JTextField();
			textFieldBusqueda.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					
					String sql = "";
					
					if(textFieldBusqueda.getText().equals("")) {
						
						sql = "select * from JAVATECA_ARTICULOS where COD_REVISTA = '"+codRevista+"'";
						
					}else {
					
						if(comboBoxBuscar.getSelectedItem().equals("Key_Words")) {
							
							sql = "select * from JAVATECA_ARTICULOS where COD_REVISTA = '"+codRevista+"' AND KEY_WORDS like upper('%"+textFieldBusqueda.getText()+"%')";
							
						}else {
							
							sql = "select * from JAVATECA_ARTICULOS where COD_REVISTA = '"+codRevista+"' AND upper("+comboBoxBuscar.getSelectedItem()+") like upper('"+textFieldBusqueda.getText()+"%')";
							
						}
						
					}
					
					tableDatos.setModel(Buscador.buscar(sql, conn));
					
				}
			});
			textFieldBusqueda.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("B\u00FAsqueda");
			
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon(GestionArticulos.class.getResource("/resources/articulo45.png")));
			
			JLabel lblNewLabel_2 = new JLabel("Gesti\u00F3n de art\u00EDculos");
			lblNewLabel_2.setFont(new Font("DejaVu Sans", Font.PLAIN, 25));
			
			JLabel lblDetalles = new JLabel("Detalles");
			lblDetalles.setFont(new Font("DejaVu Sans", Font.PLAIN, 25));
			
			JLabel lblCodRevista = new JLabel("Cod.Revista");
			lblCodRevista.setHorizontalAlignment(SwingConstants.TRAILING);
			
			codRevistaTField = new JTextField();
			codRevistaTField.setColumns(10);
			
			tituloTField = new JTextField();
			tituloTField.setColumns(10);
			
			pagsTField = new JTextField();
			pagsTField.setColumns(10);
			
			codArticuloTField = new JTextField();
			codArticuloTField.setColumns(10);
			
			autorTField = new JTextField();
			autorTField.setColumns(10);
			
			keywordsTField = new JTextField();
			keywordsTField.setColumns(10);
			
			JLabel lblTitulo = new JLabel("Titulo");
			lblTitulo.setHorizontalAlignment(SwingConstants.TRAILING);
			
			JLabel lblPags = new JLabel("P\u00E1ginas");
			lblPags.setHorizontalAlignment(SwingConstants.TRAILING);
			
			JLabel lblTtulo = new JLabel("Cod.Art\u00EDculo");
			lblTtulo.setHorizontalAlignment(SwingConstants.TRAILING);
			
			JLabel lblAutor = new JLabel("Autor");
			lblAutor.setHorizontalAlignment(SwingConstants.TRAILING);
			
			JLabel lblKeywords = new JLabel("Keywords");
			lblKeywords.setHorizontalAlignment(SwingConstants.TRAILING);
			
			btnNuevoMod = new JButton("Nuevo");

			btnNuevoMod.setToolTipText("Da de alta un nuevo registro con los datos del formulario");
			
			btnVaciar = new JButton("Vaciar");
			
			btnVaciar.setToolTipText("Vacia los campos del formulario de detalles");
			
			avisoYaExiste = new JLabel("COD_ARTICULO ya existe");
			avisoYaExiste.setFont(new Font("Tahoma", Font.PLAIN, 10));
			avisoYaExiste.setForeground(Color.WHITE);
			
			btnEliminar = new JButton("Elminar");

			btnEliminar.setToolTipText("Elimina PERMANENTEMENTE de la base de datos el registro seleccionado");
			btnEliminar.setEnabled(false);
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(77)
						.addComponent(lblDetalles, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
						.addGap(453))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(77)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblTablaDeDatos, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
								.addGap(77))))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(77)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblNewLabel_2)
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addGap(1)
								.addComponent(textFieldBusqueda, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblBuscarPor)
								.addGap(18)
								.addComponent(comboBoxBuscar, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addGap(79))))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(83)
								.addComponent(lblPags, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(pagsTField, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(60)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblCodRevista)
									.addComponent(lblTtulo, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
								.addGap(10)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(codArticuloTField, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED))
									.addComponent(codRevistaTField, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(4)
								.addComponent(avisoYaExiste, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(11)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblTitulo)
											.addComponent(lblAutor, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
										.addGap(10)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addComponent(tituloTField, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
											.addComponent(autorTField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblKeywords, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(keywordsTField, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)))
								.addGap(130))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(106)
								.addComponent(btnVaciar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(btnNuevoMod, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addGap(77))))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(33)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1)
							.addComponent(lblNewLabel_2))
						.addGap(43)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(3)
										.addComponent(lblNewLabel))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(textFieldBusqueda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblBuscarPor)))
								.addGap(22)
								.addComponent(lblTablaDeDatos))
							.addComponent(comboBoxBuscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
						.addGap(16)
						.addComponent(lblDetalles, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGap(22)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCodRevista)
								.addComponent(codRevistaTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(tituloTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTitulo)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(11)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(3)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
											.addComponent(codArticuloTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblTtulo)
											.addComponent(avisoYaExiste)))
									.addComponent(autorTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(14)
								.addComponent(lblAutor)))
						.addGap(11)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(3)
										.addComponent(lblPags))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(keywordsTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblKeywords)))
								.addGap(24)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(btnVaciar)
									.addComponent(btnNuevoMod)
									.addComponent(btnEliminar)))
							.addComponent(pagsTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(65))
			);
			contentPane.setLayout(gl_contentPane);
			avisoYaExiste.setForeground(Color.WHITE);
			
			codRevistaTField.setText(codRevista);
			codRevistaTField.setEnabled(false);
			codRevistaTField.setBackground(Color.RED);

			
			tableDatos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int fila = tableDatos.rowAtPoint(e.getPoint());
					if ((fila > -1)) {
						
					   tituloTField.setText((String)(modelo.getValueAt(fila,2)));
					   codArticuloTField.setText((String)(modelo.getValueAt(fila,0)));
					   autorTField.setText((String)(modelo.getValueAt(fila,3)));
					   keywordsTField.setText((String)(modelo.getValueAt(fila,5)));
					   pagsTField.setText(String.valueOf(modelo.getValueAt(fila,4)));
					   
					   codArticuloTField.setEditable(false);
					   codArticuloTField.setBackground(new Color(255,0,0));
					   
					   btnNuevoMod.setText("Modificar");
					   btnNuevoMod.setToolTipText("Modifica el registro seleccionado");
					   
					   btnEliminar.setEnabled(true);
					   
					}
				}
			});
			
			codArticuloTField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					
					if(Buscador.existeClave("JAVATECA_ARTICULOS", "COD_ARTICULO", codArticuloTField.getText().toUpperCase(), conn, codRevista)){
						
						avisoYaExiste.setForeground(Color.RED);
						textFieldBusqueda.setText(codRevistaTField.getText());
						String sql = "select * from JAVATECA_ARTICULOS where COD_REVISTA = '"+codRevista+"' AND COD_ARTICULO like upper('"+codArticuloTField.getText()+"')";
						tableDatos.setModel(Buscador.buscar(sql, conn));
						btnNuevoMod.setText("Modificar");
						btnNuevoMod.setToolTipText("Modifica el registro seleccionado");
						btnEliminar.setEnabled(true);
						
					}else {
						
						avisoYaExiste.setForeground(Color.WHITE);
						textFieldBusqueda.setText("");
						String sql = "select * from JAVATECA_ARTICULOS where COD_REVISTA = '"+codRevista+"'";
						tableDatos.setModel(Buscador.buscar(sql, conn));
						btnNuevoMod.setText("Nuevo");
						btnNuevoMod.setToolTipText("Da de alta un nuevo registro con los datos del formulario");
						btnEliminar.setEnabled(false);
						
					}
					
				}
			});
			
			btnVaciar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					   vaciar(conn, codRevista);
					
				}
			});
			
			btnNuevoMod.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if(btnNuevoMod.getText().equals("Nuevo")) {
						
						String sql = "INSERT INTO JAVATECA_ARTICULOS(COD_ARTICULO,COD_REVISTA,TITULO,AUTOR,NUM_PAGS,KEY_WORDS) VALUES"
								+ "('"+codArticuloTField.getText().toUpperCase()+"','"
								+ codRevista+"','"
								+ tituloTField.getText().toUpperCase()+"','"
								+ autorTField.getText().toUpperCase()+"','"
								+ pagsTField.getText().toUpperCase()+"','"
								+ keywordsTField.getText().toUpperCase()+"'"
								+ ")";
						
						if(ConBD.ejecutarUpdateSQL(conn, sql)) {
							
							
							
							textFieldBusqueda.setText("");
							sql = "select * from JAVATECA_ARTICULOS where COD_REVISTA = '"+codRevista+"' AND COD_ARTICULO = '"+codArticuloTField.getText()+"'";
							modelo = Buscador.buscar(sql, conn);
							tableDatos.setModel(modelo);
							
							Object frame = null;
							JOptionPane.showMessageDialog((Component) frame,
								    "Articulo con COD_ARTICULO '"+codArticuloTField.getText()+"' creado correctamente.","Creación correcta",JOptionPane.DEFAULT_OPTION);
							vaciar(conn,codRevista);
							
							
						}else {
							
							Object frame = null;
							JOptionPane.showMessageDialog((Component) frame,
								    "El articulo con COD_ARTICULO '"+codArticuloTField.getText()+"' no se ha creado correctamente.","Error",JOptionPane.ERROR_MESSAGE);
							
						}
						
					}else {
						
						String sql = "UPDATE JAVATECA_ARTICULOS SET "
								+ "TITULO = '"+tituloTField.getText().toUpperCase()+"',"
								+ "AUTOR = '"+autorTField.getText().toUpperCase()+"',"
								+ "KEY_WORDS= '"+keywordsTField.getText().toUpperCase()+"',"
								+ "NUM_PAGS = '"+pagsTField.getText().toUpperCase()+"'"
								+ " WHERE COD_ARTICULO = '"+codArticuloTField.getText().toUpperCase()+"' AND COD_REVISTA = '"+codRevista+"'";
						
						//System.out.println(sql);
						
						if(ConBD.ejecutarUpdateSQL(conn, sql)) {
							
							Object frame = null;
							
							
							sql = "select * from JAVATECA_ARTICULOS where COD_REVISTA = '"+codRevista+"' AND COD_ARTICULO = '"+codArticuloTField.getText()+"'";
							modelo = Buscador.buscar(sql, conn);
							tableDatos.setModel(modelo);
							
							JOptionPane.showMessageDialog((Component) frame,
								    "Articulo con COD_ARTICULO '"+codArticuloTField.getText()+"' modificado correctamente.","Modificación correcta",JOptionPane.DEFAULT_OPTION);
							
							vaciar(conn,codRevista);
							
							
						}else {
							
							Object frame = null;
							JOptionPane.showMessageDialog((Component) frame,
								    "El articulo con COD_ARTICULO '"+codArticuloTField.getText()+"' no se ha modificado correctamente.","Error",JOptionPane.ERROR_MESSAGE);
							
						}
						
						
					}
					
				}
			});
			
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					String sql = "DELETE from JAVATECA_ARTICULOS WHERE COD_ARTICULO = '"+codArticuloTField.getText()+"' AND COD_REVISTA = '"+codRevista+"'";
					
					if(ConBD.ejecutarUpdateSQL(conn, sql)) {
						
						textFieldBusqueda.setText("");
						sql = "select * from JAVATECA_ARTICULOS where COD_REVISTA = '"+codRevista+"'";
						modelo = Buscador.buscar(sql, conn);
						tableDatos.setModel(modelo);
						
						Object frame = null;
						JOptionPane.showMessageDialog((Component) frame,
							    "Articulo con COD_ARTICULO '"+codArticuloTField.getText()+"' eliminado correctamente.","Eliminación correcta",JOptionPane.DEFAULT_OPTION);
						
						vaciar(conn,codRevista);
						
					}else {
						
						Object frame = null;
						JOptionPane.showMessageDialog((Component) frame,
							    "El articulo con COD_ARTICULO '"+codArticuloTField.getText()+"' no se ha eliminado correctamente.","Error",JOptionPane.ERROR_MESSAGE);
						
					}
					
					
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return contentPane;
		
	}
	
}
