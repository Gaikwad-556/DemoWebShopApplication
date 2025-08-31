package com.DemoWebShop.PasswordRecovery;

import java.sql.SQLException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.DatabaseUtils;

public class POM_PasswordRecovery {
	
	private WebDriver driver;

	public POM_PasswordRecovery(WebDriver driver) throws SQLException {
		super();
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
//	locators
	@FindBy(linkText = "Forgot password?")
	private WebElement forgotPassword;
	
	@FindBy(id = "Email")
	private WebElement emailField;
	
	@FindBy(css = "span[data-valmsg-for='Email']>span")
	private WebElement emailError;
	
	@FindBy(css = ".password-recovery-button")
	private WebElement recoverButton;
	
	@FindBy(css = ".result")
	private WebElement emailPageError;
	
	
//	forgot password link
	public void forgotPasswordclick() {
		forgotPassword.click();
	}
	
//	email field
	public void dataEmail(String email) {
		emailField.sendKeys(email);
	}
	
//	recovery button
	public void recoverButtonClick() {
		recoverButton.click();
	}
	
//	assert check
//	validate email field
	public void emailErrorAssert(String email) {
		Assert.assertEquals(emailError.getText(), email);
	}
	
	public void emailAssert(String email) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		try {
			WebElement error = wait.until(ExpectedConditions.visibilityOf(emailPageError));
			Assert.assertEquals(error.getText(), email);
		} catch (Exception e) {
			WebElement error = wait.until(ExpectedConditions.visibilityOf(emailPageError));
			Assert.assertEquals(error.getText(), email);	
		}
	}
	
//	clean field
	public void clear() {
		emailField.clear();
	}
	
//	end connection
	public void end() throws SQLException {
		DatabaseUtils.close();
		driver.quit();
	}
	
}
