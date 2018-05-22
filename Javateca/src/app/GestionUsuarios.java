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

public class GestionUsuarios {
	
	private JPanel contentPane;
	private JTable tableDatos;
	private JTextField textFieldBusqueda;
	private JTextField codUsuTField;
	private JTextField apellido1TField;
	private JTextField nombreTField;
	private JTextField apellido2TField;
	private JButton btnNuevoMod;
	private JButton btnVaciar;
	private JButton btnEliminar;
	private JLabel avisoYaExiste;
	private DefaultTableModel modelo;
	private JButton btnArticulos;
	
	private void vaciar(Connection conn) {
		
		   codUsuTField.setText("");
		   apellido1TField.setText("");
		   nombreTField.setText("");
		   apellido2TField.setText("");
		   
		   codUsuTField.setEditable(true);
		   codUsuTField.setBackground(new Color(255,255,255));
		   
		   btnNuevoMod.setText("Nuevo");
		   btnNuevoMod.setToolTipText("Da de alta un nuevo registro con los datos del formulario");
		   
		   avisoYaExiste.setForeground(Color.WHITE);
		   
		   
		   btnEliminar.setEnabled(false);
		   btnArticulos.setEnabled(false);
		   
		   textFieldBusqueda.setText("");
		   
		   String sql = "select * from JAVATECA_USUARIOS";
		   
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
			
			modelo = Buscador.buscar("select * from JAVATECA_USUARIOS", conn);
				
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
			comboBoxBuscar.setModel(new DefaultComboBoxModel(new String[] {"CodUsuario", "Nombre", "Apellido1", "Apellido2"}));
			
			JLabel lblBuscarPor = new JLabel("Buscar por...");
			
			textFieldBusqueda = new JTextField();
			textFieldBusqueda.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					
					String sql = "";
					
					if(textFieldBusqueda.getText().equals("")) {
						
						sql = "select * from JAVATECA_USUARIOS";
						
					}else {
					
						sql = "select * from JAVATECA_USUARIOS where upper("+comboBoxBuscar.getSelectedItem()+") like upper('"+textFieldBusqueda.getText()+"%')";
						
					}
					
					tableDatos.setModel(Buscador.buscar(sql, conn));
					
				}
			});
			textFieldBusqueda.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("B\u00FAsqueda");
			
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon(GestionUsuarios.class.getResource("/resources/user45.png")));
			
			JLabel lblNewLabel_2 = new JLabel("Gesti\u00F3n de usuarios");
			lblNewLabel_2.setFont(new Font("DejaVu Sans", Font.PLAIN, 25));
			
			JLabel lblDetalles = new JLabel("Detalles");
			lblDetalles.setFont(new Font("DejaVu Sans", Font.PLAIN, 25));
			
			JLabel lblCodUsu = new JLabel("Cod. Usuario");
			lblCodUsu.setHorizontalAlignment(SwingConstants.TRAILING);
			
			codUsuTField = new JTextField();
			codUsuTField.setColumns(10);
			
			apellido1TField = new JTextField();
			apellido1TField.setColumns(10);
			
			nombreTField = new JTextField();
			nombreTField.setColumns(10);
			
			apellido2TField = new JTextField();
			apellido2TField.setColumns(10);
			
			JLabel lblApellido1 = new JLabel("Primer Apellido");
			lblApellido1.setHorizontalAlignment(SwingConstants.TRAILING);
			
			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setHorizontalAlignment(SwingConstants.TRAILING);
			
			JLabel lblApellido2 = new JLabel("Segundo Apellido");
			lblApellido2.setHorizontalAlignment(SwingConstants.TRAILING);
			
			btnNuevoMod = new JButton("Nuevo");

			btnNuevoMod.setToolTipText("Da de alta un nuevo registro con los datos del formulario");
			
			btnVaciar = new JButton("Vaciar");
			
			btnVaciar.setToolTipText("Vacia los campos del formulario de detalles");
			
			avisoYaExiste = new JLabel("Cod. usuario ya existe");
			avisoYaExiste.setForeground(Color.WHITE);
			
			btnEliminar = new JButton("Elminar");

			btnEliminar.setToolTipText("Elimina PERMANENTEMENTE de la base de datos el registro seleccionado");
			btnEliminar.setEnabled(false);
			
			btnArticulos = new JButton("Ver pr\u00E9stamos");

			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(77)
						.addComponent(lblDetalles, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
						.addGap(453))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(51)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblCodUsu, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(codUsuTField, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(avisoYaExiste)
								.addGap(5)
								.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(nombreTField, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblApellido1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(apellido1TField, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
								.addGap(75)
								.addComponent(lblApellido2, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(apellido2TField, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
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
								.addComponent(textFieldBusqueda, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
								.addGap(10)
								.addComponent(lblBuscarPor)
								.addGap(10)
								.addComponent(comboBoxBuscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(79))))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(72)
						.addComponent(btnArticulos)
						.addPreferredGap(ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
						.addComponent(btnVaciar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnNuevoMod, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addGap(77))
					.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
						.addGap(77)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(lblTablaDeDatos, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE))
						.addContainerGap())
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
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(16)
								.addComponent(lblDetalles, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addGap(22)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(3)
										.addComponent(lblCodUsu))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(codUsuTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(avisoYaExiste))
									.addComponent(nombreTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(66)
								.addComponent(lblNombre)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(11)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(apellido1TField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(apellido2TField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(14)
								.addComponent(lblApellido1))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(14)
								.addComponent(lblApellido2)))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnVaciar)
								.addComponent(btnNuevoMod)
								.addComponent(btnEliminar))
							.addComponent(btnArticulos))
						.addGap(102))
			);
			contentPane.setLayout(gl_contentPane);
			avisoYaExiste.setForeground(Color.WHITE);
			

			
			tableDatos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int fila = tableDatos.rowAtPoint(e.getPoint());
					if ((fila > -1)) {
						
					   codUsuTField.setText((String)(modelo.getValueAt(fila,0)));
					   apellido1TField.setText((String)(modelo.getValueAt(fila,3)));
					   nombreTField.setText((String)(modelo.getValueAt(fila,2)));
					   apellido2TField.setText((String)(modelo.getValueAt(fila,1)));
					   
					   codUsuTField.setEditable(false);
					   codUsuTField.setBackground(new Color(255,0,0));
					   
					   btnNuevoMod.setText("Modificar");
					   btnNuevoMod.setToolTipText("Modifica el registro seleccionado");
					   
					   btnEliminar.setEnabled(true);
					   
					   btnArticulos.setEnabled(true);
					   
					}
				}
			});
			
			codUsuTField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					
					if(Buscador.existeClave("JAVATECA_USUARIOS", "CODUSUARIO", codUsuTField.getText().toUpperCase(), conn, null)){
						
						avisoYaExiste.setForeground(Color.RED);
						textFieldBusqueda.setText(codUsuTField.getText());
						String sql = "select * from JAVATECA_USUARIOS where CODUSUARIO like upper('"+codUsuTField.getText()+"')";
						tableDatos.setModel(Buscador.buscar(sql, conn));
						btnNuevoMod.setText("Modificar");
						btnNuevoMod.setToolTipText("Modifica el registro seleccionado");
						btnEliminar.setEnabled(true);
						btnArticulos.setEnabled(true);
						
					}else {
						
						avisoYaExiste.setForeground(Color.WHITE);
						textFieldBusqueda.setText("");
						String sql = "select * from JAVATECA_USUARIOS";
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
						
						String sql = "INSERT INTO JAVATECA_USUARIOS(CODUSUARIO,NOMBRE,APELLIDO1,APELLIDO2) VALUES"
								+ "('"+codUsuTField.getText().toUpperCase()+"','"
								+ nombreTField.getText().toUpperCase()+"','"
								+ apellido1TField.getText().toUpperCase()+"','"
								+ apellido2TField.getText().toUpperCase()+"'"
								+ ")";
						
						if(ConBD.ejecutarUpdateSQL(conn, sql)) {
							
							
							
							textFieldBusqueda.setText("");
							sql = "select * from JAVATECA_USUARIOS";
							modelo = Buscador.buscar(sql, conn);
							tableDatos.setModel(modelo);
							
							Object frame = null;
							JOptionPane.showMessageDialog((Component) frame,
								    "Usuario con CODUSUARIO '"+codUsuTField.getText()+"' creada correctamente.","Creación correcta",JOptionPane.DEFAULT_OPTION);
							vaciar(conn);
							
							
						}else {
							
							Object frame = null;
							JOptionPane.showMessageDialog((Component) frame,
								    "El usuario con CODUSUARIO '"+codUsuTField.getText()+"' no se ha creado correctamente.","Error",JOptionPane.ERROR_MESSAGE);
							
						}
						
					}else {
						
						String sql = "UPDATE JAVATECA_USUARIOS SET "
								+ "NOMBRE = '"+nombreTField.getText().toUpperCase()+"',"
								+ "APELLIDO1 = '"+apellido1TField.getText().toUpperCase()+"',"
								+ "APELLIDO2 = '"+apellido2TField.getText().toUpperCase()+"'"
								+ " WHERE CODUSUARIO = '"+codUsuTField.getText().toUpperCase()+"'";
						
						//System.out.println(sql);
						
						if(ConBD.ejecutarUpdateSQL(conn, sql)) {
							
							Object frame = null;
							
							
							sql = "select * from JAVATECA_USUARIOS where CODUSUARIO = '"+codUsuTField.getText()+"'";
							modelo = Buscador.buscar(sql, conn);
							tableDatos.setModel(modelo);
							
							JOptionPane.showMessageDialog((Component) frame,
								    "Usuario con CODUSUARIO '"+codUsuTField.getText()+"' modificado correctamente.","Modificación correcta",JOptionPane.DEFAULT_OPTION);
							
							
						}else {
							
							Object frame = null;
							JOptionPane.showMessageDialog((Component) frame,
								    "El usuario con CODUSUARIO '"+codUsuTField.getText()+"' no se ha modificado correctamente.","Error",JOptionPane.ERROR_MESSAGE);
							
						}
						
						
					}
					
				}
			});
			
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					String sql = "DELETE from JAVATECA_USUARIOS WHERE CODUSUARIO = '"+codUsuTField.getText()+"'";
					
					if(ConBD.ejecutarUpdateSQL(conn, sql)) {
						
						textFieldBusqueda.setText("");
						sql = "select * from JAVATECA_USUARIOS";
						modelo = Buscador.buscar(sql, conn);
						tableDatos.setModel(modelo);
						
						Object frame = null;
						JOptionPane.showMessageDialog((Component) frame,
							    "Usuario con CODUSUARIO '"+codUsuTField.getText()+"' eliminado correctamente.","Eliminación correcta",JOptionPane.DEFAULT_OPTION);
						
						vaciar(conn);
						
					}else {
						
						Object frame = null;
						JOptionPane.showMessageDialog((Component) frame,
							    "El usuario con CODUSUARIO '"+codUsuTField.getText()+"' no se ha eliminado correctamente.","Error",JOptionPane.ERROR_MESSAGE);
						
					}
					
					
				}
			});
			btnArticulos.setEnabled(false);
			btnArticulos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					LibrosGUI.lanzaPrestamos(codUsuTField.getText());
					vaciar(conn);
					
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return contentPane;
		
	}
}
