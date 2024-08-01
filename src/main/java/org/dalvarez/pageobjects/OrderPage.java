package org.dalvarez.pageobjects;

import org.dalvarez.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;

    @FindBy(css = "tr td:nth-of-type(2)")
    private List<WebElement> productNames;

    public OrderPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean verifyOrderDisplay(String productName){
        return productNames.stream().anyMatch(product -> product.getText().equals(productName));
    }
}
