package org.dalvarez.pageobjects;

import org.dalvarez.abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Objects;

public class ProductCatalog extends AbstractComponent {

    WebDriver driver;

    public ProductCatalog(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
    @FindBy(css=".mb-3")
    List<WebElement> products;

    @FindBy(css=".ng-animating")
    WebElement spinner;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector("button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");

    public List<WebElement> getProductList(){
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductsByName(String productName){
        return getProductList().stream().filter(product -> (Objects.equals(product.findElement(By.tagName("b")).getText(), productName)))
                .findFirst().orElse(null);
                //.get().findElement(By.cssSelector("button:last-of-type")).click();
    }

    public void addProductToCart(String productName) throws InterruptedException {
        getProductsByName(productName).findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }
}

