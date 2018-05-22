package app;

import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.filechooser.*;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

public class CrearPDF {
	
	private static File file;
	
	public static void generar(Connection conn, String tablas, String titulo) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			JFrame frame = new JFrame("Guardar archivo");
			
			JFileChooser dialogoRuta = new JFileChooser();
			
			frame.add(dialogoRuta);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF", "pdx", "text");
			
			dialogoRuta.setFileFilter(filter);
			dialogoRuta.showOpenDialog(null);
			
			file = dialogoRuta.getSelectedFile();
			
			if(file.getName().length() < 5) {
				
				String rutaFile = file.getCanonicalPath().toString();
				file = new File(rutaFile+tablas+".pdf");
				System.out.println(file.getName());
				
			}
			
			String extension = file.getName().substring((file.getName().length()-4),file.getName().length());
			
			if(!extension.equals(".pdf")) {
				
				String rutaFile = file.getAbsolutePath().toString();
				file = new File(rutaFile+".pdf");
				
				
			}
			
			
			
			PdfWriter escritor = new PdfWriter(file.getAbsolutePath());
			PdfDocument pdf = new PdfDocument(escritor);
			Document documento = new Document(pdf);
			
			Paragraph encabezado = new Paragraph("Lista de "+titulo); 
			encabezado.setBold();
			encabezado.setTextAlignment(TextAlignment.CENTER);
			
			documento.add(encabezado);
			
			ResultSet rset;
			
			//Las fechas en la tabla SQL hacen que no se genere el PDF.
			if(tablas.equals("JAVATECA_PRESTAMOS")) {
				
				rset = Buscador.ejecutarSQL(conn, "select codusuario,codmaterial,tipomaterial, from JAVATECA_PRESTAMOS");
				
			}else {
			
				rset = Buscador.ejecutarSQL(conn, "select * from "+tablas);
			}
			
			ResultSetMetaData rsMeta = rset.getMetaData();
			
			int numCol = rsMeta.getColumnCount();
			
			@SuppressWarnings("deprecation")
			Table tabla = new Table(numCol);
			
			for (int i = 0; i < numCol; i++){
				
				tabla.addCell(rsMeta.getColumnLabel(i + 1));
				
			}
			
			while(rset.next()) {
				
				for(int i=0;i<numCol;i++) {
					tabla.addCell(rset.getObject(i+1).toString());
				}
				
			}
			
			rset.close();
			documento.add(tabla);
			
			documento.close();
			frame.dispose();
			
			Desktop.getDesktop().open(file);
			
        } catch (Exception ex) {
            ex.getStackTrace();
        }

		
		
		
	}

}
