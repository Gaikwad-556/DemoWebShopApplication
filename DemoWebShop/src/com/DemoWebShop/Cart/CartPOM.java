package com.DemoWebShop.Cart;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.DatabaseUtils;

public class CartPOM {
	private WebDriver driver;
	
	public CartPOM(WebDriver driver) throws SQLException {
		super();
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
//	login
	@FindBy(xpath = "//a[@class='ico-login']")
	private WebElement login;
	
	@FindBy(id = "Email")
	private WebElement email;
	
	@FindBy(id = "Password")
	private WebElement password;
	
	@FindBy(xpath = "//input[@value='Log in']")
	private WebElement loginButton;
	
	private By books = By.xpath("//a[@href='/books']");
	
//	user login
	public void login() throws FileNotFoundException, IOException {
		login.click();
		String queryOf = "loginQuery";
		String status = "pass";
		String testingType = "registeredEmailAndPassword";
		
		List<Map<String, String>> data = DatabaseUtils.getData(status, testingType, queryOf);
		for(Map<String, String> i:data) {
			String emailValue = i.get("email");
			String passwordValue = i.get("password");
			email.sendKeys(emailValue);
			password.sendKeys(passwordValue);
			loginButton.click();
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement book = wait.until(ExpectedConditions.visibilityOfElementLocated(books));
		book.click();	
	}
	
//	product
	@FindBy(xpath = "//h2[@class='product-title']/a")
	private List<WebElement> productNames;
	
	
	@FindBy(xpath = "//div[@class='prices']/span[2]")
	private List<WebElement> actualPrices;
	
	private By confirmationMessage = By.xpath("//p[@class='content']");
	
	private By checkboxesButtons = By.xpath("//td[@class='remove-from-cart']/input") ;

	@FindBy(css = ".ajax-loading-block-window")
	private WebElement loder;
	
	
	@FindBy(css = "input.product-box-add-to-cart-button")
	private List<WebElement> cartButtons;
	
//	product names
	public LinkedList<String> eachProductNames() {
		LinkedList<String> allProductNames = new LinkedList<String>();
		for(int i=0; i<productNames.size(); i++) {
			if(i%2==0) {
				allProductNames.add(productNames.get(i).getText());
			}
		}
		return allProductNames;
	}
	
//	actual price of product
	public LinkedList<String> eachActualPrices() {
		LinkedList<String> allActualPrices = new LinkedList<String>();
		for(int i=0; i<actualPrices.size(); i++) {
			if(i%2==0) {
				allActualPrices.add(actualPrices.get(i).getText());
			}
		}
		return allActualPrices;
	}
	
	
//	Items in cart
	@FindBy(xpath = "//td[@class='product']/a")
	private List<WebElement> cartProducts;
	
	@FindBy(xpath = "//a[@href='/cart' and @class='ico-cart']")
	private WebElement shoppingCartLink;
	
	@FindBy(xpath = "//td[@class='unit-price nobr']/span[2]")
	private List<WebElement> cartProductUnitPrice;
	
	@FindBy(xpath = "//td[@class='qty nobr']/input")
	private List<WebElement> cartProductQuantity;
	
	@FindBy(css =  ".subtotal>.product-subtotal")
	private List<WebElement> cartProductTotal;
	
	@FindBy(xpath = "//a[@class='ico-logout']")
	private WebElement logout;
	
	private By updatCartButton = By.xpath("//input[@name='updatecart']") ;
	
	@FindBy(xpath = "//input[@name='continueshopping']")
	private WebElement continueShoppingButton;
	
	//	other fields
		@FindBy(css = ".discount-coupon-code")
		private WebElement discountCoupon;
		
		@FindBy(css = "input[name='applydiscountcouponcode']")
		private WebElement applyCouponButton;
		
		@FindBy(css = ".gift-card-coupon-code")
		private WebElement giftCardsField;
		
		@FindBy(css = "input[name='applygiftcardcouponcode']")
		private WebElement giftCardButton;
		
		@FindBy(css = ".coupon-box .message")
		private WebElement couponErrorMessage;
		
		@FindBy(css = ".giftcard-box .message")
		private WebElement giftCardErrorMessage;
		
		@FindBy(css = ".product-price")
		private WebElement subTotalField;
		
		@FindBy(id = "terms-of-service-warning-box")
		private WebElement dialogBox;
		
		@FindBy(id = "checkout")
		private WebElement checkoutButton;
		
		@FindBy(id = "termsofservice")
		private WebElement termAndServiceCheckBox;
		
	
//	inside each product
	@FindBy(id = "add-to-cart-button-13")
	private WebElement insideProduct1CartButton;
	
	@FindBy(id = "add-to-cart-button-45")
	private WebElement insideProduct2CartButton;
	
	@FindBy(id = "add-to-cart-button-22")
	private WebElement insideProduct3CartButton;
	
	@FindBy(id = "addtocart_13_EnteredQuantity")
	private WebElement quantityFieldOfProduct1;
	
	@FindBy(id = "addtocart_45_EnteredQuantity")
	private WebElement quantityFieldOfProduct2;
	
	@FindBy(id = "addtocart_22_EnteredQuantity")
	private WebElement quantityFieldOfProduct3;
			

//	shopping cart link
	public void cartLinkClick() {
		Actions action = new Actions(driver);
		action.moveToElement(shoppingCartLink).click().perform();;
	}
	
	
//	remove Products
	public void removeProduct() {
		cartLinkClick();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		List<WebElement> checkboxes = driver.findElements(checkboxesButtons);
		for (int i = 0; i < checkboxes.size(); i++) {
			checkboxes = driver.findElements(checkboxesButtons);
			WebElement btn = wait.until(ExpectedConditions.visibilityOf(checkboxes.get(i)));
		    btn.click();
		}
		updateCart();
		bookClick();
	}
	
//	book categories
	public void bookClick() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement book = wait.until(ExpectedConditions.elementToBeClickable(books));
		book.click();
	}
	
//	update cart button
	public void updateCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement update = wait.until(ExpectedConditions.elementToBeClickable(updatCartButton));
		update.click();
	}
	
//	cart button
	public void clickAddtoCartButton() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		wait.until(ExpectedConditions.visibilityOf(cartButtons.get(0)));
		for (WebElement button : cartButtons) {
			wait.until(ExpectedConditions.invisibilityOf(loder));
			button.click();
		}
	}
	
