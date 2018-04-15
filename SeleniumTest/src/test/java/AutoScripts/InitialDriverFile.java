package AutoScripts;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

import utility.Modules;
import utility.ReusableMethods;

public class InitialDriverFile
{
  public static boolean status = true;
  public static ExtentHtmlReporter htmlReporter;
  public static ExtentReports extent;
  public static ExtentTest logger;
  public static String flag;
  public static String firefoxFlag;
  public static String chromeFlag;
  public static String ieFlag;
  public static String runMode;
  public static String tcName;
  public static String url;
  public static String uName;
  public static String uPwd;
  public static String browserSelect;
  
  @Test
  public static void initDriver() throws Exception
     {
    String cur_dir = System.getProperty("user.dir");
//    System.out.println("current Directory"+cur_dir);
    String suitePath = cur_dir + "/src/test/resources/data.xls";
    String[][] recData = ReusableMethods.readDataFromXl(suitePath, "Sheet1");
    
    extent = ReusableMethods.startReport(cur_dir + "/ExtentReport/ExtentReport.html");
    for (int i = 1; i < recData.length; i++)
    {
      tcName = recData[i][0];
      runMode = recData[i][1];
      if (runMode.equalsIgnoreCase("y"))
      {
        firefoxFlag = recData[i][2];
        chromeFlag = recData[i][4];
        ieFlag = recData[i][6];
        url=recData[i][8];
        uName=recData[i][9];
        uPwd=recData[i][10];
        System.out.println("UName is:"+uName);
        System.out.println("Test Case Name is:"+tcName);
        
        if (firefoxFlag.equalsIgnoreCase("y"))
        {
        	browserSelect="firefox";
          logger = ReusableMethods.createTestReport(tcName + " in firefox", extent);
          Method tc = AutomationScripts.class.getMethod(tcName);
        		 tc.invoke(tc);
        		 System.out.println("Status is:"+status);
          if (status)
          {
            Modules.writeXlSheet(suitePath, "Sheet1", "pass", i, 3);
            Modules.setXlColorStyle(suitePath, "Sheet1", i, 3, "pass");
          }
          else
          {
            Modules.writeXlSheet(suitePath, "Sheet1", "fail", i, 3);
            Modules.setXlColorStyle(suitePath, "Sheet1", i, 3, "fail");
          }
        }
        if (chromeFlag.equalsIgnoreCase("y"))
        {
        	browserSelect="Chrome";
          Thread.sleep(5000L);
          logger = ReusableMethods.createTestReport(tcName + " in chrome", extent);
          Method tc = AutomationScripts.class.getMethod(tcName);
		  tc.invoke(tc);
          if (status)
          {
            Modules.writeXlSheet(suitePath, "Sheet1", "pass", i, 5);
            Modules.setXlColorStyle(suitePath, "Sheet1", i, 5, "pass");
          }
          else
          {
            Modules.writeXlSheet(suitePath, "Sheet1", "fail", i, 5);
            Modules.setXlColorStyle(suitePath, "Sheet1", i, 5, "fail");
          }
        }
        if (ieFlag.equalsIgnoreCase("y"))
        {
        	browserSelect="IE";
          logger = ReusableMethods.createTestReport(tcName + " in internet explorer", extent);
          Method tc = AutomationScripts.class.getMethod(tcName);
		  tc.invoke(tc);
          if (status)
          {
            Modules.writeXlSheet(suitePath, "Sheet1", "pass", i, 7);
            Modules.setXlColorStyle(suitePath, "Sheet1", i, 7, "pass");
          }
          else
          {
            Modules.writeXlSheet(suitePath, "Sheet1", "fail", i, 7);
            Modules.setXlColorStyle(suitePath, "Sheet1", i, 7, "fail");
          }
        }
      }
    }
    ReusableMethods.endReport(extent);
  }
}
