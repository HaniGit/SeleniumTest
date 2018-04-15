package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import AutoScripts.InitialDriverFile;

public class ReusableMethods {
	static WebDriver driver;
	private static HSSFSheet ExcelWSheet;

	public static HSSFWorkbook ExcelWBook;

	public static HSSFCell Cell;
	
	public static Properties configProperty = new Properties();	
	public static String parentWindow;	

public static HSSFRow Row;
	
	 public static ExtentHtmlReporter htmlReporter;
	  public static ExtentReports extent;
	  public static ExtentTest logger;
	
	//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method

	public static void setExcelFile(String Path,String SheetName) throws Exception {

			try {

   			// Open the Excel file

			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet

			ExcelWBook = new HSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			} catch (Exception e){

				throw (e);

			}

	}

	//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

    public static String getCellData(int RowNum, int ColNum) throws Exception{

			try{

  			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

  			String CellData = Cell.getStringCellValue();

  			return CellData;

  			}catch (Exception e){

				return"";

  			}

    }	
	

    public static String getProperty(String key) throws Exception {
    	
		FileInputStream fi = new FileInputStream("C:\\Automation\\SeleniumTest\\src\\test\\java\\utility\\properties.properties");
		configProperty.load(fi);
		
		if(key!=null)
        {
        	return configProperty.getProperty(key).trim();
        }
		return null;
	
		
    }

    public static void propWrite() throws Exception {
    	Properties p = new Properties();
		FileInputStream fi = new FileInputStream("C:\\Automation\\SeleniumTest\\src\\test\\java\\utility\\properties.properties");
		p.load(fi);
	/*	String s1 = p.getProperty("user");
		String s = p.getProperty("password");
		String s2 = p.getProperty("nag");

		System.out.println("user = "+s1+"\npassword= "+s);
        System.out.println(s2);
		p.setProperty("Aana","8888");*/
		FileOutputStream fos = new FileOutputStream("C:\\Automation\\SeleniumTest\\src\\test\\java\\utility\\properties.properties");
//		p.store(fos, "changes made by Harshini");
    }
    
    public static String[][] readDataFromXl(String datapath,String sheetname) throws IOException 
    {
    	//String cur_dir=System.getProperty("user.dir");
    	 FileInputStream  fs= new FileInputStream(new File(datapath));
    	 
    	 HSSFWorkbook wb=new HSSFWorkbook(fs);
    	 
    	 HSSFSheet sheet=wb.getSheet(sheetname);
    	 int trow=sheet.getLastRowNum()+1;
    	 int tcol=sheet.getRow(0).getLastCellNum();
    	 String[][] str=new String[trow][tcol];
    	 for(int i=0;i<trow;i++) 
    	 {
    		 for(int j=0;j<tcol;j++) 
    		  {
    			 int cellType=sheet.getRow(i).getCell(j).getCellType();
    				if(cellType==HSSFCell.CELL_TYPE_NUMERIC){
    					int val=(int) sheet.getRow(i).getCell(j).getNumericCellValue();
    					str[i][j]=String.valueOf(val);
    					//sheet.getRow(i).getCell(j).getNumericCellValue();
    				}
    				
    				else

    		   str[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();
    		  }
    	 }
    	 for(int i=0;i<str.length;i++)
    	  {
    		for(int j=0;j<str[0].length;j++) {
    			System.out.print(str[i][j] + "  ");
    		}
    	System.out.println( );
    	  }
    	return str;
     }
    
    
	
	public static void validateTextContent(WebElement obj,String expectedtext,String objName) 
	{
		if(obj.isDisplayed()) 
		{
//			String actualtext=obj.getAttribute("value");
			String actualtext=obj.getText().toString();
			if(expectedtext.equals(actualtext)) 
			{
				System.out.println("PASS: "+ "expected text is:" +expectedtext+ "is matching with actual text");
				InitialDriverFile.status=true;
			}
			else
			{
				System.out.println("Fail: "+"Expected text & Actul text are not matching");
				System.out.println("Expected error message is:"+ expectedtext);
				System.out.println("Actual error message is:"+actualtext);
				InitialDriverFile.status=false;
			}
		}
		else 
		{
			System.out.println("Fail:"+expectedtext+ "does not exist");
			InitialDriverFile.status=false;
		}
		
	}
	
	public static void validateTitle(WebDriver driver, String expected, String objName) throws InterruptedException 
		{
			
				String actualtext=driver.getTitle();
//				Thread.sleep(3000);
				System.out.println("Browser Title is:"+ actualtext);
				if(expected.equals(actualtext)) 
				{
					System.out.println("PASS: "+objName+" is verified");
				}
				else
				{
					System.out.println("Fail: "+objName+" is not verified");
				}
		 
				
	 }
	
	public static ExtentReports startReport(String reportPath)
	  {
	    htmlReporter = new ExtentHtmlReporter(reportPath);
	    extent = new ExtentReports();
	    extent.attachReporter( htmlReporter);
	    extent.setSystemInfo("Host Name", "tekarch QA");
	    extent.setSystemInfo("Environment", "Automation Testing");
	    extent.setSystemInfo("User Name", "Harshini");
	    
	    htmlReporter.config().setDocumentTitle("report status");
	    htmlReporter.config().setReportName("customized report");
	    /*htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	    htmlReporter.config().setTheme(Theme.STANDARD);*/
	    return extent;
	  }
	  
	  public static ExtentTest createTestReport(String testName, ExtentReports extent)
	  {
	    logger = extent.createTest(testName);
	    return logger;
	  }
	  
	  public static void endReport(ExtentReports extent)
	  {
	    extent.flush();
	  }
	  
	  public static WebDriver switchToWindows(String xPath,String expected) throws Exception
	  {
		  System.out.println("2");
//		  parentWindow=driver.getWindowHandle();
	  
		  System.out.println("xpath is"+xPath);
			driver.findElement(By.xpath(xPath)).click();
			Thread.sleep(2000);
			Set<String>s1=driver.getWindowHandles();
			Iterator<String> I1=s1.iterator();
			
			while(I1.hasNext())
			{
				String childWindow=I1.next();
				if(!parentWindow.equals(childWindow)) {
					driver.switchTo().window(childWindow);
					String text=driver.getTitle();
					System.out.println("Actual title is: "+text);
					String exptext=expected;
					if(text.equals(exptext))
					{
						System.out.println(expected+ " page is verified");

					}
					else
					{
						System.out.println(expected+" page is not verified");
					}
					driver.close();
				}
			}
			
		return driver;
		  
	  }
     } 