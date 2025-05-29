package pages.yookassa;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

import java.time.Duration;


public class YookassaPage extends BasePage {
    public YookassaPage(WebDriver driver) {
        super(driver);
    }

    private final By squareCardLocator = By.xpath("//*[@alt='Квадрат']");
    private final By squareButtonLocator = By.xpath("(.//button[contains(@class, 'product-preview__buy-btn')])[1]");
    private final By triangleCardLocator = By.xpath("//*[@alt='Треугольник']");
    private final By triangleButtonLocator = By.xpath("(.//button[contains(@class, 'product-preview__buy-btn')])[5]");
    private final By productCountLocator = By.xpath("//*[@class='header__control-bage']");
    private final By cartLocator = By.xpath("//*[@class='icon icon-cart']");

    public YookassaPage addToCartSquare() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement cardOfSquare = wait.until(ExpectedConditions.visibilityOfElementLocated(squareCardLocator));


        Actions actions = new Actions(driver);
        actions.moveToElement(cardOfSquare).perform();
        WebElement cartButtonSquare = wait.until(ExpectedConditions.elementToBeClickable(squareButtonLocator));
        cartButtonSquare.click();

        return this;
    }

    public YookassaPage addToCartTriangle() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement cardOfTriangle = wait.until(ExpectedConditions.visibilityOfElementLocated(triangleCardLocator));


        Actions actions = new Actions(driver);
        actions.moveToElement(cardOfTriangle).perform();
        WebElement cartButtonTriangle = wait.until(ExpectedConditions.elementToBeClickable(triangleButtonLocator));
        cartButtonTriangle.click();

        return this;
    }

    public YookassaPage checkNumberOfGoodsInCart() {

        WebElement countOfProduct = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(productCountLocator));


        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(d -> countOfProduct.getText().equals("2"));

        Assertions.assertThat(countOfProduct.getText())
                .as("Проверка количества товаров в корзине")
                .isEqualTo("2");

        return this;
    }

    public YookassaPage goToCart () {

        WebElement cart = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(cartLocator));

        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(d -> {
                        try {
                            cart.click();
                            return true;
                        } catch (ElementClickInterceptedException e) {
                            return false;
                        }
                    });
        } catch (TimeoutException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cart);
        }
        return this;
    }

}





