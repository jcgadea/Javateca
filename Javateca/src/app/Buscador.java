package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class Buscador {
	
	//Inicializo variables y objetos.
	private static Statement stat;
	private static ResultSet resultado;
	private static ResultSetMetaData metaDatos;
	private static DefaultTableModel modelo;
	private static Object[] etiquetas;
	private static int numColumnas;
	
	public static DefaultTableModel buscar(String sql,Connection conn) {
		
		/*
		 * Esta clase estatica recibe una String SQL y una conexion a basede datos, y
		 * devuelve un modelo de tabla.
		 * 
		 * La usan todos los dialogos que usan algun tipo de buscador.
		 * */
		

		
		
		try {
			
			modelo = new DefaultTableModel();
			
			stat = conn.createStatement();
			resultado = stat.executeQuery(sql);
			metaDatos = resultado.getMetaData();
			
			numColumnas = metaDatos.getColumnCount();
			
			etiquetas = new Object[numColumnas];
			
			for(int i = 0; i < numColumnas; i++) {
				
				etiquetas[i] = metaDatos.getColumnLabel(i + 1);
				
			}
			
			modelo.setColumnIdentifiers(etiquetas);
			
			while (resultado.next()) {
			    	
				Object[] datosFila = new Object[numColumnas];
				
				for (int i = 0; i < numColumnas; i++) {
					
				    datosFila[i] = resultado.getObject(i + 1);
				    
				}
				
				modelo.addRow(datosFila);
			        
			}
	            
	        resultado.close();
			
			return modelo;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
							
	}
	
	public static DefaultTableModel buscarCodigos(String tipo,Connection conn) {
		
		try {
			
			String sql=null;
			
			modelo = new DefaultTableModel();
			
			stat = conn.createStatement();
			
			if(tipo.equals("usuario")) {
				
				sql = "SELECT CODUSUARIO,NOMBRE,APELLIDO1 FROM JAVATECA_USUARIOS";
				
			}else if(tipo.equals("material")) {
				
				sql ="select 'LIBRO',ISBN,TITULO from JAVATECA_LIBROS"
				+" UNION"
				+" select 'CDROM',COD_CDROM,TITULO from JAVATECA_CDROMS"
				+" UNION"
				+" select 'REVISTA',COD_REVISTA,NOMBRE from JAVATECA_REVISTAS";
				
			}
			
			resultado = stat.executeQuery(sql);
			metaDatos = resultado.getMetaData();
			
			numColumnas = metaDatos.getColumnCount();
			
			etiquetas = new Object[numColumnas];
			
			for(int i = 0; i < numColumnas; i++) {
				
				etiquetas[i] = metaDatos.getColumnLabel(i + 1);
				
			}
			
			modelo.setColumnIdentifiers(etiquetas);
			
			while (resultado.next()) {
			    	
				Object[] datosFila = new Object[numColumnas];
				
				for (int i = 0; i < numColumnas; i++) {
					
				    datosFila[i] = resultado.getObject(i + 1);
				    
				}
				
				modelo.addRow(datosFila);
			        
			}
	            
	        resultado.close();
			
			return modelo;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
							
	}
	
	public static boolean existeClave(String tabla, String campo, String clave,Connection conn,String codRevista) {
		
		String sql;
		
		if(codRevista != null) {
			
			sql = "select count(*) from "+tabla+" where "+campo+" like '"+clave+"' and COD_REVISTA like '"+codRevista+"'";
			
		} else {
		
			sql = "select count(*) from "+tabla+" where "+campo+" like '"+clave+"'";
		
		}
		
		try {
			
			stat = conn.createStatement();
			resultado = stat.executeQuery(sql);
			
			resultado.next();
			
			if(Integer.valueOf(resultado.getString(1)) >= 1) {
				
				return true;
				
			}else {
				
				return false;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		
	}
	
	public static ResultSet ejecutarSQL(Connection conn,String sql) throws SQLException {
		
		stat = conn.createStatement();
		return stat.executeQuery(sql);
		
	}
	
	
}
