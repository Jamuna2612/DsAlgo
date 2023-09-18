package org.example.testsObjects.pageObjects;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RegisterPage {

    WebDriver driver;
    
    public RegisterPage(WebDriver driver) {
	  this.driver = driver;
	}
  
    // ===================== Using POM objects and functions Start =====================
    By userNamePath = By.xpath("//input[@id='id_username']");
    By passwordPath = By.xpath("//input[@id='id_password1']");
    By retypePasswordPath = By.xpath("//input[@id='id_password2']");
    By submitPath = By.xpath("//input[@type='submit']");
    By errorPath = By.cssSelector("[class*='alert']");
    
    WebElement userName ;
    WebElement password ;
    WebElement retypePassword ;
    WebElement submit ;
    WebElement error ;
    
    // user credentials
    String userNameStr = "";
    String passwordStr = "";
    String rePasswordStr = "";

	// This function waits for given element and returns it
	public WebElement waitAndGetElement(By element) {
	    WebElement testElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
	    if (testElement.isDisplayed()) {
	      return testElement; 
	    }
	    else {
	    	  return null;
	    }
    }
	
	// This function enters username, password and repassword on register page
    public void inputCredentials() {
    	// get web elements first
    	userName = waitAndGetElement(userNamePath);
    	password = waitAndGetElement(passwordPath);
    	retypePassword = waitAndGetElement(retypePasswordPath);
    	
    	if (userNameStr == "") {
    		// print username not generated error
    	}
    	if (passwordStr == "") {
    		// print password not generated error
    	}
    	System.out.println("Entering Username: " + userNameStr + " password: " + passwordStr + " rePassword: " + rePasswordStr);      
    	userName.sendKeys(userNameStr);
    	password.sendKeys(passwordStr);
    	retypePassword.sendKeys(rePasswordStr);
	}
	
    // This function clicks Register button on register page
	public IntroPage submitClick() {
    	submit = waitAndGetElement(submitPath);
		
    	submit.click();
    	return new IntroPage(driver);
	}
	
	// This function gets error messages used mostly in Register Error test cases
	public String getErrorMessage() {
		error = waitAndGetElement(errorPath);
		String message = error.getText();
		return message;
	}
	
	// This function waits for element to be clickable and return once element is available
	// This is useful function to avoid Thread.sleep in the code
	public boolean waitForElementDisplayed(String elementStr) {
		By element = null;
		if (elementStr == "username") {
			element = userNamePath;
		}
		// add more element else if conditions based on scenario
		
	  WebElement testElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
	  if (testElement.isDisplayed()) {
	    return true;
	  }    	
		return false;
	}
    // ===================== Using POM objects and functions Ends =====================
	
	// Sets user name with given value
	public void setUserName(String tempUsername) {
		userNameStr = tempUsername;
	}
	
	// Sets password with given value
	public void setPassword(String tempPassword) {
		passwordStr = tempPassword;
	}
	
	// returns username
	public String getUserName() {
		return userNameStr;
	}
	
	// returns password	
	public String getPassword() {
		return passwordStr;
	}	
	
	// Sets repassword variable value
	public void setRetypePassword(String tempPassword) {
		if (tempPassword == "valid") {
			rePasswordStr = passwordStr;
		}else if (tempPassword == "invalid") {
			rePasswordStr = "dummy";	
		}
		else {
			rePasswordStr = tempPassword;
		}
	}
	
	// Function switches to active element and returns validation message for that element
	public String getActiveElementValidationMessage() {
		WebElement activeElement = driver.switchTo().activeElement();
		return  activeElement.getAttribute("validationMessage");
	}

	// This function generates username using length and username type
	public void generateUsername(int length, String type) {
		String alphabets =  "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String allowedSpecialChars = "@/./+/-/_";
		String notAllowedSpecialChars = "#$%^&*";
        String numbers = "0123456789";
	    String combinedCharsRange = "" ; 
	    Random random = new Random();
	    char[] usernameStr = new char[length];
	     
	    if (type.toLowerCase().contains("alphabetsonly")) {
	    	combinedCharsRange = alphabets;
	    }
	    else if (type.toLowerCase().contains("alphanumeric")) {
	    	 combinedCharsRange = alphabets + numbers;
	    }
	    else if (type.toLowerCase().contains("allowedspecialchars")) {
	    	combinedCharsRange = alphabets + allowedSpecialChars;
	    }
	    else if (type.toLowerCase().contains("invalidspecialchars")) {
	    	combinedCharsRange = notAllowedSpecialChars;
	    }
	    else if (type.toLowerCase().contains("151_alphabets")) {
	    	combinedCharsRange = alphabets;
	    }
	    else { // default
	    	combinedCharsRange = alphabets;
	    }
	     
		for ( int i=0; i<length; i++) {
			usernameStr[i] = combinedCharsRange.charAt(random.nextInt(combinedCharsRange.length()));
		}
 
		// convert username string to String so that numbers, special chars remain as is
		userNameStr = new String(usernameStr);
    }
    
	// This function generates password using length and password type
    public void generatePassword(int length, String type) {
        String alphabets = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialChars = "!@#$";
        String numbers = "0123456789";
        String combinedCharsRange = "" ; 
        Random random = new Random();
        char[] password = new char[length];
        
        if (type.toLowerCase().contains("alphanumbericSpecialChars")) {
        	combinedCharsRange = alphabets + numbers + specialChars;        	
        }
        else if (type.toLowerCase().contains("numbers")) {
        	combinedCharsRange = numbers;        	
        }else {
        	combinedCharsRange = alphabets + numbers + specialChars;
        }
        
        // generate password with combined range string        
    	for (int i = 0; i < length ; i++) {
    		password[i] = combinedCharsRange.charAt(random.nextInt(combinedCharsRange.length()));
    	}        
        passwordStr = new String(password);
        // In case of password type as mismatch, put non matching value for rePasswordStr
        if (type.toLowerCase().contains("passwordmismatch")) {
        	rePasswordStr = "dummy";
        }
        else {
            rePasswordStr = passwordStr; 
        }
    }
}