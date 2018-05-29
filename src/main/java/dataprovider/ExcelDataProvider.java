package dataprovider;
import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

//import com.abc.util.PropertyFileRead;
import util.PropertyReader;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

public class ExcelDataProvider {
	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method


@DataProvider
public static Object[][] getData()
{
	// Excel Read
	
	String xlsPath = System.getProperty("user.dir") + "\\data.xlsx";
	//String xlsPath = PropertyFileRead.FileRead("DBDetail.properties","DataProviderExcelPath"); // Use this path if File is kept in remote location other than project
	int TotalRows =0;
	 FileInputStream fis;
	 Object[][] data = null;
	try {
		fis = new FileInputStream(xlsPath);
	
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
        int index = workbook.getSheetIndex(PropertyReader.fileRead("ProjectData.properties","DataProviderExcelFileSheetName"));
       
        XSSFSheet sheet = workbook.getSheetAt(index);
        //HSSFSheet sheet = workbook.getSheet("Sheet1");
         TotalRows = sheet.getLastRowNum();
        int TotalCols = sheet.getRow(0).getLastCellNum();
        data = new Object[TotalRows][1];
        System.out.println("Total No of Column found in Excel Data Provider" +TotalCols );
        for (int i = 1; i <= TotalRows; i++) {
        	XSSFRow row = sheet.getRow(i);
        	ArrayList<String> data33 = new ArrayList<String>();
        	//row.getCell(1).getStringCellValue()
        	for (int j = 0; j <= TotalCols-1; j++) {
        		System.out.println("Excel Data in Column number "+ j + " is ::" +row.getCell(j).getStringCellValue());
        		data33.add(row.getCell(j).getStringCellValue());
        		
        	}
        	
        	
        	data[i-1][0] =new ExcelDataProviderObject(data33);
        
        }
	
		} 
	 catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

return data;
}





@DataProvider
public static Object[][] getProductData()
{
	// Excel Read
	
	String xlsPath = System.getProperty("user.dir") + "\\data.xlsx";
	//String xlsPath = PropertyFileRead.FileRead("DBDetail.properties","DataProviderExcelPath"); // Use this path if File is kept in remote location other than project
	int TotalRows =0;
	 FileInputStream fis;
	 Object[][] data = null;
	try {
		fis = new FileInputStream(xlsPath);
	
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        //XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
        int index = workbook.getSheetIndex("Approvals");
       
        XSSFSheet sheet = workbook.getSheetAt(index);
        //HSSFSheet sheet = workbook.getSheet("Sheet1");
         TotalRows = sheet.getLastRowNum();
        int TotalCols = sheet.getRow(0).getLastCellNum();
        data = new Object[TotalRows][1];
        System.out.println("Total No of Column found in Excel Data Provider" +TotalCols );
        for (int i = 1; i <= TotalRows; i++) {
        	XSSFRow row = sheet.getRow(i);
        	ArrayList<String> data33 = new ArrayList<String>();
        	//row.getCell(1).getStringCellValue()
        	for (int j = 0; j <= TotalCols-1; j++) {
                DataFormatter formatter = new DataFormatter();
                String val = formatter.formatCellValue(sheet.getRow(i).getCell(j));
        		//System.out.println("Excel Data in Column number "+ j + " is ::" +row.getCell(j).getStringCellValue());
                //data33.add(row.getCell(j).getStringCellValue());
                //String celldata = row.getCell(j).setCellType(CELL_TYPE_STRING);
                //data33.add(row.getCell(j).getStringCellValue());
                data33.add(val);
        	}
        	
        	
        	data[i-1][0] =new ExcelDataProviderObject(data33);
        
        }
	
		} 
	 catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

return data;
}

