package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.testng.Assert;
import AutoScripts.InitialDriverFile;

public class Modules {
public static WebDriver driver;

	public static WebDriver launchBrowser(String name)
	  {
	    if (name.equalsIgnoreCase("firefox"))
	    {
	    	System.out.println("Firefox entered");
	    	String driverPath="C:\\Automation\\SeleniumTest\\src\\test\\java\\utility\\geckodriver.exe";
	    	System.setProperty("webdriver.gecko.driver",driverPath);
	    	driver=new FirefoxDriver();
	    }
	    else if (name.equalsIgnoreCase("chrome"))
	    { 
	    	System.out.println("Chrome entered");
	    	String driverPath="C:\\Automation\\SeleniumTest\\src\\test\\java\\utility\\chromedriver1.exe";
	    	System.setProperty("webdriver.chrome.driver", driverPath);
	    	driver=new ChromeDriver();
	    }
	    else if (name.equalsIgnoreCase("ie"))
	    {
	      System.out.println("IE entered");
	      String driverPath="C:\\Automation\\SeleniumTest\\src\\test\\java\\utility\\IEDriverServer.exe";
	      System.setProperty("webdriver.ie.driver", driverPath);
	      
	      driver = new InternetExplorerDriver();
	    }
	    driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
	    return driver;
	  }
	  
	  public static void closeBrowser(WebDriver driver)
	  {
	    try
	    {
	      driver.quit();
	    }
	    catch (Exception localException) {}
	  }
	  
	  public static WebDriver Login_toXero(WebDriver driver) throws Exception
	  {	
		  	driver=launchBrowser(InitialDriverFile.browserSelect);
          	driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		    driver.get(InitialDriverFile.url);
		    String actual=driver.getTitle();

		    String expected=ReusableMethods.getProperty("title_loginpage");
 
             if(expected.equals(actual))
             {
            	 System.out.println("Login page title is verified");
            	 InitialDriverFile.status=true;
             }
             else {
            	 System.out.println("Login page Title not verified");
            	 InitialDriverFile.status=false;
             }
             
		    driver.findElement(By.id(ReusableMethods.getProperty("xpath_email_txt"))).sendKeys(InitialDriverFile.uName);
			driver.findElement(By.id(ReusableMethods.getProperty("xpath_pwd_txt"))).sendKeys(InitialDriverFile.uPwd);
			driver.findElement(By.id(ReusableMethods.getProperty("xpath_login_btn"))).click();
		    
		    return driver;
	    
	  }
	  
	  public static void writeXlSheet(String datapath, String sheetName, String text, int row, int col)
  		    throws IOException
   {
     File f = new File(datapath);
     FileInputStream fio = new FileInputStream(f);
     HSSFWorkbook wb = new HSSFWorkbook(fio);
     HSSFSheet sheet = wb.getSheet(sheetName);
     sheet.getRow(row).getCell(col).setCellValue(text);
     FileOutputStream fop = new FileOutputStream(f);
     wb.write(fop);
     fop.flush();
     fop.close();
   }
  
	  
	  public static void setXlColorStyle(String datapath, String sheetName, int iRow, int iCol, String status)
			    throws IOException
			  {
			    File f = new File(datapath);
			    FileInputStream fio = new FileInputStream(f);
			    HSSFWorkbook wb = new HSSFWorkbook(fio);
			    HSSFSheet sheet = wb.getSheet(sheetName);
			    
			    HSSFRow row = sheet.getRow(iRow);
			    HSSFCell cell = row.getCell(iCol);
			    if (status.equalsIgnoreCase("pass")) {
			      fillBackgroundColor(wb, "green", cell);
			    } else {
			      fillBackgroundColor(wb, "red", cell);
			    }
			    FileOutputStream fop = new FileOutputStream(f);
			    wb.write(fop);
			    fop.flush();
			    fop.close();
			  }
			  
			  public static void fillBackgroundColor(HSSFWorkbook wb, String color, HSSFCell cell)
			  {
			    HSSFCellStyle style = wb.createCellStyle();
			    if (color.equalsIgnoreCase("green")) {
			      style.setFillForegroundColor(new HSSFColor.GREEN().getIndex());
			    } else if (color.equalsIgnoreCase("red")) {
			      style.setFillForegroundColor(new HSSFColor.RED().getIndex());
			    }
			    style.setFillPattern((short)1);
			    cell.setCellStyle(style);
			  }
			  
}
