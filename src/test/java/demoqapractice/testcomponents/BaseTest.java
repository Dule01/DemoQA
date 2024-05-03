package demoqapractice.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import demoqapractice.abstractcomponent.AbstractComponent;
import demoqapractice.pageobjects.TextBox;

public class BaseTest {
	public WebDriver driver;
	public TextBox textBox;

	public WebDriver initializeDriver() throws IOException {

		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//demoqapractice//resources//GlobalData.properties");
		properties.load(fileInputStream);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: properties.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			if (browserName.contains("headless")) {
				chromeOptions.addArguments("headless");
			}
			driver = new ChromeDriver(chromeOptions);
			driver.manage().window().setSize(new Dimension(1440, 900));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// Read JSON to String
		String jsonFileContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// Convert String to HashMap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonFileContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
	
	@BeforeMethod(alwaysRun = true)
	public void launchApplication() throws IOException {
		driver = initializeDriver();
		textBox = new TextBox(driver);
		textBox.goTo();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
}
