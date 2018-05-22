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

public class GestionRevistas {
	
	private JPanel contentPane;
	private JTable tableDatos;
	private JTextField textFieldBusqueda;
	private JTextField codRevTField;
	private JTextField materiaTField;
	private JTextField nombreTField;
	private JTextField signaturaTField;
	private JButton btnNuevoMod;
	private JButton btnVaciar;
	private JButton btnEliminar;
	private JLabel avisoYaExiste;
	private DefaultTableModel modelo;
	private JButton btnArticulos;
	
	private void vaciar(Connection conn) {
		
		   codRevTField.setText("");
		   materiaTField.setText("");
		   nombreTField.setText("");
		   signaturaTField.setText("");
		   
		   codRevTField.setEditable(true);
		   codRevTField.setBackground(new Color(255,255,255));
		   
		   btnNuevoMod.setText("Nuevo");
		   btnNuevoMod.setToolTipText("Da de alta un nuevo registro con los datos del formulario");
		   
		   avisoYaExiste.setForeground(Color.WHITE);
		   
		   
		   btnEliminar.setEnabled(false);
		   btnArticulos.setEnabled(false);
		   
		   textFieldBusqueda.setText("");
		   
		   String sql = "select * from JAVATECA_REVISTAS";
		   
		   modelo = Buscador.buscar(sql, conn);
		   
		   tableDatos.setModel(modelo);
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JPanel gestion(Connection conn) {
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel lblTablaDeDatos = new JLabel("Resultados");
		
		try {
			
			modelo = Buscador.buscar("select * from JAVATECA_REVISTAS", conn);
				
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
					
					vaciar(conn);
					
				}
			});
			comboBoxBuscar.setToolTipText("Selecciona el criterio de b\u00FAsqueda");
			comboBoxBuscar.setModel(new DefaultComboBoxModel(new String[] {"Cod_Revista", "Nombre", "Materia", "Signatura"}));
			
			JLabel lblBuscarPor = new JLabel("Buscar por...");
			
			textFieldBusqueda = new JTextField();
			textFieldBusqueda.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					
					String sql = "";
					
					if(textFieldBusqueda.getText().equals("")) {
						
						sql = "select * from JAVATECA_REVISTAS";
						
					}else {
					
						sql = "select * from JAVATECA_REVISTAS where upper("+comboBoxBuscar.getSelectedItem()+") like upper('"+textFieldBusqueda.getText()+"%')";
						
					}
					
