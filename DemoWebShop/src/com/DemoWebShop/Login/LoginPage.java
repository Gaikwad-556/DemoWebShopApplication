package com.DemoWebShop.Login;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

import utils.DatabaseUtils;

public class LoginPage {

	WebDriver driver;
	POM_LoginPage pomLoginPage;
	
	@BeforeTest
	@Parameters("loginurl")
	public void setUp(String loginurl) throws SQLException, InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(loginurl);
		Thread.sleep(5000);
		pomLoginPage = new POM_LoginPage(driver);
		pomLoginPage.loginClick();
	}
	
//	Empty Password And Email field
	@Test(priority = 1)
	public void emptyPasswordAndEmail() throws SQLException, FileNotFoundException, IOException {
//		query parameters
		String queryOf = "loginQuery";
		String status = "fail";
		String testingType = "emptyPasswordAndEmail";
		
//		database data
		List<Map<String, String>> data = DatabaseUtils.getData(status, testingType, queryOf);
		
//		interacting with browser
		for(Map<String, String> i:data) {
			String email = i.get("email");
			String password = i.get("password");
			String[] result = i.get("result").split(",");
			
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
	public void invalidEmailFormat() throws SQLException, FileNotFoundException, IOException {
//		query parameters
		String queryOf = "loginQuery";
		String status = "fail";
		String testingType = "invalidEmailFormat";
		
//		database data
		List<Map<String, String>> data = DatabaseUtils.getData(status, testingType, queryOf);
		
//		interacting with browser
		for(Map<String, String> i:data) {
			String email = i.get("email");
			String password = i.get("password");
			String result = i.get("result");
			
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
	public void validEmailFormatAndIncorrctPassword() throws SQLException, FileNotFoundException, IOException {
//		query parameters
		String queryOf = "loginQuery";
		String status = "fail";
		String testingType = "validEmailFormatAndIncorrctPassword";
		
//		database data
		List<Map<String, String>> data = DatabaseUtils.getData(status, testingType, queryOf);
		
//		interacting with browser
		for(Map<String, String> i:data) {
			String email = i.get("email");
			String password = i.get("password");
			String[] result = i.get("result").split(",");
			
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
	public void validEmailAndEmptyPassword() throws SQLException, FileNotFoundException, IOException {
//		query parameters
		String queryOf = "loginQuery";
		String status = "fail";
		String testingType = "validEmailAndEmptyPassword";
		
//		database data
		List<Map<String, String>> data = DatabaseUtils.getData(status, testingType, queryOf);
		
//		interacting with browser
		for(Map<String, String> i:data) {
			String email = i.get("email");
			String password = i.get("password");
			String[] result = i.get("result").split(",");
			
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
	public void unregisteredEmailAndCorrectPassword() throws SQLException, FileNotFoundException, IOException {
//		query parameters
		String queryOf = "loginQuery";
		String status = "fail";
		String testingType = "unregisteredEmailAndCorrectPassword";
		
//		database data
		List<Map<String, String>> data = DatabaseUtils.getData(status, testingType, queryOf);
		
//		interacting with browser
		for(Map<String, String> i:data) {
			String email = i.get("email");
			String password = i.get("password");
			String[] result = i.get("result").split(",");
			
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
	public void registeredEmailAndIncorrectPassword() throws SQLException, FileNotFoundException, IOException {
//		query parameters
		String queryOf = "loginQuery";
		String status = "fail";
		String testingType = "registeredEmailAndIncorrectPassword";
		
//		database data
		List<Map<String, String>> data = DatabaseUtils.getData(status, testingType, queryOf);
		
//		interacting with browser
		for(Map<String, String> i:data) {
			String email = i.get("email");
			String password = i.get("password");
			String[] result = i.get("result").split(",");
			
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
	public void registeredEmailAndPassword() throws SQLException, FileNotFoundException, IOException {
//		query parameters
		String queryOf = "loginQuery";
		String status = "pass";
		String testingType = "registeredEmailAndPassword";
		
//		database data
		List<Map<String, String>> data = DatabaseUtils.getData(status, testingType, queryOf);
		
//		interacting with browser
		for(Map<String, String> i:data) {
			String email = i.get("email");
			String password = i.get("password");
			String[] result = i.get("result").split(",");
			
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
