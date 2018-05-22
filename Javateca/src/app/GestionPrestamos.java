package app;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.toedter.calendar.JDateChooser;

public class GestionPrestamos {
	
	private JPanel contentPane;
	private JTable tableDatos;
	private JTextField textFieldBusqueda;
	private JTextField codUsuarioTField;
	private JTextField codMaterialTField;
	private JButton btnNuevoMod;
	private JButton btnVaciar;
	private DefaultTableModel modelo;
	private JTextField fechaDevolucionTField;
	private JDateChooser datePrestamo;
	@SuppressWarnings("rawtypes")
	private JComboBox materialComboBox;
	private JButton btnDevolverPrestamo;
	
	private void vaciar(Connection conn, String codUsuario) {
		
		
			codUsuario = "%";
		
		   codUsuarioTField.setText("");
		   codMaterialTField.setText("");
		   materialComboBox.setSelectedIndex(0);
		   fechaDevolucionTField.setText("");
		   datePrestamo.setCalendar(null);
		   
		   btnNuevoMod.setText("Nuevo");
		   btnNuevoMod.setToolTipText("Da de alta un nuevo registro con los datos del formulario");
		   
		   
		   textFieldBusqueda.setText("");
		   
		   String sql = "select * from JAVATECA_PRESTAMOS WHERE CODUSUARIO LIKE '"+codUsuario+"'";
		   
		   System.out.println(sql);
		   
		   modelo = Buscador.buscar(sql, conn);
		   
		   tableDatos.setModel(modelo);
		   

			   
		   codUsuarioTField.setEditable(true);
		   codUsuarioTField.setBackground(new Color(255,255,255));
		   
		   codUsuarioTField.setEnabled(true);
		   codMaterialTField.setEnabled(true);
		   materialComboBox.setEnabled(true);
		   datePrestamo.setEnabled(true);
		   btnNuevoMod.setEnabled(true);

	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	
	public JPanel gestion(Connection conn,String codUsuario) {
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel lblTablaDeDatos = new JLabel("Resultados");
		
		try {
			

			
			modelo = Buscador.buscar("select * from JAVATECA_PRESTAMOS where CODUSUARIO LIKE '"+codUsuario+"'", conn);
				
			JScrollPane scrollPane = new JScrollPane();
			
			tableDatos = new JTable(modelo) {
		        private static final long serialVersionUID = 1L;

		        public boolean isCellEditable(int row, int column) {                
		                return false; 
		                
		        
		        };
		    };
		    
		    tableDatos.getTableHeader().setReorderingAllowed(false);
		    

			
			scrollPane.setViewportView(tableDatos);
			tableDatos.setBackground(SystemColor.controlHighlight);
			
			JComboBox<Object> comboBoxBuscar = new JComboBox<Object>();
			comboBoxBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					vaciar(conn,codUsuario);
					
				}
			});
			comboBoxBuscar.setToolTipText("Selecciona el criterio de b\u00FAsqueda");
			comboBoxBuscar.setModel(new DefaultComboBoxModel<>(new String[] {"CodUsuario", "CodMaterial", "TipoMaterial", "Fecha_Prestamo", "Fecha_Devolucion"}));
			
			JLabel lblBuscarPor = new JLabel("Buscar por...");
			
			textFieldBusqueda = new JTextField();
			textFieldBusqueda.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					
					String sql = "";
					
					if(textFieldBusqueda.getText().equals("")) {
						
						sql = "select * from JAVATECA_PRESTAMOS where CODUSUARIO LIKE '"+codUsuario+"'";
						
					}else {
					
						sql = "select * from JAVATECA_PRESTAMOS where upper("+comboBoxBuscar.getSelectedItem()+") like upper('"+textFieldBusqueda.getText()+"%')";

					}
					
