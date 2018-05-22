package app;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JOptionPane;

public class Configuracion {
	
	public static String leerValor(String config) {
		
		File file;
		BufferedReader lector = null;
		String linea;
		
		try {
			
			file = new File("config/Javateca.cfg");
			lector = new BufferedReader(new FileReader(file));
			
			while((linea = lector.readLine()) != null) {
				
				String comando = descomponerCon(linea);
				
				if(comando.equals(config)) {
					
					return descomponerValor(linea);
					
				}
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
			
		}finally {
			
			try {
				lector.close();
			} catch (IOException e) {
				Object frame = null;
				JOptionPane.showMessageDialog((Component) frame,
					    "Ocurrió un error de comunicación.\nMétodo: Configuracion.leerValor","Error IOException",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			
		}
		
		return null;
		
	}
	
	private static String descomponerCon(String linea) {
		
		int termina = 0;
		
		for (int i = 0; i < linea.length(); i++) {
			
			if(linea.charAt(i) == '=') {
				
				termina = i;
				
			}
			
		}
		
		return (linea.substring(0, termina)).trim();
		
	}
	
	private static String descomponerValor(String linea) {
		
		int termina = 0;
		
		for (int i = 0; i < linea.length(); i++) {
			
			if(linea.charAt(i) == '=') {
				
				termina = i;
				
			}
			
		}
		
		int posEmpieza = 0,posAcaba = 0;
			
		for (int i = termina; i < linea.length(); i++) {
				
			if(linea.charAt(i) == '[') {
					
				posEmpieza = i;
					
			}
				
			if(linea.charAt(i) == ']') {
					
				posAcaba = i;
					
			}
		}
			
		return linea.substring(posEmpieza+1, posAcaba);
		
	}
	
	private static void crearConfig() {
		
		File configFile = new File("config/Javateca.cfg");
		String[] lineasConfig = {"recordarLogin = [false];","login.url = [];","login.puerto = [];","login.usuario = [];","interfaz.estilo = [];"};
		
		BufferedWriter escritor;
		try {
			
			escritor = new BufferedWriter(new FileWriter(configFile));
		
		for(int i = 0; i < lineasConfig.length; i++) {
			
			escritor.write(lineasConfig[i]);
			escritor.newLine();
			
		}
		
		escritor.flush();
		
		escritor.close();
		
		} catch (IOException e) {
			Object frame = null;
			JOptionPane.showMessageDialog((Component) frame,
				    "Ocurrió un error de comunicación.\nMétodo: Configuracion.crearConfig","Error IOException",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}
	
	public static boolean escribir(String con,String valor) {
		
		
		File tempFile = new File("config/temp");
		File configFile = new File("config/Javateca.cfg");
		
		BufferedReader lector = null;
		BufferedReader lectorLineas = null;
		BufferedWriter temp;
		
		try {
			
			String linea;
			
			lector = new BufferedReader(new FileReader(configFile));
			lectorLineas = new BufferedReader(new FileReader(configFile));
			
			temp = new BufferedWriter(new FileWriter(tempFile));
			
			Object[] numLineas = lectorLineas.lines().toArray();
			
			lectorLineas.close();
			
			
					
			int i = 0;
			
			while((linea = lector.readLine()) != null) {
				
				if(!descomponerCon(linea).equals(con)) {
					
					temp.write(linea);
					
				}else {
					
					
					temp.write(con+" = ["+valor+"]");
					
				}
				
				i++;
				
				if(i < numLineas.length) {
					
					temp.newLine();

				}
				
			}
			
			temp.flush();
			lector.close();
			temp.close();

			if(configFile.delete()) {
				
				Files.move(tempFile.toPath(), configFile.toPath());
				
			}else {
				
				Object frame = null;
				JOptionPane.showMessageDialog((Component) frame,
					    "Ocurrió un error al guardar la información.","Error configFile.delete()",JOptionPane.ERROR_MESSAGE);
				
				lector.close();
				temp.close();
				
			}
			
		} catch (FileNotFoundException e) {
			
			crearConfig();
			System.out.println("Archivo de configuracion Javateca.cfg no encontrado, creando uno nuevo...");
			return false;
			
		} catch (IOException e) {
			
			Object frame = null;
			JOptionPane.showMessageDialog((Component) frame,
				    "Ocurrió un error de comunicación.\nMétodo: Configuracion.escribir","Error IOException",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
			
		}
		
		return true;


			
	
	
	}

}
