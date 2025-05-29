package pages.cart;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

import java.time.Duration;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    private final By couponLocator = By.xpath("//*[@class='form-control form-control_size-m form-control_wide']");
    private final By submitBtnLocator = By.xpath("//*[@class='icon icon-check']");
    private final By errorTextLocator = By.xpath("//*[@class='insales-ui-discount-error']");
    private final By makeOrderBtnLocator = By.xpath("//*[@class='button button_size-l button_wide']");

    public CartPage applyCoupon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement coupon = wait.until(ExpectedConditions.visibilityOfElementLocated(couponLocator));
        coupon.sendKeys("ПРОМОКОД");

        WebElement submitBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(submitBtnLocator));
        submitBtn.click();

        WebElement text = wait.until(ExpectedConditions.presenceOfElementLocated(errorTextLocator));
        Assertions.assertThat(text.getText())
                .as("Проверка, что указан несуществующий промокод")
                .contains("Указан несуществующий купон");

        return this;
    }

    public CartPage makeOrder () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement makeOrderBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(makeOrderBtnLocator));
        makeOrderBtn.click();
        return this;
    }
}