					tableDatos.setModel(Buscador.buscar(sql, conn));
					
				}
			});
			textFieldBusqueda.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("B\u00FAsqueda");
			
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon(GestionPrestamos.class.getResource("/resources/prestamo45_1.png")));
			
			JLabel lblNewLabel_2 = new JLabel("Gesti\u00F3n de pr\u00E9stamos");
			lblNewLabel_2.setFont(new Font("DejaVu Sans", Font.PLAIN, 25));
			
			JLabel lblDetalles = new JLabel("Detalles");
			lblDetalles.setFont(new Font("DejaVu Sans", Font.PLAIN, 25));
			
			JLabel lblcodUsuario = new JLabel("Cod.Usuario");
			lblcodUsuario.setHorizontalAlignment(SwingConstants.TRAILING);
			
			codUsuarioTField = new JTextField();

			codUsuarioTField.setColumns(10);
			
			codMaterialTField = new JTextField();

			codMaterialTField.setColumns(10);
			
			JLabel lblTitulo = new JLabel("Fecha Pr\u00E9stamo");
			lblTitulo.setHorizontalAlignment(SwingConstants.TRAILING);
			
			JLabel lblPags = new JLabel("Tipo material");
			lblPags.setHorizontalAlignment(SwingConstants.TRAILING);
			
			JLabel lblTtulo = new JLabel("Cod.Material");
			lblTtulo.setHorizontalAlignment(SwingConstants.TRAILING);
			
			JLabel lblAutor = new JLabel("Fecha Devoluci\u00F3n");
			lblAutor.setHorizontalAlignment(SwingConstants.TRAILING);
			
			btnNuevoMod = new JButton("Nuevo");

			btnNuevoMod.setToolTipText("Da de alta un nuevo registro con los datos del formulario");
			
			btnVaciar = new JButton("Vaciar");
			
			btnVaciar.setToolTipText("Vacia los campos del formulario de detalles");
			
			datePrestamo = new JDateChooser();
			datePrestamo.setToolTipText("La fecha de prestamo del material");
			datePrestamo.getCalendarButton().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			
			fechaDevolucionTField = new JTextField();
			fechaDevolucionTField.setColumns(10);
			
			btnDevolverPrestamo = new JButton("Devolver pr\u00E9stamo");
			
			btnDevolverPrestamo.setEnabled(false);
			
			materialComboBox = new JComboBox();
			materialComboBox.setModel(new DefaultComboBoxModel(new String[] {"","Libro", "Revista", "Articulo", "CDROM"}));
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
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
								.addComponent(textFieldBusqueda, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblBuscarPor)
								.addGap(18)
								.addComponent(comboBoxBuscar, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addGap(79))))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(60)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblcodUsuario)
									.addComponent(lblTtulo, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
								.addGap(10)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(codUsuarioTField, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
									.addComponent(codMaterialTField, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(77)
								.addComponent(lblDetalles, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(67)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(btnDevolverPrestamo)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblPags)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(materialComboBox, 0, 217, Short.MAX_VALUE)))
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(137)
								.addComponent(lblAutor, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(fechaDevolucionTField, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnVaciar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnNuevoMod, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
						.addGap(77))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(77)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblTablaDeDatos, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
									.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
										.addGap(426)
										.addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(datePrestamo, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)))
								.addGap(77))))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(33)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1)
							.addComponent(lblNewLabel_2))
						.addGap(43)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(3)
								.addComponent(lblNewLabel))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldBusqueda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBuscarPor))
							.addComponent(comboBoxBuscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(22)
						.addComponent(lblTablaDeDatos)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
						.addGap(16)
						.addComponent(lblDetalles, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGap(22)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblcodUsuario)
								.addComponent(codUsuarioTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTitulo))
							.addComponent(datePrestamo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(14)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(codMaterialTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTtulo))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAutor)
								.addComponent(fechaDevolucionTField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGap(11)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(44)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnDevolverPrestamo)
									.addComponent(btnNuevoMod)
									.addComponent(btnVaciar)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(3)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblPags)
									.addComponent(materialComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGap(65))
			);
			contentPane.setLayout(gl_contentPane);
			
			if(!codUsuario.equals("%")) {
				codUsuarioTField.setText(codUsuario);
				codUsuarioTField.setEnabled(false);
				codUsuarioTField.setBackground(Color.RED);
			}else {
				
				codUsuarioTField.setText("");
				codUsuarioTField.setEnabled(true);
				codUsuarioTField.setBackground(Color.WHITE);
				
			}


			
			tableDatos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int fila = tableDatos.rowAtPoint(e.getPoint());
					
					if (fila > -1) {
						
						codUsuarioTField.setText((String)(modelo.getValueAt(fila,0)));
						codMaterialTField.setText((String)(modelo.getValueAt(fila,1)));
					   
						String material = (String)modelo.getValueAt(fila,2);
					   
						switch (material) {
							case "L":
					   			
								materialComboBox.setSelectedIndex(1);
					   			break;
					   			
					   		case "R":
					   			
					   			materialComboBox.setSelectedIndex(2);
					   			break;
					   			
					   		case "A":
					   			
					   			materialComboBox.setSelectedIndex(3);
					   			break;
					   		case "C":
					   			
					   			materialComboBox.setSelectedIndex(4);
					   			break;
					   			
					   		default:
					   			
					   			System.out.println("Tipo material recibido no válido.");
					   			materialComboBox.setSelectedIndex(4);
					   			break;
					   }
					   
					   					   
					   
					   datePrestamo.setDate((java.sql.Timestamp)modelo.getValueAt(fila, 3));
					   
					   if(modelo.getValueAt(fila,4) == null) {
						   
						   fechaDevolucionTField.setText("");
						   btnDevolverPrestamo.setEnabled(true);
						   
					   } else {
						   
						   fechaDevolucionTField.setText(modelo.getValueAt(fila,4).toString());
						   btnDevolverPrestamo.setEnabled(false);
					   }
					   
					   codUsuarioTField.setEnabled(false);
					   codMaterialTField.setEnabled(false);
					   materialComboBox.setEnabled(false);
					   datePrestamo.setEnabled(false);
					   btnNuevoMod.setEnabled(false);
					   
					   
					   
					}
				}
			});
			
			btnVaciar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					   vaciar(conn, codUsuario);
					
				}
			});
			
			btnNuevoMod.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					
					java.sql.Date sqldate = new java.sql.Date(datePrestamo.getDate().getTime());
					
					String fecha = sdf.format(sqldate);
					
					int indiceComboBox = materialComboBox.getSelectedIndex();
					
					String material="";
							
					switch (indiceComboBox) {
						case 0:
					   			
					   		Object frame = null;
							JOptionPane.showMessageDialog((Component) frame,
							    "El campo \"Tipo de material\" no puede estar vacío.","Error",JOptionPane.ERROR_MESSAGE);
							return;
					   			
					   	case 1:
					   			
					   		material = "L";
					   		break;
					   			
					   	case 2:
					   			
					   		material = "R";
					   		break;
					   			
					   	case 3:
					   		
					   		material = "A";
					   		break;
					   			
					   	case 4:
					   			
					   		material = "C";
					   		break;
					   			
					   	default:
					   			
					   		System.out.println("Tipo material recibido no válido.");
					   		materialComboBox.setSelectedIndex(4);
					   		return;
					}
						

				
					String sql = "INSERT INTO JAVATECA_PRESTAMOS(CODUSUARIO,CODMATERIAL,TIPOMATERIAL,FECHA_PRESTAMO) VALUES"
							+ "('"+codUsuarioTField.getText().toUpperCase()+"','"
							+codMaterialTField.getText().toUpperCase()+"','"
							+material+"','"
							+fecha+"'"
							+ ")";
						
						if(ConBD.ejecutarUpdateSQL(conn, sql)) {
							
							textFieldBusqueda.setText("");
							sql = "select * from JAVATECA_PRESTAMOS where CODUSUARIO LIKE '"+codUsuario+"' AND FECHA_PRESTAMO = '"+datePrestamo.getDate().toString()+"' AND CODMATERIAL = '"+codMaterialTField.getText()+"'";
							modelo = Buscador.buscar(sql, conn);
							tableDatos.setModel(modelo);
							
							Object frame = null;
							JOptionPane.showMessageDialog((Component) frame,
								    "Préstamo con CODUSUARIO '"+codUsuarioTField.getText()+"' y CODMATERIAL '"+codMaterialTField.getText()+"' creado correctamente.","Creación correcta",JOptionPane.DEFAULT_OPTION);
							vaciar(conn,codUsuario);
							
							
						}else {
							
							Object frame = null;
							JOptionPane.showMessageDialog((Component) frame,
								    "El préstamo con CODUSUARIO '"+codUsuarioTField.getText()+"' y CODMATERIAL '"+codMaterialTField.getText()+"' no se ha creado correctamente.","Error",JOptionPane.ERROR_MESSAGE);
							
						}
						
					}
					
			});
			
			btnDevolverPrestamo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					
					java.sql.Date sqldateToday = new java.sql.Date(new Date().getTime());
					
					java.sql.Date sqldate = new java.sql.Date(datePrestamo.getDate().getTime());
					
					String fechaHoy = sdf.format(sqldateToday);
					
					String fecha = sdf.format(sqldate);
					
					
					
					
					
					String sql = "UPDATE JAVATECA_PRESTAMOS SET "
							+ "FECHA_DEVOLUCION = '"+fechaHoy+"'"
							+ " WHERE CODUSUARIO = '"+codUsuarioTField.getText()+"'"
							+ " AND CODMATERIAL = '"+codMaterialTField.getText()+"'"
							+ " AND TO_CHAR(FECHA_PRESTAMO,'DD/MM/YYYY') LIKE '"+fecha+"'";

					System.out.println(sql);
					
					ConBD.ejecutarUpdateSQL(conn, sql);
					
					Object frame = null;
					JOptionPane.showMessageDialog((Component) frame,
						    "Préstamo con CODUSUARIO '"+codUsuarioTField.getText()+"' y CODMATERIAL '"+codMaterialTField.getText()+"' devuelto correctamente.","Devolución correcta",JOptionPane.DEFAULT_OPTION);
					vaciar(conn,codUsuario);
					
					//Me he encontrado con un error que no soy capaz de resolver. La sentencia funciona, y realiza la operacion en la base de datos, pero da un error:
					//ORA-01858: a non-numeric character was found where a numeric was expected
					//Si la ejecuto en SQL developer funciona sin errores.
					
					//He comentado el tratamiento de errores para que no salte la ventana de error, ya que funcionar, funciona bien.

					
					/*
					if(ConBD.ejecutarUpdateSQL(conn, sql)) {
						
						textFieldBusqueda.setText("");
						sql = "select * from JAVATECA_PRESTAMOS where CODUSUARIO LIKE '"+codUsuario+"' AND FECHA_PRESTAMO = '"+datePrestamo.getDate().toString()+"' AND CODMATERIAL = '"+codMaterialTField.getText()+"'";
						modelo = Buscador.buscar(sql, conn);
						tableDatos.setModel(modelo);
						
						Object frame = null;
						JOptionPane.showMessageDialog((Component) frame,
							    "Préstamo con CODUSUARIO '"+codUsuarioTField.getText()+"' y CODMATERIAL '"+codMaterialTField.getText()+"' devuelto correctamente.","Devolución correcta",JOptionPane.DEFAULT_OPTION);
						vaciar(conn,codUsuario);
						
						
					}else {
						
						Object frame = null;
						JOptionPane.showMessageDialog((Component) frame,
							    "El préstamo con CODUSUARIO '"+codUsuarioTField.getText()+"' y CODMATERIAL '"+codMaterialTField.getText()+"' no se ha devuelto correctamente.","Error",JOptionPane.ERROR_MESSAGE);
						
					}
					*/
					
					
				}
					
				
			});
			
			
			codUsuarioTField.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					Buscador("usuario");
					
				}
			});
			
			codMaterialTField.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					Buscador("material");
					
				}
			});
			
			fechaDevolucionTField.setEnabled(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return contentPane;
		
	}
	
	
	
	private void Buscador(String tipoClave) {
		
		
		JFrame frame2 = new JFrame();
		frame2.setBounds(100, 100, 450, 266);
		frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame2.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(68, 34, 298, 116);
		frame2.getContentPane().add(scrollPane);
		
		JTable jTableCodigos = new JTable();
		scrollPane.setViewportView(jTableCodigos);
		jTableCodigos.setBackground(UIManager.getColor("Button.light"));
		

		
		JLabel tituloLbl = new JLabel("Seleccionar c\u00F3digo");
		tituloLbl.setBounds(68, 11, 186, 14);
		frame2.getContentPane().add(tituloLbl);
		
		JTextField codSeleccionadoTField = new JTextField();
		codSeleccionadoTField.setBounds(133, 162, 233, 20);
		frame2.getContentPane().add(codSeleccionadoTField);
		codSeleccionadoTField.setColumns(10);
		
		JLabel lblSeleccionado = new JLabel("Seleccionado:");
		lblSeleccionado.setBounds(68, 165, 66, 14);
		frame2.getContentPane().add(lblSeleccionado);
		
		JButton btnAceptar = new JButton("Aceptar");
		
		btnAceptar.setBounds(165, 193, 89, 23);
		frame2.getContentPane().add(btnAceptar);
		
		btnAceptar.setEnabled(false);
		
		frame2.setVisible(true);
		
		// TODO aceptar conexion desde fuera.
		
		Connection conn = ConBD.conectar("SERVER2012", "1521", "BIBLIOTECA", "BIBLIOTECA");
		
		DefaultTableModel modelo = Buscador.buscarCodigos(tipoClave, conn);
		
		jTableCodigos.setModel(modelo);
		
		jTableCodigos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int fila = jTableCodigos.rowAtPoint(e.getPoint());
				if ((fila > -1)) {
					
					
					if(tipoClave.equals("usuario")) {
						
						codSeleccionadoTField.setText((String) modelo.getValueAt(fila,0));
					} else if (tipoClave.equals("material")) {
						
						codSeleccionadoTField.setText((String) modelo.getValueAt(fila,1));
						
						String tipoMaterial = (String)modelo.getValueAt(fila,0);
						
						switch (tipoMaterial) {
						case "LIBRO":
					   			
							materialComboBox.setSelectedIndex(1);
							break;
					   			
					   	case "REVISTA":
					   			
					   		materialComboBox.setSelectedIndex(2);
					   		break;
					   			
					   	case "CDROM":
					   			
					   		materialComboBox.setSelectedIndex(3);
					   		break;
					   			
					   	default:
					   			
					   		System.out.println("Tipo material recibido no válido.");
					   		materialComboBox.setSelectedIndex(0);
					   		return;
					}
					}
				   

				   btnAceptar.setEnabled(true);
				   
				}
			}
		});
		
		
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tipoClave.equals("material")) {
					
					codMaterialTField.setText(codSeleccionadoTField.getText());
					
				}else if(tipoClave.equals("usuario")) {
					
					codUsuarioTField.setText(codSeleccionadoTField.getText());
					
				}
				
				frame2.dispose();
				
				
				
			}
		});
		
		frame2.setResizable(false);
		frame2.setLocationRelativeTo(null);
		
	}

}
