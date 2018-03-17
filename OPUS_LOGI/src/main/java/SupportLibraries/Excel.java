package SupportLibraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	XSSFSheet ExcelWSheet;
	XSSFWorkbook ExcelWBook;
	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	
	public Excel(String path) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			ExcelWBook = new XSSFWorkbook(fis);
			ExcelWSheet = ExcelWBook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Excel Not Found !");
		}
	}

	public Excel() {
		// TODO Auto-generated constructor stub
	}

	public Object[][] getTableArray(String FilePath, String SheetName) throws Exception {
		
		String[][] tabArray = null;
		try {
			
			
			File file = new File(FilePath);
			OPCPackage opcPackage = OPCPackage.open(file);
			XSSFWorkbook ExcelWBook = new XSSFWorkbook(opcPackage);
			
			
			//FileInputStream ExcelFile = new FileInputStream(FilePath);
			// Access the required test data sheet
			//ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int startRow = 1; // as first row is header / column values
			int startCol = 0;
			int totalRows = ExcelWSheet.getLastRowNum();
			// you can write a function as well to get Column count
			int totalCols = ExcelWSheet.getRow(0).getLastCellNum();
			tabArray = new String[totalRows][totalCols];
			for (int i = startRow; i <= totalRows; i++) {
				for (int j = startCol; j < totalCols; j++) {
					tabArray[i - 1][j] = getCellData(i, j);
				}
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}

		catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return (tabArray);
	}

	@SuppressWarnings("deprecation")
	public String getCellData(int RowNum, int ColNum) throws Exception {
		DataFormatter df = new DataFormatter();
		try {
			Row row = ExcelWSheet.getRow(RowNum);
			String CellVal = df.formatCellValue(row.getCell(ColNum));
			return CellVal.trim();

		} catch (Exception e) {
			System.out.println("Read Excel inside" + e.getMessage());
			throw (e);
		}
	}
	

	public String getCellValue(int RowNum, int ColNum) throws Exception {
		
		try {
			DataFormatter df = new DataFormatter();
			Row row = ExcelWSheet.getRow(RowNum);
			Cell cell=row.getCell(ColNum);
			if (cell == null)
				return "";
			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
					|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

				String cellText =  df.formatCellValue(row.getCell(ColNum));
				
				if(cellText.contains("AM") || cellText.contains("PM") || cellText.contains("/"))
				{
					return cellText;
				}
				
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// format in form of hh:mm
					String[] split = cellText.split( ":",2);
					int mins = Integer.valueOf(split[ 0 ]) * 60 + Integer.valueOf( split[ 1 ] );					
					cellText=String.format("%02d",(int)(mins/60)) + ":" +String.format("%02d",( mins % 60 ));
				}
				return cellText;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
			

		} catch (Exception e) {
			System.out.println("Read Excel inside" + e.getMessage());
			throw (e);
		}
	}
	
	// returns the row count in a sheet
	public int getRowCount(String sheetName) {
		int index = ExcelWBook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			ExcelWSheet = ExcelWBook.getSheetAt(index);
			int number = ExcelWSheet.getLastRowNum() + 1;
			return number;
		}
	}
	
	// returns the data from a cell
		public String getCellData(String sheetName, String colName, int rowNum) {
			try {
				if (rowNum <= 0)
					return "";
				int index = ExcelWBook.getSheetIndex(sheetName);
				int col_Num = -1;
				if (index == -1)
					return "";
				ExcelWSheet = ExcelWBook.getSheetAt(index);
				row = ExcelWSheet.getRow(0);
			
				for (int i = 0; i < row.getLastCellNum(); i++) {
					//System.out.println(row.getCell(i).getStringCellValue().trim());
					if (row.getCell(i).getStringCellValue().trim()
							.equals(colName.trim()))
						col_Num = i;
				}
				if (col_Num == -1)
					return "";
				ExcelWSheet = ExcelWBook.getSheetAt(index);
				row = ExcelWSheet.getRow(rowNum - 1);
				if (row == null)
					return "";
				cell = row.getCell(col_Num);
				if (cell == null)
					return "";
				if (cell.getCellType() == Cell.CELL_TYPE_STRING)
					return cell.getStringCellValue();
				else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
						|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

					String cellText = String.valueOf(cell.getNumericCellValue());
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						// format in form of M/D/YY
						double d = cell.getNumericCellValue();

						Calendar cal = Calendar.getInstance();
						cal.setTime(HSSFDateUtil.getJavaDate(d));
						cellText = (String.valueOf(cal.get(Calendar.YEAR)))
								.substring(2);
						cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;
					}

					return cellText;
				} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
					return "";
				else
					return String.valueOf(cell.getBooleanCellValue());
			} catch (Exception e) {
				e.printStackTrace();
				return "row " + rowNum + " or column " + colName + " does not exist in xls";
			}
		}
	
		public List<String> getColumnData(String FilePath, String colName, int rowNum, String reportName) {
			
			List<String> columnData=new ArrayList<>();
			int totalRows = 0;
		
				
				try {
					FileInputStream ExcelFile = new FileInputStream(FilePath);
					// Access the required test data sheet
					ExcelWBook = new XSSFWorkbook(ExcelFile);
					ExcelWSheet = ExcelWBook.getSheetAt(0);
					int startRow = rowNum; // Row from where the actual data will be available
					int startCol = 0;
					int totalCols = ExcelWSheet.getRow(startRow).getLastCellNum();
					for (int i = 0; i < totalCols; i++) {
						Row row = ExcelWSheet.getRow(startRow);
						Cell cell=row.getCell(i);
						System.out.println(row.getCell(i).getStringCellValue().trim());
						System.out.println(colName.trim());
						if (row.getCell(i).getStringCellValue().trim()
								.equals(colName.trim()))
						{
							startCol = i;
							break;
						}
					}
					
					if(reportName.equals("Efficiency Performance"))
					totalRows = ExcelWSheet.getLastRowNum()-3;
					else
					totalRows = ExcelWSheet.getLastRowNum();
					// you can write a function as well to get Column count
					//int totalCols = ExcelWSheet.getRow(startRow).getLastCellNum();
					
					for (int i = startRow+1; i <= totalRows; i++) {			
						//columnData.add(getCellData(i, startCol));
						String cellValue=getCellValue(i, startCol);
						if(!cellValue.equals(""))
						{
						columnData.add(getCellValue(i, startCol).trim().replaceAll(",", ""));
						}
					}
				}
				catch (FileNotFoundException e) {
					System.out.println("Could not read the Excel sheet");
					e.printStackTrace();
				}
				catch (IOException e) {
					System.out.println("Could not read the Excel sheet");
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return (columnData);
			}
		
}
