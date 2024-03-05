package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer =Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {
		String productName="Zara coat 3";
		landingpage.loginApplication("anshika@gmail.com", "Iamki@000");
		Assert.assertEquals("Incorrect email or password.",landingpage.getErrorMessage() );
		
	}
	
	@Test
		public void productErrorValidation() throws InterruptedException {
		String productName="ZARA COAT 3";
		ProductCatalogue productcatalogue=landingpage.loginApplication("rahulshetty@gmail.com","Iamking@000");
		List<WebElement> products=productcatalogue.getproductList();
		productcatalogue.addProductToCart(productName);
		CartPage cartpage=productcatalogue.goTOCartPage();
		Boolean match=cartpage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}



}