					tableDatos.setModel(Buscador.buscar(sql, conn));
					
				}
			});
			textFieldBusqueda.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("B\u00FAsqueda");
			
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon(GestionRevistas.class.getResource("/resources/revista45.png")));
			
			JLabel lblNewLabel_2 = new JLabel("Gesti\u00F3n de revistas");
			lblNewLabel_2.setFont(new Font("DejaVu Sans", Font.PLAIN, 25));
			
			JLabel lblDetalles = new JLabel("Detalles");
			lblDetalles.setFont(new Font("DejaVu Sans", Font.PLAIN, 25));
			
			JLabel lblCodRev = new JLabel("Cod. Revista");
			lblCodRev.setHorizontalAlignment(SwingConstants.TRAILING);
			
			codRevTField = new JTextField();
			codRevTField.setColumns(10);
			
			materiaTField = new JTextField();
			materiaTField.setColumns(10);
			
			nombreTField = new JTextField();
			nombreTField.setColumns(10);
			
			signaturaTField = new JTextField();
			signaturaTField.setColumns(10);
			
			JLabel lblMateria = new JLabel("Materia");
			lblMateria.setHorizontalAlignment(SwingConstants.TRAILING);
			
			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setHorizontalAlignment(SwingConstants.TRAILING);
			
			JLabel lblSignatura = new JLabel("Signatura");
			lblSignatura.setHorizontalAlignment(SwingConstants.TRAILING);
			
			btnNuevoMod = new JButton("Nuevo");

			btnNuevoMod.setToolTipText("Da de alta un nuevo registro con los datos del formulario");
			
			btnVaciar = new JButton("Vaciar");
			
			btnVaciar.setToolTipText("Vacia los campos del formulario de detalles");
			
			avisoYaExiste = new JLabel("Cod. revista ya existe");
			avisoYaExiste.setForeground(Color.WHITE);
			
			btnEliminar = new JButton("Elminar");

			btnEliminar.setToolTipText("Elimina PERMANENTEMENTE de la base de datos el registro seleccionado");
			btnEliminar.setEnabled(false);
			
			btnArticulos = new JButton("Ver art\u00EDculos");

			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(77)
						.addComponent(lblDetalles, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
						.addGap(453))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(51)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblCodRev, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(codRevTField, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(avisoYaExiste)
								.addGap(5)
								.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(nombreTField, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblMateria, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(materiaTField, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
								.addGap(116)
								.addComponent(lblSignatura, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(signaturaTField, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
						.addGap(105))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(77)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblNewLabel_2)
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addGap(1)
								.addComponent(textFieldBusqueda, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
								.addGap(10)
								.addComponent(lblBuscarPor)
								.addGap(10)
								.addComponent(comboBoxBuscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(79))))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(77)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblTablaDeDatos, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
								.addGap(77))))
					.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addGap(72)
						.addComponent(btnArticulos)
						.addPreferredGap(ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
						.addComponent(btnVaciar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnNuevoMod, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addGap(77))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(28)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblNewLabel_1)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGap(43)
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
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(16)
								.addComponent(lblDetalles, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addGap(22)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(3)
										.addComponent(lblCodRev))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(codRevTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(avisoYaExiste))
									.addComponent(nombreTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(66)
								.addComponent(lblNombre)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(11)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(materiaTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(signaturaTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(14)
								.addComponent(lblMateria))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(14)
								.addComponent(lblSignatura)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnVaciar)
									.addComponent(btnNuevoMod)
									.addComponent(btnEliminar)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(18)
								.addComponent(btnArticulos)))
						.addGap(102))
			);
			contentPane.setLayout(gl_contentPane);
			avisoYaExiste.setForeground(Color.WHITE);
			

			
			tableDatos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int fila = tableDatos.rowAtPoint(e.getPoint());
					if ((fila > -1)) {
						
					   codRevTField.setText((String)(modelo.getValueAt(fila,0)));
					   materiaTField.setText((String)(modelo.getValueAt(fila,3)));
					   nombreTField.setText((String)(modelo.getValueAt(fila,2)));
					   signaturaTField.setText((String)(modelo.getValueAt(fila,1)));
					   
					   codRevTField.setEditable(false);
					   codRevTField.setBackground(new Color(255,0,0));
					   
					   btnNuevoMod.setText("Modificar");
					   btnNuevoMod.setToolTipText("Modifica el registro seleccionado");
					   
					   btnEliminar.setEnabled(true);
					   
					   btnArticulos.setEnabled(true);
					   
					}
				}
			});
			
			codRevTField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					
					if(Buscador.existeClave("JAVATECA_REVISTAS", "COD_REVISTA", codRevTField.getText().toUpperCase(), conn, null)){
						
						avisoYaExiste.setForeground(Color.RED);
						textFieldBusqueda.setText(codRevTField.getText());
						String sql = "select * from JAVATECA_REVISTAS where COD_REVISTA like upper('"+codRevTField.getText()+"')";
						tableDatos.setModel(Buscador.buscar(sql, conn));
						btnNuevoMod.setText("Modificar");
						btnNuevoMod.setToolTipText("Modifica el registro seleccionado");
						btnEliminar.setEnabled(true);
						btnArticulos.setEnabled(true);
						
					}else {
						
						avisoYaExiste.setForeground(Color.WHITE);
						textFieldBusqueda.setText("");
						String sql = "select * from JAVATECA_REVISTAS";
						tableDatos.setModel(Buscador.buscar(sql, conn));
						btnNuevoMod.setText("Nuevo");
						btnNuevoMod.setToolTipText("Da de alta un nuevo registro con los datos del formulario");
						btnEliminar.setEnabled(false);
						btnArticulos.setEnabled(false);
						
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
						
						String sql = "INSERT INTO JAVATECA_REVISTAS(COD_REVISTA,MATERIA,NOMBRE,SIGNATURA) VALUES"
								+ "('"+codRevTField.getText().toUpperCase()+"','"
								+ materiaTField.getText().toUpperCase()+"','"
								+ nombreTField.getText().toUpperCase()+"','"
								+ signaturaTField.getText().toUpperCase()+"'"
								+ ")";
						
						if(ConBD.ejecutarUpdateSQL(conn, sql)) {
							
							
							
							textFieldBusqueda.setText("");
							sql = "select * from JAVATECA_REVISTAS";
							modelo = Buscador.buscar(sql, conn);
							tableDatos.setModel(modelo);
							
							Object frame = null;
							JOptionPane.showMessageDialog((Component) frame,
								    "Revista con COD_REVISTA '"+codRevTField.getText()+"' creada correctamente.","Creación correcta",JOptionPane.DEFAULT_OPTION);
							vaciar(conn);
							
							
						}else {
							
							Object frame = null;
							JOptionPane.showMessageDialog((Component) frame,
								    "La revista con COD_REVISTA '"+codRevTField.getText()+"' no se ha creado correctamente.","Error",JOptionPane.ERROR_MESSAGE);
							
						}
						
					}else {
						
						String sql = "UPDATE JAVATECA_REVISTAS SET "
								+ "MATERIA = '"+materiaTField.getText().toUpperCase()+"',"
								+ "NOMBRE = '"+nombreTField.getText().toUpperCase()+"',"
								+ "SIGNATURA = '"+signaturaTField.getText().toUpperCase()+"'"
								+ " WHERE COD_REVISTA = '"+codRevTField.getText().toUpperCase()+"'";
						
						//System.out.println(sql);
						
						if(ConBD.ejecutarUpdateSQL(conn, sql)) {
							
							Object frame = null;
							
							
							sql = "select * from JAVATECA_REVISTAS where COD_REVISTA = '"+codRevTField.getText()+"'";
							modelo = Buscador.buscar(sql, conn);
							tableDatos.setModel(modelo);
							
							JOptionPane.showMessageDialog((Component) frame,
								    "Revista con COD_REVISTA '"+codRevTField.getText()+"' modificado correctamente.","Modificación correcta",JOptionPane.DEFAULT_OPTION);
							
							
						}else {
							
							Object frame = null;
							JOptionPane.showMessageDialog((Component) frame,
								    "La revista con COD_REVISTA '"+codRevTField.getText()+"' no se ha modificado correctamente.","Error",JOptionPane.ERROR_MESSAGE);
							
						}
						
						
					}
					
				}
			});
			
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					String sql = "DELETE from JAVATECA_REVISTAS WHERE COD_REVISTA = '"+codRevTField.getText()+"'";
					
					if(ConBD.ejecutarUpdateSQL(conn, sql)) {
						
						textFieldBusqueda.setText("");
						sql = "select * from JAVATECA_REVISTAS";
						modelo = Buscador.buscar(sql, conn);
						tableDatos.setModel(modelo);
						
						Object frame = null;
						JOptionPane.showMessageDialog((Component) frame,
							    "Revista con COD_REVISTA '"+codRevTField.getText()+"' eliminado correctamente.","Eliminación correcta",JOptionPane.DEFAULT_OPTION);
						
						vaciar(conn);
						
					}else {
						
						Object frame = null;
						JOptionPane.showMessageDialog((Component) frame,
							    "La revista con COD_REVISTA '"+codRevTField.getText()+"' no se ha eliminado correctamente.","Error",JOptionPane.ERROR_MESSAGE);
						
					}
					
					
				}
			});
			btnArticulos.setEnabled(false);
			btnArticulos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					LibrosGUI.lanzaArticulos(codRevTField.getText());
					vaciar(conn);
					
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return contentPane;
		
	}
}
