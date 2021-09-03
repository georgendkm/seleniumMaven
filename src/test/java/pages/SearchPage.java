package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage {
	
	public WebDriver driver;
	public SearchPage(WebDriver d){
		driver = d;
	}
	
	@FindBy(xpath="//input[@name='search_query']")
	WebElement searchInput;
	
	@FindBy(xpath="//button[@class='btn btn-default button-search'][@name='submit_search']")
	WebElement searchBtn;
	
	
	@FindBy(xpath="//ul//div[@class='product-container']")
	List<WebElement> resultsList;
	
	/**
	 * This method is used to search a dress
	 * @param searchStr
	 * @return true if successful, else false
	 */
	public boolean searchDress(String searchStr) {
		if(!searchInput.isEnabled()) {
			System.out.println("Search input not enabled");
			return false;
		}
		searchInput.sendKeys(searchStr);
		searchBtn.click();
		System.out.println("Entered search string and clicked on button");
		return true;
	}
	
	/**
	 *  Method to verify search results
	 * @return blank if successful, else message
	 */
	public String verifyResults() {
		int rsize = resultsList.size();
		if(rsize > 0) {
			System.out.println("Results displayed");
		} else {
			System.out.println("Results not displayed");
			return "Results not displayed";
		}
		return "";	
	}
	
	

}
