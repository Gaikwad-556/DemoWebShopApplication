package com.DemoWebShop.PasswordRecovery;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class POM_PasswordRecovery {
	
	private WebDriver driver;
	private Connection con;

	public POM_PasswordRecovery(WebDriver driver) throws SQLException {
		super();
		this.driver = driver;
		this.con = DriverManager.getConnection(db_url,db_user,db_password);
		PageFactory.initElements(driver,this);
	}
	
//	query
	String query_fieldBlank = "select * from passwordrecoverypage where status='fail' AND testingType='fieldBlank'";
	String query_unregisteredEmail = "select * from passwordrecoverypage where status='fail' AND testingType='unregisteredEmail'";
	String query_invalidFormatedEmail = "select * from passwordrecoverypage where status='fail' AND testingType='invalidFormatedEmail'";
	String query_validEmail = "select * from passwordrecoverypage where status='pass' AND testingType='validEmail'";

//	variable
	private String db_url = System.getenv("DB_URL");
	private String db_password = System.getenv("PASSWORD_DB");
	private String db_user = System.getenv("USER_DB");
	
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
	
//	database connection
	public List<String[]> dbData(String query) throws SQLException {
		List<String[]> data = new ArrayList<>();

		ResultSet rs = con.createStatement().executeQuery(query);
		while(rs.next()) {
			String email = rs.getString("email");
			String result = rs.getString("result");
			data.add(new String[] {email,result});
		}
		return data;
	}
	
//	clean field
	public void clear() {
		emailField.clear();
	}
	
//	end connection
	public void end() throws SQLException {
		con.close();
		driver.quit();
	}
	
}
