package com.DemoWebShop.Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class POM_LoginPage {
	private WebDriver driver;
	private Connection con;

	public POM_LoginPage(WebDriver driver) throws SQLException {
		super();
		this.driver = driver;
		this.con = DriverManager.getConnection(db_url,db_user,db_password);
	}
	
//	query
	String query_emptyPasswordAndEmail = "select * from login where status='fail' AND testingType='emptyPasswordAndEmail'";
	String query_invalidEmailFormat = "select * from login where status='fail' AND testingType='invalidEmailFormat'";
	String query_validEmailFormatAndIncorrctPassword = "select * from login where status='fail' AND testingType='validEmailFormatAndIncorrctPassword'";
	String query_validEmailAndEmptyPassword = "select * from login where status='fail' AND testingType='validEmailAndEmptyPassword'";
	String query_unregisteredEmailAndCorrectPassword = "select * from login where status='fail' AND testingType='unregisteredEmailAndCorrectPassword'";
	String query_registeredEmailAndIncorrectPassword = "select * from login where status='fail' AND testingType='registeredEmailAndIncorrectPassword'";
	String query_registeredEmailAndPassword = "select * from login where status='pass' AND testingType='registeredEmailAndPassword'";
	
//	variable
	private String db_url = System.getenv("DB_URL");
	private String db_password = System.getenv("PASSWORD_DB");
	private String db_user = System.getenv("USER_DB");
	
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
	
//	database connection
	public List<String[]> dbData(String query) throws SQLException {
		List<String[]> data = new ArrayList<>();
		
		ResultSet rs = con.createStatement().executeQuery(query);
		while(rs.next()) {
			String email = rs.getString("email");
			String password = rs.getString("password");
			String result = rs.getString("result");
			
			data.add(new String[] {email,password,result});
		}		
		return data;
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
		con.close();
		driver.quit();
	}
}
