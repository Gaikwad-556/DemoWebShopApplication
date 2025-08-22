package com.DemoWebShop.Registration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class POM_RegistrationPage {
	
	private WebDriver driver;
	private Connection con;

	public POM_RegistrationPage(WebDriver driver) throws SQLException {
		super();
		this.driver = driver;
		this.con = DriverManager.getConnection(db_url,db_user,db_password);
		PageFactory.initElements(driver,this);
	}
	
//	query 
	String query_allMandatoryField = "select * from registrationpage where status='fail' AND testingType='allMandatoryField'";
	String query_invalidEmailFormat = "select * from registrationpage where status='fail' AND testingType='invalidEmailFormat'";
	String query_lesserPasswordThanSix = "select * from registrationpage where status='fail' AND testingType='lesserPasswordThanSix'";
	String query_misMatchPasswordAndConfirmPassword = "select * from registrationpage where status='fail' AND testingType='misMatchPasswordAndConfirmPassword'";
	String query_enteringRegisteredEmail = "select * from registrationpage where status='fail' AND testingType='enteringRegisteredEmail'";
	String query_invalidFirstName = "select * from registrationpage where status='fail' AND testingType='invalidFirstName'";
	String query_invalidLastName = "select * from registrationpage where status='fail' AND testingType='invalidLastName'";
	String query_invalidPasswordFormat = "select * from registrationpage where status='fail' AND testingType='invalidPasswordFormat'";
	String query_allValidEntry = "select * from registrationpage where status='pass' AND testingType='allValidEntry'";

//	variable
	private String db_url = System.getenv("DB_URL");
	private String db_password = System.getenv("PASSWORD_DB");
	private String db_user = System.getenv("USER_DB");
	
	
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
	

//	database connection
	public List<String[]> dbdata(String query) throws SQLException {
		List<String[]> data = new ArrayList<>();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) {
			String firstName = rs.getString("first name");
			String lastName = rs.getString("last name");
			String email = rs.getString("email");
			String password = rs.getString("password");
			String confirmPassword = rs.getString("confirm password");
			String result = rs.getString("result");
			
			data.add(new String[]{firstName,lastName,email,password,confirmPassword,result});
		}	
		return data;
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
		con.close();
		driver.quit();
	}
}
