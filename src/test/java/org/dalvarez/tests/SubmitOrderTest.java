package org.dalvarez.tests;

import com.google.common.primitives.Ints;
import org.apache.commons.io.FileUtils;
import org.dalvarez.pageobjects.*;
import org.dalvarez.testcomponents.BaseTest;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SubmitOrderTest extends BaseTest {


    private final String productName = "ZARA COAT 3";

    static Consumer <WebElement>findProduct = product -> {
        if(Objects.equals(product.findElement(By.tagName("b")).getText(), "ZARA COAT 3"))
            product.findElement(By.cssSelector("button[tabindex]")).click();
    };

    static Predicate <WebElement>filterProducts = product -> (Objects.equals(product.findElement(By.tagName("b")).getText(), "ZARA COAT 3"));



    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {

        ProductCatalog productCatalog =  landingPage.loginApplication(input.get("email"), input.get("password"));

        productCatalog.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalog.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("India");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        Assert.assertTrue(confirmationPage.getConfirmationMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        System.out.println("SISAS");
        //driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest() {
        ProductCatalog productCatalog =  landingPage.loginApplication("dfalvarez99@mailinator.com", "Careplafon11*");
        OrderPage orderPage = productCatalog.goToMyOrdersPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));

    }

    @DataProvider
    public Object[][] getData() throws IOException {

        return getJsonDataToMap("/src/test/java/org/dalvarez/data/PurchaseOrder.json")
                .stream().map(d -> new Object[]{d})
                .toArray(Object[][]::new);
    }

}
