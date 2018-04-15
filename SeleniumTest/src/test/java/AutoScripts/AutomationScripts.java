package AutoScripts;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.http.impl.SocketHttpServerConnection;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import utility.Modules;
import utility.ReusableMethods;

public class AutomationScripts extends ReusableMethods{

	
public static WebDriver driver;



public static void LaunchApp_01() throws Exception
{
//	System.out.println("1");
	driver=Modules.Login_toXero(driver);
	String expected=ReusableMethods.getProperty("title_homepage");
	System.out.println("Expected Home page title is:"+expected);
	Thread.sleep(5000);
	String actual=driver.getTitle();
	
		
	if(expected.equals(actual))
    {
   	 System.out.println("Home page title is verified");
   	 InitialDriverFile.status=true;
    }
    else {
   	 System.out.println("Home page Title not verified");
   	 InitialDriverFile.status=false;
    }
	
//Assert.assertEquals(actual, expected);
driver.close();
}

public static void IncorrectPassword_02() throws Exception
{
//	ReusableMethods.setExcelFile("C:\\Automation\\SeleniumTest\\src\\test\\resources\\data.xls", "Sheet1");
	System.out.println("Browser Selected is:"+InitialDriverFile.browserSelect);
	driver=Modules.launchBrowser(InitialDriverFile.browserSelect);
    driver.get(InitialDriverFile.url);
	driver.findElement(By.id("email")).sendKeys(InitialDriverFile.uName);
	driver.findElement(By.id("password")).sendKeys(InitialDriverFile.uPwd);
	driver.findElement(By.id("submitButton")).click();
	Thread.sleep(6000);
	WebElement error=driver.findElement(By.xpath(ReusableMethods.getProperty("err_incor_pwd")));
	Thread.sleep(3000);
	validateTextContent(error, ReusableMethods.getProperty("expErr_incor_pwd"), "Error Message");
	
	driver.close();
}

public static void IncorrectEmail_03() throws Exception
{
//	ReusableMethods.setExcelFile("C:\\Automation\\SeleniumTest\\src\\test\\resources\\data.xls", "Sheet1");
	driver=Modules.launchBrowser(InitialDriverFile.browserSelect);
    driver.get(InitialDriverFile.url);
	driver.findElement(By.id("email")).sendKeys(InitialDriverFile.uName);
	driver.findElement(By.id("password")).sendKeys(InitialDriverFile.uPwd);
	driver.findElement(By.id("submitButton")).click();
	Thread.sleep(6000);
	WebElement error=driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_err_incor_uname")));
	Thread.sleep(3000);
	validateTextContent(error, ReusableMethods.getProperty("expErr_incor_uname"), "Error Message");
	
	driver.close();
}

public static void NavigateToXero_04() throws Exception
{
//	ReusableMethods.setExcelFile("C:\\Automation\\SeleniumTest\\src\\test\\resources\\data.xls", "Sheet1");
	driver=Modules.launchBrowser(InitialDriverFile.browserSelect);
    driver.get(InitialDriverFile.url);
	Thread.sleep(3000);
	WebElement forgotPassword=driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_fyp_link")));
	forgotPassword.click();
	Thread.sleep(3000);
	String actualtext=driver.getTitle();
	Thread.sleep(3000);
	System.out.println("FYP Browser Title is:"+ actualtext);
//	String expected="Forgotten Password";
	String expected=ReusableMethods.getProperty("title_fyp1");
	System.out.println("Expected FYP browser title is:"+expected);
	if(expected.equals(actualtext)) 
	{
		System.out.println("forgot password page is verified");
	}
	else
	{
		System.out.println("forgot password page is not verified");
	}

	driver.findElement(By.id(ReusableMethods.getProperty("xpath_fyp_email_txt"))).sendKeys(InitialDriverFile.uName);
	driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_fyp_sendlink"))).click();
	WebElement resetpwd =driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_fyp_verifytxt")));
	System.out.println("Please check your email text is:"+resetpwd.getText());
    validateTextContent(resetpwd, ReusableMethods.getProperty("verify_fyp2_txt"), "reset password ");
    driver.close();
}

public static void SignupToXdc_05() throws Exception
{
	driver=Modules.launchBrowser(InitialDriverFile.browserSelect);
    driver.get(InitialDriverFile.url);
	Thread.sleep(3000);
	String actualtext=driver.getTitle();
	System.out.println("Main browser title is:"+actualtext);
	String expected=ReusableMethods.getProperty("title_main_brow"); 
	System.out.println("Exected Main browser title is:"+expected);		
	if(expected.equals(actualtext)) 
	{
		System.out.println("xero app page is verified");
	}
	else
	{
		System.out.println("xero app page is not verified");
	}
	driver.findElement(By.xpath(ReusableMethods.getProperty("lnk_freetail"))).click();
	Thread.sleep(6000);
	String actualtext1=driver.getTitle();
	System.out.println("Free trail window title is:"+actualtext1);
	String expected1=ReusableMethods.getProperty("title_freetrail");
	System.out.println("Expected Free Trail window title is:"+expected1);
	if(expected.contains(actualtext1)) 
	{
		System.out.println("Xero trail version  page is verified");
	}
	else
	{
		System.out.println("xero trail version  page is not verified");
	}
	Thread.sleep(5000);
	driver.findElement(By.name(ReusableMethods.getProperty("xpath_txt_freetrail_fn"))).sendKeys(ReusableMethods.getProperty("Firstname"));
	driver.findElement(By.name(ReusableMethods.getProperty("xpath_txt_freetrail_ln"))).sendKeys(ReusableMethods.getProperty("Lastname"));
	driver.findElement(By.name(ReusableMethods.getProperty("xpath_txt_freetrail_email"))).sendKeys(ReusableMethods.getProperty("Email_address"));
	driver.findElement(By.name(ReusableMethods.getProperty("xpath_txt_freetrail_pn"))).sendKeys(ReusableMethods.getProperty("Phone_number"));
	
	WebElement sel_country=driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_select_country")));
	Select select=new Select(sel_country);
	select.selectByVisibleText(ReusableMethods.getProperty("Country"));
//    driver.manage().window().maximize();
	Thread.sleep(3000);
    
//    int size = driver.findElements(By.tagName("iframe")).size();
//    System.out.println("size is:"+size);
//    
//    for(int i=0; i<=size; i++){
//		driver.switchTo().frame(i);
//		int total=driver.findElements(By.xpath(ReusableMethods.getProperty("xpath_chkbox_norobot"))).size();
//		System.out.println(total);
//		
//	    driver.switchTo().defaultContent();}
//    
////    driver.switchTo().frame("presentation");
//    
//    WebElement xpath=driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_chkbox_norobot")));
    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@role='presentation']")));
    driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_chkbox_norobot"))).click();
	Thread.sleep(6000);
	driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_chkbox_accepttoc"))).click();
	driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_btn_getstarted"))).click();

}

