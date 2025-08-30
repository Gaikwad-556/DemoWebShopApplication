package com.DemoWebShop.Registration;

import java.sql.SQLException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.DatabaseUtils;

public class POM_RegistrationPage {
	
	private WebDriver driver;

	public POM_RegistrationPage(WebDriver driver) throws SQLException {
		super();
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	

//	Male gender radio button
	@FindBy(id = "gender-male")
	private WebElement maleGenderRadioButton;
	
//	Female gender radio button
	@FindBy(id = "gender-female")
	private WebElement femaleGenderRadioButton;
	
//	First name field
	@FindBy(id = "FirstName")
	private WebElement firstNameField;
	
//	Last name field
	@FindBy(id = "LastName")
	private WebElement lastNameField;
	
//	Email field
	@FindBy(id = "Email")
	private WebElement emailField;
	
//	Password Field
	@FindBy(id = "Password")
	private WebElement passwordField;
	
//	Confirm password field
	@FindBy(id = "ConfirmPassword")
	private WebElement confirmPasswordField;
	
//	First name error message
	@FindBy(css = "span[data-valmsg-for='FirstName']>span")
	private WebElement firstNameErrorMsg;

//	Last name error message
	@FindBy(css = "span[data-valmsg-for='LastName']>span")
	private WebElement lastNameErrorMsg;
	
//	Email name error message
	@FindBy(css = "span[data-valmsg-for='Email']>span")
	private WebElement emailErrorMsg;
	
//	Password name error message
	@FindBy(css = "span[data-valmsg-for='Password']>span")
	private WebElement passwordErrorMsg;
	
//	Confirm Password name error message
	@FindBy(css = "span[data-valmsg-for='ConfirmPassword']>span")
	private WebElement confirmPasswordErrorMsg;

//	Register button	
	@FindBy(id = "register-button")
	private WebElement registerButton;
	
//	validation check
	@FindBy(css = ".validation-summary-errors li")
	private WebElement emailAlreadyExists;
	
	
//	gender radio button
	public void maleGenderSelect() {
		maleGenderRadioButton.click();
	}
	public void femaleGenderSelect() {
		femaleGenderRadioButton.click();
	}

//	first name field
	public void dataFirstName(String firstName) {
		firstNameField.sendKeys(firstName);
	}
	
//	last name field
	public void dataLastName(String lastName) {
		lastNameField.sendKeys(lastName);
	}
	
//	email field
	public void dataEmail(String email) {
		emailField.sendKeys(email);
	}
	
//	password field
	public void dataPassword(String password) {
		passwordField.sendKeys(password);
	}
	
//	confirm password field
	public void dataConfirmPassword(String confirmPassword) {
		confirmPasswordField.sendKeys(confirmPassword);
	}
	
//	register button
	public void registerClick() {
		registerButton.click();
	}
	
//	assert check
//	validate first name field
	public void firstNameAssert(String firstName) {
		Assert.assertEquals(firstNameErrorMsg.getText(), firstName);
	}
	
//	validate last name field
	public void lastNameAssert(String lastName) {
		Assert.assertEquals( lastNameErrorMsg.getText(), lastName);
	}
	
//	validate email field
	public void emailAssert(String email) {
		Assert.assertEquals(emailErrorMsg.getText(), email);
	}
	
//	validate password field
	public void passwordAssert(String password) {
		Assert.assertEquals(password, passwordErrorMsg.getText(), password);
	}
	
//	validate confirm password
	public void confirmPasswordAssert(String confirmpassword) {
		Assert.assertEquals(confirmPasswordErrorMsg.getText(), confirmpassword);
	}
	
//	validate already exists email
	public void emailAlreadyExistsError(String email) {
		try {
			String text = emailAlreadyExists.getText();
			Assert.assertEquals(text, email);
		} catch (Exception e) {
			String text = emailAlreadyExists.getText();
			Assert.assertEquals(text, email);		
		}
	}
	
//	field clear
	public void clear() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		firstNameField.clear();
		lastNameField.clear();
		emailField.clear();
		passwordField.clear();
		confirmPasswordField.clear();
	}
	
//	clean up tasks
	public void end() throws SQLException {
		DatabaseUtils.close();
		driver.quit();
	}
}
