package org.dalvarez.pageobjects;

import org.dalvarez.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".action__submit")
    WebElement submit;

    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath = "(//button[contains(@class, 'ta-item')])[2]")
    WebElement selectCountry;

    @FindBy(css = ".ta-results")
    WebElement results;

    public void selectCountry(String countryName){
        Actions actions = new Actions(driver);
        actions.sendKeys(country, countryName).build().perform();
        waitForElementToAppear(results);
        selectCountry.click();

    }

    public ConfirmationPage submitOrder(){
        submit.click();
        return new ConfirmationPage(driver);
    }

}
