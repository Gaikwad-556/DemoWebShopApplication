package com.DemoWebShop.PasswordRecovery;

import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class PasswordRecovery {
	WebDriver driver;
	POM_PasswordRecovery recoveryPom;
	
	@Parameters("passwordrecoveryurl")
	@BeforeTest
	public void setup(String passwordrecoveryurl) throws SQLException {
		driver = new ChromeDriver();
		driver.get(passwordrecoveryurl);
		driver.manage().window().maximize();
		recoveryPom = new POM_PasswordRecovery(driver);
		recoveryPom.forgotPasswordclick();
	}
	
//	all field left blank
	@Test(priority = 1)
	public void fieldBlank() throws SQLException {
		String query = recoveryPom.query_fieldBlank;
		List<String[]> data = recoveryPom.dbData(query);
		for(String[] i:data) {
//			String email = i[0];
			String result = i[1];
			
			recoveryPom.recoverButtonClick();
			
//			error check
			recoveryPom.emailErrorAssert(result);
			recoveryPom.clear();
		}
	}
	
//	Unregistered Email
	@Test(priority = 2)
	public void unregisteredEmail() throws SQLException {
		String query = recoveryPom.query_unregisteredEmail;
		
		List<String[]> data = recoveryPom.dbData(query);
		for(String[] i:data) {
			String email = i[0];
			String result = i[1];
			
			recoveryPom.dataEmail(email);
			recoveryPom.recoverButtonClick();
			
//			error check
			recoveryPom.emailAssert(result);
			recoveryPom.clear();
		}
	}
	
//	Invalid Formated Email
	@Test(priority = 3)
	public void invalidFormatedEmail() throws SQLException {
		String query = recoveryPom.query_invalidFormatedEmail;
		
		List<String[]> data = recoveryPom.dbData(query);
		for(String[] i:data) {
			String email = i[0];
			String result = i[1];
			
			recoveryPom.dataEmail(email);
			recoveryPom.recoverButtonClick();
			
//			error check
			recoveryPom.emailErrorAssert(result);
			recoveryPom.clear();
		}
	}
	
//	Valid Email
	@Test(priority = 4)
	public void validEmail() throws SQLException {
		String query = recoveryPom.query_validEmail;
		
		List<String[]> data = recoveryPom.dbData(query);
		for(String[] i:data) {
			String email = i[0];
			String result = i[1];
			
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

