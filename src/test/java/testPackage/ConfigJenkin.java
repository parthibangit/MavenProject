package testPackage;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ConfigJenkin {
    
	WebDriver driver;
	@BeforeTest
	public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.get("http://the-internet.herokuapp.com/checkboxes");
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}
	
	@Test
	public void seleniumNewTab() throws Exception {		
        driver.switchTo().newWindow(WindowType.TAB);
        driver.switchTo().newWindow(WindowType.WINDOW);
	}
	
	@Test
	public void seleniumLocators(){
		WebElement element = driver.findElement(RelativeLocator.withTagName("h3").above(By.xpath("//form")));
		System.out.println(element.getText());
		
		WebElement element1 = driver.findElement(RelativeLocator.withTagName("form").below(By.xpath("//h3")));
		System.out.println(element1.getText());
		
		WebElement element2 = driver.findElement(RelativeLocator.withTagName("div").toRightOf(By.xpath("(//a)[2]")));
		System.out.println(element2.getText());
	
	}
	
	@Test
	public void seleniumScreenshot() throws IOException {
		WebElement element = driver.findElement(By.xpath("//h1[text()='Welcome to the-internet']"));
		File srcFile = element.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./Screenshots/element.png");
		FileUtils.copyFile(srcFile, destFile);
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
}