    @DataProvider
    public static Object[][] getDealPriceData()
    {
        // Excel Read

        String xlsPath = System.getProperty("user.dir") + "\\DealPriceApprovalScenario.xlsx";
        //String xlsPath = PropertyFileRead.FileRead("DBDetail.properties","DataProviderExcelPath"); // Use this path if File is kept in remote location other than project
        int TotalRows =0;
        FileInputStream fis;
        Object[][] data = null;
        try {
            fis = new FileInputStream(xlsPath);

            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            //XSSFWorkbook workbook = new XSSFWorkbook(fis);

            int index = workbook.getSheetIndex("Approvals");

            XSSFSheet sheet = workbook.getSheetAt(index);
            //HSSFSheet sheet = workbook.getSheet("Sheet1");
            TotalRows = sheet.getLastRowNum();
            int TotalCols = sheet.getRow(0).getLastCellNum();
            data = new Object[TotalRows][1];
            System.out.println("Total No of Column found in Excel Data Provider" +TotalCols );
            for (int i = 1; i <= TotalRows; i++) {
                XSSFRow row = sheet.getRow(i);
                ArrayList<String> data33 = new ArrayList<String>();
                //row.getCell(1).getStringCellValue()
                for (int j = 0; j <= TotalCols-1; j++) {
                    DataFormatter formatter = new DataFormatter();
                    String val = formatter.formatCellValue(sheet.getRow(i).getCell(j));
                    //System.out.println("Excel Data in Column number "+ j + " is ::" +row.getCell(j).getStringCellValue());
                    //data33.add(row.getCell(j).getStringCellValue());
                    //String celldata = row.getCell(j).setCellType(CELL_TYPE_STRING);
                    //data33.add(row.getCell(j).getStringCellValue());
                    data33.add(val);
                }


                data[i-1][0] =new ExcelDataProviderObject(data33);

            }

        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return data;
    }

    @DataProvider
    public static Object[][] priceLookUpQuote()
    {
        // Excel Read

        String xlsPath = System.getProperty("user.dir") + "\\PriceLookUpQuote.xlsx";
        //String xlsPath = PropertyFileRead.FileRead("DBDetail.properties","DataProviderExcelPath"); // Use this path if File is kept in remote location other than project
        int TotalRows =0;
        FileInputStream fis;
        Object[][] data = null;
        try {
            fis = new FileInputStream(xlsPath);

            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            //XSSFWorkbook workbook = new XSSFWorkbook(fis);

            int index = workbook.getSheetIndex("Details");

            XSSFSheet sheet = workbook.getSheetAt(index);
            //HSSFSheet sheet = workbook.getSheet("Sheet1");
            TotalRows = sheet.getLastRowNum();
            int TotalCols = sheet.getRow(0).getLastCellNum();
            data = new Object[TotalRows][1];
            System.out.println("Total No of Column found in Excel Data Provider" +TotalCols );
            for (int i = 1; i <= TotalRows; i++) {
                XSSFRow row = sheet.getRow(i);
                ArrayList<String> data33 = new ArrayList<String>();
                //row.getCell(1).getStringCellValue()
                for (int j = 0; j <= TotalCols-1; j++) {
                    DataFormatter formatter = new DataFormatter();
                    String val = formatter.formatCellValue(sheet.getRow(i).getCell(j));
                    //System.out.println("Excel Data in Column number "+ j + " is ::" +row.getCell(j).getStringCellValue());
                    //data33.add(row.getCell(j).getStringCellValue());
                    //String celldata = row.getCell(j).setCellType(CELL_TYPE_STRING);
                    //data33.add(row.getCell(j).getStringCellValue());
                    data33.add(val);
                }


                data[i-1][0] =new ExcelDataProviderObject(data33);

            }

        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return data;
    }

    @DataProvider
    public static Object[][] accountCreation()
    {
        // Excel Read

        String xlsPath = System.getProperty("user.dir") + "\\AccountCreation.xlsx";
        //String xlsPath = PropertyFileRead.FileRead("DBDetail.properties","DataProviderExcelPath"); // Use this path if File is kept in remote location other than project
        int TotalRows =0;
        FileInputStream fis;
        Object[][] data = null;
        try {
            fis = new FileInputStream(xlsPath);

            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            //XSSFWorkbook workbook = new XSSFWorkbook(fis);

            int index = workbook.getSheetIndex("data");

            XSSFSheet sheet = workbook.getSheetAt(index);
            //HSSFSheet sheet = workbook.getSheet("Sheet1");
            TotalRows = sheet.getLastRowNum();
            int TotalCols = sheet.getRow(0).getLastCellNum();
            data = new Object[TotalRows][1];
            System.out.println("Total No of Column found in Excel Data Provider" +TotalCols );
            for (int i = 1; i <= TotalRows; i++) {
                XSSFRow row = sheet.getRow(i);
                ArrayList<String> data33 = new ArrayList<String>();
                //row.getCell(1).getStringCellValue()
                for (int j = 0; j <= TotalCols-1; j++) {
                    DataFormatter formatter = new DataFormatter();
                    String val = formatter.formatCellValue(sheet.getRow(i).getCell(j));
                    //System.out.println("Excel Data in Column number "+ j + " is ::" +row.getCell(j).getStringCellValue());
                    //data33.add(row.getCell(j).getStringCellValue());
                    //String celldata = row.getCell(j).setCellType(CELL_TYPE_STRING);
                    //data33.add(row.getCell(j).getStringCellValue());
                    data33.add(val);
                }


                data[i-1][0] =new ExcelDataProviderObject(data33);

            }

        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return data;
    }

}