//	interacting methods
//	interacting with product 1
	public void product1Click() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		productNames.get(0).click();
	}
	
//	interacting with product 2
	public void product2Click() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		productNames.get(2).click();
	}
	
//	interacting with product 3
	public void product3Click() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		productNames.get(4).click();
	}
	
//	changing quantity of product 1
	public void changeQuantityProduct1(String value) {
		quantityFieldOfProduct1.clear();
		quantityFieldOfProduct1.sendKeys(value);
		insideProduct1CartButton.click();
	}
	
//	changing quantity of product 2
	public void changeQuantityProduct2(String value) {
		quantityFieldOfProduct2.clear();
		quantityFieldOfProduct2.sendKeys(value);
		insideProduct2CartButton.click();
	}
	
//	changing quantity of product 3
	public void changeQuantityProduct3(String value) {
		quantityFieldOfProduct3.clear();
		quantityFieldOfProduct3.sendKeys(value);
		insideProduct3CartButton.click();
	}
	
//	navigating back in page
	public void navigateBack() {
		driver.navigate().back();
	}
	
//	quantity change for each product in cart
	public void cartQuantityUpdateForEachProductInCart(String value) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		for(WebElement i:cartProductQuantity) {
			WebElement j = wait.until(ExpectedConditions.visibilityOf(i));
			j.clear();
			j.sendKeys(value);
		}
		updateCart();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
