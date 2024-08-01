package org.dalvarez.pageobjects;

import org.dalvarez.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement passwordEle;

    @FindBy(id="login")
    WebElement submit;

    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;

    public ProductCatalog loginApplication(String email, String password){
        userEmail.sendKeys(email);
        passwordEle.sendKeys(password);
        submit.click();
        return new ProductCatalog(driver);
    }

    public String getErrorMessage(){
        waitForElementToAppear(errorMessage);
        return errorMessage.getText();
    }

    public void goToLandingPage(){
        driver.get("https://rahulshettyacademy.com/client/");
    }

}

