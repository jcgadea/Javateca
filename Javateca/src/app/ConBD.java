package app;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
			
			return null;
		}
		
		return conn;
		
	}
	
}