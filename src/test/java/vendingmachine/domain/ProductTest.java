package vendingmachine.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void 상품_생성_정상() {
        // Product product = Product.putVendingMachine("코카콜라", new Money("1000"), new Quantity("10"));
    }

    @Test
    void 상품_가격_100원_미만_예외() {
        // Assertions.assertThatThrownBy(() -> Product.putVendingMachine("코카콜라", new Money("80"), new Quantity("10")))
        //     .isInstanceOf(IllegalArgumentException.class);
    }

}