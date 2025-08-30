package com.DemoWebShop.Registration;

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
import org.testng.internal.annotations.ITest;

import utils.DatabaseUtils;

public class RegistrationPage {
	
	WebDriver driver;
	POM_RegistrationPage rPOM;
	
	@BeforeTest
	@Parameters("registerpageurl")
	public void setup(String registerpageurl) throws SQLException {
		driver = new ChromeDriver();
		driver.get(registerpageurl);
		driver.manage().window().maximize();
		rPOM = new POM_RegistrationPage(driver);
	}
	
//	All Mandatory Field
	@Test(priority = 1) 
	public void allMandatoryField() throws SQLException, FileNotFoundException, IOException {
//		query parameters
		String status = "fail";
		String testingType = "allMandatoryField";
		String queryOf = "registrationQuery";
		
//		database data
		List<Map<String, String>> data = DatabaseUtils.getData(status, testingType, queryOf);;
		
//		interacting with browser
		for(Map<String, String> i:data) {
			String firstName = i.get("firstName");
			String lastName = i.get("lastName");
			String email = i.get("email");
			String password = i.get("password");
			String confirmPassword = i.get("confirmPassword");
			String[] result = i.get("result").split(",");
			
//			entering value to fields
			rPOM.dataFirstName(firstName);
			rPOM.dataLastName(lastName);
			rPOM.dataEmail(email);
			rPOM.dataPassword(password);
			rPOM.dataConfirmPassword(confirmPassword);
			rPOM.registerClick();
			
//			validation Check
			String firstNameExpectedError = result[0];
			String lastNameExpectedError = result[1];
			String emailExpectedError = result[2];
			String passwordExpectedError = result[3];
			String confirmPasswordExpectedError = result[3];
			
//			assert check
			rPOM.firstNameAssert(firstNameExpectedError);
			rPOM.lastNameAssert(lastNameExpectedError);
			rPOM.emailAssert(emailExpectedError);
			rPOM.passwordAssert(passwordExpectedError);
			rPOM.confirmPasswordAssert(confirmPasswordExpectedError);
	
//			field cleaning
			rPOM.clear();
		}
	}
	
//	Invalid Email Format
	@Test(priority = 2)
	public void invalidEmailFormat() throws SQLException, FileNotFoundException, IOException {
//		query parameters
		String status = "fail";
		String testingType = "invalidEmailFormat";
		String queryOf = "registrationQuery";
		
//		database data
		List<Map<String, String>> data = DatabaseUtils.getData(status, testingType, queryOf);;
		
//		interacting with browser
		for(Map<String, String> i:data) {
			String firstName = i.get("firstName");
			String lastName = i.get("lastName");
			String email = i.get("email");
			String password = i.get("password");
			String confirmPassword = i.get("confirmPassword");
			String result = i.get("result");
			
//			entering value to fields
			rPOM.maleGenderSelect();
			rPOM.dataFirstName(firstName);
			rPOM.dataLastName(lastName);
			rPOM.dataEmail(email);
			rPOM.dataPassword(password);
			rPOM.dataConfirmPassword(confirmPassword);
			rPOM.registerClick();
			
//			assert check
			rPOM.emailAssert(result);
			
			rPOM.clear();
		}
	}
	
//	Entering less than six character password
	@Test(priority = 3) 
	public void lesserPasswordThanSix() throws SQLException, FileNotFoundException, IOException {
//		query parameters
		String status = "fail";
		String testingType = "lesserPasswordThanSix";
		String queryOf = "registrationQuery";
		
//		database data
		List<Map<String, String>> data = DatabaseUtils.getData(status, testingType, queryOf);;
		
//		interacting with browser
		for(Map<String, String> i:data) {
			String firstName = i.get("firstName");
			String lastName = i.get("lastName");
			String email = i.get("email");
			String password = i.get("password");
			String confirmPassword = i.get("confirmPassword");
			String result = i.get("result");
			
//			entering value to fields
			rPOM.femaleGenderSelect();
			rPOM.dataFirstName(firstName);
			rPOM.dataLastName(lastName);
			rPOM.dataEmail(email);
			rPOM.dataPassword(password);
			rPOM.dataConfirmPassword(confirmPassword);
			rPOM.registerClick();
			
//			assert check			
			rPOM.passwordAssert(result);	
			
			rPOM.clear();
		}
	}
	
//	Mis-Match Password And Confirm Password
	@Test(priority = 4)
	public void misMatchPasswordAndConfirmPassword() throws SQLException, FileNotFoundException, IOException {
//		query parameters
		String status = "fail";
		String testingType = "misMatchPasswordAndConfirmPassword";
		String queryOf = "registrationQuery";
		
//		database data
		List<Map<String, String>> data = DatabaseUtils.getData(status, testingType, queryOf);;
		
//		interacting with browser
		for(Map<String, String> i:data) {
			String firstName = i.get("firstName");
			String lastName = i.get("lastName");
			String email = i.get("email");
			String password = i.get("password");
			String confirmPassword = i.get("confirmPassword");
			String result = i.get("result");
			
//			entering value to fields
			rPOM.femaleGenderSelect();
			rPOM.dataFirstName(firstName);
			rPOM.dataLastName(lastName);
			rPOM.dataEmail(email);
			rPOM.dataPassword(password);
			rPOM.dataConfirmPassword(confirmPassword);
			rPOM.registerClick();
			
//			assert check
			rPOM.confirmPasswordAssert(result);
			
			rPOM.clear();
		}
	}
	
//	Entering Registered Email
	@Test(priority = 5)
	public void enteringRegisteredEmail() throws SQLException, FileNotFoundException, IOException {
//		query parameters
		String status = "fail";
		String testingType = "enteringRegisteredEmail";
		String queryOf = "registrationQuery";
		
//		database data
		List<Map<String, String>> data = DatabaseUtils.getData(status, testingType, queryOf);;
		
//		interacting with browser
		for(Map<String, String> i:data) {
			String firstName = i.get("firstName");
			String lastName = i.get("lastName");
			String email = i.get("email");
			String password = i.get("password");
			String confirmPassword = i.get("confirmPassword");
			String result = i.get("result");
			
//			entering value to fields
			rPOM.maleGenderSelect();
			rPOM.dataFirstName(firstName);
			rPOM.dataLastName(lastName);
			rPOM.dataEmail(email);
			rPOM.dataPassword(password);
			rPOM.dataConfirmPassword(confirmPassword);
			rPOM.registerClick();
			
//			assert check
			rPOM.emailAlreadyExistsError(result);
			
			rPOM.clear();
		}
	}
	
//	Invalid First Name
	@Test(priority = 6)
	public void invalidFirstName() throws SQLException, FileNotFoundException, IOException {
//		query parameters
		String status = "fail";
		String testingType = "invalidFirstName";
		String queryOf = "registrationQuery";
		
//		database data
		List<Map<String, String>> data = DatabaseUtils.getData(status, testingType, queryOf);;
		
//		interacting with browser
		for(Map<String, String> i:data) {
			String firstName = i.get("firstName");
			String lastName = i.get("lastName");
			String email = i.get("email");
			String password = i.get("password");
			String confirmPassword = i.get("confirmPassword");
			String result = i.get("result");
			
//			entering value to fields
			rPOM.maleGenderSelect();
			rPOM.dataFirstName(firstName);
			rPOM.dataLastName(lastName);
			rPOM.dataEmail(email);
			rPOM.dataPassword(password);
			rPOM.dataConfirmPassword(confirmPassword);
//			rPOM.registerClick();
			
//			assert check
//			rPOM.firstNameAssert(result);
			
//			field clear
			rPOM.clear();
		}
	}
	
//	Invalid Last Name
	@Test(priority = 7)
	public void invalidLastName() throws SQLException, FileNotFoundException, IOException {
//		query parameters
		String status = "fail";
		String testingType = "invalidLastName";
		String queryOf = "registrationQuery";
		
//		database data
		List<Map<String, String>> data = DatabaseUtils.getData(status, testingType, queryOf);;
		
//		interacting with browser
		for(Map<String, String> i:data) {
			String firstName = i.get("firstName");
			String lastName = i.get("lastName");
			String email = i.get("email");
			String password = i.get("password");
			String confirmPassword = i.get("confirmPassword");
			String result = i.get("result");
			
//			entering value to fields
			rPOM.maleGenderSelect();
			rPOM.dataFirstName(firstName);
			rPOM.dataLastName(lastName);
			rPOM.dataEmail(email);
			rPOM.dataPassword(password);
			rPOM.dataConfirmPassword(confirmPassword);
//			rPOM.registerClick();
			
//			Error check
//			rPOM.firstNameAssert(result);
			
//			field clear
			rPOM.clear();
		}
	}
	
//	Invalid Password Format
	@Test(priority = 8)
	public void invalidPasswordFormat() throws SQLException, FileNotFoundException, IOException {
//		query parameters
		String status = "fail";
		String testingType = "invalidPasswordFormat";
		String queryOf = "registrationQuery";
		
//		database data
		List<Map<String, String>> data = DatabaseUtils.getData(status, testingType, queryOf);;
		
//		interacting with browser
		for(Map<String, String> i:data) {
			String firstName = i.get("firstName");
			String lastName = i.get("lastName");
			String email = i.get("email");
			String password = i.get("password");
			String confirmPassword = i.get("confirmPassword");
			String result = i.get("result");
			
//			entering value to fields
			rPOM.maleGenderSelect();
			rPOM.dataFirstName(firstName);
			rPOM.dataLastName(lastName);
			rPOM.dataEmail(email);
			rPOM.dataPassword(password);
			rPOM.dataConfirmPassword(confirmPassword);
//			rPOM.registerClick();
					
//			Error check
//			rPOM.firstNameAssert(result);
			
//			field clear
			rPOM.clear();
		}
	}
	
//	Entering all valid field
	@Test(priority = 9)
	public void allValidEntry() throws SQLException, FileNotFoundException, IOException {
//		query parameters
		String status = "pass";
		String testingType = "allValidEntry";
		String queryOf = "registrationQuery";
		
//		database data
		List<Map<String, String>> data = DatabaseUtils.getData(status, testingType, queryOf);;
		
//		interacting with browser
		for(Map<String, String> i:data) {
			String firstName = i.get("firstName");
			String lastName = i.get("lastName");
			String email = i.get("email");
			String password = i.get("password");
			String confirmPassword = i.get("confirmPassword");
			String result = i.get("result");
			
//			entering value to fields
			rPOM.maleGenderSelect();
			rPOM.dataFirstName(firstName);
			rPOM.dataLastName(lastName);
			rPOM.dataEmail(email);
			rPOM.dataPassword(password);
			rPOM.dataConfirmPassword(confirmPassword);
//			rPOM.registerClick();
			
//			field clear
			rPOM.clear();
		}
	}
	
//	quite
	@AfterTest
	public void close() throws SQLException {
		rPOM.end();
	}
}