public static void SignUptoXdcB_06() throws Exception{
	driver=Modules.launchBrowser(InitialDriverFile.browserSelect);
    driver.get(InitialDriverFile.url);
	Thread.sleep(3000);
	String actualtext=driver.getTitle();
	System.out.println("Main browser title is:"+actualtext);
	String expected=ReusableMethods.getProperty("title_main_brow"); 
	System.out.println("Exected Main browser title is:"+expected);		
	if(expected.equals(actualtext)) 
	{
		System.out.println("xero app page is verified");
	}
	else
	{
		System.out.println("xero app page is not verified");
	}
	driver.findElement(By.xpath(ReusableMethods.getProperty("lnk_freetail"))).click();
	Thread.sleep(6000);
	String actualtext1=driver.getTitle();
	System.out.println("Free trail window title is:"+actualtext1);
	String expected1=ReusableMethods.getProperty("title_freetrail");
	System.out.println("Expected Free Trail window title is:"+expected1);
	if(expected.contains(actualtext1)) 
	{
		System.out.println("Xero trail version  page is verified");
	}
	else
	{
		System.out.println("xero trail version  page is not verified");
	}
	Thread.sleep(5000);
	
	
	boolean getstarted = driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_btn_getstarted"))).isEnabled();
	System.out.print("getstatded is" +getstarted);
	
	/*JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("document.querySelector(\"button[contains(text(),'Get started')]\").click()");*/
	/*WebDriver getstarted=(WebDriver) driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_btn_getstarted")));
			getstarted.((WebElement) getstarted).click();*/

}
public static void SignUptoXdcC_07() throws Exception
{
	driver=Modules.launchBrowser(InitialDriverFile.browserSelect);
    driver.get(InitialDriverFile.url);
	Thread.sleep(3000);
	String actualtext=driver.getTitle();
	System.out.println("Main browser title is:"+actualtext);
	String expected=ReusableMethods.getProperty("title_main_brow"); 
	System.out.println("Exected Main browser title is:"+expected);		
	if(expected.equals(actualtext)) 
	{
		System.out.println("xero app page is verified");
	}
	else
	{
		System.out.println("xero app page is not verified");
	}
	driver.findElement(By.xpath(ReusableMethods.getProperty("lnk_freetail"))).click();
	Thread.sleep(6000);
	String actualtext1=driver.getTitle();
	System.out.println("Free trail window title is:"+actualtext1);
	String expected1=ReusableMethods.getProperty("title_freetrail");
	System.out.println("Expected Free Trail window title is:"+expected1);
	if(actualtext1.equals(expected1)) 
	{
		System.out.println("Xero trail version  page is verified");
	}
	else
	{
		System.out.println("xero trail version  page is not verified");
	}
	
	String parentWindow=driver.getWindowHandle();
	driver.findElement(By.xpath(ReusableMethods.getProperty("lnk_termsofuse"))).click();
	Thread.sleep(2000);
	Set<String>s1=driver.getWindowHandles();
	Iterator<String> I1=s1.iterator();
	
	while(I1.hasNext())
	{
		String childWindow=I1.next();
		if(!parentWindow.equals(childWindow)) {
			driver.switchTo().window(childWindow);
			String text=driver.getTitle();
			System.out.println("terms of use title"+text);
			String exptext=ReusableMethods.getProperty("title_termsofuse");
			if(text.equals(exptext))
			{
				System.out.println("Xero terms of use   page is verified");

			}
			else
			{
				System.out.println("xero terms of use  page is not verified");
			}
			driver.close();
		}
	}
	
	driver.switchTo().window(parentWindow);
	
	
	driver.findElement(By.xpath(ReusableMethods.getProperty("lnk_privacypolicy"))).click();

	Thread.sleep(2000);
	Set<String>s2=driver.getWindowHandles();
	Iterator<String> I2=s2.iterator();
	
	while(I2.hasNext())
	{
		String childWindow1=I2.next();
		if(!parentWindow.equals(childWindow1)) {
			driver.switchTo().window(childWindow1);
			String text1=driver.getTitle();
			System.out.println("Privacy policy title is: "+text1);
			String exptext1=ReusableMethods.getProperty("title_privacypolicy");
			if(text1.equals(exptext1))
			{
				System.out.println("Xero Privacy Policy page is verified");

			}
			else
			{
				System.out.println("Xero Privacy policy page is not verified");
			}
			driver.close();
		}
	}
	
	driver.switchTo().window(parentWindow);
	driver.close();

}
public static void SignUptoXdc_D_08() throws Exception
{
	driver=Modules.launchBrowser(InitialDriverFile.browserSelect);
    driver.get(InitialDriverFile.url);
	Thread.sleep(3000);
	String actualtext=driver.getTitle();
	System.out.println("Main browser title is:"+actualtext);
	String expected=ReusableMethods.getProperty("title_main_brow"); 
	System.out.println("Exected Main browser title is:"+expected);		
	if(expected.equals(actualtext)) 
	{
		System.out.println("xero app page is verified");
	}
	else
	{
		System.out.println("xero app page is not verified");
	}
	driver.findElement(By.xpath(ReusableMethods.getProperty("lnk_freetail"))).click();
	Thread.sleep(6000);
	String actualtext1=driver.getTitle();
	System.out.println("Free trail window title is:"+actualtext1);
	String expected1=ReusableMethods.getProperty("title_freetrail");
	System.out.println("Expected Free Trail window title is:"+expected1);
	if(expected.equals(actualtext1)) 
	{
		System.out.println("Xero trail version  page is verified");
	}
	else
	{
		System.out.println("xero trail version  page is not verified");
	}
	Thread.sleep(5000);
	
/*	System.out.println("1");
	ReusableMethods.parentWindow=driver.getWindowHandle();
	driver=ReusableMethods.switchToWindows(ReusableMethods.getProperty("lnk_offerdetails"), ReusableMethods.getProperty("title_offerdetails"));
	driver.switchTo().window(parentWindow);
	System.out.println("3");
	driver.close();*/
	
	String parentWindow=driver.getWindowHandle();
	driver.findElement(By.xpath(ReusableMethods.getProperty("lnk_offerdetails"))).click();
	Thread.sleep(2000);
	Set<String>s1=driver.getWindowHandles();
	Iterator<String> I1=s1.iterator();
	
	while(I1.hasNext())
	{
		String childWindow=I1.next();
		if(!parentWindow.equals(childWindow)) {
			driver.switchTo().window(childWindow);
			String text=driver.getTitle();
			System.out.println("Actual Offer Details title is: "+text);
			String exptext=ReusableMethods.getProperty("title_offerdetails");
			if(text.equals(exptext))
			{
				System.out.println("Xero Offer Details page is verified");

			}
			else
			{
				System.out.println("xero Offer Details page is not verified");
			}
			driver.close();
		}
	}
	
	driver.switchTo().window(parentWindow);
	
	
	
}
public static  void SignUptoXdc_E_09() throws Exception
{
driver=Modules.launchBrowser(InitialDriverFile.browserSelect);
driver.get(InitialDriverFile.url);
Thread.sleep(3000);
String actualtext=driver.getTitle();
System.out.println("Main browser title is:"+actualtext);
String expected=ReusableMethods.getProperty("title_main_brow"); 
System.out.println("Exected Main browser title is:"+expected);		
if(expected.equals(actualtext)) 
{
	System.out.println("xero app page is verified");
}
else
{
	System.out.println("xero app page is not verified");
}
driver.findElement(By.xpath(ReusableMethods.getProperty("lnk_freetail"))).click();
Thread.sleep(6000);
String actualtext1=driver.getTitle();
System.out.println("Free trail window title is:"+actualtext1);
String expected1=ReusableMethods.getProperty("title_freetrail");
System.out.println("Expected Free Trail window title is:"+expected1);
if(expected1.equals(actualtext1)) 
{
	System.out.println("Xero trail version  page is verified");
}
else
{
	System.out.println("xero trail version  page is not verified");
}
Thread.sleep(3000);


driver.findElement(By.xpath(ReusableMethods.getProperty("lnk_accountant"))).click();
Thread.sleep(2000);

		String text=driver.getTitle();
		System.out.println("Actual Accountant or Bookkeeper  title is: "+text);
		String exptext=ReusableMethods.getProperty("title_accountant");
		if(text.equals(exptext))
		{
			System.out.println("Xero  Accountant or Bookkeepers page is verified");

		}
		else
		{
			System.out.println("xero  Accountant or Bookkeeper page is not verified");
		}
		driver.close();
	}


