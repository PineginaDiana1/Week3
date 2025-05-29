package tests.makeOrder;

import static constant.Constant.Urls.YOOKASSA_PAGE;

import org.testng.annotations.Test;
import tests.base.BaseTest;



public class MakeOrderTest extends BaseTest {

    @Test
    public void addToCard() {
        basePage.open(YOOKASSA_PAGE);

        yookassaPage
                .addToCartSquare()
                .addToCartTriangle()
                .checkNumberOfGoodsInCart()
                .goToCart();

        cartPage
                .applyCoupon()
                .makeOrder();

        shippingInfoPage
                .fillShippingInfo()
                .confirmOrder();

        paymentPage
                .verifyPaymentPageContent();
    }

    }

