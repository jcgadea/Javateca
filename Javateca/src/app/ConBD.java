package app;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConBD {
	
	public static Connection conectar(String url, String puerto, String usuario, String pass) {
		
		Connection conn;
		
		url = "jdbc:oracle:thin:@"+url+":"+puerto+":xe";
		
		try {
			conn = DriverManager.getConnection(url,usuario,pass);
		} catch (SQLException e) {
			
			Component frame = null;
			JOptionPane.showMessageDialog(frame,
				    e.getMessage(),
				    "Error de conexión",
				    JOptionPane.ERROR_MESSAGE);
			System.exit(0);
			return null;
			
		}
		
		return conn;
		
	}
	
	public static boolean ejecutarUpdateSQL(Connection conn,String sql) {
		
		Statement stat;
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		
	}
	
}