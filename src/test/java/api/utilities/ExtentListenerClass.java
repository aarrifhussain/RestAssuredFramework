package api.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListenerClass implements ITestListener{

	ExtentSparkReporter htmlReporter;// user information
	ExtentReports reports; //common information
	ExtentTest test; //entries for test
	
	public void configureReport() {
		String timeStamp= new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
		String reportName="PerStoreAutomationTestReport-"+timeStamp+".html";
		htmlReporter= new ExtentSparkReporter(System.getProperty("user.dir")+"//Reports//"+reportName);
		reports= new ExtentReports();
		reports.attachReporter(htmlReporter);
		
		//add sytem information /environment info t reports
		reports.setSystemInfo("Machine", "testPC1");
		reports.setSystemInfo("OS", "Windows 11");
		reports.setSystemInfo("Machine", "testPC1");
		reports.setSystemInfo("user name", "Prachi");
		
		
		//configure tochange look and feel of report
		htmlReporter.config().setDocumentTitle("Event Listener report demo");
		htmlReporter.config().setReportName("This is my first Report");
		htmlReporter.config().setTheme(Theme.DARK);
		
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Name of test method started:" + result.getName() );  
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
		System.out.println("Name of test method sucessfully executed:" + result.getName() );  		

		test = reports.createTest(result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel("Name of the passed test case is: " + result.getName() ,ExtentColor.GREEN));
	
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Name of the test method failed" +result.getName());
		test=reports.createTest(result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed Test Case is: " + result.getName() ,ExtentColor.RED));
		String screenShotPath = System.getProperty("user.dir") + "\\ScreenShots\\" + result.getName() + ".png";
		
		File screenShotFile = new File(screenShotPath);
		
		if(screenShotFile.exists())
		{
			test.fail("Captured Screenshot is below:" + test.addScreenCaptureFromPath(screenShotPath));
			
		}
		
		//	test.addScreenCaptureFromPath(null)
			
		}		

	

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Name of test method skipped:" + result.getName() );  		

		test = reports.createTest(result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of the skip test case is: " + result.getName() ,ExtentColor.YELLOW));
	
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		configureReport();
		System.out.println("On Start Method Invoked....");
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("On Start Method Invoked....");
		reports.flush();
	}

	
}
