package com.DemoWebShop.Login;

import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginPage {

	WebDriver driver;
	POM_LoginPage pomLoginPage;
	
	@BeforeTest
	@Parameters("loginurl")
	public void setUp(String loginurl) throws SQLException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(loginurl);
		pomLoginPage = new POM_LoginPage(driver);
		pomLoginPage.loginClick();
	}
	
//	Empty Password And Email field
	@Test(priority = 1)
	public void emptyPasswordAndEmail() throws SQLException {
		String query = pomLoginPage.query_emptyPasswordAndEmail;
		
		List<String[]> data = pomLoginPage.dbData(query);
		for(String[] i:data) {
			String email = i[0];
			String password = i[1];
			String[] result = i[2].split(",");
			
			pomLoginPage.emailFieldEntry(email);
			pomLoginPage.passwordFieldEntry(password);
			pomLoginPage.loginButton();
			String errorFirst = result[0];
			String errorSecond = result[1];
			
//			result check assert
			pomLoginPage.validateMessage1(errorFirst, errorSecond);
			pomLoginPage.clear();
		}
	}
	
//	Invalid Email Format
	@Test(priority = 2)
	public void invalidEmailFormat() throws SQLException {
		String query = pomLoginPage.query_invalidEmailFormat;
		
		List<String[]> data = pomLoginPage.dbData(query);
		for(String[] i:data) {
			String email = i[0];
			String password = i[1];
			String result = i[2];
			
			pomLoginPage.emailFieldEntry(email);
			pomLoginPage.passwordFieldEntry(password);
			pomLoginPage.loginButton();
			String error = result;
			
//			result check assert
			pomLoginPage.validateMessage2(error);
			pomLoginPage.clear();
		}
	}
	
//	Valid Email Format And Incorrct Password
	@Test(priority = 3)
	public void validEmailFormatAndIncorrctPassword() throws SQLException {
		String query = pomLoginPage.query_validEmailFormatAndIncorrctPassword;
		
		List<String[]> data = pomLoginPage.dbData(query);
		for(String[] i:data) {
			String email = i[0];
			String password = i[1];
			String[] result = i[2].split(",");
			
			pomLoginPage.emailFieldEntry(email);
			pomLoginPage.passwordFieldEntry(password);
			pomLoginPage.loginButton();
			String errorFirst = result[0];
			String errorSecond = result[1];
			
//			result check assert
			pomLoginPage.validateMessage1(errorFirst, errorSecond);
			pomLoginPage.clear();
		}
	}
	
//	Valid Email And Empty Password
	@Test(priority = 4)
	public void validEmailAndEmptyPassword() throws SQLException {
		String query = pomLoginPage.query_validEmailAndEmptyPassword;
		
		List<String[]> data = pomLoginPage.dbData(query);
		for(String[] i:data) {
			String email = i[0];
			String password = i[1];
			String[] result = i[2].split(",");
			
			pomLoginPage.emailFieldEntry(email);
			pomLoginPage.passwordFieldEntry(password);
			pomLoginPage.loginButton();
			String errorFirst = result[0];
			String errorSecond = result[1];
			
//			result check assert
			pomLoginPage.validateMessage1(errorFirst, errorSecond);
			pomLoginPage.clear();
		}
	}
	
//	Unregistered Email And Correct Password
	@Test(priority = 5)
	public void unregisteredEmailAndCorrectPassword() throws SQLException {
		String query = pomLoginPage.query_unregisteredEmailAndCorrectPassword;
		
		List<String[]> data = pomLoginPage.dbData(query);
		for(String[] i:data) {
			String email = i[0];
			String password = i[1];
			String[] result = i[2].split(",");
			
			pomLoginPage.emailFieldEntry(email);
			pomLoginPage.passwordFieldEntry(password);
			pomLoginPage.loginButton();
			String errorFirst = result[0];
			String errorSecond = result[1];
			
//			result check assert
			pomLoginPage.validateMessage1(errorFirst, errorSecond);
			pomLoginPage.clear();
		}
	}
	
//	Registered Email And Incorrect Password
	@Test(priority = 6)
	public void registeredEmailAndIncorrectPassword() throws SQLException {
		String query = pomLoginPage.query_registeredEmailAndIncorrectPassword;
		
		List<String[]> data = pomLoginPage.dbData(query);
		for(String[] i:data) {
			String email = i[0];
			String password = i[1];
			String[] result = i[2].split(",");
			
			pomLoginPage.emailFieldEntry(email);
			pomLoginPage.passwordFieldEntry(password);
			pomLoginPage.loginButton();
			String errorFirst = result[0];
			String errorSecond = result[1];
			
//			result check assert
			pomLoginPage.validateMessage1(errorFirst, errorSecond);
			pomLoginPage.clear();
		}
	}
	
//	Registered Email And Password
	@Test(priority = 7)
	public void registeredEmailAndPassword() throws SQLException {
		String query = pomLoginPage.query_registeredEmailAndPassword;

		List<String[]> data = pomLoginPage.dbData(query);
		for(String[] i:data) {
			String email = i[0];
			String password = i[1];
			String result = i[2];
			
			pomLoginPage.emailFieldEntry(email);
			pomLoginPage.passwordFieldEntry(password);
			pomLoginPage.loginButton();
		}
	}
	
//	logout 
	@Test(priority = 8, dependsOnMethods = "registeredEmailAndPassword")
	public void logoutButton() {
		pomLoginPage.logOut();
	}
	
//	clear 
	@AfterTest
	public void end() throws SQLException {
		pomLoginPage.end();
	}
}
