package com.DemoWebShop.PasswordRecovery;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.DatabaseUtils;


public class PasswordRecovery {
	WebDriver driver;
	POM_PasswordRecovery recoveryPom;
	
	@Parameters("passwordrecoveryurl")
	@BeforeTest
	public void setup(String passwordrecoveryurl) throws SQLException, InterruptedException {
		driver = new ChromeDriver();
		driver.get(passwordrecoveryurl);
		driver.manage().window().maximize();
		Thread.sleep(5000);
		recoveryPom = new POM_PasswordRecovery(driver);
		recoveryPom.forgotPasswordclick();
	}
	
//	all field left blank
	@Test(priority = 1)
	public void fieldBlank() throws SQLException, FileNotFoundException, IOException {
//		query parameters
		String queryOf = "passwordRecoveryQuery";
		String status = "fail";
		String testingType = "fieldBlank";
		
//		database data
		List<Map<String, String>> data = DatabaseUtils.getData(status, testingType, queryOf);
		
//		interacting with browser
		for(Map<String, String> i:data) {
//			String email = i.get("email");
			String result = i.get("result");
			
			recoveryPom.recoverButtonClick();
			
//			error check
			recoveryPom.emailErrorAssert(result);
			recoveryPom.clear();
		}
	}
	
//	Unregistered Email
	@Test(priority = 2)
	public void unregisteredEmail() throws SQLException, FileNotFoundException, IOException {
//		query parameters
		String queryOf = "passwordRecoveryQuery";
		String status = "fail";
		String testingType = "unregisteredEmail";
		
//		database data
		List<Map<String, String>> data = DatabaseUtils.getData(status, testingType, queryOf);
		
//		interacting with browser
		for(Map<String, String> i:data) {
			String email = i.get("email");
			String result = i.get("result");
			
			recoveryPom.dataEmail(email);
			recoveryPom.recoverButtonClick();
			
//			error check
			recoveryPom.emailAssert(result);
			recoveryPom.clear();
		}
	}
	
//	Invalid Formated Email
	@Test(priority = 3)
	public void invalidFormatedEmail() throws SQLException, FileNotFoundException, IOException {
//		query parameters
		String queryOf = "passwordRecoveryQuery";
		String status = "fail";
		String testingType = "invalidFormatedEmail";
		
//		database data
		List<Map<String, String>> data = DatabaseUtils.getData(status, testingType, queryOf);
		
//		interacting with browser
		for(Map<String, String> i:data) {
			String email = i.get("email");
			String result = i.get("result");
			
			recoveryPom.dataEmail(email);
			recoveryPom.recoverButtonClick();
			
//			error check
			recoveryPom.emailErrorAssert(result);
			recoveryPom.clear();
		}
	}
	
//	Valid Email
	@Test(priority = 4)
	public void validEmail() throws SQLException, FileNotFoundException, IOException {
//		query parameters
		String queryOf = "passwordRecoveryQuery";
		String status = "pass";
		String testingType = "validEmail";
		
//		database data
		List<Map<String, String>> data = DatabaseUtils.getData(status, testingType, queryOf);
		
//		interacting with browser
		for(Map<String, String> i:data) {
			String email = i.get("email");
			String result = i.get("result");
			
			recoveryPom.dataEmail(email);
			recoveryPom.recoverButtonClick();
			
//			error check
			recoveryPom.emailAssert(result);
			recoveryPom.clear();
		}
	}
	
//	clear
	@AfterTest
	public void close() throws SQLException {
		recoveryPom.end();
	}
}

