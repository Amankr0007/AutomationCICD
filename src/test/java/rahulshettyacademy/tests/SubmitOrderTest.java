package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.lang.Object;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	public String productName="ZARA COAT 3";
	
	@Test(dataProvider="getData",groups= {"purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		ProductCatalogue productcatalogue=landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products=productcatalogue.getproductList();
		productcatalogue.addProductToCart(input.get("product"));
		CartPage cartpage=productcatalogue.goTOCartPage();
		Boolean match=cartpage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.goToCheckOut();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage =checkoutpage.submitTheOrder();
		String confirmMessage =confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCatalogue productcatalogue=landingpage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrderPage orderpage=productcatalogue.goToOrderPage();
		 Assert.assertTrue(orderpage.verifyOrderDisplay(productName));
		
	}
	

	
	@DataProvider
	public Object[][] getData() throws IOException 
	{

		
		List<HashMap<String,String>> data = getJsonDataMap(System.getProperty("user.dir")+
				"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
				
	}
	
	
	
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "anshika@gmail.com");
//	map.put("password", "Iamking@000");
//	map.put("product", "ZARA COAT 3");
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "shetty@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("product", "ADIDAS ORIGINAL");	
//	@DataProvider
//	public Object[][] getData(){
//		return new Object[][] {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"},
//			{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};}



}
