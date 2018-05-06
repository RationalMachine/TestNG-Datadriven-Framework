package util;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.IOException; 
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Screenshot {

	public static String takeScreenShot(String name) throws IOException {

		WebDriver driver = InitWebDriver.Driver;

        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
        String folderName = formatter.format(today);

		File destDir = new File("'screenshots/" + folderName);
		if(!destDir.exists())
			destDir.mkdir();

		File destPath = new File(destDir.getAbsolutePath() + System.getProperty("file.separator") + name + ".jpg");
		try {
			FileUtils.copyFile(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE)
					, destPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destPath.getAbsolutePath();
	}

	public static String takeScreenShot() {
		WebDriver driver = InitWebDriver.Driver;
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}


}