 package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstarctComponents.AbstractComponent;
			

public class CartPage extends AbstractComponent {
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProduct;
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	public Boolean verifyProductDisplay(String productName)
	{
		Boolean match =cartProduct.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckOut()
	{
		checkOut.click();
		return new CheckoutPage(driver);
		
	}


}
