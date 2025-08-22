package com.DemoWebShop.Registration;

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
import org.testng.internal.annotations.ITest;

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
	public void allMandatoryField() throws SQLException {
		String query = rPOM.query_allMandatoryField;
		
		List<String[]> data = rPOM.dbdata(query);
		for(String[] i:data) {
			String firstName = i[0];
			String lastName = i[1];
			String email = i[2];
			String password = i[3];
			String confirmPassword = i[4];
			String[] result = i[5].split(",");
			
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
	public void invalidEmailFormat() throws SQLException {
		String query = rPOM.query_invalidEmailFormat;
		
		List<String[]> data = rPOM.dbdata(query);
		for(String[] i:data) {
			String firstName = i[0];
			String lastName = i[1];
			String email = i[2];
			String password = i[3];
			String confirmPassword = i[4];
			String result = i[5];
			
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
	public void lesserPasswordThanSix() throws SQLException {
		String query = rPOM.query_lesserPasswordThanSix;
		
		List<String[]> data = rPOM.dbdata(query);
		for(String[] i:data) {
			String firstName = i[0];
			String lastName = i[1];
			String email = i[2];
			String password = i[3];
			String confirmPassword = i[4];
			String result = i[5];
			
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
	public void misMatchPasswordAndConfirmPassword() throws SQLException {
		String query = rPOM.query_misMatchPasswordAndConfirmPassword;
		
		List<String[]> data = rPOM.dbdata(query);
		for(String[] i:data) {
			String firstName = i[0];
			String lastName = i[1];
			String email = i[2];
			String password = i[3];
			String confirmPassword = i[4];
			String result = i[5];
			
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
	public void enteringRegisteredEmail() throws SQLException {
		String query = rPOM.query_enteringRegisteredEmail;
		
		List<String[]> data = rPOM.dbdata(query);
		for(String[] i:data) {
			String firstName = i[0];
			String lastName = i[1];
			String email = i[2];
			String password = i[3];
			String confirmPassword = i[4];
			String result = i[5];
			
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
	public void invalidFirstName() throws SQLException {
		String query = rPOM.query_invalidFirstName;
		
		List<String[]> data = rPOM.dbdata(query);
		for(String[] i:data) {
			String firstName = i[0];
			String lastName = i[1];
			String email = i[2];
			String password = i[3];
			String confirmPassword = i[4];
			String result = i[5];
			
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
	public void invalidLastName() throws SQLException {
		String query = rPOM.query_invalidLastName;
		
		List<String[]> data = rPOM.dbdata(query);
		for(String[] i:data) {
			String firstName = i[0];
			String lastName = i[1];
			String email = i[2];
			String password = i[3];
			String confirmPassword = i[4];
			String result = i[5];
			
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
	public void invalidPasswordFormat() throws SQLException {
		String query = rPOM.query_invalidPasswordFormat;
		
		List<String[]> data = rPOM.dbdata(query);
		for(String[] i:data) {
			String firstName = i[0];
			String lastName = i[1];
			String email = i[2];
			String password = i[3];
			String confirmPassword = i[4];
			String result = i[5];
			
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
	public void allValidEntry() throws SQLException {
		String query = rPOM.query_allValidEntry;
		
		List<String[]> data = rPOM.dbdata(query);
		for(String[] i:data) {
			String firstName = i[0];
			String lastName = i[1];
			String email = i[2];
			String password = i[3];
			String confirmPassword = i[4];
			String result = i[5];
			
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
