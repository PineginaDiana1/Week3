package tests.base;

import common.CommonActions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import pages.base.BasePage;
import pages.cart.CartPage;
import pages.payment.PaymentPage;
import pages.shipping_info.ShippingInfoPage;
import pages.yookassa.YookassaPage;

import static common.Config.CLEAR_COOKIES_AND_STORAGE;
import static common.Config.HOLD_BROWSER_OPEN;

public class BaseTest {
    protected WebDriver driver = CommonActions.createDriver();
    protected BasePage basePage = new BasePage(driver);
    protected YookassaPage yookassaPage = new YookassaPage(driver);
    protected CartPage cartPage = new CartPage(driver);
    protected ShippingInfoPage shippingInfoPage = new ShippingInfoPage(driver);
    protected PaymentPage paymentPage = new PaymentPage(driver);


    @AfterTest
    public void clearCookiesAndLocalStorage(){
        if(CLEAR_COOKIES_AND_STORAGE)
        {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            driver.manage().deleteAllCookies();
            javascriptExecutor.executeScript("window.sessionStorage.clear()");
        }
    }

    @AfterSuite(alwaysRun = true)
    public void close() {
        if(HOLD_BROWSER_OPEN) {
            driver.quit();
        }
    }
}
