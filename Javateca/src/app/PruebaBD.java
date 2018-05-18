package app;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PruebaBD {
	
	public static void chorrada(Connection conn) {
		
		try {
			Statement stat = conn.createStatement();
			
			String sql = "select * from libreria";
			
			ResultSet resultado = stat.executeQuery(sql);
			
			while(resultado.next()) {
				
				System.out.println(resultado.getString(1)+" "+resultado.getString(2)+" "+resultado.getString(3));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
