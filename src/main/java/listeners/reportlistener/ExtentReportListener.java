/**
 * @author rahul.rathore
 *	
 *	24-Jul-2016
 */
package listeners.reportlistener;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.*;
import util.Screenshot;

import java.io.IOException;


public class ExtentReportListener implements ISuiteListener,ITestListener {

	private ExtentReports exReport = null;
	private ExtentTest exTest = null;

	public void onFinish(ISuite suite) {
		try {
			exReport.flush();
		} finally {
			exReport.close();
		}
	}

	public void onStart(ISuite suite) {
	    //String reportPath = System.getProperty("user.dir");
		exReport = new ExtentReports("/reports" + ".html");
		System.out.println("On Start Method executed");
	}

	public void onFinish(ITestContext test) {
		exReport.endTest(exTest);
	}

	public void onStart(ITestContext test) {
		exTest = exReport.startTest(test.getName());
		System.out.println("On Start Method executed");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult test) {
		exTest.log(LogStatus.FAIL, test.getTestClass().getName() + "." + test.getMethod().getMethodName());
		exTest.log(LogStatus.FAIL, test.getThrowable());
		try {
			String src = Screenshot.takeScreenShot(test.getMethod().getMethodName());
			exTest.log(LogStatus.INFO, exTest.addScreenCapture( src == null ? "" : src ));
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult test) {
		exTest.log(LogStatus.SKIP, test.getTestClass().getName() + "." + test.getMethod().getMethodName());
		//exTest.log(LogSta);
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult test) {
		exTest.log(LogStatus.PASS, test.getTestClass().getName() + "." + test.getMethod().getMethodName());
		
	}

}
