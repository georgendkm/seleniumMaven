package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SignPage {
	
	public WebDriver driver;
	
	public SignPage(WebDriver driver){
		this.driver = driver;
	}
	
	
	String signinXpath = "//a[normalize-space(text())='Sign in']";
	String emailinputXpath= "//input[@id='email_create']";
	String emailbtnxpath="//button[@name='SubmitCreate']";
	
	String mrradioXpath = "//input[@id='id_gender1']";
	String mrsradioXpath = "//input[@id='id_gender2']";
	
	String custfnameXpath = "//input[@id='customer_firstname']";
	String custlnameXpath = "//input[@id='customer_lastname']";
	String custemailXpath = "//input[@id='email']";
	String custpwdXpath = "//input[@id='passwd']";
	
	String daysXpath = "//select[@id='days']";
	String monthsXpath = "//select[@id='months']";
	String yearXpath = "//select[@id='years']";
	String newsLetterXpath = "//input[@name='newsletter']";
	
	String fnameXpath = "//input[@id='firstname']";
	String lnameXpath = "//input[@id='lastname']";
	
	String add1Xpath = "//input[@name='address1']";
	String cityXpath = "//input[@name='city']";
	String stateXpath = "//select[@name='id_state']";
	String zipXpath = "//input[@name='postcode']";
	String ctryXpath = "//select[@id='id_country']";
	String addinfoXpath = "//textarea[@name='other']";
	String mobileXpath = "//input[@name='phone_mobile']";
	String aliasXpath ="//input[@id='alias']";
	String submitBtnXpath= "//button[@id='submitAccount']";
	
	
	public boolean createUser(String email) throws Exception {
		driver.findElement(By.xpath(signinXpath)).click();
		Thread.sleep(5000);
		System.out.println("Clicked on sign in link");
		driver.findElement(By.xpath(emailinputXpath)).clear();
		driver.findElement(By.xpath(emailinputXpath)).sendKeys(email);
		driver.findElement(By.xpath(emailbtnxpath)).click();
		Thread.sleep(5000);
		System.out.println("Entered mail id and clicked on create user button");
		
		String errorXpath = "//div[@id='create_account_error']";
		
		try {
			if ( driver.findElement(By.xpath(errorXpath)).isDisplayed()) {
				System.out.println("error ..user already exist");
			}
		}catch(Exception e){
			System.out.println("ignoing no such element exception");
		}
		driver.findElement(By.xpath(mrradioXpath)).click();// Select Mr.
		
		driver.findElement(By.xpath(custfnameXpath)).sendKeys("John");
		driver.findElement(By.xpath(custlnameXpath)).sendKeys("Donald");
		
		String actEmail = driver.findElement(By.xpath(custemailXpath)).getAttribute("value");
		if (actEmail.equalsIgnoreCase(email)) {
			System.out.println("email validation successful");
		} else {
			System.out.println("email validation failed");
		}
		driver.findElement(By.xpath(custemailXpath)).click();
		new Actions(driver).sendKeys(Keys.TAB).build().perform();
		Thread.sleep(5000);
		String bg = driver.findElement(By.xpath(custemailXpath)).getCssValue("background");
		String color = driver.findElement(By.xpath(custemailXpath)).getCssValue("color");
		System.out.println("backgound : " + bg);
		System.out.println("color : " + color);
		
		driver.findElement(By.xpath(custpwdXpath)).sendKeys("testpwd");
		/* DOB selection*/
		Select daySelect = new Select(driver.findElement(By.xpath(daysXpath)));
		daySelect.selectByValue("10");
		
		Select monthSelect = new Select(driver.findElement(By.xpath(monthsXpath)));
		monthSelect.selectByIndex(5);
		
		Select yearSelect = new Select(driver.findElement(By.xpath(yearXpath)));
		yearSelect.selectByValue("1990");
		
		driver.findElement(By.xpath(newsLetterXpath)).click();
		
		boolean rFlag = driver.findElement(By.xpath(newsLetterXpath)).isSelected();
		System.out.println("Whether checkbox selected : " + rFlag);
		
		//driver.findElement(By.xpath(fnameXpath)).sendKeys("John");
		//driver.findElement(By.xpath(lnameXpath)).sendKeys("Donald");
		driver.findElement(By.xpath(add1Xpath)).sendKeys("E Missippi Ave, 24-105");
		driver.findElement(By.xpath(cityXpath)).sendKeys("Las Vegas");
		
		Select stateSelect = new Select(driver.findElement(By.xpath(stateXpath)));
		stateSelect.selectByVisibleText("Nevada");
		
		driver.findElement(By.xpath(zipXpath)).sendKeys("68798");
		
		driver.findElement(By.xpath(addinfoXpath)).sendKeys("1 MG Raod \n Line2 \n Line3");
		Thread.sleep(3000);
		driver.findElement(By.xpath(mobileXpath)).sendKeys("6879845345");
		
		
		driver.findElement(By.xpath(submitBtnXpath)).click();
		
		String confXpath  = "//p[contains(text(),'Welcome to your account')]";
		
		boolean userFlag = driver.findElement(By.xpath(confXpath)).isDisplayed();
		if(userFlag) {
			System.out.println("account creation successful");
		} else {
			System.out.println("account creation failed");
		}
		return true;
	}

}
