package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.SearchPage;
import pages.SignPage;

public class SampleAppTests {
	
	public WebDriver driver;
	public String bname = "Firefox";
	public String url = "http://automationpractice.com/";
	SearchPage searchPage = null;
	SignPage signInPage = null;
	
	@BeforeClass
	public void launchBrowser() throws Exception {
		
		System.setProperty("webdriver.gecko.driver", ".\\browsers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", ".\\browsers\\chromedriver.exe");
			
		if(bname.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.MINUTES);
		searchPage= PageFactory.initElements(driver,SearchPage.class);
		signInPage= PageFactory.initElements(driver,SignPage.class);
	}
	
	@Test(priority=1)
	public void searchDress() {
		boolean rFlag = searchPage.searchDress("Summer Dresses");
		Assert.assertTrue(rFlag, "Failed to peroform search operation");
		String reMsg = searchPage.verifyResults();
		System.out.println("return message : " + reMsg);
		Assert.assertTrue(reMsg.isEmpty(), reMsg);
	}
	@Test(priority=2)
	public void createNewUser() throws Exception {
		boolean rflag = signInPage.createUser("646test123409@test484.com");
		Assert.assertTrue(rflag, "Failed to create the user");
	}
	
	@AfterClass
	public void closeBrowser() throws Exception {
		driver.quit();
	}

}
