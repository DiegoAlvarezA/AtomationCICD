package org.dalvarez.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.dalvarez.pageobjects.CartPage;
import org.dalvarez.pageobjects.ProductCatalog;
import org.dalvarez.testcomponents.BaseTest;
import org.dalvarez.testcomponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void failedLogin() {

        landingPage.loginApplication("dfalvarez99@mailinator.com", "Careplafon11**");
        Assert.assertEquals( landingPage.getErrorMessage(), "Incorrect email or password.");

    }

    @Test
    public void productErrorValidation() throws InterruptedException {
        String productName = "ZARA COAT 3";
        ProductCatalog productCatalog =  landingPage.loginApplication("dfalvarez98@mailinator.com", "Careplafon12*");

        productCatalog.addProductToCart(productName);
        CartPage cartPage = productCatalog.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(productName + "3");
        Assert.assertFalse(match);

        //driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

    }
}