//	discount field
	public void dataDiscountField(String couponCode) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		discountCoupon.sendKeys(couponCode);;
	}
	
//	gift field
	public void dataGiftField(String giftCard) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		giftCardsField.sendKeys(giftCard);
	}
	
//	apply coupon
	public void applyCouponButtonClick() {
		applyCouponButton.click();
	}
	
//	apply gift
	public void giftCardButtonClick() {
		giftCardButton.click();
	}
	
//	click checkout
	public void checkoutButtonClick() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(checkoutButton)).click();
	}
		
//	Asserts
	
//	validate of confirm message 
	public void confirmMessageAssert(String msg) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		WebElement j = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage));
		Assert.assertEquals(j.getText(), msg);
	}
	
//	validate product added to cart
	public void productsAssert(LinkedList<String> products, LinkedList<String> prices) {
		for(int i=0; i<cartProducts.size(); i++) {
			Assert.assertEquals(cartProducts.get(i).getText(), products.get(i));
			Assert.assertEquals(cartProductUnitPrice.get(i).getText(), prices.get(i));
		}
	}
	
//	validate quantity and total price check of product
	public void quantityCheckAndTotalPriceCheckProductAssert(String[] value) {
		JavascriptExecutor js =  (JavascriptExecutor) driver;
		for(int i=0; i<cartProductUnitPrice.size(); i++) {
			String totalPrice = String.format("%.2f",Double.parseDouble(value[i]) * Double.parseDouble(cartProductUnitPrice.get(i).getText()));
			
			String productQuantity = (String) js.executeScript("return arguments[0].value;", cartProductQuantity.get(i));
			Assert.assertEquals(cartProductTotal.get(i).getText(), totalPrice);
			Assert.assertEquals(productQuantity, value[i]);
		}
	}

//	validate in cart change of quantity 
	public void inCartChangeOfQuantityCheckAssertProduct(String value) {
		for(int i=0; i<cartProductUnitPrice.size(); i++) {
			JavascriptExecutor js =  (JavascriptExecutor) driver;
			
			try {
				String totalPrice1 = String.format("%.2f",Double.parseDouble(value) * Double.parseDouble(cartProductUnitPrice.get(i).getText()));
				String productQuantity = (String) js.executeScript("return arguments[0].value;", cartProductQuantity.get(i));
				Assert.assertEquals(cartProductTotal.get(i).getText(), totalPrice1);
				Assert.assertEquals(productQuantity, value);
			} catch (Exception e) {
				String totalPrice1 = String.format("%.2f",Double.parseDouble(value) * Double.parseDouble(cartProductUnitPrice.get(i).getText()));
				String productQuantity = (String) js.executeScript("return arguments[0].value;", cartProductQuantity.get(i));
				Assert.assertEquals(cartProductTotal.get(i).getText(), totalPrice1);
				Assert.assertEquals(productQuantity, value);
			}
			
		}
	}
	
//	validate coupon field meassage
	public void couponMessageCheckAssert(String msg) {
		Assert.assertEquals(couponErrorMessage.getText(), msg);
	}
	
//	validate gift card field message
	public void giftCardMessageCheckAssert(String msg) {
		Assert.assertEquals(giftCardErrorMessage.getText(), msg);
	}
	
//	validate sub total price
	public void subTotalAssert() {
		double total = 0;
		for(int i=0; i<cartProductTotal.size(); i++) {
			total+=Double.parseDouble(cartProductTotal.get(i).getText());
		}
		String subtotal = String.format("%.2f",total);
		Assert.assertEquals(subTotalField.getText(), subtotal);
	}
	
//	validate term of service
	public void termOfServiceAssert(String value) {
		Assert.assertEquals(dialogBox.getText(), value);
	}
	
//	clear
	public void end() throws SQLException {
		logout.click();
		DatabaseUtils.close();
		driver.quit();
	}
}
