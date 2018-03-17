package ObjectRepository.General;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import SupportLibraries.DB;
import SupportLibraries.DBUtil;

public class ReadPDF {
/*
	public static void main(String[] args) {

		try {
		    String text = getText(new File("C:\\Working\\AM Huddle.pdf"));
		    System.out.println("Text in PDF: " + text);
		} catch (IOException e) {
		    e.printStackTrace();
		}

	}	
	static String getText(File pdfFile) throws IOException {
	    PDDocument doc = PDDocument.load(pdfFile);
	    return new PDFTextStripper().getText(doc);
	}
*/
	

	public static void main(String[] args) {

		try {
		    
			DB.getNetezzaData("select * from CCDB_QA2_RPT..FCT_RTE_EXECUTION");
			
		}  catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	
	

}
