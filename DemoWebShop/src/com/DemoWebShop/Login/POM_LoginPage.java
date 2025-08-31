package com.DemoWebShop.Login;


import java.sql.SQLException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.DatabaseUtils;

public class POM_LoginPage {
	private WebDriver driver;

	public POM_LoginPage(WebDriver driver) throws SQLException {
		super();
		this.driver = driver;
	}
	
//	locators
	private By loginBy = By.xpath("//a[@class='ico-login']");
	private By emailField = By.id("Email");
	private By passField = By.id("Password");
	private By loginButton = By.xpath("//input[@value='Log in']");
	private By errorFirst = By.cssSelector(".validation-summary-errors > span");
	private By errorSecond = By.cssSelector(".validation-summary-errors li");
	private By errorBelow = By.cssSelector(".field-validation-error > span");
	private By logout = By.xpath("//a[@class='ico-logout']");
	
//	login link
	public void loginClick() {
		driver.findElement(loginBy).click();
	}
	
//	email field
	public void emailFieldEntry(String email) {
		driver.findElement(emailField).sendKeys(email);
	}
	
//	password field
	public void passwordFieldEntry(String password) {
		driver.findElement(passField).sendKeys(password);
	}
	
//	login button
	public void loginButton() {
		driver.findElement(loginButton).click();
	}
	
//	validation 
	public String errorBelow() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorBelow));
		return error.getText();
	}
	
//	logout button
	public void logOut() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.findElement(logout).click();
	}

//	assert check
//	validate messages
	public void validateMessage1(String error1, String error2) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			WebElement actual1 = wait.until(ExpectedConditions.visibilityOfElementLocated(errorFirst));
			WebElement actual2 = wait.until(ExpectedConditions.visibilityOfElementLocated(errorSecond));
			Assert.assertEquals(actual1.getText(),error1);
			Assert.assertEquals(actual2.getText(),error2);
		}
		catch (Exception e){
			WebElement updated1 = wait.until(ExpectedConditions.visibilityOfElementLocated(errorFirst));
		    WebElement updated2 = wait.until(ExpectedConditions.visibilityOfElementLocated(errorSecond));
		    Assert.assertEquals(updated1.getText(), error1);
		    Assert.assertEquals(updated2.getText(), error2);
		}
	}
	
//	validate messages
	public void validateMessage2(String error) {
		Assert.assertEquals(errorBelow(), error);
	}
	
//	clear field 
	public void clear() {
		driver.findElement(emailField).clear();
		driver.findElement(passField).clear();
	}
	
//	quite
	public void end() throws SQLException {
		DatabaseUtils.close();
		driver.quit();
	}
}