public static void TestAllTabsPage_10() throws Exception
{
	driver=Modules.Login_toXero(driver);
	Thread.sleep(5000);
	WebElement temp1=driver.findElement(By.xpath("//div[@class='xn-h-demo-bar']/p"));
	String temp=temp1.getText();
	System.out.println("Home text is:"+temp);
	WebElement trialmsg=driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_trial_msg")));
	validateTextContent(trialmsg, ReusableMethods.getProperty("exptrial_msg"), "Trial Message");
	driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_dashboard"))).click();
	Thread.sleep(5000);
	validateTitle(driver,ReusableMethods.getProperty("exp_dash_txt"), "Dashboard title page");
	driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_accounts"))).click();
	driver.findElement(By.xpath("//a[contains(text(),'Bank Accounts')]")).isDisplayed();
	System.out.println("Accounts menu drop down is displayed");
	
	driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_reports"))).click();
	driver.findElement(By.xpath("//a[contains(text(),'Budget Manager')]")).isDisplayed();
	System.out.println("Reports menu drop down is displayed");
	
	driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_contacts"))).click();
	driver.findElement(By.xpath("//a[contains(text(),'All Contacts')]")).isDisplayed();
	System.out.println("Contacts menu drop down is displayed");
	
	driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_settings"))).click();
	driver.findElement(By.xpath("//a[contains(text(),'General Settings')]")).isDisplayed();
	System.out.println("Settings menu drop down is displayed");
	
	driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_quicklaunchTab"))).click();
	driver.findElement(By.xpath("//a[contains(text(),'Bill')]")).isDisplayed();
	System.out.println("New menu drop down is displayed");
	
	Thread.sleep(3000);
	driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_files"))).click();
	validateTitle(driver,ReusableMethods.getProperty("exp_files_txt"), "Files home page");
	
	driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_notifications"))).click();
	Thread.sleep(3000);
	 
	driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_search"))).click();
	driver.findElement(By.xpath("//input[@id='placeholder']")).isDisplayed();
	System.out.println("search box is diplayed");
	
	driver.findElement(By.xpath(ReusableMethods.getProperty("xpath_help"))).click();
driver.findElement(By.xpath("//input[@id='menu_help']")).isDisplayed();
System.out.println("help option is displayed");

}

}

 
