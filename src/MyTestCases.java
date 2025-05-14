import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MyTestCases {

	DesiredCapabilities caps = new DesiredCapabilities();

	AndroidDriver driver;

	@BeforeTest
	public void mySetup() {
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "abc");
		File myApplicationCalcultor = new File("src/myApplication/calculator.apk");
		caps.setCapability(MobileCapabilityType.APP, myApplicationCalcultor.getAbsolutePath());

	}

	@BeforeMethod
	public void BeforeEachTest() throws MalformedURLException {
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

	}

	@Test(enabled = false)
	public void myTest() throws IOException {

		driver.findElement(By.id("com.google.android.calculator:id/digit_5")).click();

		driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();

		driver.findElement(By.id("com.google.android.calculator:id/digit_9")).click();

		String results = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();

		Assert.assertEquals(results, "45");

	}

	@Test(enabled = true)
	public void ClickOnlyOnTheNumbers() throws InterruptedException, IOException {

		Date date = new Date();
		String fileName = date.toString().replace(":", "-");

		System.out.println(date);

		List<WebElement> allButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < allButtons.size(); i++) {

			if (allButtons.get(i).getDomAttribute("resource-id").contains("digit")) {
				allButtons.get(i).click();
			}

			TakesScreenshot ts = (TakesScreenshot) driver;

			File fileOfscreenShot = ts.getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(fileOfscreenShot, new File("src/ScreenShot/" + fileName + ".jpg"));

		}

	}

}
