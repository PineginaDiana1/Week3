package pages.shipping_info;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

import java.time.Duration;

public class ShippingInfoPage extends BasePage {
    public ShippingInfoPage(WebDriver driver) {
        super(driver);
    }

    private final By cityLocator = By.xpath("//*[@id='shipping_address_full_locality_name']");
    private final By firstCityOptionLocator = By.xpath("//*[@class='tt-dropdown-menu']");
    private final By nameLocator = By.xpath("//*[@id='client_name']");
    private final By phoneNumberLocator = By.xpath("//*[@id='client_phone']");
    private final By submitLocator = By.xpath("//*[@id='create_order']");

    public ShippingInfoPage fillShippingInfo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement city = wait.until(ExpectedConditions.elementToBeClickable(cityLocator));
        city.clear();
        city.sendKeys("г Красное Село, г Санкт-Петербург");
        WebElement firstCityOption = wait.until((ExpectedConditions.elementToBeClickable(firstCityOptionLocator)));
        firstCityOption.click();

        WebElement name = wait.until(ExpectedConditions.elementToBeClickable(nameLocator));
        name.sendKeys("Иванов Иван Иванович");

        WebElement phoneNumber = wait.until(ExpectedConditions.elementToBeClickable(phoneNumberLocator));
        phoneNumber.sendKeys("+7(926)111-11-11");

        return this;
    }

    public ShippingInfoPage confirmOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitLocator));

        wait.until(ExpectedConditions.and(
                ExpectedConditions.elementToBeClickable(submit),
                ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//*[contains(@class,'loading')]")
                )
        ));

        Actions actions = new Actions(driver);
        actions.moveToElement(submit)
                .pause(500)
                .click()
                .perform();

        wait.until(ExpectedConditions.urlContains("payment"));

        return this;
    }


}
