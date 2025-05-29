package pages.payment;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

import java.time.Duration;
import java.util.List;


public class PaymentPage extends BasePage {

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    private final By paymentTimerText = By.xpath("//*[contains(text(), 'Завершите платёж')]");

    public PaymentPage verifyPaymentPageContent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(d -> ((JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));

        try {
            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            if (!iframes.isEmpty()) {
                driver.switchTo().frame(0);
            }
        } catch (Exception e) {
            System.out.println("No iframes found or error switching");
        }

        WebElement timerElement = wait.until(d -> {
            List<WebElement> elements = d.findElements(paymentTimerText);
            return elements.stream()
                    .filter(WebElement::isDisplayed)
                    .findFirst()
                    .orElse(null);
        });

        String actualText = timerElement.getText().replaceAll("\\s+", " ").trim();
        Assertions.assertThat(actualText)
                .as("Проверка, что есть текст 'Завершите платеж в течение'")
                .contains("Завершите платёж в течение");

        driver.switchTo().defaultContent();

        return this;
    }
}
