package rahulshettyacademy.AbstarctComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement goToCartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement goToOrderHeader;
	
	public void waitForElementToAppear(By findBy) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		}
	
	public CartPage goTOCartPage()
	{
		goToCartHeader.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	
	public OrderPage goToOrderPage()
	{
		goToOrderHeader.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	}
	
	public void waitforElementToDisappear(WebElement element) throws InterruptedException
	{
		Thread.sleep(1000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(element));
	}
}
