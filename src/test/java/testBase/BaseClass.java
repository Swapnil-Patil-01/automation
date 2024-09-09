package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups = { "sanity", "regression", "master", "dataDriven" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {

		// Loading config file
		FileInputStream file = new FileInputStream("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass());

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {

			ChromeOptions options = new ChromeOptions();
			// EdgeOptions for Microsoft Edge browser
			EdgeOptions edgeOptions = new EdgeOptions();
			
			
			options.setCapability("browserName", "chrome");

			// OS platform settings
			if (os.equalsIgnoreCase("windows")) {
				options.setPlatformName(Platform.WIN10.name());
				edgeOptions.setPlatformName(Platform.WIN10.name());
			} else if (os.equalsIgnoreCase("mac")) {
				options.setPlatformName(Platform.MAC.name());
				edgeOptions.setPlatformName(Platform.MAC.name());
			} else {
				System.out.println("No matching OS");
				return;
			}

			// Browser settings
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new RemoteWebDriver(new URL("http://192.168.31.111:4444/wd/hub"), options);
				break;
			case "edge":
				driver = new RemoteWebDriver(new URL("http://192.168.31.111:4444/wd/hub"), edgeOptions);
				break;
			default:
				System.out.println("No matching Browser");
				return;
			}

		} else if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver(new ChromeOptions());
				break;
			case "edge":
				driver = new EdgeDriver(new EdgeOptions());
				break;
			default:
				System.out.println("Invalid browser");
				return;
			}
		}

		// Driver settings
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appurl"));
		driver.manage().window().maximize();
	}

	@AfterClass(groups = { "sanity", "regression", "master", "dataDriven" })
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		return RandomStringUtils.randomAlphabetic(5);
	}

	public String randomNumber() {
		return RandomStringUtils.randomNumeric(10);
	}

	public String randomAlphaNumeric() {
		return RandomStringUtils.randomAlphanumeric(8);
	}

	public String captureScreen(String testName) {
		if (driver instanceof TakesScreenshot) {
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			String destPath = System.getProperty("user.dir") + "/screenshots/" + testName + ".png";
			try {
				FileUtils.copyFile(srcFile, new File(destPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return destPath;
		} else {
			throw new RuntimeException("Driver does not support screenshots");
		}
	}
}
