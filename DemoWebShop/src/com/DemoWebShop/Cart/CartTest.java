package com.DemoWebShop.Cart;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.internal.annotations.ITest;

import com.beust.jcommander.Parameter;

public class CartTest {
	WebDriver driver;
	CartPOM cartpom;
	
	@BeforeTest
	@Parameters("carturl")
	public void setup(String carturl) throws SQLException {
		driver = new ChromeDriver();
		driver.get(carturl);
		driver.manage().window().maximize();
		cartpom = new CartPOM(driver);
		cartpom.login();
	}
	
//	confirm message after product added to cart
	@Test(priority = 1)
	public void confirmMessage() throws InterruptedException, SQLException {
		String query = cartpom.query_confirmMessage;
		
		List<String[]> data = cartpom.dbData(query);
		for(String[] i:data) {
			String result = i[3];
			
			cartpom.clickAddtoCartButton();
			
			//	validation check
			cartpom.confirmMessageAssert(result);
			
			//	remove product
			cartpom.removeProduct();	
		}
	}
	
//	Product Confirmation In Cart Add From Main Window
	@Test(priority = 2)
	public void productConfirmationInCartAddFromMainWindow() throws InterruptedException {
		cartpom.clickAddtoCartButton();
		
		LinkedList<String> productName = cartpom.eachProductNames();
		LinkedList<String> actualPrice = cartpom.eachActualPrices();

		cartpom.cartLinkClick();
		
		//	validation check
		cartpom.productsAssert(productName, actualPrice);

		//	remove product
		cartpom.removeProduct();	
	}
	
//	Product Confirmation In Cart Add From Each Product Window
	@Test(priority = 3)
	public void productConfirmationInCartAddFromEachProductWindow() throws SQLException {
		String query = cartpom.query_productConfirmationInCartAddFromEachProductWindow;
		
		List<String[]> data = cartpom.dbData(query);
		for(String[] i:data) {
			String[] quantity = i[0].split(",");
			
			String product1Quantity = quantity[0];
			cartpom.product1Click();
			cartpom.changeQuantityProduct1(product1Quantity);
			cartpom.navigateBack();
			
			String product2Quantity = quantity[1];
			cartpom.product2Click();
			cartpom.changeQuantityProduct2(product2Quantity);
			cartpom.navigateBack();
			
			String product3Quantity = quantity[2];
			cartpom.product3Click();
			cartpom.changeQuantityProduct3(product3Quantity);
			cartpom.navigateBack();
			
		
			cartpom.cartLinkClick();
			
			//	assert check
			cartpom.quantityCheckAndTotalPriceCheckProductAssert(quantity);
			
			//	remove product
			cartpom.removeProduct();	
		}
	}
	
//	In Cart Quantity Value Change
	@Test(priority = 4)
	public void inCartQuantityValueChange() throws InterruptedException, SQLException {
		String query = cartpom.query_inCartQuantityValueChange;
		
		List<String[]> data = cartpom.dbData(query);
		for(String[] i:data) {
			String quantity = i[0];
			
			cartpom.clickAddtoCartButton();
			cartpom.cartLinkClick();
			
			String value = quantity;
			cartpom.cartQuantityUpdateForEachProductInCart(value);
			
			//	assert check
			cartpom.inCartChangeOfQuantityCheckAssertProduct(value);
			
			//	remove product
			cartpom.removeProduct();	
		}
	}
	
//	Coupon Field Left Blank
	@Test(priority = 5) 
	public void couponFieldLeftBlank() throws InterruptedException, SQLException {
		String query = cartpom.query_couponFieldLeftBlank;
		
		List<String[]> data = cartpom.dbData(query);
		for(String[] i:data) {
			String couponField = i[1];
			String result = i[3];
			
			cartpom.clickAddtoCartButton();
			cartpom.cartLinkClick();
			
			cartpom.dataDiscountField(couponField);
			cartpom.applyCouponButtonClick();
			
			//	assert check
			String msg = result;
			cartpom.couponMessageCheckAssert(msg);
			
			//	remove product
			cartpom.removeProduct();
		}
	}
	
//	Invalid Coupon Field
	@Test(priority = 6) 
	public void invalidcouponField() throws InterruptedException, SQLException {
		String query = cartpom.query_invalidcouponField;
		
		List<String[]> data = cartpom.dbData(query);
		for(String[] i:data) {
			String couponField = i[1];
			String result = i[3];
			
			cartpom.clickAddtoCartButton();
			cartpom.cartLinkClick();
			
			cartpom.dataDiscountField(couponField);
			cartpom.applyCouponButtonClick();
			
			//	assert check
			String msg = result;
			cartpom.couponMessageCheckAssert(msg);
			
			//	remove product
			cartpom.removeProduct();
		}
	}
	
//	Gift Card Field Blank
	@Test(priority = 7) 
	public void giftCardFieldBlank() throws InterruptedException, SQLException {
		String query = cartpom.query_giftCardFieldBlank;
		
		List<String[]> data = cartpom.dbData(query);
		for(String[] i:data) {
			String giftField = i[2];
			String result = i[3];
			
			cartpom.clickAddtoCartButton();
			cartpom.cartLinkClick();
			
			cartpom.dataGiftField(giftField);
			cartpom.giftCardButtonClick();;
			
			//	assert check
			String msg = result;
			cartpom.giftCardMessageCheckAssert(msg);
			
			//	remove product
			cartpom.removeProduct();
		}
	}
	
//	Invalid Gift Card Field
	@Test(priority = 8) 
	public void invalidGiftCardField() throws InterruptedException, SQLException {
		String query = cartpom.query_invalidGiftCardField;
		
		List<String[]> data = cartpom.dbData(query);
		for(String[] i:data) {
			String giftField = i[2];
			String result = i[3];
			
			
			cartpom.clickAddtoCartButton();
			cartpom.cartLinkClick();
			
			cartpom.dataGiftField(giftField);
			cartpom.giftCardButtonClick();
			
			//	assert check
			String msg = result;
			cartpom.giftCardMessageCheckAssert(msg);
			
			//	remove product
			cartpom.removeProduct();
		}
	}
	
//	Sub-Total Check
	@Test(priority = 9)
	public void subTotalCheck() throws InterruptedException, SQLException {
		String query = cartpom.query_subTotalCheck;
		
		List<String[]> data = cartpom.dbData(query);
		for(String[] i:data) {
			String quantity = i[0];
			
			cartpom.clickAddtoCartButton();
			cartpom.cartLinkClick();
			
			String value = quantity;
			cartpom.cartQuantityUpdateForEachProductInCart(value);
			
			//	assert check
			cartpom.subTotalAssert();
			
			//	remove product
			cartpom.removeProduct();
		}
	}
	
//	Check Term Of Service
	@Test(priority = 10)
	public void checkTermOfService() throws InterruptedException, SQLException {
		String query = cartpom.query_checkTermAndService;
		
		List<String[]> data = cartpom.dbData(query);
		for(String[] i:data) {
			String result = i[3];
			
			cartpom.clickAddtoCartButton();
			cartpom.cartLinkClick();
			
			cartpom.checkoutButtonClick();
			
			//	Assert check
			String value = result;
			cartpom.termOfServiceAssert(value);
			
			//	remove product
			cartpom.removeProduct();
		}
	}
	
// 	clear 
	@AfterTest
	public void clear() throws SQLException {
		cartpom.end();
	}
}
